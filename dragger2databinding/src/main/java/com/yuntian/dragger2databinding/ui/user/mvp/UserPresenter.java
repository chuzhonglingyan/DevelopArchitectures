package com.yuntian.dragger2databinding.ui.user.mvp;

import android.os.Handler;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.yuntian.basedragger2.scope.ActivityScope;
import com.yuntian.dragger2databinding.net.entity.LoginPsdParam;
import com.yuntian.dragger2databinding.net.entity.RegisterParam;
import com.yuntian.dragger2databinding.net.entity.UserInfoParam;
import com.yuntian.dragger2databinding.ui.user.bean.TokenBean;

import java.util.UUID;

import javax.inject.Inject;

/**
 * description  P层.
 * Created by ChuYingYan on 2018/4/29.
 */
@ActivityScope
public class UserPresenter extends UserContract.Presenter {


    private Handler handler=new Handler();

    @Inject
    public UserPresenter() {

    }

    @Override
    public void login(LoginPsdParam param) {
        mModel.login(param);
        LogUtils.d("登录参数:"+param.toString());

        handler.postDelayed(()->{
            ToastUtils.showShort("登录成功");
            TokenBean tokenBean=new TokenBean();
            tokenBean.setMemberId("12233344");
            tokenBean.setToken(UUID.randomUUID().toString());
            mView.loginSuccess(tokenBean);}
        ,2000);
    }

    @Override
    public void register(RegisterParam param) {
        mModel.register(param);
        LogUtils.d("注册参数:"+param.toString());
    }

    @Override
    public void getUserInfo(UserInfoParam param) {
        mModel.getUserInfo(param);
    }
}
