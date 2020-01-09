package com.example.projetandroidsilvestre.model;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "picture_table")
public class Picture {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "picture")
    private Uri mPicture;

    public Picture(Uri picture) {this.mPicture = picture;}

    public Uri getPicture(){return this.mPicture;}
}