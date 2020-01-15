package com.example.projetandroidsilvestre.ui.annotation;

import android.app.Application;

import androidx.lifecycle.ViewModel;

import com.example.projetandroidsilvestre.model.ContactAnnotation;
import com.example.projetandroidsilvestre.model.EventAnnotation;
import com.example.projetandroidsilvestre.model.Repository;

import java.util.List;


public class AnnotationViewModel extends ViewModel {

    public Repository mRepo;
    private Application mApplication;

    public AnnotationViewModel(Application application) {
        super();
        this.mApplication = application;
        mRepo = new Repository(mApplication);
    }


    public void insertEventAnnotation(List<EventAnnotation> li){ mRepo.InsertEventAnnotation(li); }


    public void insertContactAnnotation(List<ContactAnnotation> li) {mRepo.InsertContactAnnotation(li);}

}