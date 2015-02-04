package com.mozidev.kino.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mozidev.kino.Constants;
import com.mozidev.kino.R;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

/**
 * Created by y.storchak on 30.01.15.
 */
public class OtherCompany extends Fragment implements SlidingUpPanelLayout.PanelSlideListener{

    private SlidingUpPanelLayout mSlidingUpPanelLayout;
    private Toolbar mToolbar;

    public OtherCompany() {
    }


    public static OtherCompany newInstance(Bundle args, SlidingUpPanelLayout panelLayout) {
        OtherCompany fragment = new OtherCompany();
        if (args != null)
            fragment.setArguments(args);
        //panelLayout.setPanelSlideListener(fragment);
        fragment.setSlidingUpPanelLayout(panelLayout);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable
                             ViewGroup container,
                             @Nullable
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_other_company, container, false);
        return view;
    }


    @Override
    public void onViewCreated(View view,
                              @Nullable
                              Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mSlidingUpPanelLayout.setDragView(mToolbar);
        ListView listView = (ListView) view.findViewById(R.id.list);
        listView.setAdapter(new ArrayAdapter(getActivity(), R.layout.item_company_films, getResources().getStringArray(R.array.new_people_films)));
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
