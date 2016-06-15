package anandroid.com.bouncourseplanner;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import data.Models;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    public ListView courseList;

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
        ArrayList<Models.Course> courses = gson.fromJson(Models.dummy, new TypeToken<List<Models.Course>>(){}.getType());
        courseList = (ListView) getActivity().findViewById(R.id.courseList);
    }
}
