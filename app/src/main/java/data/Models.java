package data;

import java.util.ArrayList;

/**
 * Created by tv on 13/06/16.
 */
public class Models {
    public static class Course {
        public String codeSec;
        public String name;
        public int credit;
        public int ects;
        public String instructor;
        public ArrayList<ScheduleItem> schedule;
        public String scheduleStr;
    }

    public static class ScheduleItem {
        public String day;
        public int hour;
        public String room;
    }
}
