package com.yuntian.baselibs.adapter;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * description  只缓存Fragment的状态.
 * Created by ChuYingYan on 2018/4/14.
 */
public class BaseFPageStateAdapter extends FragmentStatePagerAdapter {


    private List<Fragment> data = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private FragmentManager fragmentManager;


    public BaseFPageStateAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity.getSupportFragmentManager());
        fragmentManager=fragmentActivity.getSupportFragmentManager();
    }

    public BaseFPageStateAdapter(FragmentActivity fragmentActivity, List<Fragment> list, List<String> titleList) {
        super(fragmentActivity.getSupportFragmentManager());
        fragmentManager=fragmentActivity.getSupportFragmentManager();
        if (list != null) {
            data.addAll(list);
        }
        if (titles != null) {
            titles.addAll(titleList);
        }
    }

    public BaseFPageStateAdapter(FragmentManager fragmentManager, List<Fragment> list, List<String> titleList) {
        super(fragmentManager);
        this.fragmentManager=fragmentManager;
        if (list != null) {
            data.addAll(list);
        }
        if (titles != null) {
            titles.addAll(titleList);
        }
    }

    public BaseFPageStateAdapter(FragmentManager fragmentManager, List<Fragment> list, String[] strList) {
        this(fragmentManager, list, toList(strList));
    }


    public static List<String> toList(String[] strList) {
        List<String> title = new ArrayList<>();
        for (int i = 0; i < strList.length; i++) {
            title.add(strList[i]);
        }
        return title;
    }

    public void setData(List<Fragment> list) {
        if (list != null) {
            data.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void addFragment(Fragment fragment) {
        if (fragment != null) {
            if (fragment.isAdded()) {
            } else {
                data.add(fragment);
                notifyDataSetChanged();
            }
        }
    }

    public void addFragment(Fragment fragment, ViewPager viewPager) {
        if (fragment != null && viewPager != null) {
            if (!fragment.isAdded()) {
                data.add(fragment);
                notifyDataSetChanged();
                viewPager.setCurrentItem(data.size() - 1, true);
            }
        }
    }

    public void remveFragment(Fragment fragment, ViewPager viewPager) {
        if (fragment != null && viewPager != null) {
            if (!fragment.isAdded()) {
                data.remove(fragment);
                notifyDataSetChanged();
                viewPager.setCurrentItem(data.size() - 1, true);
            }
        }
    }

    public void remveFragment(int index, ViewPager viewPager) {
        if (index < data.size() && viewPager != null) {
            data.remove(index);
            notifyDataSetChanged();
            if (index > 1) {
                viewPager.setCurrentItem(index - 1, true);
            } else {
                viewPager.setCurrentItem(0, true);
            }
        }
    }

    /**
     * 刷新fragment
     * @param fragments
     * @param mTitles
     */
    public void updateFragments( List<Fragment> fragments, List<String> mTitles) {
        if (data != null && data.size() > 0) {
            FragmentTransaction ft = fragmentManager.beginTransaction();  //移除之前所有的Fragment
            for (Fragment f : data) {
                ft.remove(f);
            }
            ft.commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
            titles.clear();
            data.clear();
        }
        titles.addAll(mTitles);
        data.addAll(fragments);
        notifyDataSetChanged();
    }


    /**
     * 刷新fragment
     * @param fm
     * @param fragments
     * @param mTitles
     */
    public void updateFragments(FragmentManager fm, List<Fragment> fragments, List<String> mTitles) {
        if (data != null && data.size() > 0) {
            FragmentTransaction ft = fm.beginTransaction();  //移除之前所有的Fragment
            for (Fragment f : data) {
                ft.remove(f);
            }
            ft.commitAllowingStateLoss();
            fm.executePendingTransactions();
            titles.clear();
            data.clear();
        }
        titles.addAll(mTitles);
        data.addAll(fragments);
        notifyDataSetChanged();
    }


    public void swapItems(int fromPos, int toPos) {
        Collections.swap(titles, fromPos, toPos);
        Collections.swap(data, fromPos, toPos);
        notifyDataSetChanged();
    }

    public void modifyTitle(int position, String title) {
        titles.set(position, title);
        notifyDataSetChanged();
    }

    public List<Fragment> getData() {
        return data;
    }


    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }
}
