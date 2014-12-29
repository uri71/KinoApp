package com.mozidev.kino.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mozidev.kino.Constants;
import com.mozidev.kino.R;

import java.util.List;

/**
 * Created by y.storchak on 19.12.14.
 */
public class DrawerAdapter extends ArrayAdapter {


    private List<String> mTitle;
    private Context mContext;
    private int mResourse;


    public DrawerAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        mTitle = objects;
        mContext = context;
    }


    @Override
    public int getCount() {
        return mTitle.size();
    }


    @Override
    public int getPosition(Object item) {
        return mTitle.indexOf(item);
    }


    @Override
    public String getItem(int position) {
        return position == 0 || position == Constants.DRAWER_TOP_ITEM_COUNT ? null : mTitle.get(position);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {

            if (position == 0 || position == Constants.DRAWER_TOP_ITEM_COUNT) {
                mResourse = R.layout.drawer_title;
            } else {
                mResourse = R.layout.item_drawer_top;
            }
            view = LayoutInflater.from(mContext).inflate(mResourse, parent, false);
        }

        if (position == 0 || position == Constants.DRAWER_TOP_ITEM_COUNT) {
            view.setBackgroundResource(R.color.transparent);
        } else {
            view.setBackgroundResource(R.color.background_menu_transparent);
        }
        String title = mTitle.get(position);
        TextView textView = ((TextView) view.findViewById(R.id.text));
        textView.setText(title);
        textView.setTypeface(Typeface.create("sans-serif", Typeface.BOLD));
        return view;
    }
}
