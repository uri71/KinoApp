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
import com.mozidev.kino.model.Team;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        listView.setAdapter(new TeamAdapter(getActivity(), getTeamList()));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(((MainActivity)getActivity()).isConnected()) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(((TeamAdapter) listView.getAdapter()).getItem(position).uri));
                    startActivity(intent);
                } else ((MainActivity)getActivity()).showConnectedDialog();
            }
        });
    }

private List<Team> getTeamList(){
    List <Team> team = new ArrayList();
    List <Integer> images = Arrays.asList(R.drawable.team1, R.drawable.team2, R.drawable.team3, R.drawable.team4, R.drawable.team5, R.drawable.team6);
    List <String> names = Arrays.asList(getActivity().getResources().getStringArray(R.array.team_names));
    List <String> lines = Arrays.asList(getActivity().getResources().getStringArray(R.array.team_lines));
    List <String> urls = Arrays.asList(getActivity().getResources().getStringArray(R.array.team_links));
    for (int i = 0; i< names.size(); i++){
        team.add(new Team(names.get(i), lines.get(i), urls.get(i), images.get(i)));
    }
    return team;

}
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity)activity).onSectionAttached(getArguments().getInt(Constants.ARG_SECTION_NUMBER));
    }
}
