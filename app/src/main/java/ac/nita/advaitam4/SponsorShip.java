package ac.nita.advaitam4;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Info.EventsData;

public class SponsorShip extends Fragment {

    Context context;

    ArrayList<SponsorItemsSetter> myPlacesArray = new ArrayList<>();
    ListView mListView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.sponsership_activity,container,false);
    }

    public void onViewCreated(final View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        getActivity().setTitle("SPONSORS");
        FirebaseApp.initializeApp(getContext());
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = rootRef.child("SPONSOR");


        ref.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                if (dataSnapshot.exists()){
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.child("CompanyName").getChildren()){
                        SponsorItemsSetter p = dataSnapshot1.getValue(SponsorItemsSetter.class);
                        myPlacesArray.add(p);
//                        Toast.makeText(getContext(), dataSnapshot1 + "  " + myPlacesArray.get(0).getImage() , Toast.LENGTH_SHORT).show();
                    }
                    mListView = view.findViewById(R.id.sponser);
                    SponsorAdapter eventsAdapter = new SponsorAdapter(getContext(), 1, myPlacesArray);
                    mListView.setAdapter(eventsAdapter);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}



