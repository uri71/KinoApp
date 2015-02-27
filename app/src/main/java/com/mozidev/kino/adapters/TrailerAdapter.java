package com.mozidev.kino.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.mozidev.kino.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by y.storchak on 13.02.15.
 */
public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.ViewHolder> {

    List<Integer> mList;
    String[] titles;
    int mResource;
    Context mContext;
    AdapterView.OnItemClickListener mListener;


    public TrailerAdapter(Context context, int resource, List<Integer> list) {
        mList = list;
        mResource = resource;
        mContext = context;
        titles = mContext.getResources().getStringArray(R.array.trailer_title);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(mResource, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(TrailerAdapter.ViewHolder holder, int position) {
        Picasso.with(mContext).load(mList.get(position)).fit().centerCrop().into(holder.image);
        holder.title.setText(titles[position]);
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }


    public void setListener(AdapterView.OnItemClickListener listener) {
        mListener = listener;
    }


    public void onHolderClick(ViewHolder holder) {
        mListener.onItemClick(null, null, holder.getPosition(), holder.getItemId());
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        public TextView title;


        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.iv_trailer);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onHolderClick(ViewHolder.this);
                }
            });
        }
    }
}
