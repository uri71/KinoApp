package com.mozidev.kino.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.mozidev.kino.R;
import com.mozidev.kino.activity.BigShotActivity;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BigShotFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BigShotFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private String shot;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment BlankFragment.
     */
    public static BigShotFragment newInstance(String param1) {
        BigShotFragment fragment = new BigShotFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }


    public BigShotFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            shot = (String) getArguments().getSerializable(ARG_PARAM1);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shot_pager, container, false);

        final ImageView imageView = (ImageView) view.findViewById(R.id.iv_shot);
        final BigShotActivity activity = (BigShotActivity) getActivity();
        if (activity.mImageWidth == 0 || activity.mImageHeight == 0) {
            final ViewTreeObserver observer = imageView.getViewTreeObserver();
            observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    activity.mImageWidth = imageView.getWidth();
                    activity.mImageHeight = imageView.getHeight();
                    setImage(imageView);
                    imageView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            });
        } else {
            setImage(imageView);
        }
        return view;
    }


    private void setImage( ImageView imageView) {
        int width = ((BigShotActivity) getActivity()).mImageWidth;
        int height = ((BigShotActivity) getActivity()).mImageHeight;
        Picasso.with(getActivity()).load(shot).resize(width, height).centerInside().into(imageView);
    }
}
