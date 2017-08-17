package com.diazapps.toiletapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AddToiletFragment extends Fragment {

    private FusedLocationProviderClient fusedLocationProviderClient;
    Location location;
    DatabaseReference toiletsRef;
    @BindView(R.id.locationName)
    EditText title;
    @BindView(R.id.address)
    EditText address;
    @BindView(R.id.newDescription)
    EditText description;
    @BindView(R.id.rating_text)
    TextView ratingText;
    @BindView(R.id.rating)
    RatingBar ratingBar;
    @BindView(R.id.submitNewToilet)
    Button submit;
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
                LocationManager lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
                boolean gps_enabled = false;
                try {
                    gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
                } catch (Exception ex) {
                }

                if (!gps_enabled) {
                    Toast.makeText(getActivity(), "Please turn on your location.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED
                            && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }

                    fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                        @Override
                        public void onComplete(@NonNull Task<Location> task) {
                            {
                                location = task.getResult();
                                final Toilet newToilet = new Toilet(
                                        title.getText().toString(),
                                        ratingBar.getRating(),
                                        address.getText().toString(),
                                        description.getText().toString(),
                                        location.getLatitude(),
                                        location.getLongitude());
                                toiletsRef.addListenerForSingleValueEvent(new AddToiletVEListener(getActivity(), newToilet));
                                getFragmentManager().popBackStackImmediate();
                            }

                        }
                    });
                }
            }
        });
    }


    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
