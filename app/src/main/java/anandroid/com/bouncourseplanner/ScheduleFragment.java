package anandroid.com.bouncourseplanner;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends Fragment {


    GridLayout scheduleGL;

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
