package com.yuntian.newsframe.ui.video.mvp;

import com.yuntian.basedragger2.mvp.BaseModel;
import com.yuntian.basedragger2.mvp.BasePresenter;
import com.yuntian.basedragger2.mvp.BaseView;
import com.yuntian.newsframe.ui.video.bean.VideoInfo;

import java.util.List;

import io.reactivex.Observable;

/**
 * description 合约类 .
 * Created by ChuYingYan on 2018/4/29.
 */
public interface VideoContract {

    interface View extends BaseView {

        void getVideoListSuccess(List<VideoInfo> result);
    }

    abstract class Presenter extends BasePresenter<VideoContract.Model, VideoContract.View> {

        public abstract void getVideoList(String videoType,String videosId, int startPage);
    }

    interface Model extends BaseModel {

        Observable<List<VideoInfo>> getVideoList(String videoType,String videosId, int startPage);
    }
}
