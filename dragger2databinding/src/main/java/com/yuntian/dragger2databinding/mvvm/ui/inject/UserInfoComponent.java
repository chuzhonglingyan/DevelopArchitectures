package com.yuntian.dragger2databinding.mvvm.ui.inject;


import com.yuntian.basedragger2.inject.AppComponent;
import com.yuntian.basedragger2.scope.ActivityScope;
import com.yuntian.dragger2databinding.mvvm.ui.UserInfoActivity;

import dagger.Component;

@ActivityScope
@Component(modules= UserInfoModule.class, dependencies = AppComponent.class)
public interface UserInfoComponent {

    void inject(UserInfoActivity activity);


}
