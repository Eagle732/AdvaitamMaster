package Fragments;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ListView;
//import android.widget.ProgressBar;
//
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Collections;
//import java.util.TimeZone;
//
//import Adapters.EventsClassNew;
//import Info.EventsClass;
//import ac.nita.advaitam4.R;
//
//
//
//
//public class HistoryUpcomingEvents extends Fragment {
//
//    Context context;
//
//    ArrayList<EventsClassNew> myPlacesArray;
//
//    ProgressBar progressBar;
//
//    public void ListOfParticipantFragment(){
//
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        this.context = context;
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
//
//        View rootView = inflater.inflate(R.layout.events_organisers,container,false);
//        progressBar = (ProgressBar)rootView.findViewById(R.id.progress_bar);
//
//        return rootView;
//    }
////    ListView mListView;
//
//    @Override
//    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        callFirebase(view);
//    }
//
////    void callFirebase(final View view){
////        FirebaseApp.initializeApp(getContext());
////        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
////        DatabaseReference ref = rootRef.child("data/events/event1/participants");
////
////        ref.addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(DataSnapshot dataSnapshot) {
////                myPlacesArray = new ArrayList<>();
////                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
////                    EventsClass eventsClass = dataSnapshot1.getValue(EventsClass.class);
////                    //Log.d("mylog", place.getNameStudent()+place.getPhone()+place.getRoll()+" ");
////                    myPlacesArray.add(eventsClass);
////                }
////                ListView mListView = (ListView) view.findViewById(R.id.events_listview);
////            }
////
////            @Override
////            public void onCancelled(DatabaseError databaseError) {
////
////            }
////        });
////    }
//
//
//    boolean isEventUpcoming(String todayDate,String eventDate){
//        boolean result = false;
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//            result = sdf.parse(todayDate).before(sdf.parse(eventDate));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//    String getDay(){
//        DateFormat formatter= new SimpleDateFormat("dd-MM-yyyy");
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DATE,0);
//        formatter.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
//        String days=(formatter.format(calendar.getTime()));
//        return days;
//    }
//    void callFirebase(final View view) {
//        FirebaseApp.initializeApp(getContext());
//        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
//        DatabaseReference ref = rootRef.child("EVENTS_INFO");
//
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                myPlacesArray = new ArrayList<>();
//                myPlacesArray = getAllEvents(dataSnapshot);
//                //myPlacesArray = sortEvents(myPlacesArray);
//                ListView mListView = (ListView) view.findViewById(R.id.events_listview1);
//                EventsAdapter EventAdapterForP = new EventsAdapter(getContext(),1,myPlacesArray);
//                mListView.setAdapter(EventAdapterForP);
////                populateListView(view,myPlacesArray,mListView);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//    }
//    void populateListView(View view,ArrayList<EventsClassNew> arrayList,ListView mListView){
//
//
//        EventsAdapter EventAdapterForP = new EventsAdapter(getContext(),1,arrayList);
//        mListView.setAdapter(EventAdapterForP);
//    }
//    ArrayList<EventsClassNew> sortEvents(ArrayList<EventsClassNew> arrayList){
//        Collections.sort(arrayList);
//        return null;
//    }
//    ArrayList<EventsClassNew> getAllEvents(DataSnapshot dataSnapshot){
//
//        ArrayList<EventsClassNew> events = new ArrayList<>();
//
//        for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
//            for (DataSnapshot dataSnapshot2 : dataSnapshot1.getChildren()) {
//                EventsClassNew eventsClass = dataSnapshot2.getValue(EventsClassNew.class);
//                if(isEventUpcoming(getDay(),eventsClass.getDate())) {
//                    events.add(eventsClass);
//                }
//            }
//        }
//
//        return events;
//    }
//
//
//}

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;


import android.util.Log;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.TimeZone;

import Adapters.EventsClassNew;
import ac.nita.advaitam4.R;


/**
 * Created by HRITIK on 12/18/2017.
 */

public class HistoryUpcomingEvents extends Fragment {

    Context context;

    ArrayList<EventsClassNew> myPlacesArray = new ArrayList<>();

    ProgressBar progressBar;
    ListView mListView;
    public void ListOfParticipantFragment(){

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){

        View rootView = inflater.inflate(R.layout.history_upcoming_events,container,false);
        progressBar = (ProgressBar)rootView.findViewById(R.id.progress_bar);
        mListView = (ListView) rootView.findViewById(R.id.upcoming_events_listview);
        return rootView;
    }



    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        callFirebase(view);
    }

    boolean isEventUpcoming(String todayDate,String eventDate){
        boolean result = false;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            result = sdf.parse(todayDate).before(sdf.parse(eventDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
    String getDay(){
        DateFormat formatter= new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,0);
        formatter.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
        String days=(formatter.format(calendar.getTime()));
        return days;
    }
    void callFirebase(final View view) {
        FirebaseApp.initializeApp(getContext());
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = rootRef.child("EVENTS_INFO");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                myPlacesArray = new ArrayList<>();
                myPlacesArray = getAllEvents(dataSnapshot);
                //myPlacesArray = sortEvents(myPlacesArray);
                populateListView(view,myPlacesArray);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    void populateListView(View view,ArrayList<EventsClassNew> arrayList){

        EventsAdapter eventsAdapter = new EventsAdapter(getContext(),1,arrayList);
        if(eventsAdapter!=null)
            mListView.setAdapter(eventsAdapter);
    }
    ArrayList<EventsClassNew> sortEvents(ArrayList<EventsClassNew> arrayList){
        Collections.sort(arrayList);
        return null;
    }
    ArrayList<EventsClassNew> getAllEvents(DataSnapshot dataSnapshot){

        ArrayList<EventsClassNew> events = new ArrayList<>();

        for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
            for (DataSnapshot dataSnapshot2 : dataSnapshot1.getChildren()) {
                EventsClassNew eventsClass = dataSnapshot2.getValue(EventsClassNew.class);
                if(isEventUpcoming(getDay(),eventsClass.getDate())) {
                    events.add(eventsClass);
                }
            }
        }

        return events;
    }

}
