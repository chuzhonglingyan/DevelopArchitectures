package com.yuntian.newsframe.ui.news.mvp;

import com.yuntian.baselibs.net.cache.CacheStrategy;
import com.yuntian.baselibs.net.core.NetApi;
import com.yuntian.baselibs.util.TimeUtil;
import com.yuntian.newsframe.net.service.NewsService;
import com.yuntian.newsframe.ui.news.bean.NewsBean;
import com.yuntian.newsframe.util.ViewHolderUtil;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * description  M层.
 * Created by ChuYingYan on 2018/4/29.
 */
public class NewsModel implements NewsContract.Model {


    @Inject
    public NewsModel() {

    }

    @Override
    public Observable<List<NewsBean>> getNewsList(String type, final String id, int startPage) {
        return NetApi.getApi().create(NewsService.class)
                .getNewsList(CacheStrategy.getCacheControl(), type, id, startPage)
                .flatMap((stringListMap) -> Observable.fromIterable(stringListMap.get(id)))
                .map((newsBean) -> {
                    {
                        if (newsBean.isPhotoset()) {
                            newsBean.setType(ViewHolderUtil.ITEM_TYPE_PHOTO_SET);
                        }else {
                            newsBean.setType(ViewHolderUtil.ITEM_TYPE_NORMAL);
                        }
                        newsBean.setPtime(TimeUtil.formatDate(newsBean.getPtime()));
                        return newsBean;
                    }
                })
                .distinct()//去重
                .toSortedList((newsBean1, newsBean2) -> newsBean1.getPtime().compareTo(newsBean2.getPtime()))
                .toObservable();
    }
}
