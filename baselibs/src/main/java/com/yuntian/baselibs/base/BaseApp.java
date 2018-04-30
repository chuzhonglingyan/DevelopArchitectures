package com.yuntian.baselibs.base;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

/**
 * description  .
 * Created by ChuYingYan on 2018/4/29.
 */
public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    }
}
