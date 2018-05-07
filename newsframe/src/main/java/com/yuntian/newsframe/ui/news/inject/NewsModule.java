package com.yuntian.newsframe.ui.news.inject;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.yuntian.adapterlib.base.BaseRvAdapter;
import com.yuntian.basedragger2.scope.ActivityScope;
import com.yuntian.baselibs.adapter.BaseFPageStateAdapter;
import com.yuntian.newsframe.ui.news.mvp.NewsContract;
import com.yuntian.newsframe.ui.news.mvp.NewsModel;
import com.yuntian.newsframe.ui.news.mvp.NewsPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * description .
 * Created by ChuYingYan on 2018/5/1.
 */
@Module
public class NewsModule {
    private NewsContract.View view;

    public NewsModule(NewsContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    NewsContract.Model provideModel(NewsModel model) {
        return model;
    }

    @ActivityScope
    @Provides
    NewsContract.View provideView() {
        return this.view;
    }


    @ActivityScope
    @Provides
    NewsContract.Presenter providePresenter(NewsPresenter presenter) {
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

    public FragmentActivity getFragmentActivty() {
        if (view instanceof FragmentActivity) {
            return (FragmentActivity) view;
        } else if (view instanceof Fragment) {
            return ((Fragment) view).getActivity();
        }
        return null;
    }
}
