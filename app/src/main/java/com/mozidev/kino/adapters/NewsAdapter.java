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
import com.mozidev.kino.fragments.NewsFragment;
import com.mozidev.kino.model.NewsItem;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by y.storchak on 29.01.15.
 */
public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<NewsItem> mItems;
    public Context mContext;
    private AdapterView.OnItemClickListener mListener;


    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        mListener = listener;
    }

    public void onHolderClick(ViewHolder holder){
        mListener.onItemClick(null, holder.itemView, holder.getPosition(), holder.getItemId());
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView title;
        public ImageView photo_1;
        public ImageView photo_2;
        public ImageView photo_3;


        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            photo_1 = (ImageView) itemView.findViewById(R.id.iv_photo_1);
            photo_2 = (ImageView) itemView.findViewById(R.id.iv_photo_2);
            photo_3 = (ImageView) itemView.findViewById(R.id.iv_photo_3);
            itemView.findViewById(R.id.ll_container).setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            onHolderClick(this);
        }
    }


    public NewsAdapter(Context context, List<NewsItem> list) {
        mItems = list;
        mContext = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_news, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        NewsAdapter.ViewHolder holder = (NewsAdapter.ViewHolder)viewHolder;
        Picasso.with(mContext).load(mItems.get(position).photo_1).fit().centerCrop().into(holder.photo_1);
        Picasso.with(mContext).load(mItems.get(position).photo_2).fit().centerCrop().into(holder.photo_2);
        Picasso.with(mContext).load(mItems.get(position).photo_3).fit().centerCrop().into(holder.photo_3);
        holder.title.setText(mItems.get(position).title);
    }


    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
