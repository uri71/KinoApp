package com.mozidev.kino.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import com.mozidev.kino.R;
import com.mozidev.kino.fragments.DrawerFragment;
import com.mozidev.kino.fragments.HistoryFragment;
import com.mozidev.kino.fragments.CompanyFragment;
import com.mozidev.kino.fragments.NewsFragment;
import com.mozidev.kino.fragments.PosterFragment;
import com.mozidev.kino.fragments.ShotFragment;
import com.mozidev.kino.fragments.TeamFragment;


public class MainActivity extends ActionBarActivity
        implements DrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private DrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    private String[] mTitles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (DrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        mTitles = getResources().getStringArray(R.array.title);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }


    @Override
    public void onNavigationDrawerItemSelected(int position) {
        Fragment fragment = null;
        switch (position) {
            case (1):
                fragment = PosterFragment.newInstance(position);
                break;
            case (2):
                fragment = TeamFragment.newInstance(position);
                break;
            case (3):
                fragment = ShotFragment.newInstance(position);
                break;
            case (4):
                fragment = HistoryFragment.newInstance(position);
                break;
            case (5):
                fragment = NewsFragment.newInstance(position);
                break;
            case (6):
                fragment = CompanyFragment.newInstance(position);
                break;
        }
        if (fragment != null) {
            setFragment(fragment);
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


    private void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left,
                R.anim.slide_in_left, R.anim.slide_out_right);
        fragmentTransaction.replace(R.id.container, fragment)
                .commit();

    }


    public void onSectionAttached(int number) {
        mTitle = mTitles[number];
    }


    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
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
