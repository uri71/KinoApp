package com.mozidev.kino.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.mozidev.kino.Constants;
import com.mozidev.kino.KinoApplication;
import com.mozidev.kino.R;
import com.mozidev.kino.activity.BigShotActivity;
import com.mozidev.kino.activity.MainActivity;
import com.mozidev.kino.adapters.NewsAdapter;
import com.mozidev.kino.model.NewsItem;
import com.mozidev.kino.model.Photo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by y.storchak on 29.01.15.
 */
public class NewsFragment extends Fragment implements AdapterView.OnItemClickListener{


    private List<NewsItem> mItems;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable
                             ViewGroup container,
                             @Nullable
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news, container, false);
        return view;
    }


    @Override
    public void onViewCreated(View view,
                              @Nullable
                              Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView list = (RecyclerView) view.findViewById(R.id.list);
        mItems = KinoApplication.getInstance(getActivity()).getNewsItem();
        NewsAdapter adapter = new NewsAdapter(getActivity(), mItems);
        adapter.setOnItemClickListener(this);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public static NewsFragment newInstance(int number) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.ARG_SECTION_NUMBER, number);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(Constants.ARG_SECTION_NUMBER));

    }


    @Override
    public void onResume() {
        super.onResume();
        ((DrawerLayout)(getActivity()).findViewById(R.id.drawer_layout))
                .setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), BigShotActivity.class);

        intent.putExtra(Constants.ARG_NUMBER_PHOTO_SET, Constants.NEWS_SET);
        intent.putExtra(Constants.ARG_SHOT_NUMBER, position);
        startActivity(intent);
    }



}
