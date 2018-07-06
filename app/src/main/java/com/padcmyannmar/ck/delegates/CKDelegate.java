package com.padcmyannmar.ck.delegates;

import com.padcmyannmar.ck.data.vos.NewProductsVO;

/*1st step of Controller Pattern */
public interface CKDelegate {
    // add param to know user tap which itemView
    void onTapItemView(NewProductsVO newProducts);
}
