package com.yuntian.newsframe.net;

import com.yuntian.newsframe.ui.news.bean.NewsBean;
import com.yuntian.newsframe.ui.news.bean.NewsDetailInfo;
import com.yuntian.newsframe.ui.news.bean.SpecialInfo;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

import static com.yuntian.newsframe.storage.ApiConstants.AVOID_HTTP403_FORBIDDEN;

/**
 * description  .
 * Created by ChuYingYan on 2018/5/4.
 */
public interface NewsService {


    @Headers(AVOID_HTTP403_FORBIDDEN)
    @GET("http://c.m.163.com/nc/article/{type}/{id}/{startPage}-10.html")
    Observable<Map<String, List<NewsBean>>> getNewsList(
            @Header("Cache-Control") String cacheControl,
            @Path("type") String type, @Path("id") String id,
            @Path("startPage") int startPage);



    /**
     * 获取专题
     * eg: http://c.3g.163.com/nc/special/S1451880983492.html
     *
     * @param specialIde 专题ID
     * @return
     */
    @Headers(AVOID_HTTP403_FORBIDDEN)
    @GET("nc/special/{specialId}.html")
    Observable<Map<String, SpecialInfo>> getSpecial(@Path("specialId") String specialIde);

    /**
     * 获取新闻详情
     * eg: http://c.3g.163.com/nc/article/BV56RVG600011229/full.html
     *
     * @param newsId 专题ID
     * @return
     */
    @Headers(AVOID_HTTP403_FORBIDDEN)
    @GET("nc/article/{newsId}/full.html")
    Observable<Map<String, NewsDetailInfo>> getNewsDetail(@Path("newsId") String newsId);


}
