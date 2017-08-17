package com.diazapps.toiletapp;

import android.content.Context;
import android.net.Uri;
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

public class ToiletListFragment extends Fragment {

    RecyclerView toiletRecyclerView;
    ToiletListFragmentAdapter toiletAdapter;
    ArrayList<Toilet> toiletList;

    public ToiletListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters
     */
    public static ToiletListFragment newInstance() {
        ToiletListFragment fragment = new ToiletListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        toiletList = new ArrayList<>();
        toiletAdapter = new ToiletListFragmentAdapter(getContext(), toiletList);
        DatabaseReference toiletsRef = FirebaseDatabase.getInstance().getReference("Toilets");
        ToiletListVEListener toiletListVEListener = new ToiletListVEListener(getContext(), toiletList, toiletAdapter);
        toiletsRef.addListenerForSingleValueEvent(toiletListVEListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_toilet_list, container, false);
        toiletRecyclerView = (RecyclerView) view.findViewById(R.id.toiletRecyclerView);
        toiletRecyclerView.setAdapter(toiletAdapter);
        toiletRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
}
