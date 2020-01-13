package com.example.projetandroidsilvestre.ui.home;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projetandroidsilvestre.R;
import com.example.projetandroidsilvestre.model.Picture;

import java.util.ArrayList;
import java.util.List;

public class AnnotationListAdapter extends RecyclerView.Adapter<AnnotationListAdapter.AnnotationViewHolder> {

    class AnnotationViewHolder extends RecyclerView.ViewHolder {
        public ImageView annotationImg;
        public AnnotationViewHolder(View itemView) {
            super(itemView);
            annotationImg = itemView.findViewById(R.id.annotationImgView);
        }
    }

    private final LayoutInflater mInflater;
    private Context mContext;
    private List<Picture> mPicturesAnnotations;
    private List<Bitmap> mPicsSet;

    // Provide a suitable constructor (depends on the kind of dataset)
    public AnnotationListAdapter(ArrayList<Bitmap> myPicsSet, Context context) {
        mPicsSet = myPicsSet;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AnnotationListAdapter.AnnotationViewHolder onCreateViewHolder(ViewGroup parent,
                                                                             int viewType) {
        // create a new view
        //TextView itemView = (TextView)mInflater.inflate(R.layout.recyclerview_item, parent, false);
        View v = mInflater
                .inflate(R.layout.recyclerview_item_home, parent, false);
        AnnotationListAdapter.AnnotationViewHolder vh = new AnnotationListAdapter.AnnotationViewHolder(v);
        return vh;
    }
    public void onBindViewHolder(AnnotationListAdapter.AnnotationViewHolder holder, int position) {
        holder.annotationImg.setImageBitmap(mPicsSet.get(position));
    }

    public void setBitmapSet(ArrayList<Bitmap> picsSet) {
        this.mPicsSet = new ArrayList<Bitmap>(picsSet);
    }

    @Override
    public int getItemCount() {
        if (mPicsSet != null)
            return mPicsSet.size();
        else return 0;
    }

}