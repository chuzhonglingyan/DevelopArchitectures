package com.yuntian.baselibs.net.service;

import com.yuntian.baselibs.net.entity.rep.BaseResponse;
import com.yuntian.baselibs.net.entity.rep.LoginResponse;
import com.yuntian.baselibs.net.entity.rep.RegisterResponse;
import com.yuntian.baselibs.net.entity.req.LoginPsdRequest;
import com.yuntian.baselibs.net.entity.req.RegisterRequest;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * description  .
 * Created by ChuYingYan on 2018/5/1.
 */
public interface UserService {


    //密码登录
    @POST("/user_pswLogin.do")
    Observable<BaseResponse<LoginResponse>> loginWithPassword(@Body LoginPsdRequest param);

    //注册
    @POST("/user_register.do")
    Observable<BaseResponse<RegisterResponse>> register(@Body RegisterRequest param);
}
