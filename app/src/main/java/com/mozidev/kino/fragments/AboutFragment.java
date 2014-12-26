package com.mozidev.kino.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mozidev.kino.Constants;
import com.mozidev.kino.activity.MainActivity;
import com.mozidev.kino.R;
import com.mozidev.kino.adapters.AboutAdapter;


public class AboutFragment extends Fragment {

    public AboutFragment() {
    }


    public static AboutFragment newInstance(int number) {
        AboutFragment fragment = new AboutFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.ARG_SECTION_NUMBER, number);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView lv_about = (ListView) view.findViewById(R.id.lv_about);
        lv_about.setAdapter(new AboutAdapter(getActivity(), R.layout.item_list_about));
        lv_about.setVerticalScrollBarEnabled(false);

        ListView lv_facts = (ListView) view.findViewById(R.id.lv_facts);
        String[] about = getActivity().getResources().getStringArray(R.array.about_facts);
        lv_facts.setAdapter(new ArrayAdapter(getActivity(), R.layout.item_list_about_facts, about));
        lv_facts.setVerticalScrollBarEnabled(false);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(Constants.ARG_SECTION_NUMBER));
    }
}
