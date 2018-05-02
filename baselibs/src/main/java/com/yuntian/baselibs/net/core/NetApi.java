package com.yuntian.baselibs.net.core;


import com.yuntian.baselibs.BuildConfig;
import com.yuntian.baselibs.net.constant.URLConstant;
import com.yuntian.baselibs.util.HttpsUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * description .
 * Created by ChuYingYan on 2018/5/1.
 */
public class NetApi {

    //读超时长，单位：秒
    public static final int READ_TIME_OUT = 10;

    //连接时长，单位：秒
    public static final int CONNECT_TIME_OUT = 10;

    private Retrofit retrofit;


    static String HOST_URL=URLConstant.BASE_URL;


    private NetApi(){
        this(HOST_URL);
    }

    //构造方法私有
    private NetApi(String url) {
        //开启Log
        HttpLoggingInterceptor logInterceptor = createHttpLoggingInterceptor();
        //增加头部信息
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request build = chain.request().newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .build();
                return chain.proceed(build);
            }
        };
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
        builder.readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(Boolean.FALSE)            // 失败时重新连接
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(logInterceptor)
                .addInterceptor(headerInterceptor)
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
        OkHttpClient okHttpClient = builder.build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(url)
                .build();
    }


    public static NetApi getApi() {
        return getApi(HOST_URL);
    }


    public static NetApi getApi(String url) {
        HOST_URL=url;
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static NetApi INSTANCE = new NetApi();
    }


    public <T> T create(final Class<T> service) {
        T apiService = retrofit.create(service);
        return apiService;
    }


    /**
     * log interceptor
     * @return
     */
    private static HttpLoggingInterceptor createHttpLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return loggingInterceptor;
    }

}