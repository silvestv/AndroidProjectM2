package com.example.projetandroidsilvestre.ui.home;

import android.app.Application;
import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.projetandroidsilvestre.model.ContactAnnotation;
import com.example.projetandroidsilvestre.model.EventAnnotation;
import com.example.projetandroidsilvestre.model.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private LiveData<List<Uri>> mAllPicUriAnnotation;
    public Repository mRepo;
    private Application mApplication;

    private LiveData<List<ContactAnnotation>> mAllContactAnnotation;
    private LiveData<List<EventAnnotation>> mAllEventAnnotation;

    public HomeViewModel(Application application) {
        super();
        this.mApplication = application;
        mRepo = new Repository(mApplication);
        mAllPicUriAnnotation = mRepo.getAllPicUriAnnotation();
    }


    ArrayList<Uri> getAllPicturesFromTheDatabase(){
        ArrayList<Uri> result = new ArrayList<Uri>();
        if(this.mAllPicUriAnnotation.getValue()!=null){
            Iterator<Uri> it = this.mAllPicUriAnnotation.getValue().iterator();
            Uri next ;
            while(it.hasNext()){
                next = it.next();
                System.out.println("LAAAAALALALLA : "+next.toString());
                if(!result.contains(next)){
                    result.add(next);
                }

            }
        }
        return result;
    }

    public LiveData<List<Uri>> getPicsUri(){
        //System.out.println("Vecteur size Toutes les images : " + mAllPicUriAnnotation.getValue().size());
        return mAllPicUriAnnotation;
    }
}