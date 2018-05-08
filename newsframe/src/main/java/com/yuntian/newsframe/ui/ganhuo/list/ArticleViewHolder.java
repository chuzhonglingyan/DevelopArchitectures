package com.yuntian.newsframe.ui.ganhuo.list;

import android.view.View;

import com.yuntian.basecomponent.base.BaseBindingViewHolder;
import com.yuntian.newsframe.databinding.ItemGankArticleListBinding;
import com.yuntian.newsframe.ui.ganhuo.bean.GankInfo;

/**
 * description  ItemNewsListBinding.
 * Created by ChuYingYan on 2018/5/4.
 */
public class ArticleViewHolder extends BaseBindingViewHolder<ItemGankArticleListBinding, GankInfo> {


    public ArticleViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void onBindViewData(GankInfo info, int pos) {
        mBinding.setGanArticleItem(info);
    }


}
