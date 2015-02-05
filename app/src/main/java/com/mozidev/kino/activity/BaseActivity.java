package com.mozidev.kino.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;

import com.mozidev.kino.R;

/**
 * Created by y.storchak on 05.02.15.
 */
public class BaseActivity extends ActionBarActivity {


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
        }
        boolean isConnected = networkInfo.isConnectedOrConnecting();
        return isConnected;
    }


    public Dialog showConnectedDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.check_network));
        Dialog dialog = builder.show();
        return dialog;
    }
}
