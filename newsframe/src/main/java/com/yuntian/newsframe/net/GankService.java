package com.yuntian.newsframe.net;

import com.yuntian.baselibs.net.cache.CacheStrategy;
import com.yuntian.baselibs.net.entity.rep.BaseResponse;
import com.yuntian.newsframe.ui.ganhuo.bean.GankInfo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * description  .
 * Created by ChuYingYan on 2018/5/8.
 */
public interface GankService {

    /**
     * 数据类型：福利 | Android | iOS | 休息视频 | 拓展资源 |前端
     * 获取福利图片
     * eg: http://gank.io/api/data/福利/10/1
     * @param page 页码
     * @return
     */
    @Headers(CacheStrategy.CACHE_CONTROL_AGE)
    @GET("http://gank.io/api/data/{dataType}/{10}/{page}")
    Observable<BaseResponse<List<GankInfo>>> getWelfarePhotos(@Path("page") String dataType, @Path("page") int page);


    @Headers(CacheStrategy.CACHE_CONTROL_AGE)
    @GET("http://gank.io/api/data/{dataType}/{10}/{page}")
    Observable<BaseResponse<List<GankInfo>>> getArticle(@Path("page") String dataType, @Path("page") int page);


}
