package com.example.projetandroidsilvestre.ui.dashboard;

import android.app.Application;

import androidx.lifecycle.ViewModel;

import com.example.projetandroidsilvestre.model.ContactAnnotation;
import com.example.projetandroidsilvestre.model.EventAnnotation;
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

    public void insertEventAnnotation(EventAnnotation ev){ mRepo.InsertEventAnnotation(ev); }

    public void insertEventAnnotation(List<EventAnnotation> li){ mRepo.InsertEventAnnotation(li); }

    public void insertContactAnnotation(ContactAnnotation con) {mRepo.InsertContactAnnotation(con);}

    public void insertContactAnnotation(List<ContactAnnotation> li) {mRepo.InsertContactAnnotation(li);}

}