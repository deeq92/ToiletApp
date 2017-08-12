package com.diazapps.toiletapp;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class StartFragment extends Fragment {

    Button review;
    Button map;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.start_fragment, container, false);
        review = (Button) view.findViewById(R.id.review_button);
        map = (Button) view.findViewById(R.id.map_button);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment rating = new AddToiletFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_content, rating).commit();
            }
        });

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment map = new MapFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_content, map).commit();
            }
        });
    }
}
