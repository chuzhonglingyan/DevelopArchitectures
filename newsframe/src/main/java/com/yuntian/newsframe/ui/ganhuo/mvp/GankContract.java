package com.yuntian.newsframe.ui.ganhuo.mvp;

import com.yuntian.basedragger2.mvp.BaseModel;
import com.yuntian.basedragger2.mvp.BasePresenter;
import com.yuntian.basedragger2.mvp.BaseView;
import com.yuntian.newsframe.ui.ganhuo.bean.GankInfo;

import java.util.List;

import io.reactivex.Observable;

/**
 * description 合约类 .
 * Created by ChuYingYan on 2018/4/29.
 */
public interface GankContract {

    interface View extends BaseView {

        void getWelfarePhotos(List<GankInfo> result);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void getWelfarePhotos(String datatypeStr,int startPage);
    }

    interface Model extends BaseModel {
        Observable<List<GankInfo>>  getWelfarePhotos(String datatypeStr, int startPage);
    }
}
