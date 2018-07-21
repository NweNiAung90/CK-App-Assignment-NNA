/*------------------------------------------------------------------------------

This source is part of the assignment of the PADC Fun5 class.

Modification History


Date		Version		Author			Description
----------	-----------	--------------- ----------------------------------------
05 07 2018	1.0			Nwe Ni Aung		Initial Version.
------------------------------------------------------------------------------*/
package com.padcmyannmar.ck.network.responses;
//------------------------------------------------------------------------------
import com.google.gson.annotations.SerializedName;
import com.padcmyannmar.ck.data.vos.NewProductsVO;

import java.util.List;
//------------------------------------------------------------------------------
/*
 This VOs component the response json objects to save with object format for Charles & Keith App.
 */
public class GetNewProductsResponse {
    /* API specific data key name and attribute name are different.
     * Manually link to get same key and attribute. */

    /*
     * This field indicates code.
     */
    @SerializedName("code")
    private int code;

    /*
     * This field indicates Message.
     */
    @SerializedName("message")
    private String message;

    /*
     * This field indicates API Version.
     */
    @SerializedName("apiVersion")
    private String apiVersion;

    /*
     * This field indicates Page.
     */
    @SerializedName("page")
    private String page;

    /*
     * This field indicates a reference to  the objects of NewProductsVO
     * which stores overview details of all tag information.
     */
    @SerializedName("newProducts")
    private List<NewProductsVO> newProducts;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public String getPage() {
        return page;
    }

    public List<NewProductsVO> getNewProducts() {
        return newProducts;
    }

    /* Check Response is Ok or not */
    public boolean isResponseOK() {
        //expression
        return (code == 200 && newProducts != null);
    }
//--------------------------------------------------------------------------

}
//------------------------------------------------------------------------------
//
//		End Of File
//
//------------------------------------------------------------------------------
