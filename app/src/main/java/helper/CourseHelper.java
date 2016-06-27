package helper;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import adapter.CourseListAdapter;
import data.Models;
import data.Models.Course;

/**
 * Created by tv on 24/06/16.
 */
public class CourseHelper {

    public static ArrayList<Course> courses;
    public static ArrayList<Course> schedule;
    public static CourseListAdapter adapter;

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
            adapter = new CourseListAdapter(context);
        } catch (IOException e) {
            e.printStackTrace();
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
                tableItem.row = item.hour;
                int col = -1;
                switch (item.day) {
                    case "M":
                        col = 1;
                        break;
                    case "T":
                        col = 2;
                        break;
                    case "W":
                        col = 3;
                        break;
                    case "Th":
                        col = 4;
                        break;
                    case "F":
                        col = 5;
                        break;
                    case "S":
                        col = 6;
                        break;
                }
                tableItem.col = col;
                items.add(tableItem);
            }
        }
        return items;
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
