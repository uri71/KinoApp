package com.mozidev.kino.activity;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mozidev.kino.Constants;
import com.mozidev.kino.KinoApplication;
import com.mozidev.kino.R;
import com.mozidev.kino.adapters.ProfileViewPagerAdapter;
import com.mozidev.kino.model.Team;

import java.util.List;

public class ProfileActivity extends ActionBarActivity {

    private ViewPager mViewPager;
    private List<Team> mTeams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int position = getIntent().getIntExtra(Constants.PROFILE_ARG, 0);
        setContentView(R.layout.activity_profile);
        mTeams = KinoApplication.getInstance(this).getTeamsList();
        mViewPager = (ViewPager)findViewById(R.id.pager);
        ProfileViewPagerAdapter adapter = new ProfileViewPagerAdapter(getSupportFragmentManager(), mTeams);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(position);

    }


    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
