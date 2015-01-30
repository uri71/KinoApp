package com.mozidev.kino.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

import com.mozidev.kino.Constants;
import com.mozidev.kino.KinoApplication;
import com.mozidev.kino.R;
import com.mozidev.kino.adapters.BigShotFragmentAdapter;
import com.mozidev.kino.model.NewsItem;
import com.mozidev.kino.model.Photo;

import java.util.ArrayList;
import java.util.List;


public class BigShotActivity extends ActionBarActivity {

    private List<Photo> bigShot;
    private ViewPager pager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_shot);
        int set = getIntent().getIntExtra(Constants.ARG_NUMBER_PHOTO_SET, 0);
        int position = getIntent().getIntExtra(Constants.ARG_SHOT_NUMBER, 0);
        switch (set) {
            case Constants.SHOT_SET: {
                bigShot = KinoApplication.getInstance(this).getShotList();
                bigShot.remove(bigShot.size()-1);
                bigShot.remove(bigShot.size()-1);
            }
            break;
            case Constants.PHOTO_SET: {
                bigShot = KinoApplication.getInstance(this).getHistoryPhotoList();
            }
            break;
            case Constants.NEWS_SET: {
                bigShot = getNewsShot(position);
            }
            break;
        }

        pager = (ViewPager) findViewById(R.id.pager);
        //BigShotImageAdapter adapter = new BigShotImageAdapter(this, bigShot);
        BigShotFragmentAdapter adapter = new BigShotFragmentAdapter(getSupportFragmentManager(), bigShot);
        pager.setAdapter(adapter);
        if (set != Constants.NEWS_SET) {
            pager.setCurrentItem(position);
        }
    }


    private List<Photo> getNewsShot(int position) {
        List<NewsItem> list = KinoApplication.getInstance(this).getNewsItem();
        List<Photo> photo = new ArrayList<>();
        photo.add(new Photo(list.get(position).photo_1, ""));
        photo.add(new Photo(list.get(position).photo_2, ""));
        photo.add(new Photo(list.get(position).photo_3, ""));
        return photo;
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getSupportActionBar().hide();
        } else {
            getSupportActionBar().setTitle(getString(R.string.films_shot));
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
