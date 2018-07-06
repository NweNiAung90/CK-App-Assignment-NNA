package com.padcmyannmar.ck.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padcmyannmar.ck.R;
import com.padcmyannmar.ck.viewholders.CKDetailsViewHolder;

public class CKDetailsAdapter extends RecyclerView.Adapter {
    @NonNull
    @Override
    public CKDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_holder_ck_details,parent,false);
        return new CKDetailsViewHolder(view);
    }

    //Framework call these methods.
    //call onCreateViewHolder() when Recycler View needs to create item view
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 3;
    }
}
