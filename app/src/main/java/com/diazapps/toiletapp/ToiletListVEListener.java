package com.diazapps.toiletapp;

import android.content.Context;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Mellis on 8/11/2017.
 */

public class ToiletListVEListener implements ValueEventListener {

    private final ArrayList<Toilet> toiletList;
    private final Context thisContext;

    //Used to get all toilets
    public ToiletListVEListener(Context context, ArrayList<Toilet> toilets){
        thisContext = context;
        toiletList = toilets;
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        toiletList.clear();
        for (DataSnapshot node : dataSnapshot.getChildren()) {
            String title = (String) node.child("location_name").getValue();
            String address = (String) node.child("location_address").getValue();
            String description = (String) node.child("comment").getValue();
            double rating = (double) node.child("rating").getValue();
            String postid = node.getKey();
            toiletList.add(new Toilet(title, rating, address, description));
        }
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        //Toast.makeText(thisContext ,"DB ERROR: Could not get jobs",Toast.LENGTH_SHORT).show();
    }
}