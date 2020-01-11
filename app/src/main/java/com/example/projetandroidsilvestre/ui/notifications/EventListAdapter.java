package com.example.projetandroidsilvestre.ui.notifications;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projetandroidsilvestre.R;
import com.example.projetandroidsilvestre.model.PicAnnotation;
import com.example.projetandroidsilvestre.model.Picture;

import java.util.List;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventViewHolder> {

    class EventViewHolder extends RecyclerView.ViewHolder {
        private final TextView eventItemView;

        private EventViewHolder(View itemView) {
            super(itemView);
            eventItemView = itemView.findViewById(R.id.eventTextView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Picture> mPictures;
    //private List<PicAnnotation> mPicAnot;

    EventListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View eventView = mInflater.inflate(R.layout.recyclerview_event, parent, false);
        return new EventViewHolder(eventView);
    }

    public void onBindViewHolder(EventViewHolder holder, int position) {
        if (mPictures != null) {
            Picture current = mPictures.get(position);
            holder.eventItemView.setText(current.getPicture().toString());  //TODO a changer
        } else {
            // Covers the case of data not being ready yet.
            holder.eventItemView.setText("no pictures");
        }

        /*if (mPicAnot != null) {
            PicAnnotation current = mPicAnot.get(position);
            holder.eventItemView.setText(current.getContactUris().get(0).toString());  //TODO a changer
        } else {
            // Covers the case of data not being ready yet.
            holder.eventItemView.setText("no pictures");
        }*/

    }

     void setPictures(List<Picture> picture){
        mPictures = picture;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mPictures != null)
            return mPictures.size();
        else return 0;
    }

    /*void setPicAnot(List<PicAnnotation> picAnot){
        mPicAnot = picAnot;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mPicAnot != null)
            return mPicAnot.size();
        else return 0;
    }*/

}