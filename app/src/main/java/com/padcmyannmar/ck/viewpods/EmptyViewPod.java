/*------------------------------------------------------------------------------

This source is part of the assignment of the PADC Fun5 class.

Modification History


Date		Version		Author			Description
----------	-----------	--------------- ----------------------------------------
07 07 2018	1.0			Nwe Ni Aung		Initial Version.
------------------------------------------------------------------------------*/
package com.padcmyannmar.ck.viewpods;
//------------------------------------------------------------------------------

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.padcmyannmar.ck.R;
import com.padcmyannmar.ck.utils.GlideApp;

import butterknife.BindView;
import butterknife.ButterKnife;
//------------------------------------------------------------------------------
/*Composite Custom View */
public class EmptyViewPod extends RelativeLayout {
    @BindView(R.id.iv_empty)
    ImageView ivEmpty;

    @BindView(R.id.tv_empty)
    TextView tvEmpty;

    // click Alt+Enter to get these 3 constructors.
    public EmptyViewPod(Context context) {
        super(context);
    }

    public EmptyViewPod(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EmptyViewPod(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this, this);
    }

    //Composite Custom View
    public void setEmptyData(String emptyImageUrl, String emptyMsg) {
        //directly use getContext() for Composite View Image
        GlideApp.with(getContext())
                .load(emptyImageUrl)
                .into(ivEmpty);

        tvEmpty.setText(emptyMsg);
    }

    /*method override
      resource type is default int type
      Composite Custom View*/
    public void setEmptyData(int emptyImageResource, String emptyMsg) {
        ivEmpty.setImageResource(emptyImageResource);
        tvEmpty.setText(emptyMsg);

    }
//--------------------------------------------------------------------------

}
//------------------------------------------------------------------------------
//
//		End Of File
//
//------------------------------------------------------------------------------