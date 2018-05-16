package com.yuntian.newsframe.ui.ganhuo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuntian.adapterlib.base.BaseRvAdapter;
import com.yuntian.adapterlib.listener.OnItemDataClickListenerImp;
import com.yuntian.adapterlib.util.RecyclerViewUtil;
import com.yuntian.basedragger2.inject.AppComponent;
import com.yuntian.newsframe.R;
import com.yuntian.newsframe.databinding.FrgmentSmartListBinding;
import com.yuntian.newsframe.ui.ganhuo.bean.GankInfo;
import com.yuntian.newsframe.ui.ganhuo.inject.DaggerGankComponent;
import com.yuntian.newsframe.ui.ganhuo.inject.GankModule;
import com.yuntian.newsframe.ui.ganhuo.mvp.GankContract;
import com.yuntian.newsframe.ui.ganhuo.mvp.GankViewFragment;

import java.util.List;

import javax.inject.Inject;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

import static com.yuntian.newsframe.storage.AppConstants.GANK_DATATYPE;

public class ArticleListFragment extends GankViewFragment<FrgmentSmartListBinding, GankContract.Presenter> {

    private String dataType;
    private int startPage;
    @Inject
    BaseRvAdapter baseRvAdapter;



    @Override
    protected int getLayoutId() {
        return R.layout.frgment_smart_list;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setCache(true);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initView() {
        startPage = 0;
        RecyclerViewUtil.initRecyclerViewV(mContext, mViewBinding.rv, false, baseRvAdapter);
        mViewBinding.rv.setItemAnimator(new SlideInUpAnimator());
        baseRvAdapter.setOnItemDataClickListener(new OnItemDataClickListenerImp() {
            @Override
            public void onItemClick(View view, Object item, int truePos, int relativePos) {
                super.onItemClick(view, item, truePos, relativePos);
            }
        });
        mViewBinding.refreshLayout.setOnRefreshListener((refreshlayout) -> {
            startPage = 0;
            mPresenter.getWelfarePhotos(dataType,startPage);
        });
        mViewBinding.refreshLayout.setEnableLoadMore(false);
        mViewBinding.refreshLayout.setOnLoadMoreListener((refreshlayout) -> {
            mPresenter.getWelfarePhotos(dataType,startPage);
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void lazyLoad() {
        if (args != null) {
            dataType = args.getString(GANK_DATATYPE);
            mPresenter.getWelfarePhotos(dataType,startPage);
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
    public void getWelfarePhotos(List<GankInfo> result) {
        super.getWelfarePhotos(result);
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
            startPage += 1;
        } else {
            mViewBinding.refreshLayout.finishLoadMoreWithNoMoreData();
        }
    }


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerGankComponent
                .builder()
                .appComponent(appComponent)
                .gankModule(new GankModule(this))
                .build()
                .inject(this);  //调用inject，注入就完成了。此时使用@Inject来标记成员变量就可以使用了
    }

}
