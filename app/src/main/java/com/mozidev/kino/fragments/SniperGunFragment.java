package com.mozidev.kino.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mozidev.kino.Constants;
import com.mozidev.kino.R;
import com.mozidev.kino.activity.MainActivity;

/**
 * Created by y.storchak on 05.02.15.
 */
public class SniperGunFragment extends Fragment {


    public static SniperGunFragment newInstance() {
        SniperGunFragment fragment = new SniperGunFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable
                             ViewGroup container,
                             @Nullable
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sniper_gun, container, false);
        return view;
    }
}
