package com.example.projetandroidsilvestre.ui.notifications;

import android.app.Application;
import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.projetandroidsilvestre.model.ContactAnnotation;
import com.example.projetandroidsilvestre.model.EventAnnotation;
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

    List<Uri> getAllEventsFromAGivenPicture(Uri myPictureUri){
        List<Uri> result = new LinkedList<Uri>();
        if(this.mAllEventAnnotation.getValue()!=null){
          //  System.out.println("my picture URI = "+myPictureUri.toString());
            Iterator<EventAnnotation> it = this.mAllEventAnnotation.getValue().iterator();
        //    System.out.println("mAllEventAnnotation.getValue().size() = "+this.mAllEventAnnotation.getValue().size());
            EventAnnotation next ;
            while(it.hasNext()){
                next = it.next();
              //  System.out.println("picture tested : "+next.getK().getPicUri().toString());
                if(next.getK().getPicUri().toString().equals(myPictureUri.toString())){
                //    System.out.println("add");
                    result.add(next.getK().getEventUri());
                }
            }
        }
        return result;
    }

    List<Uri> getAllPicturesFromTheDatabase(){
        List<Uri> result = new LinkedList<Uri>();
        if(this.mAllEventAnnotation.getValue()!=null){
            Iterator<EventAnnotation> it = this.mAllEventAnnotation.getValue().iterator();
            EventAnnotation next ;
            while(it.hasNext()){
                next = it.next();
                result.add(next.getK().getPicUri());
                }
            }
        if(this.mAllContactAnnotation.getValue()!=null){
            Iterator<ContactAnnotation> it = this.mAllContactAnnotation.getValue().iterator();
            ContactAnnotation next ;
            while(it.hasNext()){
                next = it.next();
                result.add(next.getK().getPicUri());
            }
        }
            return result;
    }

    List<Uri> getAllContactsFromAGivenPicture(Uri myPictureUri){
        List<Uri> result = new LinkedList<Uri>();
        if(this.mAllContactAnnotation.getValue()!=null){
            Iterator<ContactAnnotation> it = this.mAllContactAnnotation.getValue().iterator();
            ContactAnnotation next ;
            while(it.hasNext()){
                next = it.next();
                if(next.getK().getPicUri().toString().equals(myPictureUri.toString())){
                    result.add(next.getK().getContactUri());
                }
            }
        }
        return result;
    }

    List<Uri> getAllPicturesFromAGivenContact(Uri myContactUri){
        List<Uri> result = new LinkedList<Uri>();
        if(this.getAllContactAnnotations().getValue()!=null){
            Iterator<ContactAnnotation> it = this.mAllContactAnnotation.getValue().iterator();
            ContactAnnotation next ;
            while(it.hasNext()){
                next = it.next();
                if(next.getK().getContactUri().toString().equals(myContactUri.toString())){
                    result.add(next.getK().getPicUri());
                }
            }
        }
        return result;
    }

    List<Uri> getAllPicturesFromAGivenEvent(Uri myEventUri){
        List<Uri> result = new LinkedList<Uri>();
        if(this.getAllEventAnnotations().getValue()!=null){
            Iterator<EventAnnotation> it = this.mAllEventAnnotation.getValue().iterator();
            EventAnnotation next ;
            while(it.hasNext()){
                next = it.next();
                if(next.getK().getEventUri().toString().equals(myEventUri.toString())){
                    result.add(next.getK().getPicUri());
                }
            }
        }
        return result;
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
                        if(next.getK().getPicUri().toString().equals(currentPicture.toString())){
                            result.add(next.getK().getEventUri());
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
                        if(next.getK().getPicUri().toString().equals(currentPicture.toString())){
                            result.add(next.getK().getContactUri());
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

    List<Uri> getAllPicturesFromSomeEvents(List<Uri> myEventsUri){
        List<Uri> result = new LinkedList<Uri>();
        Iterator<Uri> it = myEventsUri.iterator();
        result = getAllPicturesFromAGivenEvent(it.next());
        List<Uri> nextListOfPicturesFromAnEventUri;
        while(it.hasNext()){
            nextListOfPicturesFromAnEventUri = getAllContactsFromAGivenEvent(it.next());
            result = commonPicturesBetween(result,nextListOfPicturesFromAnEventUri);
        }
        return result;
    }


    List<Uri> getAllPicturesFromSomeContacts(List<Uri> myContactsUri){
        List<Uri> result = new LinkedList<Uri>();
        Iterator<Uri> it = myContactsUri.iterator();
        result = getAllPicturesFromAGivenContact(it.next());
        List<Uri> nextListOfPicturesFromAContactUri;
        while(it.hasNext()){
            nextListOfPicturesFromAContactUri = getAllPicturesFromAGivenContact(it.next());
            result = commonPicturesBetween(result,nextListOfPicturesFromAContactUri);
        }
        return result;
    }

    List<Uri> getAllPicturesFromSomeContactsAndEvents(List<Uri> myContactsUri, List<Uri> myEventsUri){
        List<Uri> result = new LinkedList<Uri>();
        if(myContactsUri.size()==0){
            if(myEventsUri.size()!=0){
                result = getAllPicturesFromSomeEvents(myEventsUri);
            }
            else{
                result = getAllPicturesFromTheDatabase();
            }
        }
        else{
            result = getAllPicturesFromSomeContacts(myContactsUri);
            if(myEventsUri.size()!=0){
                List<Uri> picturesFromMyEvents = getAllPicturesFromSomeEvents(myEventsUri);
                result = commonPicturesBetween(result,picturesFromMyEvents);
            }
        }
        return result;
    }

    private List<Uri> commonPicturesBetween(List<Uri> listA, List<Uri> listB){
        List<Uri> result = new LinkedList<Uri>();
        Iterator<Uri> it = listA.iterator();
        Uri currentUri;
        while(it.hasNext()){
            currentUri = it.next();
            if(listB.contains((Uri)currentUri)){
                result.add(currentUri);
            }
        }
        return result;
    }

    LiveData<List<EventAnnotation>> getAllEventAnnotations(){
        return this.mAllEventAnnotation;
    }

    LiveData<List<ContactAnnotation>> getAllContactAnnotations(){ return this.mAllContactAnnotation; }
}