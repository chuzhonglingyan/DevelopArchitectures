package com.yuntian.newsframe.ui.news;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.yuntian.basecomponent.util.TabLayoutUtils;
import com.yuntian.basecomponent.util.ToolBarUtil;
import com.yuntian.basedragger2.inject.AppComponent;
import com.yuntian.baselibs.adapter.BaseFPageStateAdapter;
import com.yuntian.baselibs.util.FragmentHelper;
import com.yuntian.newsframe.R;
import com.yuntian.newsframe.databinding.FragmentNewsMainBinding;
import com.yuntian.newsframe.storage.AppConstants;
import com.yuntian.newsframe.ui.news.bean.NewsChannelTable;
import com.yuntian.newsframe.ui.news.inject.DaggerNewsComponent;
import com.yuntian.newsframe.ui.news.inject.NewsModule;
import com.yuntian.newsframe.ui.news.mvp.NewsContract;
import com.yuntian.newsframe.ui.news.mvp.NewsViewFragment;
import com.yuntian.newsframe.util.NewsChannelTableManager;

import java.util.ArrayList;
import java.util.List;

public class NewsMainFragment extends NewsViewFragment<FragmentNewsMainBinding, NewsContract.Presenter> {


    public static final String TAG = "NewsMainFragment";

    private BaseFPageStateAdapter fragmentAdapter;

    private int isVisablePos=0;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news_main;
    }

    @Override
    protected void initView() {
        ToolBarUtil.initToolBar(mActivity, mViewBinding.toolbar, true, "新闻");
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        List<Fragment> fragments = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        List<NewsChannelTable> checkList = NewsChannelTableManager.loadNewsChannelsStatic();
        for (NewsChannelTable bean : checkList) {
            titles.add(bean.getNewsChannelName());
            Bundle bundle = new Bundle();
            bundle.putString(AppConstants.NEWS_ID, bean.getNewsChannelId());
            bundle.putString(AppConstants.NEWS_TYPE, bean.getNewsChannelType());
            fragments.add(FragmentHelper.newInstance(NewsListFragment.class, bundle));
        }
        fragmentAdapter = new BaseFPageStateAdapter(getChildFragmentManager(), fragments, titles);
        mViewBinding.viewPager.setAdapter(fragmentAdapter);
        mViewBinding.tabLayout.setupWithViewPager(mViewBinding.viewPager);
        //tab切换时不会调用onResmue方法，会调用setUserVisiable方法
        TabLayoutUtils.dynamicSetTabLayoutMode(mViewBinding.tabLayout);
    }


    @Override
    public void showMsg(String message, int code) {
        super.showMsg(message, code);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerNewsComponent
                .builder()
                .appComponent(appComponent)
                .newsModule(new NewsModule(this))
                .build()
                .inject(this);  //调用inject，注入就完成了。此时使用@Inject来标记成员变量就可以使用了
    }

    @Override
    protected void lazyLoad() {

    }




    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        List<Fragment> fragmentList=fragmentAdapter.getData();
        if (hidden){
            for (int i = 0; i < fragmentList.size(); i++) {
                Fragment fragment=fragmentList.get(i);
                if (fragment.getUserVisibleHint()){
                    isVisablePos=i;
                }
                fragment.setUserVisibleHint(false);
            }
        }else {
            for (int i = 0; i < fragmentList.size(); i++) {
                Fragment fragment=fragmentList.get(i);
                if (isVisablePos==i){
                    fragment.setUserVisibleHint(true);
                }else {
                    fragment.setUserVisibleHint(false);
                }
            }
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        isVisablePos=0;
    }


}
