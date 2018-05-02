package com.yuntian.baselibs.net.interceptor;

import android.text.TextUtils;

import com.blankj.utilcode.util.NetworkUtils;
import com.yuntian.baselibs.net.cache.CacheStrategy;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * description 缓存拦截器.
 * Created by ChuYingYan on 2018/5/1.
 */
public class CacheControlInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String cacheControl = request.cacheControl().toString();
        if (!NetworkUtils.isConnected()) {
            request = request.newBuilder()
                    .cacheControl(TextUtils.isEmpty(cacheControl)? CacheControl.FORCE_NETWORK: CacheControl.FORCE_CACHE)
                    .build();
        }
        Response originalResponse = chain.proceed(request);
        if (NetworkUtils.isConnected()) {
            //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
            return originalResponse.newBuilder()
                    .header("Cache-Control", cacheControl)
                    .removeHeader("Pragma")
                    .build();
        } else {
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + CacheStrategy.CACHE_STALE_SEC)
                    .removeHeader("Pragma")
                    .build();
        }
    }
}
