package com.mozidev.kino.fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.mozidev.kino.Constants;
import com.mozidev.kino.R;
import com.mozidev.kino.activity.BaseActivity;
import com.mozidev.kino.activity.BigShotActivity;
import com.mozidev.kino.activity.MainActivity;
import com.mozidev.kino.adapters.ShotAdapter;


public class ShotFragment extends Fragment implements AdapterView.OnItemClickListener {


    public ShotFragment() {
    }


    public static ShotFragment newInstance(int number) {
        ShotFragment fragment = new ShotFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.ARG_SECTION_NUMBER, number);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shot, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView list = ((RecyclerView) view.findViewById(R.id.list));
        ShotAdapter adapter = new ShotAdapter(getActivity());
        adapter.setOnItemClickListener(this);
        list.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
        list.setLayoutManager(manager);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(Constants.ARG_SECTION_NUMBER));
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(!((BaseActivity)getActivity()).isConnected()) ((BaseActivity) getActivity()).showConnectedDialog();
        Intent intent;
        intent = new Intent(getActivity(), BigShotActivity.class);
        intent.putExtra(Constants.ARG_SHOT_NUMBER, position);
        intent.putExtra(Constants.ARG_NUMBER_PHOTO_SET, Constants.SHOT_SET);
        startActivity(intent);
    }
}
