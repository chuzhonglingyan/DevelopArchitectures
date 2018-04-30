package com.yuntian.dragger2databinding.mvvm.ui.mode;

import android.view.View;
import android.widget.Toast;

import com.yuntian.dragger2databinding.databinding.ActivityUserViewmodeBinding;


/**
 * description  .
 * Created by ChuYingYan on 2018/4/30.
 */
public class UserViewModel extends BaseViewMode<ActivityUserViewmodeBinding>{


    //注意，这里都需要定义成public，否则这个字段读取不到

    public UserMode user;

    public UserViewModel(ActivityUserViewmodeBinding mBingding) {
        super(mBingding);
    }


    @Override
    void initData() {
        mBingding.setModel(this);
        user=new UserMode("LooperJing","20");
    }




    public void onItemClick(View pView) {
        Toast.makeText(pView.getContext(), "通知Model层，异步请求，获取用户信息", Toast.LENGTH_SHORT).show();
        user.onItemClick(pView);
    }


    @Override
    public void onAttach() {

    }

    @Override
    public void onDetach() {

    }
}
