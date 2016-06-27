package anandroid.com.bouncourseplanner;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import data.Models;
import helper.CourseHelper;
import interfaces.OnScheduleChangedListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends Fragment {


    GridLayout scheduleGL;
    ArrayList<View> courseViews;

    private OnScheduleChangedListener onScheduleChangedListener;

    public ScheduleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        scheduleGL = (GridLayout) getView().findViewById(R.id.scheduleGL);
        courseViews = new ArrayList<>();
        initTVs();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false);
    }

    public void updateSchedule() {
        scheduleGL.removeAllViews();
        initTVs();
        ArrayList<Models.TableItem> items = CourseHelper.getScheduleTable();
        for (int i = 0; i < items.size(); i++) {
            Models.TableItem item = items.get(i);
            TextView tv = new TextView(getActivity());
            tv.setText(item.codeSec);
            GridLayout.Spec rowSpan = GridLayout.spec(item.row, 1, GridLayout.CENTER, 1);
            GridLayout.Spec colSpan = GridLayout.spec(item.col, 1, GridLayout.CENTER, 1);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpan, colSpan);
            scheduleGL.addView(tv, params);
        }
    }

    private void initTVs() {
        String[] weeks = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (int i = 0; i < weeks.length; i++) {
            String week = weeks[i];
            TextView tv = new TextView(getActivity());
            tv.setText(week);
            GridLayout.Spec rowSpan = GridLayout.spec(0, 1, GridLayout.CENTER, 1);
            GridLayout.Spec colSpan = GridLayout.spec(i+1, 1, GridLayout.CENTER, 1);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpan, colSpan);
            scheduleGL.addView(tv, params);
        }
        for (int i = 0; i < 12; i++) {
            TextView tv = new TextView(getActivity());
            tv.setText(String.valueOf(i + 9));
            GridLayout.Spec rowSpan = GridLayout.spec(i+1, 1, GridLayout.CENTER, 1);
            GridLayout.Spec colSpan = GridLayout.spec(0, 1, GridLayout.RIGHT);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpan, colSpan);
            scheduleGL.addView(tv, params);
        }
    }
}
