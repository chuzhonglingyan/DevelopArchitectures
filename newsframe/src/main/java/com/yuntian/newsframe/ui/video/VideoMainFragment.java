package com.yuntian.newsframe.ui.video;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.yuntian.basecomponent.util.ToolBarUtil;
import com.yuntian.basedragger2.inject.AppComponent;
import com.yuntian.baselibs.adapter.BaseFPageStateAdapter;
import com.yuntian.baselibs.util.FragmentHelper;
import com.yuntian.newsframe.R;
import com.yuntian.newsframe.databinding.FragmentVideoMainBinding;
import com.yuntian.newsframe.storage.AppConstants;
import com.yuntian.newsframe.ui.video.inject.DaggerVideoComponent;
import com.yuntian.newsframe.ui.video.inject.VideoModule;
import com.yuntian.newsframe.ui.video.mvp.VideoContract;
import com.yuntian.newsframe.ui.video.mvp.VideoViewFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * description  .
 * Created by ChuYingYan on 2018/5/3.
 */
public class VideoMainFragment extends VideoViewFragment<FragmentVideoMainBinding, VideoContract.Presenter> {


    public static final String TAG = "VideoMainFragment";

    private final String[] VIDEO_ID = new String[]{"V9LG4B3A0", "V9LG4E6VR", "V9LG4CHOR", "00850FRB"};
    private final String[] VIDEO_TITLE = new String[]{"热点", "搞笑", "娱乐", "精品"};


    @Inject
    BaseFPageStateAdapter baseFPageStateAdapter;

    private int isVisablePos = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video_main;
    }


    @Override
    protected void initView() {
        ToolBarUtil.initToolBar(mActivity, mViewBinding.toolBar, true, "视频");
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < VIDEO_ID.length; i++) {
            Bundle bundle = new Bundle();
            bundle.putString(AppConstants.VIDEO_ID_KEY, VIDEO_ID[i]);
            bundle.putString(AppConstants.VIDEO_TYPE_KEY, VIDEO_TITLE[i]);
            fragments.add(FragmentHelper.newInstance(VideoListFragment.class, bundle));
        }
        baseFPageStateAdapter = new BaseFPageStateAdapter(getChildFragmentManager(), fragments, VIDEO_TITLE);//嵌套fragment问题
        mViewBinding.viewPager.setAdapter(baseFPageStateAdapter);
        mViewBinding.tabLayout.setViewPager(mViewBinding.viewPager);
    }


    @Override
    public void showMsg(String message, int code) {
        super.showMsg(message, code);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerVideoComponent
                .builder()
                .appComponent(appComponent)
                .videoModule(new VideoModule(this))
                .build()
                .inject(this);  //调用inject，注入就完成了。此时使用@Inject来标记成员变量就可以使用了
    }

    @Override
    protected void lazyLoad() {

    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        List<Fragment> fragmentList = baseFPageStateAdapter.getData();
        if (hidden) {
            for (int i = 0; i < fragmentList.size(); i++) {
                Fragment fragment = fragmentList.get(i);
                if (fragment.getUserVisibleHint()) {
                    isVisablePos = i;
                }
                fragment.setUserVisibleHint(false);
            }
        } else {
            for (int i = 0; i < fragmentList.size(); i++) {
                Fragment fragment = fragmentList.get(i);
                if (isVisablePos == i) {
                    fragment.setUserVisibleHint(true);
                } else {
                    fragment.setUserVisibleHint(false);
                }
            }
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        isVisablePos = 0;
    }


}
