package com.mozidev.kino.adapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.mozidev.kino.R;

/**
 * Created by y.storchak on 24.12.14.
 */
public class AboutAdapter extends ArrayAdapter {
    private Context mContext;
    private int mResource;
    private String [] mName;
    private String [] mData;

    public AboutAdapter(Context context, int resource) {
        super(context, resource);
        mContext = context;
        mResource = resource;
        mName = context.getResources().getStringArray(R.array.about_title);
        mData = context.getResources().getStringArray(R.array.about_data);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null){
            view = LayoutInflater.from(mContext).inflate(mResource, parent, false);
        }
        ((TextView)view.findViewById(R.id.tv_name)).setText(mName[position]);
        ((TextView)view.findViewById(R.id.tv_data)).setText(mData[position]);
        return view;

    }


    @Override
    public int getCount() {
        return mName.length;
    }
}
