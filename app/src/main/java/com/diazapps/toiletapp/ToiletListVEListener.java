package com.diazapps.toiletapp;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ToiletListVEListener implements ValueEventListener {

    private final ArrayList<Toilet> toiletList;
    private final Context context;
    ToiletListFragmentAdapter adapter;

    //Used to get all toilets
    public ToiletListVEListener(Context context, ArrayList<Toilet> toilets, ToiletListFragmentAdapter adapter){
        this.context = context;
        toiletList = toilets;
        this.adapter = adapter;
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        toiletList.clear();
        for (DataSnapshot node : dataSnapshot.getChildren()) {
            String name = (String) node.child("location_name").getValue();
            double rating = Double.valueOf(node.child("rating").getValue().toString());
            String address = (String) node.child("location_address").getValue();
            String description = (String) node.child("description").getValue();
            String postid = node.getKey();
            toiletList.add(new Toilet(name, rating, address, description));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        //Toast.makeText(thisContext ,"DB ERROR: Could not get jobs",Toast.LENGTH_SHORT).show();
    }
}