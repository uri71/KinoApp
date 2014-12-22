package com.mozidev.kino;

import com.mozidev.kino.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.VideoView;

import java.util.Arrays;
import java.util.List;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class PlayerActivity extends Activity {
private List<Integer> trailers;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        trailers = Arrays.asList(R.raw.trailer1, R.raw.trailer2, R.raw.trailer2);

        int resource = getIntent().getIntExtra(Constants.ARG_SHOT_NUMBER, Constants.SHOT_COUNT)-Constants.SHOT_COUNT;

         videoView = (VideoView)findViewById(R.id.vv_trailer);
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + "R.raw.trailer"+resource));
        videoView.start();

    }



}
