package adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

import anandroid.com.bouncourseplanner.R;
import data.Models;

public class CourseListAdapter extends BaseAdapter implements Filterable {

    public LayoutInflater inflater;
    public ArrayList<Models.Course> courses;
    public ArrayList<Models.Course> visibleCourses;
    public Filter searchFilter;

    public CourseListAdapter(Context context, ArrayList<Models.Course> courses) {
        this.courses = courses;
        this.visibleCourses = new ArrayList<>();
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        searchFilter = new SearchFilter();
    }

    @Override
    public int getCount() {
        return visibleCourses.size();
    }

    @Override
    public Object getItem(int position) {
        return visibleCourses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = inflater.inflate(R.layout.row_course, null);
        TextView courseCodeSecTV = (TextView) row.findViewById(R.id.courseCodeSecTV);
        TextView courseScheduleTV = (TextView) row.findViewById(R.id.courseScheduleTV);
        Models.Course course = visibleCourses.get(position);
        courseCodeSecTV.setText(course.codeSec);
        String schedule = "";
        for (Models.ScheduleItem item: course.schedule) {
            schedule += item.day + item.hour + ", ";
        }
        courseScheduleTV.setText(schedule);
        return row;
    }

    @Override
    public Filter getFilter() {
        return searchFilter;
    }

    public class SearchFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            filterResults.values = new ArrayList<>();
            filterResults.count = 0;
            if (constraint == null || constraint.length() == 0) {
                return filterResults;
            }

            ArrayList<Models.Course> values = new ArrayList<>();
            for (int i = 0; i < courses.size(); i++) {
                Models.Course course = courses.get(i);
                if (course.codeSec.toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                    values.add(course);
                }

            }
            filterResults.values = values;
            filterResults.count = values.size();

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            visibleCourses = (ArrayList<Models.Course>) results.values;
            notifyDataSetChanged();
        }
    }
}
