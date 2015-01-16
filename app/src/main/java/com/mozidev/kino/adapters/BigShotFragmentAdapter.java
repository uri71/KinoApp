package com.mozidev.kino.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.mozidev.kino.fragments.BigShotFragment;

import java.util.List;

/**
 * Created by y.storchak on 16.01.15.
 */
public class BigShotFragmentAdapter extends FragmentStatePagerAdapter {

    private List<Integer> shots;


    public BigShotFragmentAdapter(FragmentManager fm, List<Integer> list) {
        super(fm);
        shots = list;
    }


    @Override
    public Fragment getItem(int position) {
        BigShotFragment fragment = BigShotFragment.newInstance(shots.get(position));
        return fragment;
    }


    @Override
    public int getCount() {
        return shots.size();
    }
}
