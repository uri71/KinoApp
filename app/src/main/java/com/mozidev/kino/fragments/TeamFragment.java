package com.mozidev.kino.fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mozidev.kino.Constants;
import com.mozidev.kino.KinoApplication;
import com.mozidev.kino.activity.MainActivity;
import com.mozidev.kino.R;
import com.mozidev.kino.activity.ProfileActivity;
import com.mozidev.kino.adapters.TeamAdapter;
import com.mozidev.kino.model.Team;
import com.norbsoft.typefacehelper.TypefaceHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamFragment extends Fragment implements AdapterView.OnItemClickListener{
    private RecyclerView listView;
    private List <Team> mTeams;

    public TeamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team, container, false);
    }


    public static TeamFragment newInstance(int number) {
        TeamFragment fragment = new TeamFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.ARG_SECTION_NUMBER, number);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTeams = KinoApplication.getInstance(getActivity()).getTeamsList();
        listView = (RecyclerView)view.findViewById(R.id.list);
        TeamAdapter adapter = new TeamAdapter(getActivity(), mTeams);
        adapter.setOnItemClickListener(this);
        listView.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        listView.setLayoutManager(manager);
        TypefaceHelper.typeface(view);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity)activity).onSectionAttached(getArguments().getInt(Constants.ARG_SECTION_NUMBER));
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), ProfileActivity.class);
        intent.putExtra(Constants.PROFILE_ARG, position);
        startActivity(intent);
    }
}
