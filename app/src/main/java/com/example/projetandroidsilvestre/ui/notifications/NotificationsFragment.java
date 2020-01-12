package com.example.projetandroidsilvestre.ui.notifications;

import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import com.example.projetandroidsilvestre.model.ContactAnnotation;
import com.example.projetandroidsilvestre.model.EventAnnotation;
import com.example.projetandroidsilvestre.model.PicAnnotation;
import com.example.projetandroidsilvestre.model.Picture;
import com.example.projetandroidsilvestre.ui.dashboard.DashboardViewModel;
import com.example.projetandroidsilvestre.ui.dashboard.DashboardViewModelFactory;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;

    private SearchView searchingEventView;
    private EditText testEventEditText;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        NotificationViewModelFactory factory = new NotificationViewModelFactory(this.getActivity().getApplication());
        notificationsViewModel = ViewModelProviders.of(this, factory).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_research, container, false);

        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recyclerviewEvent);
        final EventListAdapter eventAdapter = new EventListAdapter(this.getContext());
        recyclerView.setAdapter(eventAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        testEventEditText = (EditText) root.findViewById(R.id.editTextTestEvent);

        testEventEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                List<Uri> l = notificationsViewModel.getAllEventsFromAGivenPicture(Uri.parse(editable.toString())); //exemple de fonctionnement pour récupérer tout les évènements de la photo passée en paramètre
                eventAdapter.setUri(l);
            }
        });

        notificationsViewModel.getAllEventAnnotations().observe(this, new Observer<List<EventAnnotation>>() {
            @Override
            public void onChanged(@Nullable final List<EventAnnotation> events) {   //ne pas supprimer, est nécessaire
            }
        });

        notificationsViewModel.getAllContactAnnotations().observe(this, new Observer<List<ContactAnnotation>>() {
            @Override
            public void onChanged(@Nullable final List<ContactAnnotation> events) {   //ne pas supprimer, est nécessaire
            }
        });

        return root;
    }
}