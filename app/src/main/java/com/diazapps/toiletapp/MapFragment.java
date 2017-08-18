package com.diazapps.toiletapp;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MapFragment extends android.support.v4.app.Fragment {

    private ArrayList<Toilet> toiletList; //holds all map locations
    @BindView(R.id.map) MapView mapView;
    private FusedLocationProviderClient locationClient;
    private Location location;
    static GoogleMap map;
    @BindView(R.id.bottom_sheet) View bottomSheet;
    private BottomSheetAdapter bottomSheetAdapter;
    static BottomSheetBehavior bottomSheetBehavior;
    @BindView(R.id.nearby_list) RecyclerView nearby_list;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        toiletList = new ArrayList<>();
        bottomSheetAdapter = new BottomSheetAdapter(getContext(), toiletList);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_fragment, container, false);
        ButterKnife.bind(this, view);
        nearby_list.setAdapter(bottomSheetAdapter);
        nearby_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        mapView.onCreate(savedInstanceState);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        locationClient = LocationServices.getFusedLocationProviderClient(getActivity());

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {
                map = googleMap;
                if (ActivityCompat.checkSelfPermission(getActivity(),
                        android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                googleMap.setMyLocationEnabled(true);
                locationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        location = task.getResult();
                        final LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,14)); //14 is the zoom (goes from 2-21)
                    }
                });
                getToilets();
            }

        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    private void getToilets()
    {
        toiletList.clear();
        DatabaseReference toiletsRef = FirebaseDatabase.getInstance().getReference("Toilets");
        toiletsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot node : dataSnapshot.getChildren()) {
                    String name = (String) node.child("location_name").getValue();
                    double rating = Double.valueOf(node.child("rating").getValue().toString());
                    String address = (String) node.child("location_address").getValue();
                    String comment = (String) node.child("comment").getValue();
                    double latitude = Double.valueOf(node.child("location_lat").getValue().toString());
                    double longitude = Double.valueOf(node.child("location_long").getValue().toString());
                    Toilet t = new Toilet(name, rating, address, comment, latitude, longitude);
                    t.setId(node.getKey());
                    toiletList.add(t);
                }
                for(Toilet toilet : toiletList)
                {
                    LatLng latLng = new LatLng(toilet.getLocation_lat(), toilet.getLocation_long());
                    Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.map_icon);
                    Bitmap b_resized = Bitmap.createScaledBitmap(b, 72, 72, false); //resize original image
                    Marker marker = map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title(toilet.getLocation_name())
                            .icon(BitmapDescriptorFactory.fromBitmap(b_resized)));
                    marker.showInfoWindow(); //shows title without having to click the marker
                    marker.setTag(toilet); //store the toilet object in the marker
                }
                bottomSheetAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}

