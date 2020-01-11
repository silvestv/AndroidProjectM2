package com.example.projetandroidsilvestre.ui.dashboard;

import android.app.Application;
import android.content.Context;
import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.projetandroidsilvestre.model.Picture;
import com.example.projetandroidsilvestre.model.Repository;

import java.util.List;


public class DashboardViewModel extends ViewModel {

    public Repository mRepo;
    public MutableLiveData<Uri> pictureLiveDataUri;
    public LiveData<List<Picture>> allPictures;
    private Application mApplication;
    public DashboardViewModel(Application application) {
        super();
        this.mApplication = application;
        pictureLiveDataUri = new MutableLiveData<Uri>();
        mRepo = new Repository(mApplication);
        allPictures = mRepo.getAlphabetizedPictures();
        /*if(mRepo.getAllPictures()!=null){
            System.out.println("nb Pictures in mRepo = "+mRepo.getAllPictures().size());
        }
        else{
            System.out.println("mRepo.getAllPictures()==null");
        }*/

    }

    public void insertPicture(Picture p){
         if(allPictures.getValue()!=null){
             System.out.println("nb Pictures avant insert = "+this.getAllPictures().getValue().size());
        }
        else{
            System.out.println("AllPictures.getValue()==null in insertPicture");
        }

        mRepo.insert(p);
    }

    public LiveData<Uri> getPictureUri() {
        return pictureLiveDataUri;
    }

    public LiveData<List<Picture>> getAllPictures(){
        return this.allPictures;
    }
}