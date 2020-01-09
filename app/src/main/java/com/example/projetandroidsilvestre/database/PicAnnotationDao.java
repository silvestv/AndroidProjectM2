package com.example.projetandroidsilvestre.database;

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

    @Transaction
    @Query("SELECT * from event_annotation")
    List<PicAnnotation> loadAnnotations();

}
