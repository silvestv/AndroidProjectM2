package com.example.projetandroidsilvestre.model;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class PicAnnotation {

    @Embedded
    public EventAnnotation eventAnnot;
    @Relation(
            parentColumn = "picUri",
            entityColumn = "picUri",
            entity = ContactAnnotation.class,
            projection = {"contactUri"}
    )
    public List<Uri> contactUris;



    public Uri getPicUri(){ return eventAnnot.getPicUri(); }
    public List<Uri> getEventUri(){ return eventAnnot.getEventUri(); }
    public List<Uri> getContactUris() {return contactUris; }

    @NonNull
    @Override
    public String toString() {
        String res =  eventAnnot.getPicUri()+","+eventAnnot.getEventUri()+","+contactUris+"]";
        return res;
    }
}
