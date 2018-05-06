package com.yuntian.newsframe.base;

import com.yuntian.basedragger2.base.DraggerApp;
import com.yuntian.newsframe.util.ViewHolderUtil;

/**
 * description  .
 * Created by ChuYingYan on 2018/5/5.
 */
public class NewFrameApp extends DraggerApp {

    @Override
    public void onCreate() {
        super.onCreate();
        ViewHolderUtil.init();
    }
}
