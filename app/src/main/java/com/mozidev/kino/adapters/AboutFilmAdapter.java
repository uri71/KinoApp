package com.mozidev.kino.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mozidev.kino.Constants;
import com.mozidev.kino.R;
import com.mozidev.kino.model.Item;
import com.norbsoft.typefacehelper.TypefaceHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by y.storchak on 24.12.14.
 */
public class AboutFilmAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Item> mItems = new ArrayList<>();
    private Context mContext;


    public AboutFilmAdapter(Context context, List<Item> items) {
        mItems = items;
        mContext = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = null;
        RecyclerView.ViewHolder holder = null;

        switch (i) {
            case Constants.TYPE_TITLE: {
                view = inflater.inflate(R.layout.about_title, viewGroup, false);
                break;
            }
            case Constants.TYPE_STORY: {
                view = inflater.inflate(R.layout.about_story, viewGroup, false);
                break;
            }
            case Constants.TYPE_ITEM: {
                view = inflater.inflate(R.layout.about_item, viewGroup, false);
                break;
            }
        }
        TypefaceHelper.typeface(view);
        holder = new ViewHolderItem(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        switch (getItemViewType(i)) {
            case Constants.TYPE_TITLE: {
                ((ViewHolderItem) viewHolder).mName.setText(mItems.get(i).name);
                break;
            }
            case Constants.TYPE_STORY: {
                ((ViewHolderItem) viewHolder).mName.setText(mItems.get(i).name);
                break;
            }
            default: {

                if (mItems.get(i).name != null) {
                    ((ViewHolderItem) viewHolder).mName.setText(mItems.get(i).name);
                }
                if (mItems.get(i).data != null) {
                    ((ViewHolderItem) viewHolder).mData.setText(mItems.get(i).data);
                }
                break;
            }
        }
    }


    @Override
    public int getItemViewType(int position) {
        int type = mItems.get(position).type;
        return type;
    }


    @Override
    public int getItemCount() {
        return mItems.size();
    }


    public class ViewHolderItem extends RecyclerView.ViewHolder {

        public TextView mName;
        public TextView mData;


        public ViewHolderItem(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.content);
            mData = (TextView) itemView.findViewById(R.id.tv_data);
        }
    }
}
