package com.example.projetandroidsilvestre.database;

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.projetandroidsilvestre.model.ContactAnnotation;
import com.example.projetandroidsilvestre.model.EventAnnotation;
import com.example.projetandroidsilvestre.model.PicAnnotation;

import java.util.List;

@Dao
public interface PicAnnotationDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertPictureEvent(EventAnnotation a);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertPictureContact(ContactAnnotation a);

    @Query("DELETE FROM event_annotation")
    void deleteAll();

    /*@Transaction
    @Query("SELECT * from event_annotation")
    LiveData<List<PicAnnotation>> loadAnnotations();*/

    @Query("SELECT eventUri from event_annotation where picUri = :uri")
    LiveData<List<Uri>> loadEventsFromOneAnot(Uri uri);

    @Query("SELECT contactUri from contact_annotation where picUri = :uri")
    LiveData<List<Uri>> loadContactsFromOneAnot(Uri uri);

    @Query("SELECT c.picUri, e.picUri from contact_annotation AS c, event_annotation as e")
    LiveData<List<Uri>> loadAllPictures();

}
