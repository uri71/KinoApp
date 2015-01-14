package com.mozidev.kino.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mozidev.kino.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by y.storchak on 24.12.14.
 */
public class RatingAdapter extends ArrayAdapter {
    Context mContext;
    int mResource;
    List<String> mName;
    List <String> mComment;

    public RatingAdapter(Context context, int resource) {
        super(context, resource);
        mContext = context;
        mResource = resource;
        mName = Arrays.asList(mContext.getResources().getStringArray(R.array.comment_name));
        mComment = Arrays.asList(mContext.getResources().getStringArray(R.array.comment));

    }


    @Override
    public int getCount() {
        return mName.size();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view  = convertView;
        if (view == null){
            view = LayoutInflater.from(mContext).inflate(mResource, parent, false);
        }
        ((TextView)view.findViewById(R.id.content)).setText(mName.get(position));
        ((TextView)view.findViewById(R.id.comment)).setText(mComment.get(position));

        return view;
    }
}
