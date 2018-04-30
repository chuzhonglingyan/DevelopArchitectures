package com.yuntian.dragger2databinding.ui.user;

import android.app.Application;
import android.os.Bundle;

import com.blankj.utilcode.util.LogUtils;
import com.yuntian.basedragger2.inject.AppComponent;
import com.yuntian.dragger2databinding.R;
import com.yuntian.dragger2databinding.databinding.ActivityLoginBinding;
import com.yuntian.dragger2databinding.net.entity.LoginPsdParam;
import com.yuntian.dragger2databinding.ui.user.bean.TokenBean;
import com.yuntian.dragger2databinding.ui.user.inject.DaggerUserComponent;
import com.yuntian.dragger2databinding.ui.user.inject.UserModule;
import com.yuntian.dragger2databinding.ui.user.mvp.UserContract;
import com.yuntian.dragger2databinding.ui.user.mvp.UserViewActivity;

import javax.inject.Inject;

public class LoginActivity extends UserViewActivity<ActivityLoginBinding,UserContract.Presenter> {


    @Inject
    Application draggerApp;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mViewBinding.btLogin.setOnClickListener(v -> {
            LoginPsdParam loginPsdParam = new LoginPsdParam();
            loginPsdParam.setMobile(mViewBinding.edName.getText().toString());
            loginPsdParam.setPassword(mViewBinding.edPwd.getText().toString());
            mPresenter.login(loginPsdParam);
        });

        if(draggerApp!=null){
            LogUtils.d("draggerApp:"+draggerApp.toString());
        }

    }

    @Override
    public void showMsg() {
        super.showMsg();
    }

    @Override
    public void loginSuccess(TokenBean result) {
        super.loginSuccess(result);
        LogUtils.d("登录结果:"+result.toString());
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
