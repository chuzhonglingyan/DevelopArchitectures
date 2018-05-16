package com.yuntian.newsframe.ui.news.mvp;

import android.databinding.ViewDataBinding;

import com.yuntian.basedragger2.base.BaseMvpFrgament;
import com.yuntian.basedragger2.mvp.BasePresenter;
import com.yuntian.newsframe.ui.news.bean.NewsBean;

import java.util.List;

/**
 * description 适配类 .
 * Created by ChuYingYan on 2018/4/29.
 */
public  abstract class NewsViewFragment<B extends ViewDataBinding,P extends BasePresenter> extends BaseMvpFrgament<P, B> implements NewsContract.View {


    @Override
    public void getNewsListSuccess(List<NewsBean> result) {

    }


    @Override
    public void showMsg(String message, int code) {

    }





}
