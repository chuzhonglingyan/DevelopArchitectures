package com.yuntian.newsframe.ui.ganhuo.mvp;

import com.yuntian.basedragger2.scope.ActivityScope;
import com.yuntian.baselibs.net.result.CustomObserver;
import com.yuntian.newsframe.ui.ganhuo.bean.GankInfo;

import java.util.List;

import javax.inject.Inject;

/**
 * description  P层.
 * Created by ChuYingYan on 2018/4/29.
 */
@ActivityScope
public class GankPresenter extends GankContract.Presenter {


    @Inject
    public GankPresenter() {

    }


    @Override
    public void getWelfarePhotos(String dataTtpe, int startPage) {
          mModel.getWelfarePhotos(dataTtpe,startPage)
                .subscribe(new CustomObserver<List<GankInfo>>() {
                    @Override
                    protected void _onNext(List<GankInfo> photoGirls) {
                         mView.getWelfarePhotos(photoGirls);
                    }

                    @Override
                    protected void _onError(String message, int code) {
                        mView.showMsg(message,code);
                    }
                });
    }
}
