package com.example.projetandroidsilvestre.database;
/*
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.projetandroidsilvestre.model.Picture;

import java.util.List;

@Dao
public interface PictureDao {

    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Picture picture);

    @Query("DELETE FROM picture_table")
    void deleteAll();

    @Query("SELECT * from picture_table ORDER BY picture ASC")
    LiveData<List<Picture>> getAlphabetizedPictures();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<Picture> picture);

}*/