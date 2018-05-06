package com.yuntian.basecomponent.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;

import com.yuntian.baselibs.base.BaseActivity;

/**
 * description  .
 * Created by ChuYingYan on 2018/4/29.
 */
public abstract class BaseDataBindingActivity<B extends ViewDataBinding> extends BaseActivity{


    protected B mViewBinding;

    @Override
    protected void init() {
        mContext = this;
        mActivity = this;
        mViewBinding = DataBindingUtil.setContentView(this, getLayoutId());
    }


}
