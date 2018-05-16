package com.yuntian.newsframe.ui.video.mvp;

import com.yuntian.basedragger2.scope.ActivityScope;
import com.yuntian.baselibs.net.result.CustomObserver;
import com.yuntian.newsframe.ui.video.bean.VideoInfo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * description  P层.
 * Created by ChuYingYan on 2018/4/29.
 */
@ActivityScope
public class VideoPresenter extends VideoContract.Presenter {


    @Inject
    public VideoPresenter() {

    }


    @Override
    public void getVideoList(String videoType, String videosId, int startPage) {
        mModel.getVideoList(videoType, videosId, startPage)
                .subscribeOn(Schedulers.io()) //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread()) //回到主线程去处理请求结果
                .subscribe(new CustomObserver<List<VideoInfo>>() {
                    @Override
                    protected void _onNext(List<VideoInfo> newsBeans) {
                        mView.getVideoListSuccess(newsBeans);
                    }

                    @Override
                    protected void _onError(String message, int code) {
                        mView.showMsg(message, code);
                    }
                });
    }
}
