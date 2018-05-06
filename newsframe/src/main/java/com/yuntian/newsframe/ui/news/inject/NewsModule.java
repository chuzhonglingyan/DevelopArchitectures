package com.yuntian.newsframe.ui.news.inject;


import com.yuntian.basedragger2.scope.ActivityScope;
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

}
