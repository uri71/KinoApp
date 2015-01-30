package com.mozidev.kino.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mozidev.kino.Constants;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

/**
 * Created by y.storchak on 30.01.15.
 */
public class OtherCompany extends Fragment implements SlidingUpPanelLayout.PanelSlideListener{

    private SlidingUpPanelLayout mSlidingUpPanelLayout;

    public OtherCompany() {
    }


    public static OtherCompany newInstance(Bundle args, SlidingUpPanelLayout panelLayout) {
        OtherCompany fragment = new OtherCompany();
        if (args != null)
            fragment.setArguments(args);
        panelLayout.setPanelSlideListener(fragment);
        fragment.setSlidingUpPanelLayout(panelLayout);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable
                             ViewGroup container,
                             @Nullable
                             Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onViewCreated(View view,
                              @Nullable
                              Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //mSlidingUpPanelLayout.setDragView();
    }

    public void setSlidingUpPanelLayout(SlidingUpPanelLayout l) {
        this.mSlidingUpPanelLayout = l;
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
