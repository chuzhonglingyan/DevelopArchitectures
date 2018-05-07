package com.yuntian.newsframe.ui.news;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.yuntian.adapterlib.listener.OnItemDataClickListenerImp;
import com.yuntian.adapterlib.util.RecyclerViewUtil;
import com.yuntian.basedragger2.inject.AppComponent;
import com.yuntian.newsframe.R;
import com.yuntian.newsframe.databinding.FrgmentSmartListBinding;
import com.yuntian.newsframe.ui.news.bean.NewsBean;
import com.yuntian.newsframe.ui.news.inject.DaggerNewsComponent;
import com.yuntian.newsframe.ui.news.inject.NewsModule;
import com.yuntian.newsframe.ui.news.mvp.NewsContract;
import com.yuntian.newsframe.ui.news.mvp.NewsViewFragment;

import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

import static com.yuntian.newsframe.storage.AppConstants.NEWS_ID;
import static com.yuntian.newsframe.storage.AppConstants.NEWS_TYPE;

public class NewsListFragment extends NewsViewFragment<FrgmentSmartListBinding, NewsContract.Presenter> {


    private String mNewsId;
    private String mNewsType;
    private int startPage;

    @Override
    protected int getLayoutId() {
        return R.layout.frgment_smart_list;
    }

    @Override
    protected void initView() {
        startPage = 0;
        RecyclerViewUtil.initRecyclerViewV(mContext, mViewBinding.rv, true, baseRvAdapter);
        mViewBinding.rv.setItemAnimator(new SlideInUpAnimator());
        baseRvAdapter.setOnItemDataClickListener(new OnItemDataClickListenerImp() {
            @Override
            public void onItemClick(View view, Object item, int truePos, int relativePos) {
                super.onItemClick(view, item, truePos, relativePos);
            }
        });
        mViewBinding.refreshLayout.setOnRefreshListener((refreshlayout) -> {
            startPage = 0;
            mPresenter.getNewsList(mNewsType, mNewsId, startPage);
        });
        mViewBinding.refreshLayout.setEnableLoadMore(false);
        mViewBinding.refreshLayout.setOnLoadMoreListener((refreshlayout) -> {
            mPresenter.getNewsList(mNewsType, mNewsId, startPage);
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void lazyLoad() {
        if (args != null) {
            LogUtils.d("mNewsId" + mNewsId + ",mPresenter" + mPresenter.toString());
            mNewsId = args.getString(NEWS_ID);
            mNewsType = args.getString(NEWS_TYPE);
            mPresenter.getNewsList(mNewsType, mNewsId, startPage);
        }
    }


    @Override
    public void showMsg(String message, int code) {
        super.showMsg(message, code);
        if (startPage == 0) {
            hasLoad=false;
            mViewBinding.refreshLayout.finishRefresh(1000, false);
        } else {
            mViewBinding.refreshLayout.finishLoadMore(1000, false, false);
        }
    }

    @Override
    public void getNewsListSuccess(List<NewsBean> result) {
        super.getNewsListSuccess(result);
        if (startPage == 0) {
            baseRvAdapter.setData(result);
            mViewBinding.refreshLayout.finishRefresh();
            mViewBinding.refreshLayout.setEnableLoadMore(true);
            hasLoad=true;
        } else {
            baseRvAdapter.adds(result);
            mViewBinding.refreshLayout.finishLoadMore();
        }
        if (result != null && result.size() > 0) {
            startPage += 10;
        } else {
            mViewBinding.refreshLayout.finishLoadMoreWithNoMoreData();
        }
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

}
