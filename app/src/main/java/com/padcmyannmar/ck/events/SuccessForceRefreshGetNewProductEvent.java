package com.padcmyannmar.ck.events;

import com.padcmyannmar.ck.data.vos.NewProductsVO;

import java.util.List;

public class SuccessForceRefreshGetNewProductEvent extends SuccessGetNewProductsEvent{


    public SuccessForceRefreshGetNewProductEvent(List<NewProductsVO> nwewProductList) {
        super(nwewProductList);
    }

}