package com.example.projetandroidsilvestre.model;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.projetandroidsilvestre.database.PicAnnotationDao;
import com.example.projetandroidsilvestre.database.PictureDao;
import com.example.projetandroidsilvestre.database.SempicDatabase;

import java.util.List;

public class Repository {

    private PictureDao mPictureDao;
    private LiveData<List<Picture>> mAllPictures;

    private PicAnnotationDao mPicAnotDao;
    //private LiveData<List<PicAnnotation>> mAllPicAnot;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public Repository(Application application) {
        SempicDatabase db = SempicDatabase.getDatabase(application);

        mPictureDao = db.pictureDao();
        mAllPictures = mPictureDao.getAlphabetizedPictures();

        //mPicAnotDao = db.getPicAnnotationDao();
        //mAllPicAnot = mPicAnotDao.loadAnnotations();
    }

    public void insert(Picture picture) {
        SempicDatabase.databaseWriteExecutor.execute(() -> {
            System.out.println("PREEEEEEEEEEESDSSSQUE : "+picture.getPicture());
            mPictureDao.insert(picture);

        });
    }

    public LiveData<List<Picture>> getAlphabetizedPictures() {
        //SempicDatabase.databaseWriteExecutor.execute(() -> {
            return mAllPictures;
            //System.out.println("getAlphabetizedPictures object.size() = : "+this.mAllPictures.size());
        //});
    }
/*
    public LiveData<List<PicAnnotation>> getAllPicAnot(){
        //SempicDatabase.databaseWriteExecutor.execute(() -> {
        return mAllPicAnot;
       //});
    }*/

}