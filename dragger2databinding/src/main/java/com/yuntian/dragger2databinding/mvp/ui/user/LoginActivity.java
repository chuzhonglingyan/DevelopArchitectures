package com.yuntian.dragger2databinding.mvp.ui.user;

import android.app.Application;
import android.os.Bundle;

import com.blankj.utilcode.util.LogUtils;
import com.yuntian.basedragger2.inject.AppComponent;
import com.yuntian.baselibs.net.core.ApiManager;
import com.yuntian.baselibs.net.entity.rep.NewsBean;
import com.yuntian.baselibs.net.result.CustomObserver;
import com.yuntian.baselibs.net.service.NewsService;
import com.yuntian.baselibs.net.result.RxHandleResult;
import com.yuntian.dragger2databinding.R;
import com.yuntian.dragger2databinding.databinding.ActivityLoginBinding;
import com.yuntian.dragger2databinding.mvp.ui.user.bean.TokenBean;
import com.yuntian.dragger2databinding.mvp.ui.user.inject.DaggerUserComponent;
import com.yuntian.dragger2databinding.mvp.ui.user.inject.UserModule;
import com.yuntian.dragger2databinding.mvp.ui.user.mvp.UserContract;
import com.yuntian.dragger2databinding.mvp.ui.user.mvp.UserViewActivity;
import com.yuntian.dragger2databinding.net.entity.LoginPsdParam;

import java.util.List;

import javax.inject.Inject;

public class LoginActivity extends UserViewActivity<ActivityLoginBinding, UserContract.Presenter> {


    @Inject
    Application draggerApp;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mViewBinding.btLogin.setOnClickListener(v -> {
            LoginPsdParam loginPsdParam = new LoginPsdParam();
            loginPsdParam.setMobile(mViewBinding.edName.getText().toString());
            loginPsdParam.setPassword(mViewBinding.edPwd.getText().toString());
            mPresenter.login(loginPsdParam);
        });

        if (draggerApp != null) {
            LogUtils.d("draggerApp:" + draggerApp.toString());
        }
        //testApi();
        testApi2();
    }

    public void testApi() {
        NewsService newsService = ApiManager.getApi().create(NewsService.class);
        newsService.getNewsList("1")
                .compose(RxHandleResult.handleResult())
                .subscribe(new CustomObserver<List<NewsBean>>() {

                    @Override
                    protected void _onNext(List<NewsBean> newsBeans) {
                        LogUtils.d("当前线程" + Thread.currentThread().getName() + ":" + newsBeans.toString());
                    }

                    @Override
                    protected void _onError(String message, int code) {
                    }
                });
    }

    public void testApi2() {
        NewsService newsService = ApiManager.getApi().create(NewsService.class);
        newsService.getNewsListA()
                .compose(RxHandleResult.handleResult())
                .subscribe(new CustomObserver<List<NewsBean>>() {

                    @Override
                    protected void _onNext(List<NewsBean> newsBeans) {
                        if (newsBeans!=null){
                            LogUtils.d("当前线程" + Thread.currentThread().getName() + ":" + newsBeans.toString());
                        }
                        LogUtils.d("当前线程" + Thread.currentThread().getName() + ":" + newsBeans);
                    }

                    @Override
                    protected void _onError(String message, int code) {
                    }
                });
    }

    @Override
    public void showMsg() {
        super.showMsg();
    }

    @Override
    public void loginSuccess(TokenBean result) {
        super.loginSuccess(result);
        LogUtils.d("登录结果:" + result.toString());
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerUserComponent
                .builder()
                .appComponent(appComponent)
                .userModule(new UserModule(this))
                .build()
                .inject(this);  //调用inject，注入就完成了。此时使用@Inject来标记成员变量就可以使用了
    }


}
