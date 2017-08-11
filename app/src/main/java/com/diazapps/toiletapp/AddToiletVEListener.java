package com.diazapps.toiletapp;

import android.content.Context;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Mellis on 8/11/2017.
 */

public class AddToiletVEListener implements ValueEventListener {

    Toilet toiletToAdd;

    public AddToiletVEListener(Context activityContext, Toilet newToilet){
        toiletToAdd = newToilet;
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        dataSnapshot.getRef().push().setValue(toiletToAdd);
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
