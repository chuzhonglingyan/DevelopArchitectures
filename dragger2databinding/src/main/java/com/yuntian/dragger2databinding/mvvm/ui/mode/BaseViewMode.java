package com.yuntian.dragger2databinding.mvvm.ui.mode;

import android.databinding.ViewDataBinding;

/**
 * description  .
 * Created by ChuYingYan on 2018/4/30.
 */
public abstract class BaseViewMode<B extends ViewDataBinding>  implements ViewModel{

    public B mBingding;

    public BaseViewMode(B mBingding) {
        this.mBingding = mBingding;
        initData();
    }

    abstract  void  initData();
}
