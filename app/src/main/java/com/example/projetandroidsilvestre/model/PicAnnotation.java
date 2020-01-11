package com.example.projetandroidsilvestre.model;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import com.example.projetandroidsilvestre.database.PicAnnotationDao;

import java.lang.annotation.Annotation;
import java.util.List;

public class PicAnnotation {

//    @PrimaryKey
  //  @NonNull
    public Uri picUri;
    public List<Uri> eventAnnot;
    public List<Uri> contactUris;

    Repository r;

    public PicAnnotation(Uri picUri, List<Uri> eventAnnot, List<Uri> contactUris){
        this.picUri = picUri;
        this.eventAnnot = eventAnnot;
        this.contactUris = contactUris;
    }

    public Uri getPicUri(){ return this.picUri; }
    public List<Uri> getEventUri(){ return eventAnnot ; }
    public List<Uri> getContactUris() {return contactUris; }

  //  @NonNull
   // @Override
    public String toString() {
        String res =  eventAnnot+","+eventAnnot+","+contactUris+"]";
        return res;
    }
}
