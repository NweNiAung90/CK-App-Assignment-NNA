/*------------------------------------------------------------------------------

This source is part of the assignment of the PADC Fun5 class.

Modification History


Date		Version		Author			Description
----------	-----------	--------------- ----------------------------------------
30 06 2018	1.0			Nwe Ni Aung		Initial Version.
------------------------------------------------------------------------------*/
package com.padcmyannmar.ck.activities;
//------------------------------------------------------------------------------
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.padcmyannmar.ck.R;
import com.padcmyannmar.ck.adapters.CKDetailsAdapter;
import com.padcmyannmar.ck.adapters.CKLikeAdapter;
import com.padcmyannmar.ck.data.models.CKModel;
import com.padcmyannmar.ck.data.vos.NewProductsVO;
import com.padcmyannmar.ck.utils.CKConstants;
import com.padcmyannmar.ck.viewpods.EmptyViewPod;

import butterknife.BindView;
import butterknife.ButterKnife;
//------------------------------------------------------------------------------
public class ItemsDetailsActivity extends BaseActivity {

    CKDetailsAdapter mCKDetailsAdapter;
    CKLikeAdapter mCKCkLikeAdapter;

    @BindView(R.id.rv_details_items)
    RecyclerView rvDetailsItems;

    @BindView(R.id.rv_like_items)
    RecyclerView rvLikeItems;

    @BindView(R.id.vp_empty)
    EmptyViewPod vpEmpty;

    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_details);

        //Bind Obj to UI Component
        ButterKnife.bind(this, this);

        mCKDetailsAdapter = new CKDetailsAdapter();
        rvDetailsItems.setAdapter(mCKDetailsAdapter);
        rvDetailsItems.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));

        mCKCkLikeAdapter = new CKLikeAdapter();
        rvLikeItems.setAdapter(mCKCkLikeAdapter);
        rvLikeItems.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL, false));

        //Get productId from from putExtra()
        int productId = getIntent().getIntExtra(CKConstants.PRODUCT_ID, 1);

        Log.d("ItemsDetailsActivity", "productId : " + productId);

        //Retrieve ID
        NewProductsVO newProducts = CKModel.getObjInstance().getProductById(productId);
        //Check news before show Empty View List
        if (newProducts != null) {
            bindData(newProducts);
        } else {
            vpEmpty.setVisibility(View.VISIBLE);
            //hide coordinatorLayout when data cann't show
            coordinatorLayout.setVisibility(View.GONE);
        }
        vpEmpty.setEmptyData("https://www.iconspng.com/images/sad-frog-feels-bad-man-meme/sad-frog-feels-bad-man-meme.jpg"
                , getString(R.string.empty_msg_news_details));
    }

    private void bindData(NewProductsVO newProducts) {


    }

//------------------------------------------------------------------------------

}
//------------------------------------------------------------------------------
//
//		End Of File
//
//------------------------------------------------------------------------------