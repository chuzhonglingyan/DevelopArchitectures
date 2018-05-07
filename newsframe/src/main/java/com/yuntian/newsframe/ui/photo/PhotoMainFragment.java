package com.yuntian.newsframe.ui.photo;

import android.os.Bundle;

import com.yuntian.basecomponent.base.BaseDataBindingFrgament;
import com.yuntian.basecomponent.util.ToolBarUtil;
import com.yuntian.newsframe.R;
import com.yuntian.newsframe.databinding.FragmentPhotoMainBinding;

/**
 * description  .
 * Created by ChuYingYan on 2018/5/3.
 */
public class PhotoMainFragment extends BaseDataBindingFrgament<FragmentPhotoMainBinding> {

    public static final String TAG = "PhotoMainFragment";

  //  private BaseFPageStateAdapter fragmentAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_photo_main;
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
//        List<Fragment> fragments = new ArrayList<>();
//        fragments.add(new BeautyListFragment());
//        fragments.add(new WelfareListFragment());
//        fragments.add(new PhotoMainFragment());
//        fragmentAdapter = new BaseFPageStateAdapter(getChildFragmentManager(), fragments, new String[]{"美女", "福利", "生活"});
//        mViewBinding.viewPager.setAdapter(fragmentAdapter);
//        mViewBinding.tabLayout.setViewPager(mViewBinding.viewPager);
    }

}
