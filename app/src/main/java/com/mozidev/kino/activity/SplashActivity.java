package com.mozidev.kino.activity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.mozidev.kino.R;
import com.squareup.picasso.Picasso;

/**
 * Created by y.storchak on 23.01.15.
 */
public class SplashActivity extends ActionBarActivity {
ImageView mImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach);
        mImage = (ImageView) findViewById(R.id.splash_screen);
        Animation animationIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        final Animation animationOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        getSupportActionBar().hide();
        mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendIntent();
            }
        });

        animationIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }


            @Override
            public void onAnimationEnd(Animation animation) {
                mImage.startAnimation(animationOut);
            }


            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animationOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }


            @Override
            public void onAnimationEnd(Animation animation) {
                sendIntent();
            }


            @Override
            public void onAnimationRepeat(Animation animation) {


            }
        });
        mImage.startAnimation(animationIn);
        Picasso.with(this).load(R.drawable.poster_1).fit().centerCrop().into(mImage);
    }


    private void sendIntent() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onPause() {
        super.onPause();
        mImage.setVisibility(View.GONE);
        mImage.clearAnimation();
    }
}
