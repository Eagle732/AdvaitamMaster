package ac.nita.advaitam4;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import Adapters.GalleryAdapter;
import Adapters.GalleryRecyclerAdapter;
import Info.Place;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * Created by sourav8256 on 12/18/2017.
 */

public class Gallery extends Fragment {

    Context context;

    ArrayList<Place> myPlacesArray;

    ProgressBar progressBar;
    private DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();
    private List<String> list = new ArrayList<>();
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){

        getActivity().setTitle("Gallery");
        View rootView = inflater.inflate(R.layout.content_gallery,container,false);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FirebaseApp.initializeApp(getContext());
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
//        DatabaseReference ref = rootRef.child("data/events/event1/participants");



        readData(new MyCallback2() {
            @Override
            public void onCallback2(List<String> list) {
//                galleryAdapter = new GalleryAdapter(getContext(), list);
//                mLayoutManager = new StaggeredGridLayoutManager(3,1);
//                recyclerView.setLayoutManager(mLayoutManager);
//                recyclerView.setAdapter(galleryAdapter);


                int length =list.size()/10;
                 String[][] imageUrls = new String[length][10];


                for(int j=0;j<list.size()/10;j++)
                for(int i=0;i<10;i++){
                    imageUrls[j][i] = list.get(i+j);
                }

                LinearLayoutManager layoutManager
                        = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                RecyclerView recyclerView1 = (RecyclerView) view.findViewById(R.id.gallery_rv);
                GalleryRecyclerAdapter galleryRecyclerAdapter = new GalleryRecyclerAdapter(getActivity(),imageUrls);
                GridLayoutManager mLayoutManager1 = new GridLayoutManager(getApplicationContext(),1,GridLayoutManager.HORIZONTAL,false);
                recyclerView1.setLayoutManager(mLayoutManager1);
                //recyclerView1.setItemViewCacheSize(3);
                recyclerView1.setItemAnimator(new DefaultItemAnimator());
                recyclerView1.setAdapter(galleryRecyclerAdapter);



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


    }}


