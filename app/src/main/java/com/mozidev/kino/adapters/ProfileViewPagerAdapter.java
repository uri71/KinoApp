package com.mozidev.kino.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.mozidev.kino.Constants;
import com.mozidev.kino.fragments.ProfileFragment;
import com.mozidev.kino.model.Team;

import java.util.List;

/**
 * Created by y.storchak on 14.01.15.
 */
public class ProfileViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<Team> mTeams;


    public ProfileViewPagerAdapter(FragmentManager fm, List<Team> teams) {
        super(fm);
        mTeams = teams;
    }


    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constants.PROFILE_ARG_TEAM, mTeams.get(position));
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getCount() {
        return mTeams.size();
    }
}
