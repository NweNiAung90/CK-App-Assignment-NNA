package com.padcmyannmar.ck.data.models;

import com.padcmyannmar.ck.data.vos.NewProductsVO;
import com.padcmyannmar.ck.events.SuccessForceRefreshGetNewProductEvent;
import com.padcmyannmar.ck.events.SuccessGetNewProductsEvent;
import com.padcmyannmar.ck.network.CKDataAgent;
import com.padcmyannmar.ck.network.RetrofitDataAgentImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CKModel {

    private static final String DUMMY_ACCESS_TOKEN = "b002c7e1a528b7cb460933fc2875e916";

    /* Singleton Design Pattern */
    //2. static class type attribute
    private static CKModel objInstance;

    private CKDataAgent mDataAgent;

    private Map<Integer, NewProductsVO> mNewProductsMap;

    private int mPage;

    //1. private constructor
    public CKModel() {
        mDataAgent = RetrofitDataAgentImpl.getInstance();
        /*empty collection type for mull safety
          attribute is Map , object initialize is HashMap
        */
        mNewProductsMap = new HashMap<>();
        mPage = 1;

        /* 2 steps for listen  broadcast event from EventBus
           1. register event
           2. define Event listen method
        * */
        EventBus.getDefault().register(this);
    }

    //3. getter method -Factory Logic
    public static CKModel getObjInstance() {
        /*Factory Logic
         * only one obj for this class type
         */
        if (objInstance == null) {
            objInstance = new CKModel();
        }
        return objInstance;
    }

    /*
     * loadProductsList - This method is called load Products List from network call
     *
     * @param accessToken
     * @param page
     * @return void         listen response asynchronously
     */
    public void loadProductsList() {
        mDataAgent.loadProductsList(DUMMY_ACCESS_TOKEN, mPage, false);

    }

    public void forcedRefreshNewProductList(){
        //for mPage++ issue ,set page 1 for don't miss pages
        mPage = 1;
        //for pull to refresh check news data comes or not
        mDataAgent.loadProductsList(DUMMY_ACCESS_TOKEN, 1,true);

    }

    /*Send Id to details activity
     Retrieve data from data Repository
   */
    public NewProductsVO getProductById(Integer productId) {
        return null; //TODO remove this after testing empty view layout in news details screen.
        //return mNewProductsMap.get(productId);
    }

    //composite
    /* Define Event Listen Method */
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onSuccessGetNewProducts(SuccessGetNewProductsEvent event) {
        //Reach data into data repository
        //cannot directly add list into Map
        setDataIntoRepository(event.getProductsList());
        mPage++;

    }

    //composite
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onSuccessForceRefreshGetNewProducts(SuccessForceRefreshGetNewProductEvent event){
        setDataIntoRepository(event.getProductsList());
    }

    //helper method
    private void setDataIntoRepository( List<NewProductsVO> productsList){
        for (NewProductsVO newProducts : productsList) {
            mNewProductsMap.put(newProducts.getProductId(), newProducts);
        }
    }
//--------------------------------------------------------------------------

}
//------------------------------------------------------------------------------
//
//		End Of File
//
//------------------------------------------------------------------------------
