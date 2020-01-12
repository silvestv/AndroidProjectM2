package com.example.projetandroidsilvestre.ui.notifications;

import android.app.Application;
import android.net.Uri;
import android.util.EventLog;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.projetandroidsilvestre.model.ContactAnnotation;
import com.example.projetandroidsilvestre.model.EventAnnotation;
import com.example.projetandroidsilvestre.model.PicAnnotation;
import com.example.projetandroidsilvestre.model.Picture;
import com.example.projetandroidsilvestre.model.Repository;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class NotificationsViewModel extends ViewModel {

    public Repository mRepo;
    private Application mApplication;

    private LiveData<List<ContactAnnotation>> mAllContactAnnotation;
    private LiveData<List<EventAnnotation>> mAllEventAnnotation;

    public NotificationsViewModel(Application application) {
        super();
        this.mApplication = application;
        mRepo = new Repository(mApplication);

        mAllContactAnnotation = mRepo.getAllContactAnnotation();
        mAllEventAnnotation = mRepo.getAllEventAnnotation();
    }

    List<Uri> getAllEventsFromAGivenPicture(Uri myPictureUri){        //fonctionne
        if(this.mAllEventAnnotation.getValue()!=null){
            Iterator<EventAnnotation> it = this.mAllEventAnnotation.getValue().iterator();
            EventAnnotation next ;
            List<Uri> result = new LinkedList<Uri>();
            while(it.hasNext()){
                next = it.next();
                if(next.getPicUri().toString().equals(myPictureUri.toString())){
                    result.add(next.getEventUri());
                }
            }
            return result;
        }
        else{
            return null;
        }
    }

    List<Uri> getAllContactsFromAGivenPicture(Uri myPictureUri){
        if(this.mAllContactAnnotation.getValue()!=null){
            Iterator<ContactAnnotation> it = this.mAllContactAnnotation.getValue().iterator();
            ContactAnnotation next ;
            List<Uri> result = new LinkedList<Uri>();
            while(it.hasNext()){
                next = it.next();
                if(next.getPicUri().toString().equals(myPictureUri.toString())){
                    result.add(next.getContactUri());
                }
            }
            return result;
        }
        else{
            return null;
        }
    }

    List<Uri> getAllPicturesFromAGivenContact(Uri myContactUri){
        if(this.getAllContactAnnotations().getValue()!=null){
            Iterator<ContactAnnotation> it = this.mAllContactAnnotation.getValue().iterator();
            ContactAnnotation next ;
            List<Uri> result = new LinkedList<Uri>();
            while(it.hasNext()){
                next = it.next();
                if(next.getContactUri().toString().equals(myContactUri.toString())){
                    result.add(next.getPicUri());
                }
            }
            return result;
        }
        else{
            return null;
        }
    }

    List<Uri> getAllPicturesFromAGivenEvent(Uri myEventUri){
        if(this.getAllEventAnnotations().getValue()!=null){
            Iterator<EventAnnotation> it = this.mAllEventAnnotation.getValue().iterator();
            EventAnnotation next ;
            List<Uri> result = new LinkedList<Uri>();
            while(it.hasNext()){
                next = it.next();
                if(next.getEventUri().toString().equals(myEventUri.toString())){
                    result.add(next.getPicUri());
                }
            }
            return result;
        }
        else{
            return null;
        }
    }


    List<Uri> getAllEventsFromAGivenContact(Uri myContactUri){

        List<Uri> picturesWithMyContacts = getAllPicturesFromAGivenContact(myContactUri);
        if(picturesWithMyContacts!=null){
            if(this.mAllEventAnnotation.getValue()!=null){
                Iterator<EventAnnotation> it = this.mAllEventAnnotation.getValue().iterator();
                EventAnnotation next ;
                List<Uri> result = new LinkedList<Uri>();
                while(it.hasNext()){
                    next = it.next();
                    //regarder si chaque photo contient l'évènement.
                    Iterator<Uri> it2 = picturesWithMyContacts.iterator();
                    Uri currentPicture ;
                    while(it2.hasNext()){
                        currentPicture = it2.next();
                        if(next.getPicUri().toString().equals(currentPicture.toString())){
                            result.add(next.getEventUri());
                        }
                    }
                }
                return result;
            }
            else{
                return null;
            }
        }
        else{
            return null;
        }
    }

    List<Uri> getAllContactsFromAGivenEvent(Uri myEventUri){

        List<Uri> picturesWithMyEvent = getAllPicturesFromAGivenEvent(myEventUri);
        if(picturesWithMyEvent!=null){
            if(this.mAllContactAnnotation.getValue()!=null){
                Iterator<ContactAnnotation> it = this.mAllContactAnnotation.getValue().iterator();
                ContactAnnotation next ;
                List<Uri> result = new LinkedList<Uri>();
                while(it.hasNext()){
                    next = it.next();
                    //regarder si chaque photo contient le contact.
                    Iterator<Uri> it2 = picturesWithMyEvent.iterator();
                    Uri currentPicture ;
                    while(it2.hasNext()){
                        currentPicture = it2.next();
                        if(next.getPicUri().toString().equals(currentPicture.toString())){
                            result.add(next.getContactUri());
                        }
                    }
                }
                return result;
            }
            else{
                return null;
            }
        }
        else{
            return null;
        }
    }


    LiveData<List<EventAnnotation>> getAllEventAnnotations(){
        return this.mAllEventAnnotation;
    }

    LiveData<List<ContactAnnotation>> getAllContactAnnotations(){ return this.mAllContactAnnotation; }
}