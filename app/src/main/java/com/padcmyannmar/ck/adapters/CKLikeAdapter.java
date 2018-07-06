package com.padcmyannmar.ck.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padcmyannmar.ck.R;
import com.padcmyannmar.ck.viewholders.CKLikeViewHolder;

public class CKLikeAdapter extends RecyclerView.Adapter{
    @NonNull
    @Override
    public CKLikeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_holder_ck_like,parent,false);
        return new CKLikeViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 5;
    }
}
