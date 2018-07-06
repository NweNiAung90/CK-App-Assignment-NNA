/*------------------------------------------------------------------------------

This source is part of the assignment of the PADC Fun5 class.

Modification History


Date		Version		Author			Description
----------	-----------	--------------- ----------------------------------------
06 07 2018	1.0			Nwe Ni Aung		Initial Version.
------------------------------------------------------------------------------*/
package com.padcmyannmar.ck.network;

import com.padcmyannmar.ck.network.responses.GetNewProductsResponse;
import com.padcmyannmar.ck.utils.CKConstants;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface CKApi {
    /* Call<Generic Type> is <Convert Obj Format Response Type>
           @FormUrlEncoded annotation is used for Request Body Form Data
           loadTedTalksList() request param is Request Body form-data , access_token and page
           use @POST annotation to invoke API endpoint url
         */
    @FormUrlEncoded
    @POST(CKConstants.GET_CK)
    //GET_CK = "getNewProducts.php"
    Call<GetNewProductsResponse> loadCKList(
            @Field(CKConstants.PARAM_ACCESS_TOKEN) String accessToken,
            @Field(CKConstants.PAGE_ACCESS_TOKEN) int page);

//--------------------------------------------------------------------------

}
//------------------------------------------------------------------------------
//
//		End Of File
//
//------------------------------------------------------------------------------

