package com.mozidev.kino.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mozidev.kino.Constants;
import com.mozidev.kino.R;
import com.mozidev.kino.activity.MainActivity;


public class HistoryFragment extends Fragment implements View.OnClickListener {

    public HistoryFragment() {
    }


    public static HistoryFragment newInstance(int number) {
        HistoryFragment fragment = new HistoryFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.ARG_SECTION_NUMBER, number);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.biography).setOnClickListener(this);
        view.findViewById(R.id.snipers).setOnClickListener(this);
        view.findViewById(R.id.snipers_guns).setOnClickListener(this);
        view.findViewById(R.id.gallery).setOnClickListener(this);


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
    public void onClick(View v) {
        Fragment fragment;
        String title;
        switch (v.getId()) {
            case R.id.biography: {
                title = "Биография Людмилы Павличенко";
                fragment = BiographyFragment.newInstance(getResources().getString(R.string.biography_text), title);
            }
            break;
            case R.id.snipers: {
                title = "История снайперсокого движения";
                fragment = SnipersFragment.newInstance();
            }
            break;
            case R.id.snipers_guns: {
                title = "Снайперские винтовки";
                fragment =  SniperGunFragment.newInstance();
            }
            break;
            default: {
                title = "Исторические фото";
                fragment = PhotoFragment.newInstance();
            }
        }
        replaceFragment(fragment, title);

    }


    private void replaceFragment(Fragment fragment, String title) {
        ((DrawerLayout)(getActivity()).findViewById(R.id.drawer_layout))
                .setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.addToBackStack(null);
        transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left,
                R.anim.slide_in_left, R.anim.slide_out_right);
        transaction.setBreadCrumbTitle(title);
        transaction.replace(R.id.container, fragment)
                .commit();

    }
}
