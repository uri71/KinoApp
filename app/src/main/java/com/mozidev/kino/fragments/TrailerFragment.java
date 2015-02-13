package com.mozidev.kino.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mozidev.kino.Constants;
import com.mozidev.kino.R;
import com.mozidev.kino.activity.MainActivity;
import com.mozidev.kino.adapters.TrailerAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by y.storchak on 13.02.15.
 */
public class TrailerFragment extends Fragment {


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
        ((MainActivity)getActivity()).onSectionAttached(getArguments().getInt(Constants.ARG_SECTION_NUMBER));
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
        ListView listView = (ListView) view.findViewById(R.id.list);
        listView.setAdapter(new TrailerAdapter(getActivity(), R.layout.item_trailer, getTrailerList()));
    }


    public List<Integer> getTrailerList() {
        return Arrays.asList(R.drawable.trailer1, R.drawable.trailer2);
    }
}
