package com.mozidev.kino.fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.mozidev.kino.activity.BigShotActivity;
import com.mozidev.kino.Constants;
import com.mozidev.kino.activity.MainActivity;
import com.mozidev.kino.activity.PlayerActivity;
import com.mozidev.kino.R;
import com.mozidev.kino.adapters.ShotAdapter;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Use the {@link com.mozidev.kino.fragments.ShotFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShotFragment extends Fragment implements AdapterView.OnItemClickListener{


    public ShotFragment() {
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment StoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShotFragment newInstance(int number) {
        ShotFragment fragment = new ShotFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.ARG_SECTION_NUMBER, number);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shot, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GridView gridView = ((GridView)view.findViewById(R.id.gv_shot));
        gridView.setAdapter(new ShotAdapter(getActivity()));
        gridView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        /*FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left,
                R.anim.slide_in_left, R.anim.slide_out_right);
        fragmentTransaction.replace(R.id.container, BigShotFragment.newInstance(position))
                .commit();*/
        Intent intent;
        if(position > Constants.SHOT_COUNT){
            intent = new Intent (getActivity(), PlayerActivity.class);
        } else {
             intent = new Intent(getActivity(), BigShotActivity.class);

        }
        intent.putExtra(Constants.ARG_SHOT_NUMBER, position);
        startActivity(intent);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(Constants.ARG_SECTION_NUMBER));
    }


}
