package com.example.projetandroidsilvestre.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.projetandroidsilvestre.model.ContactAnnotation;
import com.example.projetandroidsilvestre.model.EventAnnotation;
import com.example.projetandroidsilvestre.model.PicAnnotation;

import java.util.Iterator;
import java.util.List;

@Dao
public abstract class PicAnnotationDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract void insertPictureEvent(EventAnnotation a);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract void insertPictureContact(ContactAnnotation a);

    @Query("DELETE FROM event_annotation")
    public abstract void deleteAllEvent();

    @Query("DELETE FROM contact_annotation")
    public abstract void deleteAllContacts();

    @Query("Select * from event_annotation")
    public abstract LiveData<List<EventAnnotation>> loadAllEventAnnotation();

    @Query("Select * from contact_annotation")
    public abstract LiveData<List<ContactAnnotation>> loadAllContactAnnotation();

    @Transaction
    public void insertAllEvent(List<EventAnnotation> li) {
        Iterator<EventAnnotation> it = li.iterator();
        while(it.hasNext()){
            System.out.println("inserting an annot");
            insertPictureEvent(it.next());
        }
    }

    @Transaction
    public void insertAllContact(List<ContactAnnotation> li) {
        Iterator<ContactAnnotation> it = li.iterator();
        while(it.hasNext()){
            System.out.println("inserting a contact");
            insertPictureContact(it.next());
        }
    }
}