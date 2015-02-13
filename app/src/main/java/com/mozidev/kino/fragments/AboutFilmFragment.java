package com.mozidev.kino.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mozidev.kino.Constants;
import com.mozidev.kino.R;
import com.mozidev.kino.activity.MainActivity;
import com.mozidev.kino.adapters.AboutFilmAdapter;
import com.mozidev.kino.model.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFilmFragment extends Fragment {


    public AboutFilmFragment() {
        // Required empty public constructor
    }


    public static AboutFilmFragment newInstance(int number) {
        AboutFilmFragment fragment = new AboutFilmFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.ARG_SECTION_NUMBER, number);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_poster, container, false);

        return view;
    }


    private List setItems() {
        List<Item> items = new ArrayList();
        String[] names = getResources().getStringArray(R.array.about_title);
        String[] data = getResources().getStringArray(R.array.about_data);
        String[] facts = getResources().getStringArray(R.array.about_facts);
        for (int i = 0; i < names.length + facts.length + 2; i++) {
            if (i > 1 && i < names.length + 2) {
                items.add(new Item(Constants.TYPE_ITEM, names[i - 2], data[i - 2]));
            } else if (i == 0) {
                items.add(new Item(Constants.TYPE_TITLE, getResources().getString(R.string.app_name), ""));
            } else if (i == 1) {
                items.add(new Item(Constants.TYPE_STORY, getResources().getString(R.string.story), ""));
            } else if (i == names.length + 2) {
                items.add(new Item(Constants.TYPE_TITLE, getResources().getString(R.string.title_facts), ""));
            } else {
                items.add(new Item(Constants.TYPE_STORY, facts[i - names.length - 2], ""));
            }
        }
        return items;
    }


    @Override
    public void onViewCreated(View view,
                              @Nullable
                              Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_about);
        recyclerView.setHasFixedSize(true);


        AboutFilmAdapter adapter = new AboutFilmAdapter(getActivity(), setItems());
        //adapter.setHasStableIds(true);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(Constants.ARG_SECTION_NUMBER));
    }
}
