package com.mozidev.kino.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mozidev.kino.Constants;
import com.mozidev.kino.R;
import com.mozidev.kino.model.Team;
import com.squareup.picasso.Picasso;

/**
 * Created by y.storchak on 14.01.15.
 */
public class ProfileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context mContext;
    Team mTeam;


    public ProfileAdapter(Context context, Team team) {
        mContext = context;
        mTeam = team;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view;
        if (viewType == Constants.TYPE_TITLE) {
            view = inflater.inflate(R.layout.item_profile_image, parent, false);
            holder = new ImageViewHolder(view);
        } else {
            view = inflater.inflate(R.layout.about_story, parent, false);
            holder = new TextViewHolder(view);
        }
        return holder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case Constants.TYPE_TITLE: {
                ImageViewHolder viewHolder = (ImageViewHolder) holder;
                Picasso.with(mContext).load(mTeam.image).fit().centerCrop().into(viewHolder.image);
                viewHolder.rating.setText(mContext.getString(R.string.rating) + mTeam.rating);
                viewHolder.name.setText(mTeam.name);
            }
            break;
            default: {
                TextViewHolder viewHolder = (TextViewHolder) holder;
                switch (position) {
                    case 1: {
                        viewHolder.textView.setText(mTeam.about);
                    }
                    break;
                    case 2: {
                        viewHolder.textView.setText(mContext.getString(R.string.filmografy));
                        viewHolder.textView.setTypeface(Typeface.create("sans-serif", Typeface.BOLD));
                        viewHolder.textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                    }
                    break;
                    default: {
                        viewHolder.textView.setText(mTeam.films[position - 3]);
                    }

                }
            }
        }
    }


    @Override
    public int getItemCount() {
        return mTeam.films.length + 3;
    }


    @Override
    public int getItemViewType(int position) {
        int type;
        type = position == 0 ? Constants.TYPE_TITLE : Constants.TYPE_ITEM;
        return type;
    }


    public class ImageViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name;
        TextView rating;


        public ImageViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            rating = (TextView) itemView.findViewById(R.id.rating);
            name = (TextView) itemView.findViewById(R.id.name);

        }
    }

    public class TextViewHolder extends RecyclerView.ViewHolder {

        TextView textView;


        public TextViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.content);

        }
    }
}