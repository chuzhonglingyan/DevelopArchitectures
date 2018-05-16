package com.yuntian.newsframe.ui.video.mvp;

import com.yuntian.baselibs.net.core.NetApi;
import com.yuntian.newsframe.net.VideoService;
import com.yuntian.newsframe.ui.video.bean.VideoInfo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * description  M层.
 * Created by ChuYingYan on 2018/4/29.
 */
public class VideoModel implements VideoContract.Model {


    @Inject
    public VideoModel() {

    }


    @Override
    public Observable<List<VideoInfo>> getVideoList(String videoType, String videosId, int startPage) {
        return NetApi.getApi().create(VideoService.class)
                .getVideoList(videosId, startPage)
                .flatMap((stringListMap) -> Observable.fromIterable(stringListMap.get(videosId)))
                .toList()
                .toObservable();
    }
}
