package com.yuntian.basedragger2.base;

import android.databinding.ViewDataBinding;

import com.yuntian.basedragger2.inject.AppComponent;
import com.yuntian.basedragger2.mvp.BasePresenter;
import com.yuntian.basedragger2.mvp.BaseView;

import javax.inject.Inject;

/**
 * description  .
 * Created by ChuYingYan on 2018/4/29.
 */
public abstract class BaseMvpActivity<P extends BasePresenter, B extends ViewDataBinding> extends BaseDataBindingActivity<B> implements BaseView {


    @Inject
    protected P mPresenter;


    @Override
    protected void init() {
        super.init();
        setupActivityComponent(getApplicationComponent());

        if (mPresenter != null) {
            mPresenter.onCreate();
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
        if (mPresenter != null) mPresenter.onDestroy();
    }


    @Override
    public void transmit() {

    }
}
