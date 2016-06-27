package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import anandroid.com.bouncourseplanner.ScheduleFragment;
import anandroid.com.bouncourseplanner.SearchFragment;

/**
 * Created by tv on 11/06/16.
 */
public class PageAdapter extends FragmentPagerAdapter {

    public static final String key = "object";

    public SearchFragment searchFragment;
    public ScheduleFragment scheduleFragment;
    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                SearchFragment fragment = new SearchFragment();
                this.searchFragment = fragment;
                return searchFragment;
            case 1:
                ScheduleFragment fragment1 = new ScheduleFragment();
                this.scheduleFragment = fragment1;
                return scheduleFragment;
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Search";
            case 1:
                return "Schedule";
        }
        return null;
    }
}
