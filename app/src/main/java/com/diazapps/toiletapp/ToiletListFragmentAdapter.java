package com.diazapps.toiletapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mellis on 8/12/2017.
 */

public class ToiletListFragmentAdapter extends RecyclerView.Adapter<ToiletListFragmentAdapter.ToiletHolder> {

    private ArrayList<Toilet> toiletList;
    private static Context context;

    public ToiletListFragmentAdapter(Context ctx, ArrayList<Toilet> toiletList){
        this.toiletList = toiletList;
        context = ctx;
    }
    @Override
    public ToiletHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.toilet_row_item, parent, false);
        return new ToiletHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(ToiletListFragmentAdapter.ToiletHolder holder, int position) {

        Toilet t = toiletList.get(position);
        holder.toiletName.setText(t.getLocation_name());
        holder.rating.setText(String.valueOf(t.getRating()));
        holder.address.setText(t.getLocation_address());
        holder.comment.setText(t.getComment());
        holder.toilet = toiletList.get(position);
        holder.itemView.setOnClickListener(holder);
    }

    @Override
    public int getItemCount() {
        return toiletList.size();
    }

    public static class ToiletHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Toilet toilet;
        @BindView(R.id.toiletName) TextView toiletName;
        @BindView(R.id.address) TextView address;
        @BindView(R.id.rating) TextView rating;
        @BindView(R.id.comment) TextView comment;

        public ToiletHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

        @Override
        public void onClick(View v) {

            Fragment toiletDetail = ToiletDetailFragment.newInstance(toilet);
            FragmentTransaction transaction = ((MainActivity) context).getSupportFragmentManager().beginTransaction();
            transaction.addToBackStack("detail");
            transaction.replace(R.id.main_content, toiletDetail).commit();
        }
    }
}
