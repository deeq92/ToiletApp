package com.diazapps.toiletapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class ToiletDetailFragment extends Fragment {

    private static final String TOILET = "toilet_key";

    @BindView(R.id.locationName) TextView name;
    @BindView(R.id.rating) TextView rating;
    @BindView(R.id.address) TextView address;
    @BindView(R.id.description) TextView description;
    @BindView(R.id.review_button) Button reviewButton;
    @BindView(R.id.reviewListFrag) FrameLayout reviewListFragLayout;
    private Unbinder unbinder;

    Toilet toilet;
    private ArrayList<Review> reviewList;
    private ReviewListAdapter reviewAdapter;

    public ToiletDetailFragment() {
        // Required empty public constructor
    }

    public static ToiletDetailFragment newInstance(Toilet toilet) {
        ToiletDetailFragment fragment = new ToiletDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(TOILET, toilet);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            toilet = (Toilet) getArguments().getSerializable(TOILET);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_toilet_detail, container, false);
        unbinder = ButterKnife.bind(this,view);
        name.setText(toilet.getLocation_name());
        address.setText(toilet.getLocation_address());
        description.setText(toilet.getDescription());
        rating.setText(String.valueOf(toilet.getRating()));

        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment reviewToilet = ReviewToiletFragment.newInstance(toilet);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.addToBackStack("review");
                transaction.replace(R.id.main_content, reviewToilet).commit();
            }
        });


        reviewList = new ArrayList<>();
        reviewAdapter = new ReviewListAdapter(getContext(), reviewList);
        DatabaseReference reviewsRef = FirebaseDatabase.getInstance().getReference("Reviews").child(toilet.getId());
        Log.d("TTT"," : " + toilet.getId());
        ReviewListVEListener reviewListVEListener = new ReviewListVEListener(getContext(), reviewList, reviewAdapter);
        reviewsRef.addListenerForSingleValueEvent(reviewListVEListener);

        Fragment reviewListFrag = ReviewListFragment.newInstance(toilet);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.addToBackStack("reviewList");
        transaction.replace(R.id.reviewListFrag, reviewListFrag).commit();

        return view;
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
