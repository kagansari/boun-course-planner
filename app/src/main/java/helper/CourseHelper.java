package helper;

import android.content.Context;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import adapter.CourseListAdapter;
import anandroid.com.bouncourseplanner.R;
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
