package com.mozidev.kino.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

import com.mozidev.kino.Constants;
import com.mozidev.kino.KinoApplication;
import com.mozidev.kino.R;
import com.mozidev.kino.adapters.BigShotFragmentAdapter;
import com.mozidev.kino.model.Photo;

import java.util.Arrays;
import java.util.List;


public class BigShotActivity extends ActionBarActivity {

    private List<Photo> bigShot;
    private ViewPager pager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_shot);
        int set = getIntent().getIntExtra(Constants.ARG_NUMBER_PHOTO_SET, 0);
        if (set == 0) {
            bigShot = KinoApplication.getInstance(this).getShotList();
        } else {
            bigShot = KinoApplication.getInstance(this).getHistoryPhotoList();
        }

        pager = (ViewPager) findViewById(R.id.pager);
        int position = getIntent().getIntExtra(Constants.ARG_SHOT_NUMBER, 0);
        //BigShotImageAdapter adapter = new BigShotImageAdapter(this, bigShot);
        BigShotFragmentAdapter adapter = new BigShotFragmentAdapter(getSupportFragmentManager(), bigShot);
        pager.setAdapter(adapter);
        pager.setCurrentItem(position);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getSupportActionBar().hide();
        } else {
            getSupportActionBar().setTitle("Кадры фильма");
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
