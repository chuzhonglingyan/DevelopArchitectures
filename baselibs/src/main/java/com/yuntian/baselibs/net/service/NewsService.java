package com.yuntian.baselibs.net.service;

import com.yuntian.baselibs.net.entity.rep.BaseResponse;
import com.yuntian.baselibs.net.entity.rep.NewsBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * description  .
 * Created by ChuYingYan on 2018/5/2.
 */
public interface NewsService {


    //获取新闻
    @GET("http://gank.io/api/data/Android/10/{pageSize}")
    Observable<BaseResponse<List<NewsBean>>> getNewsList(@Path("pageSize") String pageSize);

}
