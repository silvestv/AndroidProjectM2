package com.example.projetandroidsilvestre.ui.notifications;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class NotificationViewModelFactory implements ViewModelProvider.Factory {

    private Application mApplication;

    public NotificationViewModelFactory(Application application){
        mApplication = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new NotificationsViewModel(mApplication);
    }
}

