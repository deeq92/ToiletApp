package com.diazapps.toiletapp;

import android.*;
import android.Manifest;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.Permission;
import java.security.Permissions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AddToiletFragment extends Fragment {

    private FusedLocationProviderClient fusedLocationProviderClient;
    DatabaseReference toiletsRef;
    @BindView(R.id.locationName) EditText title;
    @BindView(R.id.address) EditText address;
    @BindView(R.id.newDescription) EditText description;
    @BindView(R.id.rating_text) TextView ratingText;
    @BindView(R.id.rating) RatingBar ratingBar;
    @BindView(R.id.submitNewToilet) Button submit;
    private Unbinder unbinder;

    public AddToiletFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_toilet, container, false);
        //This is where all the @BindView(R.id.whatever) Things get initiated
        unbinder = ButterKnife.bind(this, view);


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toiletsRef = FirebaseDatabase.getInstance().getReference("Toilets");
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                ratingText.setText(String.valueOf(v));
            }
        });
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Toilet newToilet = new Toilet(
                        title.getText().toString(),
                        ratingBar.getRating(),
                        address.getText().toString(),
                        description.getText().toString());
                //setLatitudeAndLongtitude of the newToilet;
                toiletsRef.addListenerForSingleValueEvent(new AddToiletVEListener(getActivity(), newToilet));
                getFragmentManager().popBackStackImmediate();
            }
        });
    }


    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
