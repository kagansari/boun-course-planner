package adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import anandroid.com.bouncourseplanner.R;
import data.Models;
import helper.CourseHelper;

public class ConflictListAdapter extends BaseAdapter {

    public Activity context;
    public LayoutInflater inflater;

    public ConflictListAdapter(Context context) {
        this.context = (Activity) context;
        this.inflater = this.context.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return CourseHelper.getConflicts().size();
    }

    @Override
    public Models.Conflict getItem(int position) {
        return CourseHelper.getConflicts().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Models.Conflict conflict = CourseHelper.getConflicts().get(position);
        String info = "";
        String time = conflict.day + conflict.hour;
        for (int i = 0; i < conflict.codeSecs.size(); i++) {
            String codeSec =  conflict.codeSecs.get(i);
            if (i != 0) info = ", " + info;
            info += codeSec;
        }
        View view = inflater.inflate(R.layout.row_conflict, null);
        TextView timeTV = (TextView) view.findViewById(R.id.conflictTime);
        TextView infoTV = (TextView) view.findViewById(R.id.conflictInfo);
        timeTV.setText(time);
        infoTV.setText(info);
        return view;
    }
}
