package com.example.projetandroidsilvestre.ui.notifications;

import android.content.Context;
import android.net.Uri;
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
    private List<Uri> myUri;
    //private List<PicAnnotation> mPicAnot;

    EventListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View eventView = mInflater.inflate(R.layout.recyclerview_event, parent, false);
        return new EventViewHolder(eventView);
    }

    public void onBindViewHolder(EventViewHolder holder, int position) {
        if (myUri != null) {
            Uri current = myUri.get(position);
            holder.eventItemView.setText(current.toString());
        } else {
            // Covers the case of data not being ready yet.
            holder.eventItemView.setText("no Uri");
        }
    }

    void setUri(List<Uri> uri){
        myUri = uri;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (myUri != null)
            return myUri.size();
        else return 0;
    }

}