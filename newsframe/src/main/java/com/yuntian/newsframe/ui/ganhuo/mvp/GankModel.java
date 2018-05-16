package com.yuntian.newsframe.ui.ganhuo.mvp;

import android.text.TextUtils;

import com.yuntian.baselibs.glide.ImageLoaderUtil;
import com.yuntian.baselibs.net.core.NetApi;
import com.yuntian.baselibs.net.result.RxHandleResult;
import com.yuntian.newsframe.net.GankService;
import com.yuntian.newsframe.ui.ganhuo.bean.GankInfo;
import com.yuntian.newsframe.util.GankUitl;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.yuntian.newsframe.storage.AppConstants.GANK_ARTICLE;
import static com.yuntian.newsframe.storage.AppConstants.GANK_REST;
import static com.yuntian.newsframe.storage.AppConstants.GANK_WELFARE;

/**
 * description  M层.
 * Created by ChuYingYan on 2018/4/29.
 */
public class GankModel implements GankContract.Model {


    @Inject
    public GankModel() {

    }



    @Override
    public  Observable<List<GankInfo>>  getWelfarePhotos(final String datatypeStr, int startPage) {
        if (TextUtils.equals(datatypeStr,GANK_ARTICLE)){
            return NetApi.getApi().create(GankService.class)
                    .getWelfarePhotos(datatypeStr,startPage)
                    .compose(RxHandleResult.handleResult())
                    .concatMap((list)->Observable.fromIterable(list))
                    .map((ganInfo)-> {  ganInfo.setDatetype(GankUitl.getDataType(datatypeStr));return ganInfo;})
                    .filter((ganInfo)->!(ganInfo.getType().equals(GANK_WELFARE)||ganInfo.getType().equals(GANK_REST)))
                    .toList().toFlowable().toObservable();
        }else if(TextUtils.equals(datatypeStr,GANK_WELFARE)){
            return NetApi.getApi().create(GankService.class)
                    .getWelfarePhotos(datatypeStr,startPage)
                    .compose(RxHandleResult.handleResult())
                    .concatMap((list)->Observable.fromIterable(list))
                    .map((ganInfo)-> {  ganInfo.setDatetype(GankUitl.getDataType(datatypeStr));
                         return ganInfo;})
                    .observeOn(Schedulers.io())
                    .filter((ganInfo)->{try { ganInfo.setPixel(ImageLoaderUtil.calePhotoSize(ganInfo.getUrl()));
                        return true;
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                        return false;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return false;
                    }})
                    .toList().toFlowable().toObservable()
                    .observeOn(AndroidSchedulers.mainThread());
        }else if(TextUtils.equals(datatypeStr,GANK_REST)){
            return NetApi.getApi().create(GankService.class)
                    .getWelfarePhotos(datatypeStr,startPage)
                    .compose(RxHandleResult.handleResult())
                    .concatMap((list)->Observable.fromIterable(list))
                    .map((ganInfo)-> { ganInfo.setDatetype(GankUitl.getDataType(datatypeStr)); return ganInfo;})
                    .toList().toFlowable().toObservable();
        }
        return  null;
    }

}
