package com.yuntian.basedragger2.inject;

import android.app.Application;

import com.yuntian.basedragger2.base.DraggerApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 管理三方生产的实例
 * Created by Administrator on 2017/1/3.
 */
@Module
public class AppModule {


    public AppModule(DraggerApp application) {
        this.mApplication = application;
    }

    private Application mApplication;


    @Singleton
    @Provides
    public Application provideApplication() {
        return mApplication;
    }


}