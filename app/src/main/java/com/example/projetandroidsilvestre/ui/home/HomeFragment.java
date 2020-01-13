package com.example.projetandroidsilvestre.ui.home;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetandroidsilvestre.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerViewAnnotationsSaved;
    private AnnotationListAdapter adapterAnnotations;
    private RecyclerView.LayoutManager layoutManagerAnnotations;


    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModelFactory factory = new HomeViewModelFactory(this.getActivity().getApplication());
        homeViewModel = ViewModelProviders.of(this, factory).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //final TextView textView = root.findViewById(R.id.text_home);
        recyclerViewAnnotationsSaved = (RecyclerView) root.findViewById(R.id.listAnnotRcl);
        recyclerViewAnnotationsSaved.setHasFixedSize(true);
        layoutManagerAnnotations = new LinearLayoutManager(getActivity());
        recyclerViewAnnotationsSaved.setLayoutManager(layoutManagerAnnotations);
        adapterAnnotations = new AnnotationListAdapter(new ArrayList<Bitmap>(), getActivity());
        recyclerViewAnnotationsSaved.setAdapter(adapterAnnotations);

        homeViewModel.getPicsUri().observe(this, new Observer<List<Uri>>() {
            @Override
            public void onChanged(List<Uri> allPicsUri) {

             /* ArrayList<Uri> dynamicListPicsUri = new ArrayList<Uri>(homeViewModel.getAllPicturesFromTheDatabase());
                ArrayList<Bitmap> dynamicListPics = new ArrayList<Bitmap>();
                for (Uri u : dynamicListPicsUri){
                    //System.out.println("COUCOUCOUC"+u.toString());
                    dynamicListPics.add(findUriToPictureBitmap(u));
                }
                System.out.println("COUCOUCOUC"+dynamicListPics.size());
                adapterAnnotations.setBitmapSet(dynamicListPics);
                adapterAnnotations.notifyDataSetChanged();*/
            }
        });





        return root;
    }

    public Bitmap findUriToPictureBitmap(Uri annotationPictureUri){

        String[] filePathColumn = { MediaStore.Images.Media.DATA };
        Cursor cursor = getActivity().getContentResolver().query(annotationPictureUri,
                filePathColumn, null, null, null);
        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();
        return BitmapFactory.decodeFile(picturePath);
    }

}