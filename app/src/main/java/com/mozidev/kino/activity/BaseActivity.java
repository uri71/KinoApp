package com.mozidev.kino.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.mozidev.kino.Constants;
import com.mozidev.kino.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by y.storchak on 05.02.15.
 */
public class BaseActivity extends ActionBarActivity {

    private SharedPreferences preferences;
    boolean isDownloadURLS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = getPreferences(MODE_PRIVATE);
        isDownloadURLS = preferences.getString(Constants.ARG_URL_IOS, "").isEmpty() || preferences.getString(Constants.ARG_URL_ANDROID, "").isEmpty();

    }


    @Override
    protected void onResume() {
        super.onResume();
        if (!isDownloadURLS) {
            requestDescription();
        }
    }


    public void sendIntent(String uri) {
        if (!isConnected()) {
            showConnectedDialog();
            return;
        } else {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            startActivity(intent);
        }
    }


    public boolean isConnected() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return false;
        } else if (!isDownloadURLS) {
            requestDescription();
            return false;
        }
        boolean isConnected = networkInfo.isConnectedOrConnecting();
        return isConnected;
    }


    private void requestDescription() {
        setDescription(getString(R.string.api_url_base) + getString(R.string.link_googleplay), Constants.ARG_URL_IOS);
        setDescription(getString(R.string.api_url_base) + getString(R.string.link_appstore), Constants.ARG_URL_ANDROID);
    }


    public Dialog showConnectedDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.check_network));
        Dialog dialog = builder.show();
        return dialog;
    }


    public boolean checkSDKVersion() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
    }


    public void setDescription(String url, final String key) {
        AQuery aq = new AQuery(this);
        aq.ajax(url, JSONObject.class, new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject json, AjaxStatus status) {
                if (json != null) {
                    try {
                        String uri = json.getString("link");
                        if (uri != null && !uri.isEmpty()) {
                            preferences.edit().putString(key, uri).apply();
                        }
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
