/*------------------------------------------------------------------------------

This source is part of the assignment of the PADC Fun5 class.

Modification History


Date		Version		Author			Description
----------	-----------	--------------- ----------------------------------------
05 07 2018	1.0			Nwe Ni Aung		Initial Version.
------------------------------------------------------------------------------*/
package com.padcmyannmar.ck.network;
//------------------------------------------------------------------------------
public interface CKDataAgent {
    //add new param for forceRefresh
    void loadProductsList(String accessToken,int page,boolean isForceRefresh);
//--------------------------------------------------------------------------

}
//------------------------------------------------------------------------------
//
//		End Of File
//
//------------------------------------------------------------------------------
