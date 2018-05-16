package com.yuntian.newsframe.ui.main;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.yuntian.basecomponent.base.BaseDataBindingActivity;
import com.yuntian.baselibs.util.FragmentHelper;
import com.yuntian.newsframe.R;
import com.yuntian.newsframe.databinding.ActivityMainBinding;
import com.yuntian.newsframe.storage.AppConstants;
import com.yuntian.newsframe.ui.video.VideoMainFragment;
import com.yuntian.newsframe.ui.news.NewsMainFragment;
import com.yuntian.newsframe.ui.ganhuo.GankMainFragment;

import java.util.List;
import java.util.Stack;

/**
 * description show/hide切换fragment.
 * Created by ChuYingYan on 2018/5/7.
 */
public class MainActivity extends BaseDataBindingActivity<ActivityMainBinding> implements NavigationView.OnNavigationItemSelectedListener {

    private int mItemId = R.id.nav_news;
    private Stack<Integer> stackTabs = new Stack<>();
    private List<Fragment> fragmentList = new Stack<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initDrawerLayout(mViewBinding.drawerLayout, mViewBinding.navView);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mItemId = savedInstanceState.getInt(AppConstants.HOME_CURRENT_TAB_POSITION);
        }
        switchTo(mItemId);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(AppConstants.HOME_CURRENT_TAB_POSITION, mItemId);
        LogUtils.d("HOME_CURRENT_TAB_POSITION=" + mItemId);
    }


    /**
     * 初始化 DrawerLayout
     *
     * @param drawerLayout DrawerLayout
     * @param navView      NavigationView
     */
    private void initDrawerLayout(DrawerLayout drawerLayout, NavigationView navView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
            //将侧边栏顶部延伸至status bar
            drawerLayout.setFitsSystemWindows(true);
            //将主页面顶部延伸至status bar
            drawerLayout.setClipToPadding(false);
        }
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerClosed(View drawerView) {
                switchTo(mItemId);
            }
        });
        navView.setNavigationItemSelectedListener(this);
    }


    public void addToStack(int id) {
        if (!stackTabs.contains(id)) {
            stackTabs.push(id);
        }

    }


    /**
     * 切换
     */
    private void switchTo(int position) {
        switch (position) {
            //新闻
            case R.id.nav_news:
                stackTabs.clear();
                FragmentHelper.addHideShowFragment(this, fragmentList, NewsMainFragment.class, R.id.fl_container, NewsMainFragment.TAG);
                addToStack(R.id.nav_news);
                break;
            //图片
            case R.id.nav_photos:
                FragmentHelper.addHideShowFragment(this, fragmentList, GankMainFragment.class, R.id.fl_container, GankMainFragment.TAG);
                addToStack(R.id.nav_photos);
                break;
            //视频
            case R.id.nav_videos:
                FragmentHelper.addHideShowFragment(this, fragmentList, VideoMainFragment.class, R.id.fl_container, VideoMainFragment.TAG);
                addToStack(R.id.nav_videos);
                break;
            //设置
            case R.id.nav_setting:
                ToastUtils.showShort("设置");
                break;
            default:
                break;
        }
    }


    @Override
    public void onBackPressed() {
        // 获取堆栈里有几个
        final int stackEntryCount = stackTabs.size();
        if (mViewBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            mViewBinding.drawerLayout.closeDrawer(GravityCompat.START);
        } else if (stackEntryCount <= 1) {
            exit(); // 如果剩一个说明在主页，提示按两次退出app
        } else {
            final int tabId = stackTabs.get(stackEntryCount - 2);
            mViewBinding.navView.setCheckedItem(tabId);
            stackTabs.pop();
            switchTo(tabId);
            mItemId = tabId;
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mViewBinding.drawerLayout.closeDrawer(GravityCompat.START);
        mItemId = item.getItemId();
        if (item.isChecked()) {
            return true;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mViewBinding.drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private long mExitTime = 0;

    /**
     * 退出
     */
    private void exit() {
        if (System.currentTimeMillis() - mExitTime > 2000) {
            ToastUtils.showShort("再按一次退出程序");
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fragmentList.clear();
        stackTabs.clear();
    }


}
