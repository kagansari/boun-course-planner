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

import anandroid.com.bouncourseplanner.MainActivity;
import anandroid.com.bouncourseplanner.R;
import data.Models;
import helper.CourseHelper;
import view.CourseInfoFragment;

public class CourseListAdapter extends BaseAdapter implements Filterable {

    public LayoutInflater inflater;
    public ArrayList<Models.Course> visibleCourses;
    public SearchFilter searchFilter;
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

        final int realPos = CourseHelper.courses.indexOf(course);

        View row = inflater.inflate(R.layout.row_course, null);
        TextView courseCodeSecTV = (TextView) row.findViewById(R.id.courseCodeSecTV);
        TextView courseScheduleTV = (TextView) row.findViewById(R.id.courseScheduleTV);
        final Button addBtn = (Button) row.findViewById(R.id.addBtn);
        final Button removeBtn = (Button) row.findViewById(R.id.removeBtn);

        courseCodeSecTV.setText(course.codeSec);
        courseScheduleTV.setText(course.scheduleStr);

        boolean isInSchedule = CourseHelper.isInSchedule(course);
        if (isInSchedule) {
            removeBtn.setVisibility(View.VISIBLE);
        } else {
            addBtn.setVisibility(View.VISIBLE);
        }

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CourseInfoFragment fragment = new CourseInfoFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("index", realPos);
                fragment.setArguments(bundle);
                fragment.show(context.getSupportFragmentManager(), "courseInfo");
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CourseHelper.addToSchedule(course);
                CourseHelper.courseListAdapter.notifyDataSetChanged();
                ((MainActivity)context).onScheduleChanged();
            }
        });

        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CourseHelper.removeFromSchedule(course);
                CourseHelper.courseListAdapter.notifyDataSetChanged();
                ((MainActivity)context).onScheduleChanged();
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
            if (constraint == null || constraint.length() == 0) {
                filterResults.values = CourseHelper.schedule;
                filterResults.count = CourseHelper.schedule.size();
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

        public void publishResults() {
            notifyDataSetChanged();
        }
    }
}
