package com.yuntian.dragger2databinding;

import android.content.Intent;
import android.os.Bundle;
import com.yuntian.dragger2databinding.databinding.ActivityMainBinding;
import com.yuntian.basedragger2.base.BaseDataBindingActivity;
import com.yuntian.dragger2databinding.ui.user.LoginActivity;

public class MainActivity extends BaseDataBindingActivity<ActivityMainBinding> {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        mViewBinding.gotoLogin.setOnClickListener((v)->{
            startActivity(new Intent(mActivity, LoginActivity.class));
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }
}
