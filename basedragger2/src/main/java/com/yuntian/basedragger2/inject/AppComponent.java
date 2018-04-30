package com.yuntian.basedragger2.inject;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 提供全局使用
 * Created by Administrator on 2017/1/3.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    Application application();

}
