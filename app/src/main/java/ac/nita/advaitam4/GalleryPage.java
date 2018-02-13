package ac.nita.advaitam4;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Adapters.GalleryAdapter;
import Info.EventsData;

public class GalleryPage extends Fragment {


    RecyclerView recyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    GalleryAdapter galleryAdapter;
    DatabaseReference mRef ;
    List<String> list = new ArrayList<>();
    public GalleryPage(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.gallerypart2,container,false);
        recyclerView = view.findViewById(R.id.recForGallery);
        mRef = FirebaseDatabase.getInstance().getReference();
        return view;
    }




    public void onViewCreated(View view,Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        getActivity().setTitle("Gallery");
        readData(new MyCallback2() {
            @Override
            public void onCallback2(List<String> list) {
                galleryAdapter = new GalleryAdapter(getContext(), list);
                mLayoutManager = new StaggeredGridLayoutManager(3,1);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(galleryAdapter);
            }

        });




    }



    public void readData(final MyCallback2 myCallback1) {
        mRef.child("GALLERY").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    list.clear();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        String p = dataSnapshot1.getValue(String.class);
                        list.add(p);
                     }
                    myCallback1.onCallback2(list);

                } else {
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        });
    }



}
