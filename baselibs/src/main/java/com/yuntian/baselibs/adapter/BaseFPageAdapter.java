package com.yuntian.baselibs.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * description  缓存Fragment的实例.
 * Created by ChuYingYan on 2018/4/14.
 */
public class BaseFPageAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private List<Fragment> data = new ArrayList<>();
    private List<String>  title=new ArrayList<>();



    public BaseFPageAdapter(FragmentActivity fragmentActivity, List<Fragment> list, List<String>  titleList) {
        super(fragmentActivity.getSupportFragmentManager());
        this.mContext = fragmentActivity;
        if (list != null) {
            data.addAll(list);
        }
        if (title!=null){
            title.addAll(titleList);
        }
    }


    public void setData(List<Fragment> list) {
        if (list != null) {
            data.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void updateData() {

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }
}
