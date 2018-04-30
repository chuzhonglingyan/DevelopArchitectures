package com.yuntian.basedragger2.mvp;

import android.content.Context;

import javax.inject.Inject;

/**
 * description P层.
 * Created by ChuYingYan on 2018/4/30.
 */
public abstract class BasePresenter<M extends BaseModel, V extends BaseView> implements IBasePresenter {

    protected final String TAG = this.getClass().getSimpleName();

    @Inject
    protected M mModel;
    @Inject
    protected V mView;

    protected Context mContext;

    public BasePresenter() {

    }


    public Context  getContext(){
        if (mView instanceof Context){
            return (Context) mView;
        }
        return  null;
    }



    @Override
    public void onCreate() {
        mContext=getContext();
    }

    @Override
    public void onStart() {

    }


    @Override
    public void onResume() {

    }


    @Override
    public void onPause() {

    }


    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }
}
