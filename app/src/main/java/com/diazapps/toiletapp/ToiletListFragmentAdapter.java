package com.diazapps.toiletapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.provider.Contacts.SettingsColumns.KEY;

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
        holder.itemView.setOnClickListener(holder);
    }

    @Override
    public int getItemCount() {
        return toiletList.size();
    }

    public static class ToiletHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Toilet toilet;
        @BindView(R.id.toiletName) TextView toiletName;
        @BindView(R.id.textView2) TextView tv2;
        @BindView(R.id.textView3) TextView tv3;
        @BindView(R.id.textView4) TextView tv4;

        public ToiletHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

        @Override
        public void onClick(View v) {
        }
    }
}
