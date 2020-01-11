package com.example.projetandroidsilvestre.database;

import android.net.Uri;

import androidx.room.TypeConverter;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Converters {

    @TypeConverter
    public static Uri fromString(String value) {
        return value == null ? null : Uri.parse(value);
    }

    @TypeConverter
    public static String UriToString(Uri value) {
        return value == null ? null : value.toString();
    }

    @TypeConverter

    public static List<Uri> fromListString(List<String> value) {
        List<Uri> result = new LinkedList<Uri>();
        Iterator<String> it = value.iterator();
        while(it.hasNext()){
            result.add(Uri.parse(it.next()));
        }
        return value == null ? null : result;
    }

    @TypeConverter
    public static List<String> ListUriToListString(List<Uri> value) {
        List<String> result = new LinkedList<String>();
        Iterator<Uri> it = value.iterator();
        while(it.hasNext()){
            result.add(it.next().toString());
        }
        return value == null ? null : result;
    }

}