package com.yuntian.dragger2databinding.mvvm.ui.inject;


import com.yuntian.basedragger2.scope.ActivityScope;
import com.yuntian.dragger2databinding.databinding.ActivityUserViewmodeBinding;

import dagger.Module;
import dagger.Provides;

@Module
public class UserInfoModule {


    private ActivityUserViewmodeBinding viewDataBinding;

    public UserInfoModule(ActivityUserViewmodeBinding viewDataBinding) {
        this.viewDataBinding = viewDataBinding;
    }


    @ActivityScope
    @Provides
    ActivityUserViewmodeBinding provideViewBinding() {
        return viewDataBinding;
    }


}
