package com.example.projetandroidsilvestre.ui.notifications;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetandroidsilvestre.ChooseEvent;
import com.example.projetandroidsilvestre.R;
import com.example.projetandroidsilvestre.model.ContactAnnotation;
import com.example.projetandroidsilvestre.model.EventAnnotation;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;

    private Button serachingContactBtn;
    private RecyclerView recyclerViewContactSearch;
    private ItemListAdapter adapterContactSearch;
    private RecyclerView.LayoutManager layoutManagerContactSearch;
    private ArrayList<Uri> selectedContactsUri = new ArrayList<>();
    private ArrayList<String> selectedContactsData = new ArrayList<>();

    private Button searchingEventBtn;
    private RecyclerView recyclerViewEventSearch;
    private ItemListAdapter adapterEventSearch;
    private RecyclerView.LayoutManager layoutManagerEventSearch;
    private ArrayList<Uri> selectedEventsUri = new ArrayList<>();
    private ArrayList<String> selectedEventsData = new ArrayList<>();

    private Button launchSearchBtn;
    private RecyclerView recyclerViewResultSearch;
    private ResultListAdapter adapterResultSearch;
    private RecyclerView.LayoutManager layoutManagerResultSearch;

    private static int RESULT_LOAD_CONTACT =1;
    private static int RESULT_LOAD_EVENT =2;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        NotificationViewModelFactory factory = new NotificationViewModelFactory(this.getActivity().getApplication());
        notificationsViewModel = ViewModelProviders.of(this, factory).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_research, container, false);

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

        notificationsViewModel.getPicsUri().observe(this, new Observer<List<Uri>>() {
            @Override
            public void onChanged(List<Uri> uris) {

            }
        });

        serachingContactBtn = (Button) root.findViewById(R.id.searchingByContactBtn);
        serachingContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CONTACTS) !=
                            PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS}, RESULT_LOAD_CONTACT);
                    } else {
                        Intent contactPicker = new Intent(
                                Intent.ACTION_PICK,
                                ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                        startActivityForResult(contactPicker, RESULT_LOAD_CONTACT);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        recyclerViewContactSearch = (RecyclerView) root.findViewById(R.id.listSelectedContactSearchRcl);
        recyclerViewContactSearch.setHasFixedSize(true);
        layoutManagerContactSearch = new LinearLayoutManager(getActivity());
        recyclerViewContactSearch.setLayoutManager(layoutManagerContactSearch);
        adapterContactSearch = new ItemListAdapter(new ArrayList<String>(), getActivity());
        recyclerViewContactSearch.setAdapter(adapterContactSearch);

        adapterContactSearch.addRemoveContactListener(new RemoveEventSearchListener() {
            @Override
            public void deleteEvent(int position) {
                //selectedContactsData.remove(position);
            }
        });
        searchingEventBtn = (Button) root.findViewById(R.id.searchingByEventBtn);
        searchingEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CALENDAR) !=
                            PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.READ_CALENDAR, Manifest.permission.READ_CALENDAR}, RESULT_LOAD_EVENT);
                    } else {
                        Intent eventPicker = new Intent(getActivity(), ChooseEvent.class);
                        startActivityForResult(eventPicker, RESULT_LOAD_EVENT);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        recyclerViewEventSearch = (RecyclerView) root.findViewById(R.id.listSelectEventSearchRcl);
        recyclerViewEventSearch.setHasFixedSize(true);
        layoutManagerEventSearch = new LinearLayoutManager(getActivity());
        recyclerViewEventSearch.setLayoutManager(layoutManagerEventSearch);
        adapterEventSearch = new ItemListAdapter(new ArrayList<String>(), getActivity());
        recyclerViewEventSearch.setAdapter(adapterEventSearch);

        adapterEventSearch.addRemoveContactListener(new RemoveEventSearchListener() {
            @Override
            public void deleteEvent(int position) {
                //selectedContactsData.remove(position);

            }
        });

        launchSearchBtn = (Button) root.findViewById(R.id.startSearchBtn);

        recyclerViewResultSearch = (RecyclerView) root.findViewById(R.id.recyclerViewResult);
        recyclerViewResultSearch.setHasFixedSize(true);
        layoutManagerResultSearch = new LinearLayoutManager(getActivity());
        recyclerViewResultSearch.setLayoutManager(layoutManagerResultSearch);
        adapterResultSearch = new ResultListAdapter(new ArrayList<Bitmap>(), getActivity());
        recyclerViewResultSearch.setAdapter(adapterResultSearch);

        launchSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CONTACTS) !=
                            PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS}, RESULT_LOAD_CONTACT);
                    } else {
                        //C'EST ICI QUE DOIVENT ETRE TESTER LES FONCTIONS DE RECHERCHE EN PARAMETREE DE l'ARRAYLIST DYNAMICURISPICS !!!
                        ArrayList<Uri> dynamicUrisResult = new ArrayList<Uri>(notificationsViewModel.getAllPicturesFromTheDatabase());
                        System.out.println(dynamicUrisResult.size());
                        ArrayList<Bitmap> dynamicListPicsBitmapResult = new ArrayList<Bitmap>();
                        for (Uri u : dynamicUrisResult){
                            dynamicListPicsBitmapResult.add(findUriToPictureBitmap(u));
                        }
                        adapterResultSearch.setBitmapSet(dynamicListPicsBitmapResult);
                        adapterResultSearch.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return root;
    }

    public Bitmap findUriToPictureBitmap(Uri resultUri){

        String[] filePathColumn = { MediaStore.Images.Media.DATA };
        Cursor cursor = getActivity().getContentResolver().query(resultUri,
                filePathColumn, null, null, null);
        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();
        return BitmapFactory.decodeFile(picturePath);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Context applicationContext = MainActivity.getContextOfApplication();
        if (requestCode == RESULT_LOAD_CONTACT && resultCode == RESULT_OK && null != data) {
            try{
                String contactNumber = null;
                String contactName = null;
                Uri selectedContact = data.getData();
                selectedContactsUri.add(selectedContact);
                //Uri photoUri = Uri.withAppendedPath(selectedContact, ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);
                Cursor cursor = getActivity().getContentResolver().query(selectedContact, null, null, null, null);
                cursor.moveToFirst();
                int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                int nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                //int photoContact = cursor.getColumnIndex(ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);
                contactNumber = cursor.getString(phoneIndex);
                contactName = cursor.getString(nameIndex);
                //String contactPhoto = cursor.getString(photoContact);
                cursor.close();
                String contactResult = "Name : "+contactName+" ----- "+"Number : "+contactNumber;
                selectedContactsData.add(contactResult);
                adapterContactSearch.setData(selectedContactsData);
                adapterContactSearch.notifyDataSetChanged();

            } catch (Exception e)  {
                e.printStackTrace();
            }

        } else if (requestCode == RESULT_LOAD_EVENT  && resultCode == Activity.RESULT_OK && null != data) {
            Uri selectedEventUri = data.getData();
            selectedEventsUri.add(selectedEventUri);
            Cursor cursor = getActivity().getContentResolver().query(selectedEventUri, null, null,null,null);
            cursor.moveToFirst();
            final String se =  cursor.getString(cursor.getColumnIndex(CalendarContract.Events.TITLE));
            selectedEventsData.add(se);
            adapterEventSearch.setData(selectedEventsData);
            adapterEventSearch.notifyDataSetChanged();
        }
    }

}