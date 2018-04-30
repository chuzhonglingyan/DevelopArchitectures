package com.yuntian.dragger2databinding.mvp.ui.user.inject;


import com.yuntian.basedragger2.inject.AppComponent;
import com.yuntian.basedragger2.scope.ActivityScope;
import com.yuntian.dragger2databinding.mvp.ui.user.LoginActivity;
import com.yuntian.dragger2databinding.mvp.ui.user.RegisterActivity;

import dagger.Component;

@ActivityScope
@Component(modules = UserModule.class, dependencies = AppComponent.class)
public interface UserComponent {

    void inject(LoginActivity activity);


    void inject(RegisterActivity activity);
}
