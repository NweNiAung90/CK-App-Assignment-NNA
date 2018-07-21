/*------------------------------------------------------------------------------

This source is part of the assignment of the PADC Fun5 class.

Modification History


Date		Version		Author			Description
----------	-----------	--------------- ----------------------------------------
30 06 2018	1.0			Nwe Ni Aung		Initial Version.
------------------------------------------------------------------------------*/
package com.padcmyannmar.ck.events;
//------------------------------------------------------------------------------
import com.padcmyannmar.ck.data.vos.NewProductsVO;

import java.util.List;
//------------------------------------------------------------------------------
public class SuccessForceRefreshGetNewProductEvent extends SuccessGetNewProductsEvent {


    public SuccessForceRefreshGetNewProductEvent(List<NewProductsVO> nwewProductList) {
        super(nwewProductList);
    }

//------------------------------------------------------------------------------

}
//------------------------------------------------------------------------------
//
//		End Of File
//
//------------------------------------------------------------------------------