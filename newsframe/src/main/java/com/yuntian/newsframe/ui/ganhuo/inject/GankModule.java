package com.yuntian.newsframe.ui.ganhuo.inject;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.yuntian.adapterlib.base.BaseRvAdapter;
import com.yuntian.basedragger2.scope.ActivityScope;
import com.yuntian.baselibs.adapter.BaseFPageAdapter;
import com.yuntian.baselibs.adapter.BaseFPageStateAdapter;
import com.yuntian.newsframe.ui.ganhuo.mvp.GankContract;
import com.yuntian.newsframe.ui.ganhuo.mvp.GankModel;
import com.yuntian.newsframe.ui.ganhuo.mvp.GankPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * description .
 * Created by ChuYingYan on 2018/5/1.
 */
@Module
public class GankModule {


    private GankContract.View view;

    public GankModule(GankContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    GankContract.Model provideModel(GankModel model) {
        return model;
    }

    @ActivityScope
    @Provides
    GankContract.View provideView() {
        return this.view;
    }


    @ActivityScope
    @Provides
    GankContract.Presenter providePresenter(GankPresenter presenter) {
        return presenter;
    }

    @ActivityScope
    @Provides
    BaseRvAdapter provideBaseAdapter() {
        return new BaseRvAdapter() {};
    }


    @ActivityScope
    @Provides
    BaseFPageStateAdapter provideBaseFPageStateAdapter() {
        return new BaseFPageStateAdapter(getFragmentActivty());
    }

    @ActivityScope
    @Provides
    BaseFPageAdapter provideBaseFPageAdapter() {
        return new BaseFPageAdapter(getFragmentActivty());
    }


    public FragmentActivity getFragmentActivty() {
        if (view instanceof FragmentActivity) {
            return (FragmentActivity) view;
        } else if (view instanceof Fragment) {
            return ((Fragment) view).getActivity();
        }
        return null;
    }


}
