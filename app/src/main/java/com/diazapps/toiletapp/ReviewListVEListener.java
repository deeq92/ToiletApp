package com.diazapps.toiletapp;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Mellis on 8/16/2017.
 */

class ReviewListVEListener implements ValueEventListener{
    private final ArrayList<Review> reviewList;
    private final Context context;
    ReviewListAdapter adapter;

    //Used to get all toilets
    public ReviewListVEListener(Context context, ArrayList<Review> reviews, ReviewListAdapter adapter){
        this.context = context;
        reviewList = reviews;
        this.adapter = adapter;
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        reviewList.clear();
        for (DataSnapshot node : dataSnapshot.getChildren()) {
            double rating = Double.valueOf(node.child("rating").getValue().toString());
            String comment = (String) node.child("comment").getValue();
            Review r = new Review(rating, comment);
            Log.d("RRR"," : " + r.getComment() + " " + r.getRating());
            reviewList.add(r);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
