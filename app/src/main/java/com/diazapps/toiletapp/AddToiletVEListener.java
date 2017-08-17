package com.diazapps.toiletapp;

import android.content.Context;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Mellis on 8/11/2017.
 *
 * What else should happen when a toilet gets added by the client
 */

public class AddToiletVEListener implements ValueEventListener {

    Toilet toiletToAdd;
    String overwrite;

    public AddToiletVEListener(Context activityContext, Toilet newToilet){
        toiletToAdd = newToilet;
        overwrite = null;
    }
    public AddToiletVEListener(Context activityContext, Toilet newToilet, String id){
        toiletToAdd = newToilet;
        overwrite = id;
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        if(overwrite != null) {
            dataSnapshot.getRef().child(overwrite).setValue(toiletToAdd);
        }else {
            dataSnapshot.getRef().push().setValue(toiletToAdd);
        }
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

        //Toast.makeText(currentActivity ,"DB ERROR: Could not add or remove",Toast.LENGTH_SHORT).show();
    }
}
