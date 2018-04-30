package com.yuntian.dragger2databinding.ui.user.mvp;

import android.database.Observable;

import com.yuntian.dragger2databinding.net.entity.LoginPsdParam;
import com.yuntian.dragger2databinding.net.entity.RegisterParam;
import com.yuntian.dragger2databinding.net.entity.UserInfoParam;
import com.yuntian.dragger2databinding.ui.user.bean.TokenBean;
import com.yuntian.dragger2databinding.ui.user.bean.UserInfoBean;
import javax.inject.Inject;

/**
 * description  M层.
 * Created by ChuYingYan on 2018/4/29.
 */
public class UserModel implements UserContract.Model {


    @Inject
    public UserModel(){

    }

    @Override
    public Observable<TokenBean> login(LoginPsdParam param) {


        return null;
    }

    @Override
    public Observable<TokenBean> register(RegisterParam param) {
        return null;
    }

    @Override
    public Observable<UserInfoBean> getUserInfo(UserInfoParam param) {
        return null;
    }
}
