package com.yuntian.basecomponent.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuntian.baselibs.base.BaseLazyFragment;

/**
 * description  .
 * Created by ChuYingYan on 2018/4/11.
 */
public abstract class BaseLazyBindingFragment<B extends ViewDataBinding> extends BaseLazyFragment {


    protected B mViewBinding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (isCache && rootView != null) {
            return rootView;
        }
        mViewBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        rootView=mViewBinding.getRoot();
        isInitialized=true;
        init();
        initView();
        initData(savedInstanceState);
        return rootView;
    }



}
