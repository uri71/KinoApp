package com.mozidev.kino.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mozidev.kino.R;

/**
 * Created by user on 16.03.2015.
 */
public class AboutTenderFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about_tender, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
        Button btn_start = (Button) view.findViewById(R.id.btn_start);
    }
}
