package com.yuntian.dragger2databinding.mvp.ui.user;

import android.os.Bundle;

import com.yuntian.basedragger2.inject.AppComponent;
import com.yuntian.dragger2databinding.R;
import com.yuntian.dragger2databinding.databinding.ActivityRegisterBinding;
import com.yuntian.dragger2databinding.net.entity.RegisterParam;
import com.yuntian.dragger2databinding.mvp.ui.user.bean.TokenBean;
import com.yuntian.dragger2databinding.mvp.ui.user.inject.DaggerUserComponent;
import com.yuntian.dragger2databinding.mvp.ui.user.inject.UserModule;
import com.yuntian.dragger2databinding.mvp.ui.user.mvp.UserContract;
import com.yuntian.dragger2databinding.mvp.ui.user.mvp.UserViewActivity;

public class RegisterActivity extends UserViewActivity<ActivityRegisterBinding,UserContract.Presenter> {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mViewBinding.btRegister.setOnClickListener(v -> {
            RegisterParam registerParam = new RegisterParam();
            registerParam.setName(mViewBinding.edName.getText().toString());
            registerParam.setMobile(mViewBinding.edMobile.getText().toString());
            registerParam.setPassword(mViewBinding.edPwd.getText().toString());
            mPresenter.register(registerParam);
        });

    }


    @Override
    public void showMsg(String message, int code) {

    }

    @Override
    public void registerSuccess(TokenBean result) {
        super.registerSuccess(result);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerUserComponent
                .builder()
                .appComponent(appComponent)
                .userModule(new UserModule(this))
                .build()
                .inject(this);  //调用inject，注入就完成了。此时使用@Inject来标记成员变量就可以使用了
    }


}
