package com.mozidev.kino.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.mozidev.kino.R;

import java.util.List;

/**
 * Created by y.storchak on 13.02.15.
 */
public class TrailerAdapter extends ArrayAdapter {
    List <Integer> mList;
    int mResource;
    Context mContext;

    public TrailerAdapter(Context context, int resource, List<Integer> list) {
        super(context, resource, list);
        mList = list;
        mResource = resource;
        mContext = context;
    }


    @Override
    public int getCount() {
        return mList.size();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view =  convertView;
        if(view == null) view = LayoutInflater.from(mContext).inflate(mResource, parent, false);
        ((ImageView)view.findViewById(R.id.trailer)).setImageResource(mList.get(position));

        return view;
    }
}
