package com.diazapps.toiletapp;

import android.content.Context;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Mellis on 8/16/2017.
 */

public class AddReviewVEListener implements ValueEventListener {
    Review review;
    String toilet;

    public AddReviewVEListener(Context activityContext, Review newReview, String toiletId){
        review = newReview;
        toilet = toiletId;
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        dataSnapshot.getRef().child(toilet).push().setValue(review);
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
