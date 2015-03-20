package com.mozidev.kino.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.mozidev.kino.Constants;
import com.mozidev.kino.R;
import com.mozidev.kino.util.RippleDrawable;
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
        Button btn_tender = (Button) findViewById(R.id.btn_tender);
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
        mImage.setVisibility(View.GONE);
        mImage.clearAnimation();
    }
}
