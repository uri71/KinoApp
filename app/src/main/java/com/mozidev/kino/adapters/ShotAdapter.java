package com.mozidev.kino.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.mozidev.kino.R;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

/**
 * Created by y.storchak on 22.12.14.
 */
public class ShotAdapter extends RecyclerView.Adapter<ShotAdapter.ViewHolder> {

    private List<Integer> mImageResource;
    private Context mContext;
    private AdapterView.OnItemClickListener mOnItemClickListener;


    public ShotAdapter() {
        super();
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
                R.drawable.trailer1, R.drawable.trailer2);

        return images;
    }


    @Override
    public ShotAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_shot, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(mContext).load(mImageResource.get(position)).into(holder.image);
    }


    @Override
    public int getItemCount() {
        return mImageResource.size();
    }


    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }


    public void onHolderItemClick(ViewHolder holder) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(null, holder.itemView, holder.getPosition(), holder.getItemId());
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView image;


        public ViewHolder(View view) {
            super(view);
            this.image = (ImageView) view.findViewById(R.id.iv_shot);
            view.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            onHolderItemClick(this);
        }
    }
}
