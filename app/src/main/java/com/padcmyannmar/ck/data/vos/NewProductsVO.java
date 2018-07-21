/*------------------------------------------------------------------------------

This source is part of the assignment of the PADC Fun5 class.

Modification History


Date		Version		Author			Description
----------	-----------	--------------- ----------------------------------------
05 07 2018	1.0			Nwe Ni Aung		Initial Version.
------------------------------------------------------------------------------*/
package com.padcmyannmar.ck.data.vos;
//------------------------------------------------------------------------------

import com.google.gson.annotations.SerializedName;

//------------------------------------------------------------------------------
/*
 This VOs component the response json objects to save with object format for CK App.
 */
public class NewProductsVO {
    /* Key name and attribute name are different.
     * Manually link to get same key and attribute. */

    /*
     * This field indicates Product Id of Get New Products endpoint.
     */
    @SerializedName("product-id")
    private int productId;

    /*
     * This field indicates Product Image of Get New Products endpoint.
     */
    @SerializedName("product-image")
    private String productImage;

    /*
     * This field indicates Product Id of Get New Products endpoint.
     */
    @SerializedName("product-title")
    private String productTitle;

    public int getProductId() {
        return productId;
    }

    public String getProductImage() {
        return productImage;
    }

    public String getProductTitle() {
        return productTitle;
    }


//--------------------------------------------------------------------------

}
//------------------------------------------------------------------------------
//
//		End Of File
//
//------------------------------------------------------------------------------
