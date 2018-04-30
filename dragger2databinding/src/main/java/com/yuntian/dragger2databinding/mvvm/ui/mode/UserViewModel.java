package com.yuntian.dragger2databinding.mvvm.ui.mode;

import android.view.View;
import android.widget.Toast;

import com.yuntian.dragger2databinding.mvvm.ui.UserInfoActivity;

/**
 * description  .
 * Created by ChuYingYan on 2018/4/30.
 */
public class UserViewModel extends BaseViewMode<UserInfoActivity, UserMode> {


    public UserViewModel(UserInfoActivity mActivity, UserMode userMode) {
        super(mActivity, userMode);
    }

    @Override
    void initData() {

    }


    public void onItemClick(View pView) {
        Toast.makeText(pView.getContext(), "通知Medel层，异步请求，获取用户信息", Toast.LENGTH_SHORT).show();
    }


}
