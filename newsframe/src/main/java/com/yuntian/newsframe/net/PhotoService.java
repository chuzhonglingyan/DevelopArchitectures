package com.yuntian.newsframe.net;

import com.yuntian.baselibs.net.cache.CacheStrategy;
import com.yuntian.newsframe.storage.table.BeautyPhotoInfo;
import com.yuntian.newsframe.ui.photo.bean.PhotoInfo;
import com.yuntian.newsframe.ui.photo.bean.PhotoSetInfo;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * description  .
 * Created by ChuYingYan on 2018/5/4.
 */
public interface PhotoService {


    /**
     * 获取新闻详情
     * eg: http://c.3g.163.com/photo/api/set/0006/2136404.json
     *
     * @param photoId 图集ID
     * @return
     */
    @Headers(CacheStrategy.CACHE_CONTROL_AGE)
    @GET("photo/api/set/{photoId}.json")
    Observable<PhotoSetInfo> getPhotoSet(@Path("photoId") String photoId);

    /**
     * 获取图片列表
     * eg: http://c.3g.163.com/photo/api/list/0096/4GJ60096.json
     *
     * @return
     */
    @Headers(CacheStrategy.CACHE_CONTROL_AGE)
    @GET("photo/api/list/0096/4GJ60096.json")
    Observable<List<PhotoInfo>> getPhotoList();

    /**
     * 获取更多图片列表
     * eg: http://c.3g.163.com/photo/api/morelist/0096/4GJ60096/106571.json
     *
     * @return
     */
    @Headers(CacheStrategy.CACHE_CONTROL_AGE)
    @GET("photo/api/morelist/0096/4GJ60096/{setId}.json")
    Observable<List<PhotoInfo>> getPhotoMoreList(@Path("setId") String setId);

    /**
     * 获取美女图片，这个API不完整，省略了好多参数
     * eg: http://c.3g.163.com/recommend/getChanListNews?channel=T1456112189138&size=20&offset=0
     * @param offset 起始页码
     * @return
     */
    @Headers(CacheStrategy.CACHE_CONTROL_AGE)
    @GET("recommend/getChanListNews?channel=T1456112189138&size=20")
    Observable<Map<String, List<BeautyPhotoInfo>>> getBeautyPhoto(@Query("offset") int offset);


}
