package com.example.projetandroidsilvestre.ui.research;

import android.app.Application;
import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.projetandroidsilvestre.model.ContactAnnotation;
import com.example.projetandroidsilvestre.model.EventAnnotation;
import com.example.projetandroidsilvestre.model.Repository;

import java.util.List;

public class ResearchViewModel extends ViewModel {

    public Repository mRepo;
    private Application mApplication;

    private LiveData<List<Uri>> mAllPicUriAnnotation;
    private LiveData<List<Uri>> mAllPicUriFromAGivenContact;
    private LiveData<List<ContactAnnotation>> mAllContactAnnotation;
    private LiveData<List<EventAnnotation>> mAllEventAnnotation;

    private LiveData<List<Uri>> mAllPicUriFromSomeContactsSomeEvents;
    private LiveData<List<Uri>> mAllPicUriFromSomeContacts;
    private LiveData<List<Uri>> mAllPicUriFromSomeEvents;

    public ResearchViewModel(Application application) {
        super();
        this.mApplication = application;
        mRepo = new Repository(mApplication);
        mAllPicUriAnnotation = mRepo.getAllPicUriAnnotation();
        mAllContactAnnotation = mRepo.getAllContactAnnotation();
        mAllEventAnnotation = mRepo.getAllEventAnnotation();
    }

    LiveData<List<EventAnnotation>> getAllEventAnnotations(){
        return this.mAllEventAnnotation;
    }

    LiveData<List<ContactAnnotation>> getAllContactAnnotations(){ return this.mAllContactAnnotation; }


    public void setAllPictureUriFromAGivenContact(Uri myContactUri){
        mAllPicUriFromAGivenContact = this.mRepo.getAllPictureFromAGivenContact(myContactUri);
    }
    public LiveData<List<Uri>> getAllPictureUriTest(){
        return this.mAllPicUriFromAGivenContact;
    }


    ////////////////////////////////////////////////////////////////////////////////////////
    public void setAllPictureUriFromSomeContactsSomeEvents(List<Uri> myContacts, List<Uri> myEvents){
        mAllPicUriFromSomeContactsSomeEvents = this.mRepo.getAllPictureFromSomeContactSomeEvents(myContacts, myEvents);
    }
    public LiveData<List<Uri>> getAllPictureUriFromSomeContactsSomeEvents(){
        return this.mAllPicUriFromSomeContactsSomeEvents;
    }


    public void setAllPictureUriFromSomeContacts(List<Uri> myContacts){
        mAllPicUriFromSomeContacts = this.mRepo.getAllPictureFromSomeContact(myContacts);
    }
    public LiveData<List<Uri>> getAllPictureUriFromSomeContacts(){
        return this.mAllPicUriFromSomeContacts;
    }

    public void setAllPictureUriFromSomeEvents(List<Uri> myEvents){
        mAllPicUriFromSomeEvents = this.mRepo.getAllPictureFromSomeEvents(myEvents);
    }
    public LiveData<List<Uri>> getAllPictureUriFromSomeEvents(){
        return this.mAllPicUriFromSomeEvents;
    }




    /////////////////////PREMIERE TECHNIQUE CONTOURNEMENT DE PROBLEME DE PARAMETRE RESOLU//////////////////////////////////////////////////////////////////:
    /*
    ArrayList<Uri> getAllPicturesFromTheDatabase(){
        ArrayList<Uri> result = new ArrayList<Uri>();
        if(this.mAllPicUriAnnotation.getValue()!=null){
            Iterator<Uri> it = this.mAllPicUriAnnotation.getValue().iterator();
            Uri next ;
            while(it.hasNext()){
                next = it.next();
                if(!result.contains(next)){
                    result.add(next);
                }
            }
        }
        return result;
    }

    List<Uri> getAllEventsFromAGivenPicture(Uri myPictureUri){
        List<Uri> result = new LinkedList<Uri>();
        if(this.mAllEventAnnotation.getValue()!=null){
            Iterator<EventAnnotation> it = this.mAllEventAnnotation.getValue().iterator();
            EventAnnotation next ;
            int i = 0;
            while(it.hasNext()){
                next = it.next();
                if(next.getK().getPicUri().toString().equals(myPictureUri.toString())){
                    result.add(next.getK().getEventUri());
                }
                i++;
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
*/


}