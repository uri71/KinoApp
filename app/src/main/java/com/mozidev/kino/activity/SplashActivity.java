package com.mozidev.kino.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.text.format.DateUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mozidev.kino.Constants;
import com.mozidev.kino.KinoApplication;
import com.mozidev.kino.R;
import com.mozidev.kino.util.RippleDrawable;
import com.norbsoft.typefacehelper.TypefaceHelper;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by y.storchak on 23.01.15.
 */
public class SplashActivity extends ActionBarActivity {

    ImageView mImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach);
        KinoApplication.getInstance(this).onCreate();
        mImage = (ImageView) findViewById(R.id.splash_screen);
        Button btn_tender = (Button) findViewById(R.id.btn_tender);
        TextView message = (TextView) findViewById(R.id.text);
        RippleDrawable.createRipple(btn_tender, Color.parseColor("#ffffff"));
        btn_tender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendIntent(true);
            }
        });
        getSupportActionBar().hide();
        mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendIntent(false);
            }
        });
        Calendar calendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.add(Calendar.YEAR, 2015);
        endCalendar.add(Calendar.MONTH, Calendar.MAY);
        endCalendar.add(Calendar.DAY_OF_MONTH, 2);
        endCalendar.add(Calendar.HOUR_OF_DAY, 0);
       // boolean finish = calendar.after(endCalendar);
        boolean finish = true;
        SharedPreferences preferences = getSharedPreferences(Constants.PREFERENCES, MODE_PRIVATE);
        preferences.edit().putBoolean(Constants.PREFERENCES_FINISH, finish).commit();
        if(finish){
            btn_tender.setVisibility(View.GONE);
            message.setVisibility(View.GONE);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    sendIntent(false);
                }
            }, 3 * DateUtils.SECOND_IN_MILLIS);
        }

        TypefaceHelper.typeface(this);

    }




    private void sendIntent(boolean isTender) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(Constants.ARG_SET_TENDER, isTender);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }


    @Override
    protected void onPause() {
        super.onPause();
       /* mImage.setVisibility(View.GONE);
        mImage.clearAnimation();*/
    }
}
