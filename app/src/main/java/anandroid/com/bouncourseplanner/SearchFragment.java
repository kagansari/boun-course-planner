package anandroid.com.bouncourseplanner;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import adapter.CourseListAdapter;
import data.Models;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements TextWatcher {

    public ListView courseList;
    public EditText courseET;
    public CourseListAdapter adapter;

    public SearchFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Gson gson = new Gson();
        try {
            InputStream is = getActivity().getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            ArrayList<Models.Course> courses = gson.fromJson(new String(buffer, "UTF-8"), new TypeToken<List<Models.Course>>(){}.getType());
            courseList = (ListView) view.findViewById(R.id.courseList);
            adapter = new CourseListAdapter(getActivity(), courses);
            courseList.setAdapter(adapter);
            courseET = (EditText) view.findViewById(R.id.courseET);
            courseET.addTextChangedListener(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        adapter.getFilter().filter(s);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
