package com.diazapps.toiletapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

public class ReviewToiletFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";

    Toilet toilet;
    DatabaseReference toiletsRef;
    @BindView(R.id.locationName) TextView title;
    @BindView(R.id.address) TextView address;
    @BindView(R.id.newDescription) EditText description;
    @BindView(R.id.rating_text) TextView ratingText;
    @BindView(R.id.rating) RatingBar ratingBar;
    @BindView(R.id.submitNewToilet) Button submit;
    private Unbinder unbinder;

    public ReviewToiletFragment() {
        // Required empty public constructor
    }

    public static ReviewToiletFragment newInstance(Toilet toilet) {
        ReviewToiletFragment fragment = new ReviewToiletFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, toilet);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            toilet = (Toilet) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_review_toilet, container, false);
        //This is where all the @BindView(R.id.whatever) Things get initiated
        unbinder = ButterKnife.bind(this, view);
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        title.setText(toilet.getLocation_name());
        address.setText(toilet.getLocation_address());
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
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
