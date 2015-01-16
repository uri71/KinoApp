package com.mozidev.kino.fragments;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mozidev.kino.Constants;
import com.mozidev.kino.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BigShotFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BigShotFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private int shot;
    private Bitmap bitmap;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment BlankFragment.
     */
    public static BigShotFragment newInstance(int param1) {
        BigShotFragment fragment = new BigShotFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }


    public BigShotFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            shot = getArguments().getInt(ARG_PARAM1);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shot_pager, container, false);
        bitmap = getBitmap();
        ((ImageView) view.findViewById(R.id.iv_shot)).setImageBitmap(bitmap);
        return view;
    }


    private Bitmap getBitmap() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), shot, options);
        int with = options.outWidth;
        int height = options.outHeight;
        Log.e("BigShotActivity ", " with -" + with + " height - " + height);
        Bitmap bitmap;
        if (with > Constants.IMAGE_MAX_SIZE || height > Constants.IMAGE_MAX_SIZE) {
            int ratio = (int) Math.ceil((with > height ? with / Constants.IMAGE_MAX_SIZE : height / Constants.IMAGE_MAX_SIZE));
            options.inSampleSize = ratio;
        } else {
            options.inScaled = false;
        }
        options.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeResource(getResources(), shot, options);
        return bitmap;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bitmap.recycle();

    }
}
