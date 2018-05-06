package com.yuntian.dragger2databinding.mvp.ui.user;

import android.app.Application;
import android.os.Bundle;

import com.blankj.utilcode.util.LogUtils;
import com.yuntian.basedragger2.inject.AppComponent;
import com.yuntian.baselibs.net.core.ApiManager;
import com.yuntian.baselibs.net.entity.rep.GanHuoBean;
import com.yuntian.baselibs.net.result.CustomObserver;
import com.yuntian.baselibs.net.service.GanHuoService;
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
        GanHuoService ganHuoService = ApiManager.getApi().create(GanHuoService.class);
        ganHuoService.getGanHuoList("1")
                .compose(RxHandleResult.handleResult())
                .subscribe(new CustomObserver<List<GanHuoBean>>() {

                    @Override
                    protected void _onNext(List<GanHuoBean> ganHuoBeans) {
                        LogUtils.d("当前线程" + Thread.currentThread().getName() + ":" + ganHuoBeans.toString());
                    }

                    @Override
                    protected void _onError(String message, int code) {
                    }
                });
    }

    public void testApi2() {
        GanHuoService ganHuoService = ApiManager.getApi().create(GanHuoService.class);
        ganHuoService.getGanHuoListTest()
                .compose(RxHandleResult.handleResult())
                .subscribe(new CustomObserver<List<GanHuoBean>>() {

                    @Override
                    protected void _onNext(List<GanHuoBean> ganHuoBeans) {
                        if (ganHuoBeans !=null){
                            LogUtils.d("当前线程" + Thread.currentThread().getName() + ":" + ganHuoBeans.toString());
                        }
                        LogUtils.d("当前线程" + Thread.currentThread().getName() + ":" + ganHuoBeans);
                    }

                    @Override
                    protected void _onError(String message, int code) {
                    }
                });
    }


    @Override
    public void showMsg(String message, int code) {
        super.showMsg(message, code);
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
