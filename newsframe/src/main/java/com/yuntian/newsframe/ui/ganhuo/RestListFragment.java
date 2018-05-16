package com.yuntian.newsframe.ui.ganhuo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.SizeUtils;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.yuntian.adapterlib.base.BaseRvAdapter;
import com.yuntian.adapterlib.listener.OnItemDataClickListenerImp;
import com.yuntian.basedragger2.inject.AppComponent;
import com.yuntian.newsframe.R;
import com.yuntian.newsframe.databinding.FrgmentSmartListBinding;
import com.yuntian.newsframe.ui.ganhuo.bean.GankInfo;
import com.yuntian.newsframe.ui.ganhuo.inject.DaggerGankComponent;
import com.yuntian.newsframe.ui.ganhuo.inject.GankModule;
import com.yuntian.newsframe.ui.ganhuo.list.GridDividerItemDecoration;
import com.yuntian.newsframe.ui.ganhuo.mvp.GankContract;
import com.yuntian.newsframe.ui.ganhuo.mvp.GankViewFragment;

import java.util.List;

import javax.inject.Inject;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

import static com.yuntian.newsframe.storage.AppConstants.GANK_DATATYPE;

public class RestListFragment extends GankViewFragment<FrgmentSmartListBinding, GankContract.Presenter> {

    @Inject
    BaseRvAdapter baseRvAdapter;


    private String dataType;
    private int startPage;

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
        initRecyclerViewV(mContext, mViewBinding.rv, new GridDividerItemDecoration(SizeUtils.dp2px(5),getResources().getColor(R.color.ddd_color)), baseRvAdapter,2);
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

        mViewBinding.rv.addOnScrollListener(new RecyclerView.OnScrollListener() {

            int firstVisibleItem, lastVisibleItem;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager= (LinearLayoutManager) mViewBinding.rv.getLayoutManager();
                firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                //大于0说明有播放
                if (GSYVideoManager.instance().getPlayPosition() >= 0) {
                    //当前播放的位置
                    int position = GSYVideoManager.instance().getPlayPosition();
                    //对应的播放列表TAG
                    if (GSYVideoManager.instance().getPlayTag().equals("RecyclerItemNormalHolder")
                            && (position < firstVisibleItem || position > lastVisibleItem)) {
                        //如果滑出去了上面和下面就是否，和今日头条一样
                        if(!GSYVideoManager.isFullState(getActivity())) {
                            GSYVideoManager.releaseAllVideos();
                            baseRvAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        });


    }

    public static void initRecyclerViewV(Context context, RecyclerView view, RecyclerView.ItemDecoration itemDecoration, RecyclerView.Adapter adapter, int column) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false);
        view.setLayoutManager(layoutManager);
        view.setItemAnimator(new DefaultItemAnimator());
        for(int i = 0; i < view.getItemDecorationCount(); ++i) {
            view.removeItemDecorationAt(i);
        }
        if (itemDecoration!=null) {
            view.addItemDecoration(itemDecoration);
        }
        view.setAdapter(adapter);
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



    @Override
    public void onResume() {
        super.onResume();
        GSYVideoManager.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
    }

    public boolean onBackPressed() {
        if (GSYVideoManager.backFromWindowFull(getActivity())) {
            return true;
        }
        return false;
    }




}
