package com.yuntian.dragger2databinding.ui.user.inject;


import com.yuntian.basedragger2.scope.ActivityScope;
import com.yuntian.dragger2databinding.ui.user.mvp.UserContract;
import com.yuntian.dragger2databinding.ui.user.mvp.UserModel;
import com.yuntian.dragger2databinding.ui.user.mvp.UserPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class UserModule {
    private UserContract.View view;

    public UserModule(UserContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    UserContract.Model provideModel(UserModel model) {
        return model;
    }

    @ActivityScope
    @Provides
    UserContract.View provideView() {
        return this.view;
    }


    @ActivityScope
    @Provides
    UserContract.Presenter providePresenter(UserPresenter presenter) {
        return presenter;
    }
}
