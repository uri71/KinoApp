package com.mozidev.kino.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mozidev.kino.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by y.storchak on 23.12.14.
 */
public class TeamAdapter extends BaseAdapter {
private List<Integer> mImage;
private List<String> mName;
private List<String> mLines;
    private List <String> mUri;
    private Context mContext;

    public TeamAdapter(Context context) {
        mContext = context;
        mImage = Arrays.asList(R.drawable.team1,R.drawable.team2,R.drawable.team3,R.drawable.team4,R.drawable.team5, R.drawable.team6);
        mName = Arrays.asList(mContext.getResources().getStringArray(R.array.team_names));
        mLines = Arrays.asList(mContext.getResources().getStringArray(R.array.team_lines));
        mUri = Arrays.asList(mContext.getResources().getStringArray(R.array.team_links));
    }


    @Override
    public int getCount() {
        return mName.size();
    }


    @Override
    public String getItem(int position) {
        return mUri.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_list_team, parent,false);
        }
        ((ImageView)view.findViewById(R.id.iv_team)).setImageResource(mImage.get(position));
        ((TextView)view.findViewById(R.id.tv_name)).setText(mName.get(position));
        ((TextView)view.findViewById(R.id.tv_lines)).setText(mLines.get(position));
        return view;
    }
}
