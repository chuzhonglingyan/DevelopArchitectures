package com.yuntian.dragger2databinding.ui.user.mvp;

import android.databinding.ViewDataBinding;

import com.yuntian.basedragger2.base.BaseMvpActivity;
import com.yuntian.basedragger2.mvp.BasePresenter;
import com.yuntian.dragger2databinding.ui.user.bean.TokenBean;
import com.yuntian.dragger2databinding.ui.user.bean.UserInfoBean;

/**
 * description 适配类 .
 * Created by ChuYingYan on 2018/4/29.
 */
public  abstract class UserViewActivity<B extends ViewDataBinding,P extends BasePresenter> extends BaseMvpActivity<P, B>  implements UserContract.View {


    @Override
    public void loginSuccess(TokenBean result) {

    }

    @Override
    public void registerSuccess(TokenBean result) {

    }

    @Override
    public void getUserInfoSuccess(UserInfoBean result) {

    }

    @Override
    public void showMsg() {

    }




}
