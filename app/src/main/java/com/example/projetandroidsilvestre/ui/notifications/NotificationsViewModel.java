package com.example.projetandroidsilvestre.ui.notifications;

import android.app.Application;
import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.projetandroidsilvestre.model.PicAnnotation;
import com.example.projetandroidsilvestre.model.Picture;
import com.example.projetandroidsilvestre.model.Repository;

import java.util.List;

public class NotificationsViewModel extends ViewModel {

    public Repository mRepo;
    private Application mApplication;

    private LiveData<List<Picture>> allPictures;
    //private LiveData<List<PicAnnotation>> allPicAnot;

    public NotificationsViewModel(Application application) {
        super();
        this.mApplication = application;
        //pictureLiveDataUri = new MutableLiveData<Uri>();
        mRepo = new Repository(mApplication);
        allPictures = mRepo.getAlphabetizedPictures();
        //allPicAnot = mRepo.getAllPicAnot();
        /*if(mRepo.getAlphabetizedPictures().getValue()!=null){
            System.out.println("nb Pictures in mRepo = "+mRepo.getAlphabetizedPictures().getValue().size());
        }
        else{
            System.out.println("mRepo.getAlphabetizedPictures()==null");
        }*/
    }

    LiveData<List<Picture>> getAllPictures() { return allPictures; }
   // LiveData<List<PicAnnotation>> getAllPicAnot() {return allPicAnot;}
}