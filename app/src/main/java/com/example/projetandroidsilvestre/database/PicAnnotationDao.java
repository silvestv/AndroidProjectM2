package com.example.projetandroidsilvestre.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.projetandroidsilvestre.model.ContactAnnotation;
import com.example.projetandroidsilvestre.model.EventAnnotation;

import java.util.List;

@Dao
public interface PicAnnotationDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertPictureEvent(EventAnnotation a);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertPictureContact(ContactAnnotation a);

    @Query("DELETE FROM event_annotation")
    void deleteAllEvent();

    @Query("DELETE FROM contact_annotation")
    void deleteAllContacts();

    @Query("Select * from event_annotation")
    LiveData<List<EventAnnotation>> loadAllEventAnnotation();

    @Query("Select * from contact_annotation")
    LiveData<List<ContactAnnotation>> loadAllContactAnnotation();
}