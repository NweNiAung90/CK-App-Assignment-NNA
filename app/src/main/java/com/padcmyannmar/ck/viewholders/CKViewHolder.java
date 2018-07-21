/*------------------------------------------------------------------------------

This source is part of the assignment of the PADC Fun5 class.

Modification History


Date		Version		Author			Description
----------	-----------	--------------- ----------------------------------------
06 07 2018	1.0			Nwe Ni Aung		Initial Version.
------------------------------------------------------------------------------*/
package com.padcmyannmar.ck.viewholders;
//------------------------------------------------------------------------------
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.padcmyannmar.ck.R;
import com.padcmyannmar.ck.data.vos.NewProductsVO;
import com.padcmyannmar.ck.delegates.CKDelegate;
import com.padcmyannmar.ck.utils.GlideApp;

import butterknife.BindView;
import butterknife.ButterKnife;
//------------------------------------------------------------------------------
public class CKViewHolder extends RecyclerView.ViewHolder {
    //productImage
    @BindView(R.id.iv_ck_shoes)
    public ImageView ivProductImage;
    //productTitle
    @BindView(R.id.tv_title)
    public TextView tvTitle;
    /*3rd step of controller pattern*/
    private CKDelegate mCkDelegate;
    private NewProductsVO mNewProducts;

    /* relay to activity */
    public CKViewHolder(View itemView, CKDelegate ckDelegate) {
        super(itemView);

        //Bind with ButterKnife
        ButterKnife.bind(this, itemView);

        mCkDelegate = ckDelegate;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*4th step of controller pattern */
                mCkDelegate.onTapItemView(mNewProducts);
            }
        });
    }

    public void setProductsData(NewProductsVO newProducts) {
        this.mNewProducts = newProducts;
        tvTitle.setText(newProducts.getProductTitle());

        if (!newProducts.getProductImage().isEmpty()) {
            //get image from network
            GlideApp.with(ivProductImage.getContext())
                    .load(newProducts.getProductImage())
                    .placeholder(R.drawable.img_placeholder)
                    .error(R.drawable.img_errorstop)
                    .into(ivProductImage);
        } else {
            //hide null image place
            ivProductImage.setVisibility(View.GONE);
        }

    }
//--------------------------------------------------------------------------

}
//------------------------------------------------------------------------------
//
//		End Of File
//
//------------------------------------------------------------------------------

