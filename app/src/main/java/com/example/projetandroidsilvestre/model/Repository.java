package com.example.projetandroidsilvestre.model;

import android.app.Application;

import com.example.projetandroidsilvestre.database.PictureDao;
import com.example.projetandroidsilvestre.database.SempicDatabase;

import java.util.List;

public class Repository {

    private PictureDao mPictureDao;
    private List<Picture> mAllPictures;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public Repository(Application application) {
        SempicDatabase db = SempicDatabase.getDatabase(application);
        mPictureDao = db.pictureDao();
        mAllPictures = mPictureDao.getAlphabetizedPictures();
    }

    public List<Picture> getAllPictures() {
        return mAllPictures;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.

    public void insert(Picture picture) {
        SempicDatabase.databaseWriteExecutor.execute(() -> {
            mPictureDao.insert(picture);
        });
    }

}