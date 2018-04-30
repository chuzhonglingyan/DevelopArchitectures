package com.yuntian.basedragger2.base;

import android.databinding.ViewDataBinding;

import com.yuntian.basedragger2.inject.AppComponent;
import com.yuntian.basedragger2.mvvm.BaseViewMode;

import javax.inject.Inject;

/**
 * description  .
 * Created by ChuYingYan on 2018/4/30.
 */
public abstract class BaseMvvMActivity<VM extends BaseViewMode,B extends ViewDataBinding> extends BaseDataBindingActivity<B> {

    @Inject
    protected VM  mViewMode;


    @Override
    protected void init() {
        super.init();
        setupActivityComponent(getApplicationComponent());
        if (mViewMode != null) {
            mViewMode.onAttach();
        }
    }

    protected AppComponent getApplicationComponent() {
        return ((DraggerApp) getApplication()).component();
    }

    /**
     * 依赖注入的入口
     */
    protected abstract void setupActivityComponent(AppComponent appComponent);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mViewMode != null) mViewMode.onDetach();
    }


}
