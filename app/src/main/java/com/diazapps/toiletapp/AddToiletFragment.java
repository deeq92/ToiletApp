package com.diazapps.toiletapp;

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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddToiletFragment extends Fragment {
    EditText title, address, description;
    TextView ratingText;
    RatingBar ratingBar;
    Button submit;
    DatabaseReference toiletsRef;

    public AddToiletFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_toilet_fragment, container, false);
        title = (EditText) view.findViewById(R.id.title);
        address = (EditText) view.findViewById(R.id.address);
        description = (EditText) view.findViewById(R.id.description);
        ratingText = (TextView) view.findViewById(R.id.rating_text);
        ratingBar = (RatingBar) view.findViewById(R.id.rating);
        submit = (Button) view.findViewById(R.id.button2);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        toiletsRef = FirebaseDatabase.getInstance().getReference("Toilets");
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                ratingText.setText(String.valueOf(v));
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toilet newToilet = new Toilet(
                        title.getText().toString(),
                        ratingBar.getRating(),
                        address.getText().toString(),
                        description.getText().toString());
                toiletsRef.addListenerForSingleValueEvent(new AddToiletVEListener(getActivity(), newToilet));
            }
        });
    }

}
