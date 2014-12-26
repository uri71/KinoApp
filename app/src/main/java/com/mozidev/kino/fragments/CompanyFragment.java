package com.mozidev.kino.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mozidev.kino.Constants;
import com.mozidev.kino.activity.MainActivity;
import com.mozidev.kino.R;


public class CompanyFragment extends Fragment implements View.OnClickListener{

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
        view.findViewById(R.id.comp_prodaction).setOnClickListener(this);
        view.findViewById(R.id.comp_distribution).setOnClickListener(this);
        view.findViewById(R.id.comp_television).setOnClickListener(this);
        view.findViewById(R.id.comp_postprodaction).setOnClickListener(this);
        view.findViewById(R.id.comp_studio).setOnClickListener(this);
        view.findViewById(R.id.comp_films).setOnClickListener(this);
        view.findViewById(R.id.comp_new_films).setOnClickListener(this);
        view.findViewById(R.id.comp_soon).setOnClickListener(this);
        view.findViewById(R.id.comp_education).setOnClickListener(this);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(Constants.ARG_SECTION_NUMBER));
    }


    @Override
    public void onClick(View v) {
        String [] uris = getResources().getStringArray(R.array.uri_about);
        String uri = "";
        switch (v.getId()){
            case R.id.comp_prodaction:uri = uris[0];break;
            case R.id.comp_distribution:uri = uris[1];break;
            case R.id.comp_television:uri = uris[2];break;
            case R.id.comp_postprodaction:uri = uris[3];break;
            case R.id.comp_studio:uri = uris[4];break;
            case R.id.comp_films:uri = uris[5];break;
            case R.id.comp_new_films:uri = uris[6];break;
            case R.id.comp_soon:uri = uris[7];break;
            case R.id.comp_education:uri = uris[8];break;
        }
        if (!uri.equals("")) {
            ((MainActivity) getActivity()).sendIntent(uri);
        }
    }
}
