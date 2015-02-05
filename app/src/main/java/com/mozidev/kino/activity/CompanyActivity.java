package com.mozidev.kino.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.mozidev.kino.R;
import com.mozidev.kino.fragments.FilmUaFragment;
import com.mozidev.kino.fragments.OtherCompany;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

/**
 * Created by y.storchak on 05.02.15.
 */
public class CompanyActivity extends BaseActivity  implements SlidingUpPanelLayout.PanelSlideListener{
    private SlidingUpPanelLayout mSlidingUpPanelLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_company);
        getSupportActionBar().hide();
        mSlidingUpPanelLayout = (SlidingUpPanelLayout) findViewById(R.id.main_container);
        mSlidingUpPanelLayout.setOverlayed(true);
        mSlidingUpPanelLayout.setCoveredFadeColor(Color.argb(200, 110, 110, 110));
        mSlidingUpPanelLayout.setAnchorPoint(0.7f);
        mSlidingUpPanelLayout.setPanelSlideListener(this);
        if (savedInstanceState == null) {
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction()
                    .add(R.id.container, new FilmUaFragment())
                    .add(R.id.slider_container, OtherCompany.newInstance(null, mSlidingUpPanelLayout))
                    .commit();
        }
    }


    @Override
    public void onPanelSlide(View view, float v) {

    }


    @Override
    public void onPanelCollapsed(View view) {

    }


    @Override
    public void onPanelExpanded(View view) {

    }


    @Override
    public void onPanelAnchored(View view) {

    }


    @Override
    public void onPanelHidden(View view) {

    }
}
