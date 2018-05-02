package com.yuntian.baselibs.rx;

import com.yuntian.baselibs.net.core.ApiManager;
import com.yuntian.baselibs.net.entity.rep.BaseResponse;
import com.yuntian.baselibs.net.entity.rep.LoginResponse;
import com.yuntian.baselibs.net.entity.rep.RegisterResponse;
import com.yuntian.baselibs.net.entity.req.LoginPsdRequest;
import com.yuntian.baselibs.net.entity.req.RegisterRequest;
import com.yuntian.baselibs.net.result.CustomObserver;
import com.yuntian.baselibs.net.service.UserService;

import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * description  .
 * Created by ChuYingYan on 2018/5/1.
 */
public class TestNet {


    public void login() {
        UserService userService = ApiManager.getApi().create(UserService.class);
        userService.loginWithPassword(new LoginPsdRequest())
                .subscribeOn(Schedulers.io()) //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread()) //回到主线程去处理请求结果
                .subscribe(new CustomObserver<BaseResponse<LoginResponse>>() {

                    @Override
                    protected void _onNext(BaseResponse<LoginResponse> loginResponseBaseResponse) {

                    }

                    @Override
                    protected void _onError(String message, int code) {

                    }
                });
    }

    public void login2() {
        UserService userService = ApiManager.getApi().create(UserService.class);
        userService.loginWithPassword(new LoginPsdRequest())
                .compose(RxHandleResult.<LoginResponse>handleResult())
                .subscribe(new CustomObserver<LoginResponse>() {


                    @Override
                    protected void _onNext(LoginResponse loginResponse) {

                    }

                    @Override
                    protected void _onError(String message, int code) {

                    }
                });

    }

    public void register() {
        UserService userService = ApiManager.getApi().create(UserService.class);
        userService.register(new RegisterRequest())
                .subscribeOn(Schedulers.io()) //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread()) //回到主线程去处理请求结果
                .subscribe(new CustomObserver<BaseResponse<RegisterResponse>>() {

                    @Override
                    protected void _onNext(BaseResponse<RegisterResponse> registerResponseBaseResponse) {

                    }

                    @Override
                    protected void _onError(String message, int code) {

                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    public void mergeApi() {
        final UserService userService = ApiManager.getApi().create(UserService.class);
        userService.register(new RegisterRequest()) //发起注册请求
                .subscribeOn(Schedulers.io()) //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread()) //回到主线程去处理请求注册结果
                .doOnNext(new Consumer<BaseResponse<RegisterResponse>>() {
                    @Override
                    public void accept(BaseResponse<RegisterResponse> registerResponseBaseResponse) throws Exception {
                        //先根据注册的响应结果去做一些操作
                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Function<BaseResponse<RegisterResponse>, ObservableSource<BaseResponse<LoginResponse>>>() {
                    @Override
                    public ObservableSource<BaseResponse<LoginResponse>> apply(BaseResponse<RegisterResponse> registerResponseBaseResponse) throws Exception {

                        return userService.loginWithPassword(new LoginPsdRequest());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread()) //回到主线程去处理请求登录结果
                .subscribe(new CustomObserver<BaseResponse<LoginResponse>>() {


                    @Override
                    protected void _onNext(BaseResponse<LoginResponse> loginResponseBaseResponse) {

                    }

                    @Override
                    protected void _onError(String message, int code) {

                    }
                });

    }

}
