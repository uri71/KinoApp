package com.mozidev.kino.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mozidev.kino.R;
import com.mozidev.kino.model.Team;

import java.util.Arrays;
import java.util.List;

/**
 * Created by y.storchak on 23.12.14.
 */
public class TeamAdapter extends BaseAdapter {


    private Context mContext;
    private List<Team> mTeam;


    public TeamAdapter(Context context, List <Team> team) {
        mContext = context;
        mTeam = team;
    }


    @Override
    public int getCount() {
        return mTeam.size();
    }


    @Override
    public Team getItem(int position) {
        return mTeam.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_list_team, parent, false);
        }
        ((ImageView) view.findViewById(R.id.iv_team)).setImageResource(mTeam.get(position).image);
        ((TextView) view.findViewById(R.id.tv_name)).setText(mTeam.get(position).name);
        ((TextView) view.findViewById(R.id.tv_lines)).setText(mTeam.get(position).line);
        return view;
    }
}
