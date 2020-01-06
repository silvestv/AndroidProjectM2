package com.example.projetandroidsilvestre.ui.dashboard;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetandroidsilvestre.MainActivity;
import com.example.projetandroidsilvestre.R;

import static android.app.Activity.RESULT_OK;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private Button chooseImgBtn;
    private Button chooseEventBtn;
    private Button chooseContactBtn;
    private static int RESULT_LOAD_IMAGE = 1;
    private static int RESULT_LOAD_CONTACT =1001;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
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
        final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Context applicationContext = MainActivity.getContextOfApplication();
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            System.out.println("ICIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView avatarChosen = (ImageView) root.findViewById(R.id.avatarChosen);

            avatarChosen.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        } else if (requestCode == RESULT_LOAD_CONTACT && resultCode == RESULT_OK && null != data) {
            System.out.println("LAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            try{
                String contactNumber = null;
                String contactName = null;
                Uri selectedContact = data.getData();
                Cursor cursor = getActivity().getContentResolver().query(selectedContact, null, null, null, null);
                cursor.moveToFirst();
                int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                int nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                contactNumber = cursor.getString(phoneIndex);
                contactName = cursor.getString(nameIndex);
                TextView nameContactView = (TextView) root.findViewById(R.id.tvContactName);
                TextView numberContactView = (TextView) root.findViewById(R.id.tvContactNumber);
                nameContactView.setText("Contact Name : ".concat(contactName));
                numberContactView.setText("Contact Number : ".concat(contactNumber));
            } catch (Exception e)  {
                e.printStackTrace();
            }

        }
    }

/*    package fr.uga.miashs.chooseevent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.selection.OnItemActivatedListener;
import androidx.recyclerview.selection.SelectionPredicates;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.selection.StableIdKeyProvider;
import androidx.recyclerview.selection.StorageStrategy;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.CalendarView;
import android.widget.Toast;

    public class ChooseEvent extends AppCompatActivity implements CalendarView.OnDateChangeListener, OnItemActivatedListener<Long> {

        private final static int MY_PERMISSIONS_REQUEST_READ_CALENDARS=0;
        private EventListAdapter adapter;

        private boolean readCalendarAuthorized=false;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_choose_event);

            CalendarView calendarView = findViewById(R.id.calendarView);
            calendarView.setOnDateChangeListener(this);

            RecyclerView recyclerView = findViewById(R.id.listEvent);

            // create the adapter and bind it to the recycler view
            adapter = new EventListAdapter(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            SelectionTracker tracker = new SelectionTracker.Builder<>(
                    "my-selection-id",
                    recyclerView,
                    new StableIdKeyProvider(recyclerView),
                    new EventListAdapter.EventDetailsLookup(recyclerView),
                    StorageStrategy.createLongStorage())
                    .withSelectionPredicate(SelectionPredicates.<Long>createSelectSingleAnything())
                    .withOnItemActivatedListener(this)
                    .build();


        }
        // called when an event item has been selected
        @Override
        public boolean onItemActivated(@NonNull ItemDetailsLookup.ItemDetails<Long> item, @NonNull MotionEvent e) {

            Log.v("ITEM SELECTED", "event id : "+ item.getSelectionKey());

            Uri selectedEvent = Uri.withAppendedPath(CalendarContract.Events.CONTENT_URI, item.getSelectionKey().toString());

            Intent res = new Intent();
            res.setData(selectedEvent);
            this.setResult(Activity.RESULT_OK, res);
            this.finish();

            Toast.makeText(this,"event uri : "+ selectedEvent, Toast.LENGTH_LONG).show();
            return false;
        }

        @Override
        public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

            Toast.makeText(this.getApplicationContext(), "Date : "+dayOfMonth+"/"+(month+1)+"/"+year, Toast.LENGTH_SHORT).show();

            checkCalendarReadPermission();
            if (readCalendarAuthorized) {
                adapter.setDate(year, month, dayOfMonth);
            }
        }

        public void checkCalendarReadPermission() {
            // Here, thisActivity is the current activity
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_CALENDAR)
                    != PackageManager.PERMISSION_GRANTED) {

                // Permission is not granted
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.READ_CALENDAR)) {
                    // Show an explanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.
                } else {
                    // No explanation needed; request the permission
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.READ_CALENDAR},
                            MY_PERMISSIONS_REQUEST_READ_CALENDARS);

                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                }
            } else {
                // Permission has already been granted
                readCalendarAuthorized=true;
            }
        }


        @Override
        public void onRequestPermissionsResult(int requestCode,
                                               String[] permissions, int[] grantResults) {
            switch (requestCode) {
                case MY_PERMISSIONS_REQUEST_READ_CALENDARS: {
                    // If request is cancelled, the result arrays are empty.
                    if (grantResults.length > 0
                            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        // permission was granted, yay! Do the
                        // contacts-related task you need to do.
                        readCalendarAuthorized=true;
                    } else {
                        // permission denied, boo! Disable the
                        // functionality that depends on this permission.
                        readCalendarAuthorized=false;
                    }
                    return;
                }

                // other 'case' lines to check for other
                // permissions this app might request.
            }
        }


    }*/


}