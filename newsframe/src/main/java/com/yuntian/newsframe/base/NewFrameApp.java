package com.yuntian.newsframe.base;

import com.alibaba.android.arouter.launcher.ARouter;
import com.yuntian.basedragger2.base.DraggerApp;
import com.yuntian.newsframe.BuildConfig;
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
        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }
}
