package com.yuntian.baselibs.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.LogUtils;


/**
 * description  .
 * Created by ChuYingYan on 2018/4/11.
 */
public abstract class BaseLazyFragment extends BaseFragment {

    protected boolean isVisible = false;
    protected boolean hasLoad = false;
    protected boolean isCache = false;
    protected boolean isInitialized = false;
    private boolean isPrepared = true;

    /**
     * 此方法是结合FragmentPagerAdapter使用
     * 初始化的时候，第一个fragment会执行两次：先false，再true,执行在onCreateView之前
     * 初始化的时候，均执行在onCreateView之前
     * 初始化之后，均执行在onResume之后
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }

    }

    public boolean isCache() {
        return isCache;
    }

    public void setCache(boolean cache) {
        isCache = cache;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (isCache && rootView != null) {
            return rootView;
        }
        View view = super.onCreateView(inflater, container, savedInstanceState);
        init();
        initView();
        initData(savedInstanceState);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (isVisible && isPrepared) {
            lazyLoad();
            isPrepared = false;
        }
    }

    protected abstract void lazyLoad();


    /**
     * 可见
     */
    protected void onVisible() {
        if (isInitialized && !hasLoad) { //第一个fragment有点特殊，没初始化就true
            LogUtils.d("lazy加载开始" + ",args:" + args.toString());
            lazyLoad();
        } else {
            if (hasLoad) {
                LogUtils.d("lazy已经加载成功：hasLoad=" + hasLoad + ",args:" + args.toString());
            }else {
                lazyLoad();
            }
        }
    }

    /**
     * 不可见
     */
    protected void onInvisible() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (isCache) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isVisible = false;
        hasLoad = false;
        isCache = false;
        isInitialized = false;
        isPrepared = true;
    }
}
