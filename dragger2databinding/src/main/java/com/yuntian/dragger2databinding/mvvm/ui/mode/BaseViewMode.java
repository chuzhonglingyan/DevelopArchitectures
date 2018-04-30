package com.yuntian.dragger2databinding.mvvm.ui.mode;

import com.yuntian.basedragger2.BR;
import com.yuntian.basedragger2.base.BaseDataBindingActivity;

/**
 * description  .
 * Created by ChuYingYan on 2018/4/30.
 */
public abstract class BaseViewMode<T extends BaseDataBindingActivity,D extends IMode>  {

    public T mActivity;


    public D mMode;

    public BaseViewMode(T mActivity,D mMode) {
        this.mActivity = mActivity;
        this.mMode = mMode;
        mActivity.getmViewBinding().setVariable(BR._all,mMode);
        initData();
    }

    abstract  void  initData();
}
