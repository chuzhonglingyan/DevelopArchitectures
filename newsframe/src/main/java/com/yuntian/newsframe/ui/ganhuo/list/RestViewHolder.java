package com.yuntian.newsframe.ui.ganhuo.list;

import android.view.View;

import com.yuntian.basecomponent.base.BaseBindingViewHolder;
import com.yuntian.newsframe.databinding.ItemGankRestListBinding;
import com.yuntian.newsframe.ui.ganhuo.bean.GankInfo;

/**
 * description  ItemNewsListBinding.
 * Created by ChuYingYan on 2018/5/4.
 */
public class RestViewHolder extends BaseBindingViewHolder<ItemGankRestListBinding, GankInfo> {


    public RestViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void onBindViewData(GankInfo info, int pos) {
        mBinding.setGanRestItem(info);
    }


}
