package com.yuntian.dragger2databinding.mvp.ui.user.mvp;

import android.database.Observable;

import com.yuntian.basedragger2.mvp.BaseModel;
import com.yuntian.basedragger2.mvp.BasePresenter;
import com.yuntian.basedragger2.mvp.BaseView;
import com.yuntian.dragger2databinding.net.entity.LoginPsdParam;
import com.yuntian.dragger2databinding.net.entity.RegisterParam;
import com.yuntian.dragger2databinding.net.entity.UserInfoParam;
import com.yuntian.dragger2databinding.mvp.ui.user.bean.TokenBean;
import com.yuntian.dragger2databinding.mvp.ui.user.bean.UserInfoBean;

import retrofit2.http.Body;

/**
 * description 合约类 .
 * Created by ChuYingYan on 2018/4/29.
 */
public interface UserContract {

    interface View extends BaseView {

        void loginSuccess(TokenBean result);

        void registerSuccess(TokenBean result);

        void getUserInfoSuccess(UserInfoBean result);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        /**
         * 登录
         */
        public abstract void login(LoginPsdParam param);

        /**
         * 注册
         */
        public abstract void register(RegisterParam param);


        public abstract void getUserInfo(UserInfoParam param);

    }

    interface Model extends BaseModel {

        /**
         * 登录
         */
        Observable<TokenBean> login(@Body LoginPsdParam param);

        /**
         * 注册
         */
        Observable<TokenBean> register(@Body RegisterParam param);


        Observable<UserInfoBean> getUserInfo(@Body UserInfoParam param);

    }
}
