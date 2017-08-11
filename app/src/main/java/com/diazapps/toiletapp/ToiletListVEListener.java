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
        //Add all the jobs to the array list
        for (DataSnapshot jobNode : dataSnapshot.getChildren()) {
            String title = (String) jobNode.child("location_name").getValue();
            String address = (String) jobNode.child("location_address").getValue();
            String description = (String) jobNode.child("comment").getValue();
            double rating = (double) jobNode.child("rating").getValue();
            String postid = jobNode.getKey();
            toiletList.add(new Toilet(title, rating, address, description));
        }
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        //Toast.makeText(thisContext ,"DB ERROR: Could not get jobs",Toast.LENGTH_SHORT).show();
    }
}