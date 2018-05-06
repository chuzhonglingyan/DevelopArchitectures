package com.yuntian.basecomponent.mvvm;

import android.databinding.ViewDataBinding;

/**
 * description  .
 * Created by ChuYingYan on 2018/4/30.
 */
public abstract class BaseViewMode<B extends ViewDataBinding>  implements ViewModel {

    public B mBingding;


    public BaseViewMode(B mBingding) {
        this.mBingding = mBingding;
    }

    public abstract  void  initData();


    @Override
    public  void onAttach() {
        initData();
    }


    @Override
    public void onDetach() {

    }


}
