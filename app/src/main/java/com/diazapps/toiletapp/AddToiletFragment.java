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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AddToiletFragment extends Fragment {

    DatabaseReference toiletsRef;
    @BindView(R.id.newName) EditText title;
    @BindView(R.id.newAddress) EditText address;
    @BindView(R.id.newDescription) EditText description;
    @BindView(R.id.rating_text) TextView ratingText;
    @BindView(R.id.rating) RatingBar ratingBar;
    @BindView(R.id.button2) Button submit;
    private Unbinder unbinder;

    public AddToiletFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_toilet_fragment, container, false);
        //This is where all the @BindView(R.id.whatever) Things get initiated
        unbinder = ButterKnife.bind(this, view);
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
                //Fragment map = new MapFragment();
                //FragmentTransaction transaction = getFragmentManager().beginTransaction();
                //transaction.replace(R.id.main_content, map).commit();
            }
        });
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
