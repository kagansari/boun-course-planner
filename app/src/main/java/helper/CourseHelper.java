package helper;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import adapter.ConflictListAdapter;
import adapter.CourseListAdapter;
import data.Models;
import data.Models.Course;

/**
 * Created by tv on 24/06/16.
 */
public class CourseHelper {

    public static ArrayList<Course> courses;
    public static ArrayList<Course> schedule;
    public static CourseListAdapter courseListAdapter;
    public static ConflictListAdapter conflictListAdapter;

    public static final String[] days = {"M", "T", "W", "Th", "F", "S"};
    public static final String[] daysLong = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    public static final int hoursCount = 12;

    public static void init(Context context) {
        schedule = new ArrayList<>();
        Gson gson = new Gson();
        try {
            InputStream is = context.getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            courses = gson.fromJson(new String(buffer, "UTF-8"), new TypeToken<List<Course>>(){}.getType());
            initHoursString();
            courseListAdapter = new CourseListAdapter(context);
            conflictListAdapter = new ConflictListAdapter(context);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getDayIndex(String day) {
        switch (day) {
            case "M":
                return 0;
            case "T":
                return 1;
            case "W":
                return 2;
            case "Th":
                return 3;
            case "F":
                return 4;
            case "S":
                return 5;
            default:
                return -1;
        }
    }

    private static void initHoursString() {
        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            String scheduleStr = "";
            for (int j = 0; j < course.schedule.size(); j++) {
                Models.ScheduleItem schedule = course.schedule.get(j);
                scheduleStr += schedule.day + schedule.hour + ", ";
            }
            course.scheduleStr = scheduleStr;
        }
    }

    public static ArrayList<Models.TableItem> getScheduleTable() {
        ArrayList<Models.TableItem> items = new ArrayList<>();
        for (int i = 0; i < CourseHelper.schedule.size(); i++) {
            Course course = schedule.get(i);
            for (int j = 0; j < course.schedule.size(); j++) {
                Models.ScheduleItem item = course.schedule.get(j);
                Models.TableItem tableItem = new Models.TableItem();
                tableItem.codeSec = course.codeSec;
                tableItem.row = item.hour - 1;
                tableItem.col = getDayIndex(item.day);
                items.add(tableItem);
            }
        }
        return items;
    }

    public static ArrayList<Models.Conflict> getConflicts() {
        ArrayList<Models.Conflict> conflicts = new ArrayList<>();
        Models.Conflict[][] conflictMatrix = new Models.Conflict[days.length][hoursCount];
        for (int i = 0; i < schedule.size(); i++) {
            Course course = schedule.get(i);
            for (int j = 0; j < course.schedule.size(); j++) {
                Models.ScheduleItem item = course.schedule.get(j);
                int day = getDayIndex(item.day);
                Models.Conflict conflict = conflictMatrix[day][item.hour];
                if (conflict == null) {
                    conflict = new Models.Conflict();
                    conflictMatrix[day][item.hour] = conflict;
                    conflict.day = item.day;
                    conflict.hour = item.hour;
                }
                conflict.codeSecs.add(course.codeSec);
            }

        }

        for (int i = 0; i < days.length; i++) {
            for (int j = 0; j < hoursCount; j++) {
                if (conflictMatrix[i][j] != null) {
                    if (conflictMatrix[i][j].codeSecs.size() > 1) {
                        conflicts.add(conflictMatrix[i][j]);
                    }
                }
            }
        }

        return conflicts;
    }

    public static boolean isInSchedule(Course course) {
        return schedule.contains(course);
    }

    public static void addToSchedule (Course course) {
        if (!schedule.contains(course)) {
            schedule.add(course);
        }
    }

    public static void removeFromSchedule (Course course) {
        if (schedule.contains(course)) {
            schedule.remove(course);
        }
    }
}
