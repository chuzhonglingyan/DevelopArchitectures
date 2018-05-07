package com.yuntian.newsframe.ui.photo.mvp;

import com.yuntian.basedragger2.mvp.BaseModel;
import com.yuntian.basedragger2.mvp.BasePresenter;
import com.yuntian.basedragger2.mvp.BaseView;
import com.yuntian.newsframe.ui.news.bean.NewsBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * description 合约类 .
 * Created by ChuYingYan on 2018/4/29.
 */
public interface PhotoContract {

    interface View extends BaseView {

        void getNewsListSuccess(List<NewsBean> result);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void getNewsList(String type,final String id, int startPage);
    }

    interface Model extends BaseModel {

        Observable<List<NewsBean>> getNewsList(String type, final String id, int startPage);
    }
}
