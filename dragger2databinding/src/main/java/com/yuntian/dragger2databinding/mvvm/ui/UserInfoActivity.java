package com.yuntian.dragger2databinding.mvvm.ui;

import android.os.Bundle;

import com.yuntian.basedragger2.base.BaseMvvMActivity;
import com.yuntian.basedragger2.inject.AppComponent;
import com.yuntian.dragger2databinding.R;
import com.yuntian.dragger2databinding.databinding.ActivityUserViewmodeBinding;
import com.yuntian.dragger2databinding.mvvm.ui.inject.DaggerUserInfoComponent;
import com.yuntian.dragger2databinding.mvvm.ui.inject.UserInfoModule;
import com.yuntian.dragger2databinding.mvvm.ui.mode.UserViewModel;

/**
 * description  .
 * Created by ChuYingYan on 2018/4/30.
 */
public class UserInfoActivity extends BaseMvvMActivity<UserViewModel,ActivityUserViewmodeBinding> {



    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_viewmode;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerUserInfoComponent
                .builder()
                .appComponent(appComponent)
                .userInfoModule(new UserInfoModule(mViewBinding))
                .build()
                .inject(this);  //调用inject，注入就完成了。此时使用@Inject来标记成员变量就可以使用了
    }


}
