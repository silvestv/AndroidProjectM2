package com.example.projetandroidsilvestre.ui.research;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ResearchViewModelFactory implements ViewModelProvider.Factory {

    private Application mApplication;

    public ResearchViewModelFactory(Application application){
        mApplication = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ResearchViewModel(mApplication);
    }
}

