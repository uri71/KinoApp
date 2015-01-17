package com.mozidev.kino.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.mozidev.kino.R;
import com.mozidev.kino.adapters.PhotoAdapter;


public class PhotoFragment extends Fragment implements AdapterView.OnItemClickListener{



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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
