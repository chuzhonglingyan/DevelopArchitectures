package com.yuntian.basecomponent.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuntian.baselibs.base.BaseFragment;

/**
 * description  .
 * Created by ChuYingYan on 2018/4/29.
 */
public abstract class BaseDataBindingFrgament<B extends ViewDataBinding> extends BaseFragment{


    protected B mViewBinding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mViewBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        rootView=mViewBinding.getRoot();
        isViewInitialized=true;
        return rootView;
    }


}
