package com.example.projetandroidsilvestre.model;/*package com.example.projetandroidsilvestre.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projetandroidsilvestre.R;

import java.util.List;

public class PictureListAdapter extends RecyclerView.Adapter<PictureListAdapter.PictureViewHolder> {

    class PictureViewHolder extends RecyclerView.ViewHolder {
        private final TextView pictureItemView;

        private PictureViewHolder(View itemView) {
            super(itemView);
            pictureItemView = itemView.findViewById(R.id.textView);//TODO verfiier textview
        }
    }

    private final LayoutInflater mInflater;
    private List<Picture> mPictures;

    PictureListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public PictureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new PictureViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PictureViewHolder holder, int position) {
        if (mPictures != null) {
            Picture current = mPictures.get(position);
            holder.pictureItemView.setText(current.getPicture().toString());
        } else {
            // Covers the case of data not being ready yet.
            holder.pictureItemView.setText("no users");
        }
    }

    void setPictures(List<Picture> picture){
        mPictures = picture;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mPictures != null)
            return mPictures.size();
        else return 0;
    }
}*/