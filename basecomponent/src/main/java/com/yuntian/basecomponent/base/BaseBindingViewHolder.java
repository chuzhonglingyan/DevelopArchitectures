package com.yuntian.basecomponent.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.View;

import com.yuntian.adapterlib.base.BaseViewHolder;

/**
 * description  .
 * Created by ChuYingYan on 2018/5/4.
 */
public abstract class BaseBindingViewHolder<B  extends ViewDataBinding,D> extends BaseViewHolder<D> {

    protected B mBinding;


    public BaseBindingViewHolder(View itemView) {
        super(itemView);
        mBinding=DataBindingUtil.bind(itemView);
    }
}
