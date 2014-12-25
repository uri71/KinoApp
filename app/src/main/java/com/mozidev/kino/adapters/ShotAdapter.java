package com.mozidev.kino.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.mozidev.kino.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by y.storchak on 22.12.14.
 */
public class ShotAdapter extends BaseAdapter {

    private Context mContext;
    private List<Integer> mImageResource;


    public ShotAdapter(Context context) {
        super();
        mContext = context;
        mImageResource = setImageList();
    }


    private List<Integer> setImageList() {
        List images = Arrays.asList(
                R.drawable.sm_1, R.drawable.sm_2,
                R.drawable.sm_3, R.drawable.sm_4,
                R.drawable.sm_5, R.drawable.sm_6,
                R.drawable.sm_7, R.drawable.sm_8,
                R.drawable.sm_9, R.drawable.sm_10,
                R.drawable.sm_11, R.drawable.sm_12,
                R.drawable.sm_13, R.drawable.sm_14,
                R.drawable.sm_15, R.drawable.sm_16,
                R.drawable.trailer1, R.drawable.trailer2 );

        //List images = Arrays.asList(mContext.getResources().getIntArray(R.array.small_shot));
        return images;
    }


    @Override
    public int getCount() {
        return mImageResource.size();
    }


    @Override
    public Object getItem(int position) {
        return mImageResource.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.card_shot, parent, false);
        }
        ((ImageView) view.findViewById(R.id.iv_shot)).setImageResource(mImageResource.get(position));
        return view;
    }
}
