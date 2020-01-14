package com.example.projetandroidsilvestre.model;

import android.app.Application;
import android.net.Uri;

import androidx.lifecycle.LiveData;

import com.example.projetandroidsilvestre.database.PicAnnotationDao;
import com.example.projetandroidsilvestre.database.SempicDatabase;

import java.util.List;

public class Repository {

    private PicAnnotationDao mPicAnotDao;

    private LiveData<List<EventAnnotation>> mEventAnnotation;
    private LiveData<List<ContactAnnotation>> mContactAnnotation;
    private LiveData<List<Uri>> mAllPicUriAnnotation;

    public Repository(Application application) {
        SempicDatabase db = SempicDatabase.getDatabase(application);

        mPicAnotDao = db.getPicAnnotationDao();

        mEventAnnotation = mPicAnotDao.loadAllEventAnnotation();
        mContactAnnotation = mPicAnotDao.loadAllContactAnnotation();
        mAllPicUriAnnotation = mPicAnotDao.loadAllPicUriAnnotation();
    }

    public void InsertEventAnnotation(EventAnnotation eventAnnotation){
        SempicDatabase.databaseWriteExecutor.execute(() -> {
            System.out.println("insertion dans la bdd ---");
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

    public LiveData<List<ContactAnnotation>> getAllContactAnnotation(){ return mContactAnnotation; }

    public void InsertEventAnnotation(List<EventAnnotation> li){
        SempicDatabase.databaseWriteExecutor.execute(() -> {
            mPicAnotDao.insertAllEvent(li);
        });
    }

    public void InsertContactAnnotation(List<ContactAnnotation> li){
        SempicDatabase.databaseWriteExecutor.execute(() -> {
            mPicAnotDao.insertAllContact(li);
        });
    }

    public LiveData<List<Uri>> getAllPicUriAnnotation(){
        return mAllPicUriAnnotation;
    }

    public LiveData<List<Uri>> getAllPictureFromAGivenContact(Uri contactUri){
        return this.mPicAnotDao.loadAllPictureWithAGivenContact(contactUri);
    }

    ////////////////////////////////////////////////////////////////////////////////

    public LiveData<List<Uri>> getAllPictureFromSomeContactSomeEvents(List<Uri> contacts, List<Uri> events){
        return this.mPicAnotDao.loadAllPicturesWithSomeContactsAndSomeEvent(contacts, events);
    }

    public LiveData<List<Uri>> getAllPictureFromSomeContact(List<Uri> contacts){
        return this.mPicAnotDao.loadAllPicturesWithSomeContacts(contacts);
    }

    public LiveData<List<Uri>> getAllPictureFromSomeEvents(List<Uri> events){
        return this.mPicAnotDao.loadAllPicturesWithSomeEvents(events);
    }

}