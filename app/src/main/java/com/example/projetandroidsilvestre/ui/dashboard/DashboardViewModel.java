package com.example.projetandroidsilvestre.ui.dashboard;

import android.app.Application;
import android.content.Context;
import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.projetandroidsilvestre.model.ContactAnnotation;
import com.example.projetandroidsilvestre.model.EventAnnotation;
import com.example.projetandroidsilvestre.model.Picture;
import com.example.projetandroidsilvestre.model.Repository;

import java.util.List;


public class DashboardViewModel extends ViewModel {

    public Repository mRepo;
    private Application mApplication;

    public DashboardViewModel(Application application) {
        super();
        this.mApplication = application;
        mRepo = new Repository(mApplication);
    }

    public void insertEventAnnotation(EventAnnotation ev){
        mRepo.InsertEventAnnotation(ev);
    }

    public void insertContactAnnotation(ContactAnnotation con) {mRepo.InsertContactAnnotation(con);}

}