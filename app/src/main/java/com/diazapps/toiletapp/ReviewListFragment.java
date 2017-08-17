package com.diazapps.toiletapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ReviewListFragment extends Fragment {

    private static final String TOILET = "toilet_key";
    RecyclerView reviewRecyclerView;
    ReviewListAdapter reviewAdapter;
    ArrayList<Review> reviewList;

    public ReviewListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters
     */
    public static ReviewListFragment newInstance(Toilet toilet) {
        ReviewListFragment fragment = new ReviewListFragment();
        Bundle args = new Bundle();
        args.putSerializable(TOILET, toilet);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toilet t = (Toilet) getArguments().getSerializable(TOILET);
        reviewList = new ArrayList<>();
        reviewAdapter = new ReviewListAdapter(getContext(), reviewList);
        DatabaseReference reviewsRef = FirebaseDatabase.getInstance().getReference("Reviews").child(t.getId());
        ReviewListVEListener reviewListVEListener = new ReviewListVEListener(getContext(), reviewList, reviewAdapter);
        reviewsRef.addListenerForSingleValueEvent(reviewListVEListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_review_list, container, false);
        reviewRecyclerView = (RecyclerView) view.findViewById(R.id.reviewRecycler);
        reviewRecyclerView.setAdapter(reviewAdapter);
        reviewRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
}
