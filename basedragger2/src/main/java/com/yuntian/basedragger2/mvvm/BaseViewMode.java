package com.yuntian.basedragger2.mvvm;

import android.databinding.ViewDataBinding;

import javax.inject.Inject;

/**
 * description  .
 * Created by ChuYingYan on 2018/4/30.
 */
public abstract class BaseViewMode<B extends ViewDataBinding>  implements ViewModel{

    @Inject
    public B mBingding;


    public abstract  void  initData();


    @Override
    public  void onAttach() {
        initData();
    }


    @Override
    public void onDetach() {

    }


}
