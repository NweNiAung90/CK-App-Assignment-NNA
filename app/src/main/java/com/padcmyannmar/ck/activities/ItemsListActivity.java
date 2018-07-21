/*------------------------------------------------------------------------------

This source is part of the assignment of the PADC Fun5 class.

Modification History


Date		Version		Author			Description
----------	-----------	--------------- ----------------------------------------
06 07 2018	1.0			Nwe Ni Aung		Initial Version.
------------------------------------------------------------------------------*/
package com.padcmyannmar.ck.activities;
//------------------------------------------------------------------------------

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.padcmyannmar.ck.R;
import com.padcmyannmar.ck.adapters.CKAdapter;
import com.padcmyannmar.ck.data.models.CKModel;
import com.padcmyannmar.ck.data.vos.NewProductsVO;
import com.padcmyannmar.ck.delegates.CKDelegate;
import com.padcmyannmar.ck.events.ApiErrorEvent;
import com.padcmyannmar.ck.events.SuccessForceRefreshGetNewProductEvent;
import com.padcmyannmar.ck.events.SuccessGetNewProductsEvent;
import com.padcmyannmar.ck.utils.CKConstants;
import com.padcmyannmar.ck.viewpods.EmptyViewPod;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
//------------------------------------------------------------------------------

/*2nd step of Controller Pattern */
public class ItemsListActivity extends BaseActivity implements CKDelegate {
    CKAdapter mCKAdapter;

    @BindView(R.id.vp_empty)
    EmptyViewPod vpEmpty;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.rv_items)
    RecyclerView rvItems;

    @BindView(R.id.iv_single_view)
    ImageView ivSingleView;

    @BindView(R.id.v_single_selected_line)
    View vSingleSelectedLine;

    @BindView(R.id.iv_dual_view)
    ImageView ivDualView;

    @BindView(R.id.v_dual_selected_line)
    View vDualSelectedLine;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_list);
        //Bind Obj to UI Component
        ButterKnife.bind(this, this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mCKAdapter = new CKAdapter(this);
        rvItems.setAdapter(mCKAdapter);
        rvItems.setLayoutManager(new GridLayoutManager(this, 2));

        //Load More Data and calculate user scroll reach end of the list
        rvItems.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private boolean isListEndReached = false;

            @Override
            //This method is called when user start scroll and state change
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d("", "addOnScrollListener:onScrollStateChanged " + newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE &&
                        ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition()
                                == recyclerView.getAdapter().getItemCount() - 1
                        && !isListEndReached) {
                    isListEndReached = true;
                    CKModel.getObjInstance().loadProductsList();
                }

            }

            @Override
            //This method is called during scrolling
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d("", "OnScrollListener:onScrolled -dx:" + dx + "dy: " + dy);

                int visibleItemCount = recyclerView.getLayoutManager().getChildCount();
                int totalItemCount = recyclerView.getLayoutManager().getItemCount();
                int pastVisibleItems = ((LinearLayoutManager) rvItems.getLayoutManager())
                        .findFirstCompletelyVisibleItemPosition();

                if (visibleItemCount + pastVisibleItems < totalItemCount) {
                    isListEndReached = false;
                }

            }
        });

        //Load Response Data
        CKModel.getObjInstance().loadProductsList();

        ivSingleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvItems.setLayoutManager(new GridLayoutManager(v.getContext(), 1));
                vSingleSelectedLine.setVisibility(View.VISIBLE);
                vDualSelectedLine.setVisibility(View.GONE);

            }
        });

        ivDualView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvItems.setLayoutManager(new GridLayoutManager(v.getContext(), 2));
                vDualSelectedLine.setVisibility(View.VISIBLE);
                vSingleSelectedLine.setVisibility(View.GONE);
            }
        });

        CKModel.getObjInstance().forcedRefreshNewProductList();

        //show loading jatster but never disappear this loading jaster
        swipeRefreshLayout.setRefreshing(true);
        // load data when user pull down
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            //call this method while loading jatster start loading
            @Override
            public void onRefresh() {
                CKModel.getObjInstance().forcedRefreshNewProductList();

            }
        });
        vpEmpty.setEmptyData(R.drawable.empty_data_placeholder, getString(R.string.empty_msg));


    }

    /* Don't refresh UI data at Stop state. Event Bus Listen is refresh UI data. That's why unregister Event Bus at Stop state. */
    @Override
    protected void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    /* 1st step for Broadcast Listening*/
    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    /*2nd step of Controller Pattern */
    @Override
    public void onTapItemView(NewProductsVO newProducts) {
        Intent intent = new Intent(getApplicationContext(), ItemsDetailsActivity.class);
        //add id to intent
        intent.putExtra(CKConstants.PRODUCT_ID, newProducts.getProductId());
        startActivity(intent);
    }

    /* 2n Step Event Bus Listen Method */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessGetNewProducts(SuccessGetNewProductsEvent event) {
        Log.d("onSuccessGetNewProducts", "onSuccessGetNewProducts :" + event.getProductsList());
        // pass data to Adapter.
        mCKAdapter.appendProductsList(event.getProductsList());
        //make disappear after loading
        swipeRefreshLayout.setRefreshing(false);
        //Hide Empty View
        vpEmpty.setVisibility(View.GONE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessForceRefreshGetNewProducts(SuccessForceRefreshGetNewProductEvent event) {
        mCKAdapter.setmProductsList(event.getProductsList());
        swipeRefreshLayout.setRefreshing(false);
    }

    /* Good user experience for users */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFailGetNewProduct(ApiErrorEvent event) {
        //don't show loading jatster because it doesn't make sense loading jaster appear when data cannot load
        swipeRefreshLayout.setRefreshing(false);
        //solve bad experience while loading fail
        //Show Empty View
        vpEmpty.setVisibility(View.VISIBLE);
        Snackbar.make(swipeRefreshLayout, event.getErrorMsg(), Snackbar.LENGTH_INDEFINITE).show();

        if (mCKAdapter.getItemCount() < 0) {
            vpEmpty.setVisibility(View.VISIBLE);
        } else {
            //vpEmpty.setVisibility(View.GONE);

        }
    }
//--------------------------------------------------------------------------

}
//------------------------------------------------------------------------------
//
//		End Of File
//
//------------------------------------------------------------------------------
