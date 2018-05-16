package com.yuntian.newsframe.net;

import com.yuntian.newsframe.ui.video.bean.VideoInfo;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

import static com.yuntian.newsframe.storage.ApiConstants.AVOID_HTTP403_FORBIDDEN;

/**
 * description  .
 * Created by ChuYingYan on 2018/5/4.
 */
public interface VideoService {


    /**
     * 获取视频列表
     * eg: http://c.3g.163.com/nc/video/list/V9LG4B3A0/n/10-10.html
     *
     * @param id        video ID
     * @param startPage 起始页码
     * @return
     */
    @Headers(AVOID_HTTP403_FORBIDDEN)
    @GET("http://c.3g.163.com/nc/video/list/{id}/n/{startPage}-10.html")
    Observable<Map<String, List<VideoInfo>>> getVideoList(@Path("id") String id, @Path("startPage") int startPage);


}
