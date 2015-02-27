package com.mozidev.kino.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mozidev.kino.Constants;
import com.mozidev.kino.R;
import com.mozidev.kino.activity.MainActivity;
import com.mozidev.kino.activity.PlayerActivity;
import com.mozidev.kino.adapters.TrailerAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by y.storchak on 13.02.15.
 */
public class TrailerFragment extends Fragment implements AdapterView.OnItemClickListener {


    public static TrailerFragment newInstance(int number) {
        TrailerFragment fragment = new TrailerFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.ARG_SECTION_NUMBER, number);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) getActivity()).onSectionAttached(getArguments().getInt(Constants.ARG_SECTION_NUMBER));
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable
                             ViewGroup container,
                             @Nullable
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trailer, container, false);
        return view;
    }


    @Override
    public void onViewCreated(View view,
                              @Nullable
                              Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView list = (RecyclerView) view.findViewById(R.id.list);

        TrailerAdapter adapter = new TrailerAdapter(getActivity(), R.layout.item_trailer, getTrailerList());
        adapter.setListener(this);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));

    }


    public List<Integer> getTrailerList() {
        return Arrays.asList(R.drawable.trailer1, R.drawable.trailer2, R.drawable.backstage1, R.drawable.backstage2,
                R.drawable.backstage3, R.drawable.backstage4, R.drawable.backstage5);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (((MainActivity) getActivity()).isConnected()) {
            Intent intent = new Intent(getActivity(), PlayerActivity.class);
            intent.putExtra(Constants.ARG_NUMBER_TRAILER, position);
            startActivity(intent);
        } else {
            ((MainActivity) getActivity()).showConnectedDialog();
        }
    }
}
