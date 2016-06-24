package adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

import anandroid.com.bouncourseplanner.R;
import data.Models;
import helper.CourseHelper;
import view.CourseInfoFragment;

public class CourseListAdapter extends BaseAdapter implements Filterable {

    public LayoutInflater inflater;
    public ArrayList<Models.Course> visibleCourses;
    public Filter searchFilter;
    public AppCompatActivity context;

    public CourseListAdapter(Context context) {
        this.visibleCourses = new ArrayList<>();
        this.context = (AppCompatActivity) context;
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
        final Models.Course course = visibleCourses.get(position);

        View row = inflater.inflate(R.layout.row_course, null);
        TextView courseCodeSecTV = (TextView) row.findViewById(R.id.courseCodeSecTV);
        TextView courseScheduleTV = (TextView) row.findViewById(R.id.courseScheduleTV);
        final Button addBtn = (Button) row.findViewById(R.id.addBtn);
        final Button removeBtn = (Button) row.findViewById(R.id.removeBtn);

        courseCodeSecTV.setText(course.codeSec);
        String schedule = "";
        for (Models.ScheduleItem item: course.schedule) {
            schedule += item.day + item.hour + ", ";
        }
        courseScheduleTV.setText(schedule);

        boolean isInSchedule = CourseHelper.isInSchedule(course);
        if (isInSchedule) {
            removeBtn.setVisibility(View.VISIBLE);
        } else {
            addBtn.setVisibility(View.VISIBLE);
        }

        final String finalSchedule = schedule;
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CourseInfoFragment fragment = new CourseInfoFragment();
                Bundle bundle = new Bundle();
                bundle.putString("codeSec", course.codeSec);
                bundle.putString("name", course.name);
                bundle.putString("hours", finalSchedule);
                bundle.putString("instructor", course.instructor);
                bundle.putInt("credit", course.credit);
                bundle.putInt("ects", course.ects);
                fragment.setArguments(bundle);
                fragment.show(context.getSupportFragmentManager(), "courseInfo");
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CourseHelper.addToSchedule(course);
                addBtn.setVisibility(View.INVISIBLE);
                removeBtn.setVisibility(View.VISIBLE);
            }
        });
        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CourseHelper.removeFromSchedule(course);
                addBtn.setVisibility(View.VISIBLE);
                removeBtn.setVisibility(View.INVISIBLE);
            }
        });
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
            for (int i = 0; i < CourseHelper.courses.size(); i++) {
                Models.Course course = CourseHelper.courses.get(i);
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
