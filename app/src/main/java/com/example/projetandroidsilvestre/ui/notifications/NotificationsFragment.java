package com.example.projetandroidsilvestre.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetandroidsilvestre.R;
import com.example.projetandroidsilvestre.model.PicAnnotation;
import com.example.projetandroidsilvestre.model.Picture;
import com.example.projetandroidsilvestre.ui.dashboard.DashboardViewModel;
import com.example.projetandroidsilvestre.ui.dashboard.DashboardViewModelFactory;

import java.util.List;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;

    private SearchView searchingEventView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        NotificationViewModelFactory factory = new NotificationViewModelFactory(this.getActivity().getApplication());
        notificationsViewModel = ViewModelProviders.of(this, factory).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_research, container, false);

        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recyclerviewEvent);
        final EventListAdapter eventAdapter = new EventListAdapter(this.getContext());
        recyclerView.setAdapter(eventAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        notificationsViewModel.getAllPictures().observe(this, new Observer<List<Picture>>() {
            @Override
            public void onChanged(@Nullable final List<Picture> pictures) {

                eventAdapter.setPictures(pictures);
            }
        });

        /*notificationsViewModel.getAllPicAnot().observe(this, new Observer<List<PicAnnotation>>() {
            @Override
            public void onChanged(@Nullable final List<PicAnnotation> picAnots) {

                eventAdapter.setPicAnot(picAnots);
            }
        });*/

        searchingEventView = (SearchView) root.findViewById(R.id.searchingEventView);

        //final TextView textView = root.findViewById(R.id.text_notifications);
        /*notificationsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });*/
        return root;
    }
}