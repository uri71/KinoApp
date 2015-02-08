package com.mozidev.kino.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.mozidev.kino.Constants;
import com.mozidev.kino.R;
import com.mozidev.kino.activity.BigShotActivity;
import com.mozidev.kino.adapters.PhotoAdapter;


public class PhotoFragment extends Fragment implements AdapterView.OnItemClickListener {


    public static PhotoFragment newInstance() {
        return new PhotoFragment();
    }


    public PhotoFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_photo, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        RecyclerView list = ((RecyclerView) view.findViewById(R.id.list));
        PhotoAdapter adapter = new PhotoAdapter(getActivity());
        adapter.setOnItemClickListener(this);
        list.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
        list.setLayoutManager(manager);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            getActivity().getSupportFragmentManager().popBackStack();
        }
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), BigShotActivity.class);
        intent.putExtra(Constants.ARG_NUMBER_PHOTO_SET, Constants.PHOTO_SET);
        intent.putExtra(Constants.ARG_SHOT_NUMBER, position);
        startActivity(intent);

    }
}
