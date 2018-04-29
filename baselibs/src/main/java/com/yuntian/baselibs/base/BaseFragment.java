package com.yuntian.baselibs.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * description  .
 * Created by ChuYingYan on 2018/4/11.
 */
public abstract class BaseFragment extends Fragment {


    private static final String TAG = "BaseFragment";

    protected Context mContext;
    protected Activity mActivity;
    protected View rootView;
    protected Bundle args;

    protected boolean isCreate = false; //fragment创建了

    protected boolean isViewInitialized = false; //view初始化了


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        mActivity = (Activity) context;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isCreate = true;
        if (getArguments() != null) {
            args = getArguments();
        } else {
            args = new Bundle();
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(getLayoutId(), container, false);
        isViewInitialized = true;
        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }


    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (args != null) {
            outState.putAll(args);
        }

    }


    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
            args = savedInstanceState;
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onStop() {
        super.onStop();

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

        isViewInitialized=false;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        isCreate = false;

    }


    @Override
    public void onDetach() {
        super.onDetach();

    }


    public Context getContext() {
        return mContext;
    }


    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();


    /**
     * 此方法是结合FragmentPagerAdapter使用
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

    }

    /**
     * 此方法是结合add和hide方法使用
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

}
