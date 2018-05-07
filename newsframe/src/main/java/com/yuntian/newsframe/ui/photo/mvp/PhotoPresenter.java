package com.yuntian.newsframe.ui.photo.mvp;

import com.yuntian.basedragger2.scope.ActivityScope;
import com.yuntian.baselibs.net.result.CustomObserver;
import com.yuntian.newsframe.ui.news.bean.NewsBean;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * description  P层.
 * Created by ChuYingYan on 2018/4/29.
 */
@ActivityScope
public class PhotoPresenter extends PhotoContract.Presenter {


    @Inject
    public PhotoPresenter() {

    }

    @Override
    public void getNewsList(String type, String id, int startPage) {
        mModel.getNewsList(type, id, startPage)
                .subscribeOn(Schedulers.io()) //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread()) //回到主线程去处理请求结果
                .subscribe(new CustomObserver<List<NewsBean>>() {
                    @Override
                    protected void _onNext(List<NewsBean> newsBeans) {
                        mView.getNewsListSuccess(newsBeans);
                    }

                    @Override
                    protected void _onError(String message, int code) {
                        mView.showMsg(message, code);
                    }
                });
    }

}
