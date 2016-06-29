package anandroid.com.bouncourseplanner;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
    CardView[][] courseViews = new CardView[12][6];

    private OnScheduleChangedListener onScheduleChangedListener;

    public ScheduleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        scheduleGL = (GridLayout) getView().findViewById(R.id.scheduleGL);
        initTVs();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false);
    }

    public void updateSchedule() {
        courseViews = new CardView[12][6];
        scheduleGL.removeAllViews();
        initTVs();
        ArrayList<Models.TableItem> items = CourseHelper.getScheduleTable();
        for (int i = 0; i < items.size(); i++) {
            Models.TableItem item = items.get(i);
            CardView cellCV = courseViews[item.row][item.col];
            if (cellCV == null) {
                LayoutInflater inflater = getActivity().getLayoutInflater();
                cellCV = (CardView) inflater.inflate(R.layout.cell_course_schedule, scheduleGL, false);
                courseViews[item.row][item.col] = cellCV;

                GridLayout.Spec rowSpan = GridLayout.spec(item.row+1);
                GridLayout.Spec colSpan = GridLayout.spec(item.col+1);
                GridLayout.LayoutParams cellParams = new GridLayout.LayoutParams(rowSpan, colSpan);
                cellCV.setLayoutParams(cellParams);
                scheduleGL.addView(cellCV);
            }
            LinearLayout cellLL = (LinearLayout) cellCV.findViewById(R.id.courseCellLL);
            TextView tv = new TextView(getActivity());
            tv.setText(item.codeSec);
            cellLL.addView(tv);
        }
    }

    private void initTVs() {
        String[] weeks = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (int i = 0; i < weeks.length; i++) {
            String week = weeks[i];
            LinearLayout cell = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.cell_schedule, scheduleGL, false);
            TextView tv = new TextView(getActivity());
            tv.setText(week);
            GridLayout.Spec rowSpan = GridLayout.spec(0);
            GridLayout.Spec colSpan = GridLayout.spec(i+1);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpan, colSpan);
            params.setGravity(Gravity.CENTER);
            cell.setLayoutParams(params);
            cell.addView(tv);
            scheduleGL.addView(cell);
        }
        for (int i = 0; i < 12; i++) {
            LinearLayout cell = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.cell_schedule, scheduleGL, false);
            TextView tv = new TextView(getActivity());
            tv.setText(String.valueOf(i + 9));
            GridLayout.Spec rowSpan = GridLayout.spec(i+1);
            GridLayout.Spec colSpan = GridLayout.spec(0);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpan, colSpan);
            params.setGravity(Gravity.RIGHT);
            cell.setLayoutParams(params);
            cell.addView(tv);
            scheduleGL.addView(cell);
        }
    }
}
