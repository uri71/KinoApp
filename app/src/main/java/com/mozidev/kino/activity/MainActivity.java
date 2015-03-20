package com.mozidev.kino.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.View;

import com.mozidev.kino.R;
import com.mozidev.kino.fragments.AboutFilmFragment;
import com.mozidev.kino.fragments.AboutTenderFragment;
import com.mozidev.kino.fragments.DrawerFragment;
import com.mozidev.kino.fragments.HistoryFragment;
import com.mozidev.kino.fragments.ShotFragment;
import com.mozidev.kino.fragments.TeamFragment;
import com.mozidev.kino.fragments.TrailerFragment;


public class MainActivity extends BaseActivity
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
                fragment = AboutFilmFragment.newInstance(position);
                break;
            case (2):
                fragment = TrailerFragment.newInstance(position);
                break;
            case (3):
                fragment = ShotFragment.newInstance(position);
                break;
            case (4):
                fragment = TeamFragment.newInstance(position);
                break;
            case (5):
                fragment = HistoryFragment.newInstance(position);
                break;
            case (6):
                fragment = AboutTenderFragment.newInstance(position);
                break;
        }
        if (fragment != null) {
            setFragment(fragment);
        }

    }


    public void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left,
                R.anim.slide_in_left, R.anim.slide_out_right);
        fragmentTransaction.replace(R.id.container, fragment)
                .commit();

    }


    public void onSectionAttached(int number) {
        mTitle = mTitles[number - 1];
    }


    public void restoreActionBar() {

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            actionBar.setTitle(mTitle);
        } else {
            actionBar.setTitle(getResources().getString(R.string.app_name));
        }
    }


    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        restoreActionBar();
        return false;
    }


    @Override
    public void onBackPressed() {
        showEscapeDialog();
    }


    private Dialog showEscapeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Ви дійсно хочете покинути додаток?");
        builder.setNegativeButton("Ні", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("Так", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                MainActivity.this.finish();
            }
        });
        builder.show();
        return null;
    }
}
