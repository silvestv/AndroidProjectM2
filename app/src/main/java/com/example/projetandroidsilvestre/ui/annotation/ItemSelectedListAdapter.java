package com.example.projetandroidsilvestre.ui.annotation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projetandroidsilvestre.R;

import java.util.ArrayList;
import java.util.List;


public class ItemSelectedListAdapter extends RecyclerView.Adapter<ItemSelectedListAdapter.ItemSelectedViewHolder> {
    private ArrayList<String> mDataset;
    private Context mContext;
    private List<RemoveEventListener> removeItemListeners;
    private final LayoutInflater mInflater;
   // private int pos;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ItemSelectedViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public Button deleteItemBtn;
        public ItemSelectedViewHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.itemSelectedTv);
            deleteItemBtn = v.findViewById(R.id.deleteItem);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ItemSelectedListAdapter(ArrayList<String> myDataset, Context context) {
        mDataset = myDataset;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ItemSelectedListAdapter.ItemSelectedViewHolder onCreateViewHolder(ViewGroup parent,
                                                                                int viewType) {
        // create a new view
        //TextView itemView = (TextView)mInflater.inflate(R.layout.recyclerview_item, parent, false);
        View v = mInflater
                .inflate(R.layout.recyclerview_itemselected, parent, false);
        ItemSelectedViewHolder vh = new ItemSelectedViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ItemSelectedViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.textView.setText(mDataset.get(position));
        holder.deleteItemBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mDataset.remove(position);
                notifyDataSetChanged();
                notifyDeleted(position);
            }
        });
    }

    public void notifyDeleted(int pos) {
        for(int i = 0; i < removeItemListeners.size(); i++){
            removeItemListeners.get(i).deleteEvent(pos);
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    public void addRemoveContactListener(RemoveEventListener Eventlistener) {
        if (removeItemListeners==null) {
            removeItemListeners=new ArrayList<>();
        }
        removeItemListeners.add(Eventlistener);
    }

    public void setData(ArrayList<String> dataSet) {
        this.mDataset = dataSet;
    }

}
