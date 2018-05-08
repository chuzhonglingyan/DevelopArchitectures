package com.yuntian.newsframe.ui.ganhuo.inject;


import com.yuntian.basedragger2.inject.AppComponent;
import com.yuntian.basedragger2.scope.ActivityScope;
import com.yuntian.newsframe.ui.ganhuo.ArticleListFragment;
import com.yuntian.newsframe.ui.ganhuo.GankMainFragment;
import com.yuntian.newsframe.ui.ganhuo.RestListFragment;
import com.yuntian.newsframe.ui.ganhuo.WelfareListFragment;

import dagger.Component;

/**
 * description .
 * Created by ChuYingYan on 2018/5/1.
 */
@ActivityScope
@Component(modules = GankModule.class, dependencies = AppComponent.class)
public interface GankComponent {

    void inject(GankMainFragment fragment);

    void inject(WelfareListFragment fragment);

    void inject(RestListFragment fragment);

    void inject(ArticleListFragment fragment);
}
