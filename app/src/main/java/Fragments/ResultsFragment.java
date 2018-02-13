package Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ac.nita.advaitam4.R;

/**
 * Created by HP on 06-08-2017.
 */

public class ResultsFragment extends Fragment {

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	/* *
	* 1. Inflate the xml
	* 2. Setup the UI
	* 3. Return the rootView
	* */
        final View rootView =  inflater.inflate(R.layout.fragment_results, container, false);

	    String add1 = getArguments().getString("KEY3");
        final Map<String ,String> ItemsData = new HashMap<>();
        final List<String> myPlacesArray = new ArrayList<>();
        DatabaseReference participantsRef = firebaseDatabase.getReference(add1);


        participantsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                myPlacesArray.clear();
                if(dataSnapshot.exists()){
                    int i=0;
                    for(DataSnapshot  op : dataSnapshot.getChildren()){

                        String items = op.getValue().toString();
//                        ItemsData.put("Name"+i,items);
                        myPlacesArray.add(items);
                        i++;
                    }
                }

//                Log.d("Tag",myPlacesArray.toString());
                ListView mListView =  rootView.findViewById(R.id.listview_results);
//                PlaceArrayAdapter mArrayAdapter = new PlaceArrayAdapter(getActivity(), R.layout.row, myPlacesArray);
//                mListView.setAdapter(mArrayAdapter);
////
                ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(getActivity(),R.layout.list_item,myPlacesArray){
                    @NonNull
                    @Override
                    public View getView(int position, View convertView, @NonNull ViewGroup parent){
                        View view = super.getView(position,convertView,parent);
                        TextView textView = (TextView)view.findViewById(R.id.text_11);
                        textView.setTextColor(Color.WHITE);
                        return view;
                    }
                };
                mListView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return rootView;


    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
