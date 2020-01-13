package com.example.projetandroidsilvestre.model;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "event_annotation")
public class EventAnnotation {

    /*@PrimaryKey
    @NonNull
    private Uri picUri;
    private Uri eventUri;*/

    @Entity(primaryKeys = {"picUri","eventUri"})
    public static class Key {
        @NonNull
        private Uri picUri;
        @NonNull
        private Uri eventUri;

        public Key(Uri picUri, Uri eventUri) {
            this.picUri = picUri;
            this.eventUri = eventUri;
        }

        public Uri getPicUri() {
            return picUri;
        }

        public Uri getEventUri() {
            return eventUri;
        }


        @NonNull
        @Override
        public String toString() {
            return "[" + picUri + "," + eventUri + "]";
        }
        }

        @PrimaryKey
        @NonNull
        @Embedded
        Key k;

        public EventAnnotation(Key k) {
            this.k = k;
        }

        public Key getK() {
            return this.k;
        }

    /*public EventAnnotation(Uri picUri, Uri eventUri) {
        this.picUri=picUri;
        this.eventUri=eventUri;
    }*/
}
