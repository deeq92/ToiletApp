package com.diazapps.toiletapp;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Mellis on 8/11/2017.
 *
 * What else should happen when a toilet gets added by the client
 */

public class AddToiletVEListener implements ValueEventListener {

    Context context;
    Toilet toiletToAdd;

    public AddToiletVEListener(Context activityContext, Toilet newToilet){
        toiletToAdd = newToilet;
        context = activityContext;
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        dataSnapshot.getRef().push().setValue(toiletToAdd);
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        Toast.makeText(context ,"DB ERROR: Could not add or remove",Toast.LENGTH_SHORT).show();
    }
}
