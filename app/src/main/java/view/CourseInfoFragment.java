package view;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import anandroid.com.bouncourseplanner.MainActivity;
import anandroid.com.bouncourseplanner.R;
import data.Models;
import helper.CourseHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class CourseInfoFragment extends DialogFragment {


    public CourseInfoFragment() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        int index = bundle.getInt("index");
        final Models.Course course = CourseHelper.courses.get(index);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(course.codeSec);
        builder.setMessage(course.name);
        if (CourseHelper.isInSchedule(course)) {
            builder.setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    CourseHelper.removeFromSchedule(course);
                    CourseHelper.courseListAdapter.notifyDataSetChanged();
                    ((MainActivity) getActivity()).onScheduleChanged();
                }
            });
        } else {
            builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    CourseHelper.addToSchedule(course);
                    CourseHelper.courseListAdapter.notifyDataSetChanged();
                    ((MainActivity) getActivity()).onScheduleChanged();
                }
            });
        }
        builder.setNegativeButton("OK", null);

        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_course_info, null);
        TextView instructorTV = (TextView) view.findViewById(R.id.instuctor);
        TextView hoursTV = (TextView) view.findViewById(R.id.hours);
        TextView creditTV = (TextView) view.findViewById(R.id.credit);
        TextView ectsTV = (TextView) view.findViewById(R.id.ects);

        instructorTV.setText(course.instructor);
        hoursTV.setText(course.scheduleStr);
        creditTV.setText(String.valueOf(course.credit));
        ectsTV.setText(String.valueOf(course.ects));

        builder.setView(view);
        return builder.create();
    }
}
