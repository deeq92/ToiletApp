package com.diazapps.toiletapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class StartFragment extends Fragment {

    @BindView(R.id.review_button) Button addToilet;
    @BindView(R.id.map_button) Button map;
    @BindView(R.id.list_button) Button toiletList;
    private Unbinder unbinder;

    public StartFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.start_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        addToilet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment addToilet = new AddToiletFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.addToBackStack("newlocation");
                transaction.replace(R.id.main_content, addToilet).commit();
            }
        });

        toiletList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment listFragment = new ToiletListFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.addToBackStack("list");
                transaction.replace(R.id.main_content, listFragment).commit();
            }
        });

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment map = new MapFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.addToBackStack("newlocation");
                transaction.replace(R.id.main_content, map).commit();
            }
        });
    }
}
