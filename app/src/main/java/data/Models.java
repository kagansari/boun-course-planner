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
    }

    public static class ScheduleItem {
        public String day;
        public int hour;
        public String room;
    }

    public static String dummy = "[\n" +
            "  {\n" +
            "    \"codeSec\": \"ASIA511.01\",\n" +
            "    \"name\": \"INDIAN SOCIETY & CULTURE\",\n" +
            "    \"credit\": 3,\n" +
            "    \"ects\": 8,\n" +
            "    \"instructor\": \"ESENBEL\",\n" +
            "    \"schedule\": []\n" +
            "  },\n" +
            "  {\n" +
            "    \"codeSec\": \"ASIA518.01\",\n" +
            "    \"name\": \"HISTORY OF MODERN JAPAN\",\n" +
            "    \"credit\": 3,\n" +
            "    \"ects\": 8,\n" +
            "    \"instructor\": \"ESENBEL\",\n" +
            "    \"schedule\": []\n" +
            "  },\n" +
            "  {\n" +
            "    \"codeSec\": \"ASIL517.01\",\n" +
            "    \"name\": \"INT.TO CENTRAL ASIAN TURKIC LANGUAGES I\",\n" +
            "    \"credit\": 3,\n" +
            "    \"ects\": 6,\n" +
            "    \"instructor\": \"ÖLMEZ\",\n" +
            "    \"schedule\": []\n" +
            "  },\n" +
            "  {\n" +
            "    \"codeSec\": \"ATA421.01\",\n" +
            "    \"name\": \"ECONOMIC&SOCIAL HISTORY OF MODERN TURKEY\",\n" +
            "    \"credit\": 3,\n" +
            "    \"ects\": 6,\n" +
            "    \"instructor\": \"TOKTAMIŞ\",\n" +
            "    \"schedule\": [\n" +
            "      {\n" +
            "        \"day\": \"T\",\n" +
            "        \"hour\": 3,\n" +
            "        \"room\": \"EF 04\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"day\": \"T\",\n" +
            "        \"hour\": 4,\n" +
            "        \"room\": \"EF 04\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"day\": \"W\",\n" +
            "        \"hour\": 3,\n" +
            "        \"room\": \"N/A\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"day\": \"W\",\n" +
            "        \"hour\": 4,\n" +
            "        \"room\": \"N/A\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"day\": \"Th\",\n" +
            "        \"hour\": 3,\n" +
            "        \"room\": \"EF 04\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"day\": \"Th\",\n" +
            "        \"hour\": 4,\n" +
            "        \"room\": \"EF 04\"\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  {\n" +
            "    \"codeSec\": \"ATA425.01\",\n" +
            "    \"name\": \"CULTURE&SOCIETY IN MODERN TURKEY\",\n" +
            "    \"credit\": 3,\n" +
            "    \"ects\": 6,\n" +
            "    \"instructor\": \"TOKTAMIŞ\",\n" +
            "    \"schedule\": [\n" +
            "      {\n" +
            "        \"day\": \"T\",\n" +
            "        \"hour\": 5,\n" +
            "        \"room\": \"EF 04\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"day\": \"T\",\n" +
            "        \"hour\": 6,\n" +
            "        \"room\": \"EF 04\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"day\": \"W\",\n" +
            "        \"hour\": 5,\n" +
            "        \"room\": \"N/A\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"day\": \"W\",\n" +
            "        \"hour\": 6,\n" +
            "        \"room\": \"N/A\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"day\": \"Th\",\n" +
            "        \"hour\": 5,\n" +
            "        \"room\": \"EF 04\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"day\": \"Th\",\n" +
            "        \"hour\": 6,\n" +
            "        \"room\": \"EF 04\"\n" +
            "      }\n" +
            "   \n" +
            "    ]\n" +
            "  }\n" +
            "]";
}
