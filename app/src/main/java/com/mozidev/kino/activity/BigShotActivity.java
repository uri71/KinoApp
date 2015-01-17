package com.mozidev.kino.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

import com.mozidev.kino.Constants;
import com.mozidev.kino.R;
import com.mozidev.kino.adapters.BigShotFragmentAdapter;

import java.util.Arrays;
import java.util.List;


public class BigShotActivity extends ActionBarActivity {

    private List<Integer> bigShot;
    private ViewPager pager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_shot);
        bigShot = Arrays.asList(
                R.raw.poster_1, R.raw.poster_2,
                R.raw.poster_3, R.raw.shot_1, R.raw.shot_2,
                R.raw.shot_3, R.raw.shot_4,
                R.raw.shot_5, R.raw.shot_6,
                R.raw.shot_7, R.raw.shot_8,
                R.raw.shot_9, R.raw.shot_10,
                R.raw.shot_11, R.raw.shot_12,
                R.raw.shot_13, R.raw.shot_14,
                R.raw.shot_15, R.raw.shot_16);

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
