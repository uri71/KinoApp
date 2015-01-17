package com.mozidev.kino.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mozidev.kino.Constants;
import com.mozidev.kino.R;
import com.mozidev.kino.model.Sniper;

import java.util.List;

/**
 * Created by y.storchak on 17.01.15.
 */
public class SniperAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<Sniper> mList;


    public SniperAdapter(Context context, List<Sniper> list) {
        mContext = context;
        mList = list;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view;
        if (viewType == Constants.TYPE_TITLE) {
            view = inflater.inflate(R.layout.item_snipers_about, parent, false);
            holder = new TextViewHolder(view);
        } else {
            view = inflater.inflate(R.layout.item_snipers_list, parent, false);
            holder = new ListViewHolder(view);
        }
        return holder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case Constants.TYPE_ITEM: {
                ListViewHolder viewHolder = (ListViewHolder) holder;
                viewHolder.photo.setImageResource(mList.get(position - 1).photo);
                viewHolder.name.setText(mList.get(position - 1).name);
                viewHolder.result.setText(mList.get(position - 1).result);
                if (mList.get(position - 1).about != null) {
                    viewHolder.about.setText(mList.get(position - 1).about);
                }
            }
        }
    }


    @Override
    public int getItemCount() {
        return mList.size()+1;
    }


    @Override
    public int getItemViewType(int position) {
        int type;
        type = (position == 0 ? Constants.TYPE_TITLE : Constants.TYPE_ITEM);
        return type;
    }


    public class TextViewHolder extends RecyclerView.ViewHolder {

        public TextViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        public ImageView photo;
        public TextView name;
        public TextView result;
        public TextView about;


        public ListViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            result = (TextView) itemView.findViewById(R.id.result);
            about = (TextView) itemView.findViewById(R.id.about);
            photo = (ImageView) itemView.findViewById(R.id.photo);
        }
    }

}
