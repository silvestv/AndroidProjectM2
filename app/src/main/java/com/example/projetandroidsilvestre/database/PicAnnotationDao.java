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

    @Query("SELECT DISTINCT picUri from contact_annotation UNION SELECT DISTINCT picUri from event_annotation")
    public abstract LiveData<List<Uri>> loadAllPicUriAnnotation();

    @Query("SELECT DISTINCT picUri from contact_annotation WHERE contactUri=:contactUri")
    public abstract LiveData<List<Uri>> loadAllPictureWithAGivenContact(Uri contactUri);

    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Query("SELECT DISTINCT ca.picUri from contact_annotation ca, event_annotation ea WHERE ca.contactUri IN(:contactUri) AND ea.eventUri IN(:eventUri)")
    public abstract LiveData<List<Uri>> loadAllPicturesWithSomeContactsAndSomeEvent(List<Uri> contactUri, List<Uri> eventUri);

    @Query("SELECT DISTINCT picUri from contact_annotation AS ca WHERE ca.contactUri IN(:contactUri)")
    public abstract LiveData<List<Uri>> loadAllPicturesWithSomeContacts(List<Uri> contactUri);

    @Query("SELECT DISTINCT picUri from event_annotation AS ea WHERE ea.eventUri IN(:eventUri)")
    public abstract LiveData<List<Uri>> loadAllPicturesWithSomeEvents(List<Uri> eventUri);


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