package com.yuntian.newsframe.ui.ganhuo;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.yuntian.basecomponent.util.ToolBarUtil;
import com.yuntian.basedragger2.inject.AppComponent;
import com.yuntian.baselibs.util.FragmentHelper;
import com.yuntian.newsframe.R;
import com.yuntian.newsframe.databinding.FragmentGankMainBinding;
import com.yuntian.newsframe.storage.AppConstants;
import com.yuntian.newsframe.ui.ganhuo.inject.DaggerGankComponent;
import com.yuntian.newsframe.ui.ganhuo.inject.GankModule;
import com.yuntian.newsframe.ui.ganhuo.mvp.GankContract;
import com.yuntian.newsframe.ui.ganhuo.mvp.GankViewFragment;

import java.util.ArrayList;
import java.util.List;

import static com.yuntian.newsframe.storage.AppConstants.GANK_ARTICLE;
import static com.yuntian.newsframe.storage.AppConstants.GANK_REST;
import static com.yuntian.newsframe.storage.AppConstants.GANK_WELFARE;

/**
 * description  .
 * Created by ChuYingYan on 2018/5/3.
 */
public class GankMainFragment extends GankViewFragment<FragmentGankMainBinding, GankContract.Presenter> {

    public static final String TAG = "PhotoMainFragment";

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gank_main;
    }

    @Override
    protected void init() {
        ToolBarUtil.initToolBar(mActivity, mViewBinding.toolBar, true, "图片");
    }


    @Override
    protected void initView() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

        List<Fragment> fragments = new ArrayList<>();
        Bundle bundlearticle=new Bundle();
        bundlearticle.putString(AppConstants.GANK_DATATYPE,GANK_ARTICLE);

        Bundle bundlearWelfare=new Bundle();
        bundlearWelfare.putString(AppConstants.GANK_DATATYPE,GANK_WELFARE);

        Bundle bundleRest=new Bundle();
        bundleRest.putString(AppConstants.GANK_DATATYPE,GANK_REST);

        fragments.add(FragmentHelper.newInstance(ArticleListFragment.class,bundlearticle));
        fragments.add(FragmentHelper.newInstance(WelfareListFragment.class,bundlearWelfare));
        fragments.add(FragmentHelper.newInstance(RestListFragment.class,bundleRest));

        baseFPageAdapter.updateFragments(fragments,new String[]{"技术文章", "福利生活", "休息视频"});

        mViewBinding.viewPager.setAdapter(baseFPageAdapter);
        mViewBinding.tabLayout.setViewPager(mViewBinding.viewPager);
    }

    @Override
    protected void lazyLoad() {

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
