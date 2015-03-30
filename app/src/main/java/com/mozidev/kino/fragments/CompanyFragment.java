package com.mozidev.kino.fragments;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mozidev.kino.Constants;
import com.mozidev.kino.R;
import com.mozidev.kino.activity.MainActivity;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;


public class CompanyFragment extends Fragment implements SlidingUpPanelLayout.PanelSlideListener{

    private SlidingUpPanelLayout mSlidingUpPanelLayout;


    public CompanyFragment() {
    }


    public static CompanyFragment newInstance(int number) {
        CompanyFragment fragment = new CompanyFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.ARG_SECTION_NUMBER, number);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_company, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSlidingUpPanelLayout = (SlidingUpPanelLayout) view.findViewById(R.id.main_container);
        mSlidingUpPanelLayout.setOverlayed(true);
        mSlidingUpPanelLayout.setCoveredFadeColor(Color.argb(200, 110, 110, 110));
        mSlidingUpPanelLayout.setAnchorPoint(0.7f);
        mSlidingUpPanelLayout.setPanelSlideListener(this);
        if (savedInstanceState == null) {
            FragmentManager fm = getActivity().getSupportFragmentManager();
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
