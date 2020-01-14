package com.example.projetandroidsilvestre.ui.annotation;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class AnnotationViewModelFactory implements ViewModelProvider.Factory {

    private Application mApplication;

    public AnnotationViewModelFactory(Application application){
        mApplication = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AnnotationViewModel(mApplication);
    }
}
