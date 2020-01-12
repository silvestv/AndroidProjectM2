package com.example.projetandroidsilvestre.model;

import android.app.Application;
import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.projetandroidsilvestre.database.PicAnnotationDao;
import com.example.projetandroidsilvestre.database.PictureDao;
import com.example.projetandroidsilvestre.database.SempicDatabase;

import java.util.LinkedList;
import java.util.List;

public class Repository {

    private PicAnnotationDao mPicAnotDao;

    private LiveData<List<EventAnnotation>> mEventAnnotation;
    private LiveData<List<ContactAnnotation>> mContactAnnotation;

    public Repository(Application application) {
        SempicDatabase db = SempicDatabase.getDatabase(application);

        mPicAnotDao = db.getPicAnnotationDao();

        mEventAnnotation = mPicAnotDao.loadAllEventAnnotation();
        mContactAnnotation = mPicAnotDao.loadAllContactAnnotation();
    }

    public void InsertEventAnnotation(EventAnnotation eventAnnotation){
        SempicDatabase.databaseWriteExecutor.execute(() -> {
           mPicAnotDao.insertPictureEvent(eventAnnotation);
        });
    }

    public void InsertContactAnnotation(ContactAnnotation contactAnnotation){
        SempicDatabase.databaseWriteExecutor.execute(() -> {
            mPicAnotDao.insertPictureContact(contactAnnotation);
        });
    }

   /* public void setAllEventsFromOnePicture(Uri myPictureUri){
        System.out.println("repo : this.mAllEventsFromAGivenPicture = this.mPicAnotDao.loadEventsFromOneAnot "+myPictureUri+")");
        SempicDatabase.databaseWriteExecutor.execute(() -> {
            this.mAllEventsFromAGivenPicture = this.mPicAnotDao.loadEventsFromOneAnot(myPictureUri);
        });
    }*/

    /*public LiveData<List<Uri>> getAllEventsFromOnePicture(){
        return mAllEventsFromAGivenPicture;
    }*/

    public LiveData<List<EventAnnotation>> getAllEventAnnotation(){
        return mEventAnnotation;
    }

    public LiveData<List<ContactAnnotation>> getAllContactAnnotation(){
        return mContactAnnotation;
    }

}