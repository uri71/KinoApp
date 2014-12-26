package com.mozidev.kino.activity;

import android.app.Activity;
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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.mozidev.kino.Constants;
import com.mozidev.kino.R;
import com.mozidev.kino.fragments.AboutFragment;
import com.mozidev.kino.fragments.CompanyFragment;
import com.mozidev.kino.fragments.NavigationDrawerFragment;
import com.mozidev.kino.fragments.PosterFragment;
import com.mozidev.kino.fragments.RatingFragment;
import com.mozidev.kino.fragments.ShotFragment;
import com.mozidev.kino.fragments.StoryFragment;
import com.mozidev.kino.fragments.TeamFragment;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    private String[] mTitles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
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
        String uri = "";
        String [] uris = getResources().getStringArray(R.array.uri_about);
        switch (position) {
            case (1):
                fragment = PosterFragment.newInstance(position);
                break;
            case (2):
                fragment = StoryFragment.newInstance(position);
                break;
            case (3):
                fragment = ShotFragment.newInstance(position);
                break;
            case (4):
                fragment = TeamFragment.newInstance(position);
                break;
            case (5):
                fragment = AboutFragment.newInstance(position);
                break;
            case (6):
                fragment = RatingFragment.newInstance(position);
                break;
            case (8):
                fragment = CompanyFragment.newInstance(position);
                break;
            case (9):uri = uris[6];
                break;
            case (10):uri = uris[7];
                break;
            case (11):uri = uris[8];
                break;

        }
        if (fragment != null) {
            setFragment(fragment);
        } else if (!uri.equals(""))sendIntent(uri);

    }


    public void sendIntent(String uri) {
        if(!isConnected()){
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {

            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
