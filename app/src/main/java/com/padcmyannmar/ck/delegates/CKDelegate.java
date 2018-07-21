/*------------------------------------------------------------------------------

This source is part of the assignment of the PADC Fun5 class.

Modification History


Date		Version		Author			Description
----------	-----------	--------------- ----------------------------------------
30 06 2018	1.0			Nwe Ni Aung		Initial Version.
------------------------------------------------------------------------------*/
package com.padcmyannmar.ck.delegates;
//------------------------------------------------------------------------------
import com.padcmyannmar.ck.data.vos.NewProductsVO;
//------------------------------------------------------------------------------
/*1st step of Controller Pattern */
public interface CKDelegate {
    // add param to know user tap which itemView
    void onTapItemView(NewProductsVO newProducts);
//------------------------------------------------------------------------------

}
//------------------------------------------------------------------------------
//
//		End Of File
//
//------------------------------------------------------------------------------
