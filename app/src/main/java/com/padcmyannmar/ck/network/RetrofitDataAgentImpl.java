package com.padcmyannmar.ck.network;

import com.padcmyannmar.ck.events.ApiErrorEvent;
import com.padcmyannmar.ck.events.SuccessForceRefreshGetNewProductEvent;
import com.padcmyannmar.ck.events.SuccessGetNewProductsEvent;
import com.padcmyannmar.ck.network.responses.GetNewProductsResponse;
import com.padcmyannmar.ck.utils.CKConstants;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitDataAgentImpl implements CKDataAgent {
    private static RetrofitDataAgentImpl sObjInstance;

    private CKApi mTheApi;

    // add required initialization for Retrofit
    private RetrofitDataAgentImpl() {
        //configure okHtttpClient
        final OkHttpClient okHttpClient = new OkHttpClient.Builder() //1.
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        /* configure Retrofit Object
        API_BASE = "http://padcmyanmar.com/padc-5/ck/"
        */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CKConstants.API_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        /*pass interface type from retrofit object and take interface object
          CKApi Object Initialization
        */
        mTheApi = retrofit.create(CKApi.class);
    }

    public static RetrofitDataAgentImpl getInstance() {
        /*Factory Logic
         * only one obj for this class type
         */
        if(sObjInstance == null){
            sObjInstance = new RetrofitDataAgentImpl();
        }
        return sObjInstance;
    }

    @Override
    public void loadProductsList(String accessToken, int page,final boolean isForceRefresh) {
        /* call Interface Type method with param and get Call object
           Pass GetNewProductsResponse type as Call Object Generic Type */
        Call<GetNewProductsResponse> loadNewProductsCall = mTheApi.loadCKList(accessToken,page);
        // pass Callback Interface as Anonymous Inner Class
        loadNewProductsCall.enqueue(new Callback<GetNewProductsResponse>() {
            @Override
            public void onResponse(Call<GetNewProductsResponse> call, Response<GetNewProductsResponse> response) {
                //Retrieve Response Body
                GetNewProductsResponse newProductsResponse = response.body();
                //Response is ok.
                if(newProductsResponse != null && newProductsResponse.isResponseOK()){
                    if(isForceRefresh){
                        SuccessForceRefreshGetNewProductEvent event = new SuccessForceRefreshGetNewProductEvent(newProductsResponse.getNewProducts());
                        EventBus.getDefault().post(event);
                    }else {
                     SuccessGetNewProductsEvent event = new SuccessGetNewProductsEvent(newProductsResponse.getNewProducts());
                    EventBus.getDefault().post(event);
                    }
                }else { //Response is not ok. Error Case. Response Null Case
                    if(newProductsResponse == null){
                        //For 200OK
                        ApiErrorEvent event = new ApiErrorEvent("Empty response in network call.");
                        EventBus.getDefault().post(event);
                    }else {
                        //shows server message
                        ApiErrorEvent event = new ApiErrorEvent(newProductsResponse.getMessage());
                        EventBus.getDefault().post(event);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetNewProductsResponse> call, Throwable t) {
                //cannot get internet connection or  serer down or api crush
                ApiErrorEvent event = new ApiErrorEvent(t.getMessage());
                EventBus.getDefault().post(event);
            }
        });




    }


//--------------------------------------------------------------------------

}
//------------------------------------------------------------------------------
//
//		End Of File
//
//------------------------------------------------------------------------------

