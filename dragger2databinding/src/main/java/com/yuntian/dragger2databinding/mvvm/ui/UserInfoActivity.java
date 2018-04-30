package com.yuntian.dragger2databinding.mvvm.ui;

import android.os.Bundle;
import com.yuntian.dragger2databinding.databinding.ActivityUserViewmodeBinding;
import com.yuntian.basedragger2.base.BaseDataBindingActivity;
import com.yuntian.dragger2databinding.R;
import com.yuntian.dragger2databinding.mvvm.ui.mode.UserMode;
import com.yuntian.dragger2databinding.mvvm.ui.mode.UserViewModel;

/**
 * description  .
 * Created by ChuYingYan on 2018/4/30.
 */
public class UserInfoActivity extends BaseDataBindingActivity<ActivityUserViewmodeBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_viewmode;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        UserViewModel userViewModel = new UserViewModel(this, new UserMode("jack", 22));
    }
}
