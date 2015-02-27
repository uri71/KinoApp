package com.mozidev.kino.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.mozidev.kino.R;
import com.squareup.picasso.Picasso;

/**
 * Created by y.storchak on 22.12.14.
 */
public class ShotAdapter extends RecyclerView.Adapter<ShotAdapter.ViewHolder> {

    private String[] mShotUrls;
    private Context mContext;
    private AdapterView.OnItemClickListener mOnItemClickListener;


    public ShotAdapter(Context context) {
        super();
        mContext = context;
        mShotUrls = mContext.getResources().getStringArray(R.array.shot_urls);
    }


    @Override
    public ShotAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_shot, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        Picasso.with(mContext).load(mShotUrls[position]).fit().centerCrop().into(holder.image);
    }


    @Override
    public int getItemCount() {
        return mShotUrls.length;
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
