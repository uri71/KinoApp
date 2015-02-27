package com.mozidev.kino.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.mozidev.kino.Constants;
import com.mozidev.kino.KinoApplication;
import com.mozidev.kino.R;
import com.mozidev.kino.model.Photo;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by y.storchak on 17.01.15.
 */
public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {

    private String [] mPhoto;
    private Context mContext;
    private AdapterView.OnItemClickListener mOnItemClickListener;


    public PhotoAdapter(Context context) {
        super();
        mContext = context;
        mPhoto = mContext.getResources().getStringArray(R.array.history_urls);

    }


    @Override
    public PhotoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_shot, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final PhotoAdapter.ViewHolder holder, final int position) {
        /*final ViewTreeObserver observer = holder.image.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Picasso.with(mContext).load(mPhoto[position]).fit().centerCrop().into(holder.image);
                holder.image.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });*/
        Picasso.with(mContext).load(mPhoto[position]).fit().centerCrop().into(holder.image);
    }

    @Override
    public int getItemCount() {
        return mPhoto.length;
    }


    public void setOnItemClickListener(AdapterView.OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }


    public void onHolderItemClick(ViewHolder holder) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(null, holder.itemView, holder.getPosition(), holder.getItemId());
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView image;


        public ViewHolder(View itemView) {
            super(itemView);
            this.image = (ImageView) itemView.findViewById(R.id.iv_shot);
            image.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            onHolderItemClick(this);
        }
    }
}