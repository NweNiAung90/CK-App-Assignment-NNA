/*------------------------------------------------------------------------------

This source is part of the assignment of the PADC Fun5 class.

Modification History


Date		Version		Author			Description
----------	-----------	--------------- ----------------------------------------
18 06 2018	1.0			Nwe Ni Aung		Initial Version.
------------------------------------------------------------------------------*/
package com.padcmyannmar.ck.adapters;
//------------------------------------------------------------------------------
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padcmyannmar.ck.R;
import com.padcmyannmar.ck.data.vos.NewProductsVO;
import com.padcmyannmar.ck.delegates.CKDelegate;
import com.padcmyannmar.ck.viewholders.CKViewHolder;

import java.util.ArrayList;
import java.util.List;

//------------------------------------------------------------------------------
/* 1 Adapter tightly connected with 1 View Holder */
public class CKAdapter extends RecyclerView.Adapter<CKViewHolder> {
    private CKDelegate mCkDelegate;

    private List<NewProductsVO> mProductsList;

    public CKAdapter(CKDelegate ckDelegate) {
        mCkDelegate = ckDelegate;
        mProductsList = new ArrayList<>();
    }


    //Framework call these methods.
    //call onCreateViewHolder() when Recycler View needs to create item view
    @NonNull
    @Override
    public CKViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_holder_ck,parent,false);
        return new CKViewHolder(view, mCkDelegate);
    }

    /*call onBindViewHolder for adding data to Item View and Bind data to Item View when user scroll down*/
    @Override
    public void onBindViewHolder(@NonNull CKViewHolder holder, int position) {
        //Adjust Adapter to change POC to live
        holder.setProductsData(mProductsList.get(position));
    }

    @Override
    public int getItemCount() {
        return mProductsList.size();
    }

    public void setmProductsList(List<NewProductsVO> mProductsList) {
        this.mProductsList = mProductsList;
        //refresh all itemViews
        notifyDataSetChanged();
    }

    //to append news data behind old data
    public void appendProductsList(List<NewProductsVO> productsList) {
        productsList.addAll(productsList);
        notifyDataSetChanged();
    }
//--------------------------------------------------------------------------

}
//------------------------------------------------------------------------------
//
//		End Of File
//
//------------------------------------------------------------------------------
