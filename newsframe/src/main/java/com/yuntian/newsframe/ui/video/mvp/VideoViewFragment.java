package com.yuntian.newsframe.ui.video.mvp;

import android.databinding.ViewDataBinding;

import com.yuntian.basedragger2.base.BaseMvpFrgament;
import com.yuntian.basedragger2.mvp.BasePresenter;
import com.yuntian.newsframe.ui.video.bean.VideoInfo;

import java.util.List;

/**
 * description 适配类 .
 * Created by ChuYingYan on 2018/4/29.
 */
public  abstract class VideoViewFragment<B extends ViewDataBinding,P extends BasePresenter> extends BaseMvpFrgament<P, B> implements VideoContract.View {


    @Override
    public void getVideoListSuccess(List<VideoInfo> result) {

    }

    @Override
    public void showMsg(String message, int code) {

    }



}
