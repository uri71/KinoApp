package com.mozidev.kino.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mozidev.kino.Constants;
import com.mozidev.kino.activity.MainActivity;
import com.mozidev.kino.R;
import com.mozidev.kino.adapters.RatingAdapter;


public class RatingFragment extends Fragment {


    public RatingFragment() {
    }



    public static RatingFragment newInstance(int number) {
        RatingFragment fragment = new RatingFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.ARG_SECTION_NUMBER, number);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rating, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView listView = (ListView)view.findViewById(R.id.list);
        listView.setAdapter(new RatingAdapter(getActivity(), R.layout.item_list_rating));

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(Constants.ARG_SECTION_NUMBER));
    }

}
