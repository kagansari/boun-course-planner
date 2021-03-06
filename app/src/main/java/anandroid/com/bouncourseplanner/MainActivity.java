package anandroid.com.bouncourseplanner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.LinearLayout;

import adapter.PageAdapter;
import helper.CourseHelper;
import interfaces.OnScheduleChangedListener;

public class MainActivity extends AppCompatActivity implements OnScheduleChangedListener {

    public ViewPager viewPager;
    public PageAdapter pageAdapter;
    public TabLayout tabLayout;
    public LinearLayout leftDrawer;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle drawerToggle;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        drawerToggle.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CourseHelper.init(this);
        initTabs();
        initDrawer();
    }

    private void initTabs() {
        viewPager = (ViewPager) findViewById(R.id.pager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        pageAdapter = new PageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pageAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initDrawer() {
        leftDrawer = (LinearLayout) findViewById(R.id.leftDrawer);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.course, R.string.course);
        drawerLayout.addDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public void onScheduleChanged() {
        pageAdapter.scheduleFragment.updateSchedule();
    }
}
