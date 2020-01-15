package com.example.projetandroidsilvestre.ui.annotation;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
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
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetandroidsilvestre.ChooseEvent;
import com.example.projetandroidsilvestre.R;
import com.example.projetandroidsilvestre.model.ContactAnnotation;
import com.example.projetandroidsilvestre.model.EventAnnotation;
import com.example.projetandroidsilvestre.model.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class AnnotationFragment extends Fragment {

    private AnnotationViewModel dashboardViewModel;

    private Button chooseImgBtn;
    private Uri selectedImgUri;

    private Button chooseContactBtn;
    private RecyclerView recyclerViewContact;
    private ItemSelectedListAdapter adapterContact;
    private RecyclerView.LayoutManager layoutManagerContact;
    private ArrayList<String> selectedContactsData = new ArrayList<String>();
    private List<Uri> selectedContactsUri = new ArrayList<>();

    private Button chooseEventBtn;
    private RecyclerView recyclerViewEvent;
    private ItemSelectedListAdapter adapterEvent;
    private RecyclerView.LayoutManager layoutManagerEvent;
    private ArrayList<String> selectedEventsData = new ArrayList<String>();
    private List<Uri> selectedEventsUri = new ArrayList<>();

    private Repository rep ;
    private Button deleteItemBtn;
    private Button saveAnnotDbButton ;

    private static int RESULT_LOAD_IMAGE = 1;
    private static int RESULT_LOAD_CONTACT =1001;
    private static int RESULT_LOAD_EVENT = 2;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        AnnotationViewModelFactory factory = new AnnotationViewModelFactory(this.getActivity().getApplication());
        dashboardViewModel = ViewModelProviders.of(this, factory).get(AnnotationViewModel.class);
        root = inflater.inflate(R.layout.fragment_annotations, container, false);
        chooseImgBtn = (Button) root.findViewById(R.id.chooseImg);
        chooseImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) !=
                    PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, RESULT_LOAD_IMAGE);
                    } else {
                        Intent imagePicker = new Intent(
                                Intent.ACTION_PICK,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(imagePicker, RESULT_LOAD_IMAGE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        chooseContactBtn = (Button) root.findViewById(R.id.chooseContact);
        chooseContactBtn.setOnClickListener(new View.OnClickListener() {
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
        chooseEventBtn = (Button) root.findViewById(R.id.chooseEvent);
        chooseEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CALENDAR) !=
                            PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.READ_CALENDAR, Manifest.permission.READ_CALENDAR}, RESULT_LOAD_EVENT);
                    } else {
                        Intent eventPicker = new Intent(getActivity(),ChooseEvent.class);
                        startActivityForResult(eventPicker, RESULT_LOAD_EVENT);
                        deleteItemBtn = (Button) root.findViewById(R.id.deleteItem);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        saveAnnotDbButton = (Button) root.findViewById(R.id.saveAnnotDbBtn);
        saveAnnotDbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((selectedEventsUri!=null)&&(selectedImgUri!=null)){
                    Iterator<Uri> it = selectedEventsUri.iterator();
                    List<EventAnnotation> li = new LinkedList<EventAnnotation>();
                    while(it.hasNext()){
                        Uri eventUri = it.next();
                        EventAnnotation ev = new EventAnnotation(new EventAnnotation.Key(selectedImgUri, eventUri));
                        li.add(ev);
                    }
                    dashboardViewModel.insertEventAnnotation(li);
                }
                if((selectedContactsUri!=null)&&(selectedImgUri!=null)){
                    Iterator<Uri> it = selectedContactsUri.iterator();
                    List<ContactAnnotation> li = new LinkedList<ContactAnnotation>();
                    while(it.hasNext()){
                        Uri contactUri = it.next();
                        ContactAnnotation ev = new ContactAnnotation(new ContactAnnotation.Key(selectedImgUri, contactUri));
                        li.add(ev);
                    }
                    dashboardViewModel.insertContactAnnotation(li);
                }
            }
        });

        recyclerViewEvent = (RecyclerView) root.findViewById(R.id.listSelectEventRcl);
        recyclerViewEvent.setHasFixedSize(true);
        layoutManagerEvent = new LinearLayoutManager(getActivity());
        recyclerViewEvent.setLayoutManager(layoutManagerEvent);
        adapterEvent = new ItemSelectedListAdapter(new ArrayList<String>(), getActivity());
        recyclerViewEvent.setAdapter(adapterEvent);

        adapterEvent.addRemoveContactListener(new RemoveEventListener() {
            @Override
            public void deleteEvent(int position) {
            }
        });


        recyclerViewContact = (RecyclerView) root.findViewById(R.id.listSelectedContactRcl);
        recyclerViewContact.setHasFixedSize(true);
        layoutManagerContact = new LinearLayoutManager(getActivity());
        recyclerViewContact.setLayoutManager(layoutManagerContact);
        adapterContact = new ItemSelectedListAdapter(new ArrayList<String>(), getActivity());
        recyclerViewContact.setAdapter(adapterContact);

        adapterContact.addRemoveContactListener(new RemoveEventListener() {
            @Override
            public void deleteEvent(int position) {
            }
        });

        return root;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Context applicationContext = MainActivity.getContextOfApplication();
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            selectedImgUri = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getActivity().getContentResolver().query(selectedImgUri,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            System.out.println("PPPPPPP : "+picturePath);
            cursor.close();

            ImageView avatarChosen = (ImageView) root.findViewById(R.id.avatarChosen);

            avatarChosen.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        } else if (requestCode == RESULT_LOAD_CONTACT && resultCode == RESULT_OK && null != data) {
            try{
                String contactNumber = null;
                String contactName = null;
                Uri selectedContact = data.getData();
                selectedContactsUri.add(selectedContact);
                Cursor cursor = getActivity().getContentResolver().query(selectedContact, null, null, null, null);
                cursor.moveToFirst();
                int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                int nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                contactNumber = cursor.getString(phoneIndex);
                contactName = cursor.getString(nameIndex);
                cursor.close();
                String contactResult = "Name : "+contactName+" ----- "+"Number : "+contactNumber;
                selectedContactsData.add(contactResult);
                adapterContact.setData(selectedContactsData);
                adapterContact.notifyDataSetChanged();

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
            adapterEvent.setData(selectedEventsData);
            adapterEvent.notifyDataSetChanged();
        }
    }


}