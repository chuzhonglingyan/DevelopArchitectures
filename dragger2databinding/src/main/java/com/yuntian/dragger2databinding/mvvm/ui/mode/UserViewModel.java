package com.yuntian.dragger2databinding.mvvm.ui.mode;

import android.view.View;
import android.widget.Toast;

import com.yuntian.basedragger2.base.BaseViewMode;
import com.yuntian.dragger2databinding.databinding.ActivityUserViewmodeBinding;

import javax.inject.Inject;


/**
 * description  .
 * Created by ChuYingYan on 2018/4/30.
 */
public class UserViewModel extends BaseViewMode<ActivityUserViewmodeBinding> {


    //注意，这里都需要定义成public，否则这个字段读取不到

    public UserModel user;

    @Inject
    public UserViewModel() {
    }


    @Override
    public void initData() {
        mBingding.setModel(this);
        user=new UserModel();
        user.requestNetData();
    }


    public void onItemClick(View pView) {
        Toast.makeText(pView.getContext(), "通知Model层，异步请求，获取用户信息", Toast.LENGTH_SHORT).show();
        user.onItemClick(pView);
    }


}
