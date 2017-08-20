package com.diazapps.toiletapp;

import android.content.Context;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;

import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BottomSheetAdapter extends RecyclerView.Adapter<BottomSheetAdapter.Itemholder> {

    private static ArrayList<Toilet> toiletList;
    private static Context c;
    DecimalFormat decimalFormat = new DecimalFormat("0.0");

    public BottomSheetAdapter (Context ctx, ArrayList<Toilet> toilets)
    {
        toiletList = toilets;
        c = ctx;
    }

    @Override
    public Itemholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(c).inflate(R.layout.bottom_sheet_item, parent, false);
        return new Itemholder(view);
    }

    @Override
    public void onBindViewHolder(Itemholder holder, int position) {

        holder.t = toiletList.get(position);
        holder.title.setText(holder.t.getLocation_name());
        holder.rating.setText(String.valueOf(holder.t.getRating()));
        holder.distance.setText(String.valueOf(decimalFormat.format(holder.t.getDistance_away())) + " miles away");
        holder.itemView.setOnClickListener(holder);

    }

    @Override
    public int getItemCount() {
        return toiletList.size();
    }

    public static class Itemholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        Toilet t;
        @BindView(R.id.pic) ImageView pic;
        @BindView(R.id.title) TextView title;
        @BindView(R.id.rating) TextView rating;
        @BindView(R.id.distance) TextView distance;


        public Itemholder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

        @Override
        public void onClick(View v) {
            LatLng latLng = new LatLng(t.getLocation_lat(), t.getLocation_long());
            MapFragment.map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
            MapFragment.bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
    }
}
