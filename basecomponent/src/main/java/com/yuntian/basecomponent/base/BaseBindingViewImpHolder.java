package com.yuntian.basecomponent.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.View;

import com.yuntian.adapterlib.imp.BaseViewHolderImp;

/**
 * description  .
 * Created by ChuYingYan on 2018/5/4.
 */
public abstract class BaseBindingViewImpHolder<B  extends ViewDataBinding,D> extends BaseViewHolderImp<D> {

    protected B mBinding;


    public BaseBindingViewImpHolder(View itemView) {
        super(itemView);
        mBinding=DataBindingUtil.bind(itemView);
    }
}
