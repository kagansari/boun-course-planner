package anandroid.com.bouncourseplanner;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import helper.CourseHelper;
import interfaces.OnScheduleChangedListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements TextWatcher {

    public ListView courseList;
    public ListView conflictList;
    public EditText courseET;

    private OnScheduleChangedListener onScheduleChangedListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public SearchFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        courseList = (ListView) view.findViewById(R.id.courseList);
        courseList.setAdapter(CourseHelper.courseListAdapter);
        conflictList = (ListView) view.findViewById(R.id.conflictList);
        conflictList.setAdapter(CourseHelper.conflictListAdapter);
        courseET = (EditText) view.findViewById(R.id.courseET);
        courseET.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        CourseHelper.courseListAdapter.getFilter().filter(s);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
