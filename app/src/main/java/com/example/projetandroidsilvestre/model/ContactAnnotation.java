package com.example.projetandroidsilvestre.model;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "contact_annotation")
public class ContactAnnotation {

    /*@PrimaryKey
    @NonNull
    private Uri picUri;
    @NonNull
    private Uri contactUri;*/

    @Entity(primaryKeys = {"picUri","contactUri"})
    public static class Key {
        @NonNull
        private Uri picUri;
        @NonNull
        private Uri contactUri;

        public Key(Uri picUri, Uri contactUri) {
            this.picUri = picUri;
            this.contactUri = contactUri;
        }

        public Uri getPicUri() {
            return picUri;
        }

        public Uri getContactUri() {
            return contactUri;
        }


        @NonNull
        @Override
        public String toString() {
            return "[" + picUri + "," + contactUri + "]";
        }
    }

    @PrimaryKey
    @NonNull
    @Embedded
    ContactAnnotation.Key k;

    public ContactAnnotation(ContactAnnotation.Key k) {
        this.k = k;
    }

    public ContactAnnotation.Key getK() {
        return this.k;
    }
}
