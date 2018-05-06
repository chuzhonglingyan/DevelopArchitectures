package com.yuntian.newsframe.ui.news.inject;


import com.yuntian.basedragger2.inject.AppComponent;
import com.yuntian.basedragger2.scope.ActivityScope;
import com.yuntian.newsframe.ui.news.NewsListFragment;
import com.yuntian.newsframe.ui.news.NewsMainFragment;

import dagger.Component;

/**
 * description .
 * Created by ChuYingYan on 2018/5/1.
 */
@ActivityScope
@Component(modules = NewsModule.class, dependencies = AppComponent.class)
public interface NewsComponent {

    void inject(NewsMainFragment fragment);

    void inject(NewsListFragment fragment);
}
