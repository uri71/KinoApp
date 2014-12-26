package com.mozidev.kino.fragments;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mozidev.kino.Constants;
import com.mozidev.kino.activity.MainActivity;
import com.mozidev.kino.R;
import com.mozidev.kino.adapters.TeamAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamFragment extends Fragment {


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
        final ListView listView = (ListView)view.findViewById(R.id.list);
        listView.setAdapter(new TeamAdapter(getActivity()));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(((TeamAdapter) listView.getAdapter()).getItem(position)));
                startActivity(intent);
            }
        });
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity)activity).onSectionAttached(getArguments().getInt(Constants.ARG_SECTION_NUMBER));
    }
}
