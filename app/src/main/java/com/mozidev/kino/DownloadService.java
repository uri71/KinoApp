package com.mozidev.kino;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.mozidev.kino.activity.MainActivity;
import com.mozidev.kino.notifications.GcmBroadcastReceiver;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

/**
 * Created by y.storchak on 25.03.15.
 */
public class DownloadService extends IntentService {
    public static final String TAG = "DownloadService";
    AQuery aq;


    public DownloadService() {
        super("DownloadService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        boolean isDownloadImage = getSharedPreferences
                (Constants.PREFERENCES, MODE_PRIVATE).getBoolean(Constants.PREFS_DOWNLOAD_PHOTO, false);
        if (isDownloadImage) stopSelf();
        aq = new AQuery(getApplicationContext());
        aq.ajax(Constants.URL_BASE + Constants.URL_POSTS, JSONObject.class, new AjaxCallback<JSONObject>() {

            @Override
            public void callback(String url, JSONObject json, AjaxStatus status) {


                if (json != null) {
                    try {
                        String image_url = json.getJSONObject("data").getString("image");
                        getImage(aq, image_url);
                        Log.d(TAG, "SUCCESS download URL:" + status.getCode());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.d(TAG, "Error download URL:" + status.getCode());
                }
            }
        });
    }

    private void getImage(AQuery aq, String url) {

        File target = new File(getFilesDir().getPath(), Constants.IMAGE_PATH);

        aq.download(url, target, new AjaxCallback<File>() {

            public void callback(String url, File file, AjaxStatus status) {

                if (file != null) {
                    SharedPreferences.Editor editor = getSharedPreferences
                            (Constants.PREFERENCES, MODE_PRIVATE).
                            edit();
                    editor.putBoolean(Constants.PREFS_DOWNLOAD_PHOTO, true);
                    Log.d(TAG, "SUCCESS download IMG:" + status.getCode());
                } else {
                    Log.d(TAG, "Error download IMG:" + status.getCode());
                }
            }

        });
    }


}