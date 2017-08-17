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

public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.ReviewHolder> {

    private ArrayList<Review> reviewList;
    private static Context context;

    public ReviewListAdapter(Context ctx, ArrayList<Review> reviews){
        this.reviewList = reviews;
        context = ctx;
    }
    @Override
    public ReviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_row_item, parent, false);
        return new ReviewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(ReviewListAdapter.ReviewHolder holder, int position) {

        Review r = reviewList.get(position);
        holder.rating.setText(String.valueOf(r.getRating()));
        holder.comment.setText(r.getComment());
        holder.review = r;
        holder.itemView.setOnClickListener(holder);
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public static class ReviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Review review;
        @BindView(R.id.rating) TextView rating;
        @BindView(R.id.comment) TextView comment;

        public ReviewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

        @Override
        public void onClick(View v) {
        }
    }
}
