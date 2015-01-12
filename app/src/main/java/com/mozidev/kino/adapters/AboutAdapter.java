package com.mozidev.kino.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mozidev.kino.R;
import com.mozidev.kino.model.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by y.storchak on 24.12.14.
 */
public class AboutAdapter extends RecyclerView.Adapter<AboutAdapter.ViewHolder> {

    private List<Item> mItems = new ArrayList<>();


    public AboutAdapter(List<Item> items) {
        mItems = items;

    }


    @Override
    public AboutAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view= inflater.inflate(R.layout.item_list_about, viewGroup, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(AboutAdapter.ViewHolder viewHolder, int i) {
        if(mItems.get(i).name !=null) viewHolder.mName.setText(mItems.get(i).name);
        if(mItems.get(i).data !=null) viewHolder.mData.setText(mItems.get(i).data);
    }


    @Override
    public int getItemCount() {
        return mItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mName;
        public TextView mData;


        public ViewHolder(View itemView) {
            super(itemView);
            mName = (TextView)itemView.findViewById(R.id.tv_name);
            mData = (TextView)itemView.findViewById(R.id.tv_data);
        }
    }
}
