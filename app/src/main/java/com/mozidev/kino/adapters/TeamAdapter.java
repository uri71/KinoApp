package com.mozidev.kino.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.mozidev.kino.R;
import com.mozidev.kino.model.Team;
import com.mozidev.kino.util.RippleDrawable;
import com.norbsoft.typefacehelper.TypefaceHelper;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by y.storchak on 23.12.14.
 */
public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {

    private Context mContext;
    private List<Team> mTeam;
    private AdapterView.OnItemClickListener mOnItemClickListener;


    public TeamAdapter(Context context, List<Team> team) {
        mContext = context;
        mTeam = team;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_list_team, parent, false);
        RippleDrawable.createRipple(view, Color.parseColor("#ffffff"));
        TypefaceHelper.typeface(view);
        return new ViewHolder(this, view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(mContext).load(mTeam.get(position).image).fit().centerCrop().into(holder.image);
        holder.name.setText(mTeam.get(position).name);
        holder.lines.setText(mTeam.get(position).line);
    }


    @Override
    public int getItemCount() {
        return mTeam.size();
    }


    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }


    private void onHolderClick(ViewHolder viewHolder) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(null, viewHolder.itemView, viewHolder.getPosition(), viewHolder.getItemId());
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView image;
        public TextView name;
        public TextView lines;
        private TeamAdapter adapter;


        public ViewHolder(TeamAdapter adapter, View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.iv_team);
            name = (TextView) itemView.findViewById(R.id.content);
            lines = (TextView) itemView.findViewById(R.id.tv_lines);
            itemView.setOnClickListener(this);
            this.adapter = adapter;
        }


        @Override
        public void onClick(View v) {
            adapter.onHolderClick(this);

        }
    }


}
