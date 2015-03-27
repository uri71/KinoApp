package com.mozidev.kino.fragments;


import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mozidev.kino.R;
import com.mozidev.kino.activity.MainActivity;
import com.norbsoft.typefacehelper.TypefaceHelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BiographyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class  BiographyFragment extends Fragment {


    private static final String ARG_TITLE = "param1";
    private static final String ARG_TEXT = "param2";
    private String mTitle;
    private String mText;


    public static BiographyFragment newInstance(String param1, String param2) {
        BiographyFragment fragment = new BiographyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, param2);
        args.putString(ARG_TEXT, param1);
        fragment.setArguments(args);
        return fragment;
    }


    public BiographyFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString(ARG_TITLE);
            mText = getArguments().getString(ARG_TEXT);
        }
        setHasOptionsMenu(true);
        ActionBar actionBar = ((MainActivity)getActivity()).getActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_article, container, false);
    }


    @Override
    public void onViewCreated(View view,
                              @Nullable
                              Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((TextView)view.findViewById(R.id.title)).setText(mTitle);
        ((TextView)view.findViewById(R.id.article)).setText(mText);
        ImageView photo = (ImageView) view.findViewById(R.id.iv_photo);
        photo.setImageResource(R.drawable.article_photo);
        TypefaceHelper.typeface(view);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            getActivity().getSupportFragmentManager().popBackStack();
        }
        return false;
    }
}
