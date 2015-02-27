package com.mozidev.kino.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import com.mozidev.kino.Constants;
import com.mozidev.kino.R;
import com.mozidev.kino.util.SystemUiHider;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class PlayerActivity extends Activity {

    private VideoView videoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        int number = getIntent().getIntExtra(Constants.ARG_NUMBER_TRAILER, 0);
        String[] trailers = getResources().getStringArray(R.array.trailers);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbar);
        videoView = (VideoView) findViewById(R.id.vv_trailer);
        videoView.setMediaController(new MediaController(this));
        videoView.requestFocus(View.FOCUS_FORWARD);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                progressBar.setVisibility(View.GONE);
                videoView.start();
            }
        });
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                showErrorDialog();
                return true;
            }
        });

        videoView.canPause();
        videoView.canSeekBackward();
        videoView.canSeekForward();
        videoView.setVideoURI(Uri.parse(trailers[number]));

    }


    private Dialog showErrorDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.player_dialog_title));
        Dialog dialog = builder.create();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                finish();
            }
        });
        dialog.show();
        return dialog;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoView.stopPlayback();
    }
}
