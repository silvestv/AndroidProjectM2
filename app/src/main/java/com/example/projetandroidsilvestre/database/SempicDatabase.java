package com.example.projetandroidsilvestre.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.projetandroidsilvestre.model.ContactAnnotation;
import com.example.projetandroidsilvestre.model.EventAnnotation;
import com.example.projetandroidsilvestre.model.Picture;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Picture.class, EventAnnotation.class, ContactAnnotation.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class SempicDatabase extends RoomDatabase {

    public abstract PictureDao pictureDao();
    public abstract PicAnnotationDao getPicAnnotationDao();

    private static volatile SempicDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static public final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static public SempicDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SempicDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SempicDatabase.class, "sempic_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback sRoomDatabaseCallback = new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {

                PictureDao pDao= INSTANCE.pictureDao();
                //pDao.deleteAll();
                //TODO insert
            });
        }
    };
}