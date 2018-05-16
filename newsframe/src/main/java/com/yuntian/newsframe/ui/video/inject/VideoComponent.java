package com.yuntian.newsframe.ui.video.inject;


import com.yuntian.basedragger2.inject.AppComponent;
import com.yuntian.basedragger2.scope.ActivityScope;
import com.yuntian.newsframe.ui.video.VideoListFragment;
import com.yuntian.newsframe.ui.video.VideoMainFragment;

import dagger.Component;

/**
 * description .
 * Created by ChuYingYan on 2018/5/1.
 */
@ActivityScope
@Component(modules = VideoModule.class, dependencies = AppComponent.class)
public interface VideoComponent {

    void inject(VideoMainFragment fragment);

    void inject(VideoListFragment fragment);


}
