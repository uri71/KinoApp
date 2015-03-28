package com.mozidev.kino.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

import com.mozidev.kino.Constants;
import com.mozidev.kino.KinoApplication;
import com.mozidev.kino.R;
import com.mozidev.kino.adapters.BigShotFragmentAdapter;
import com.mozidev.kino.model.NewsItem;
import com.mozidev.kino.model.Photo;

import java.util.ArrayList;
import java.util.List;


public class BigShotActivity extends ActionBarActivity {

    private String[] bigShot;
    private ViewPager pager;
    private String title;
    public int mImageWidth = 0;
    public int mImageHeight = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_shot);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        int set = getIntent().getIntExtra(Constants.ARG_NUMBER_PHOTO_SET, 0);
        int position = getIntent().getIntExtra(Constants.ARG_SHOT_NUMBER, 0);
        title = getString(R.string.films_shot);
        switch (set) {
            case Constants.SHOT_SET: {
                bigShot = getResources().getStringArray(R.array.shot_urls);
            }
            break;
            case Constants.PHOTO_SET: {
                bigShot = getResources().getStringArray(R.array.history_urls);
                title = getString(R.string.title_activity_big_photo);
            }
            break;

        }
        pager = (ViewPager) findViewById(R.id.pager);
        BigShotFragmentAdapter adapter = new BigShotFragmentAdapter(getSupportFragmentManager(), bigShot);
        pager.setAdapter(adapter);
        if (set != Constants.NEWS_SET) {
            pager.setCurrentItem(position);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getSupportActionBar().hide();
        } else {
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
