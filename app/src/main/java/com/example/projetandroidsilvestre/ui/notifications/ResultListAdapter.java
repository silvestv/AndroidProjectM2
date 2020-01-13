package com.example.projetandroidsilvestre.ui.notifications;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projetandroidsilvestre.R;

import java.util.ArrayList;
import java.util.List;

public class ResultListAdapter extends RecyclerView.Adapter<ResultListAdapter.ResultViewHolder> {

    class ResultViewHolder extends RecyclerView.ViewHolder {
        public ImageView resultImg;
        public ResultViewHolder(View itemView) {
            super(itemView);
            resultImg = itemView.findViewById(R.id.annotationImgView);
        }
    }

    private final LayoutInflater mInflater;
    private Context mContext;
    private List<Bitmap> mPicsSet;

    // Provide a suitable constructor (depends on the kind of dataset)
    public ResultListAdapter(ArrayList<Bitmap> myPicsSet, Context context) {
        mPicsSet = myPicsSet;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ResultListAdapter.ResultViewHolder onCreateViewHolder(ViewGroup parent,
                                                                                                                    int viewType) {
        // create a new view
        //TextView itemView = (TextView)mInflater.inflate(R.layout.recyclerview_item, parent, false);
        View v = mInflater
                .inflate(R.layout.recyclerview_item_home, parent, false);
        ResultListAdapter.ResultViewHolder vh = new ResultListAdapter.ResultViewHolder(v);
        return vh;
    }
    public void onBindViewHolder(ResultListAdapter.ResultViewHolder holder, int position) {
        holder.resultImg.setImageBitmap(mPicsSet.get(position));
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