package com.yuntian.newsframe.ui.news.list.viewholder;

import android.view.View;

import com.yuntian.baselibs.glide.ImageLoaderUtil;
import com.yuntian.basecomponent.base.BaseBindingViewHolder;
import com.yuntian.newsframe.databinding.ItemNewsListBinding;
import com.yuntian.newsframe.ui.news.bean.NewsBean;

/**
 * description  ItemNewsListBinding.
 * Created by ChuYingYan on 2018/5/4.
 */
public class NewsItemViewHolder01 extends BaseBindingViewHolder<ItemNewsListBinding, NewsBean> {


    public NewsItemViewHolder01(View itemView) {
        super(itemView);
    }

    @Override
    public void onBindViewData(NewsBean newsBean, int pos) {
        mBinding.setNewsItem(newsBean);
        mBinding.labelView.setVisibility(newsBean.isSpecial()?View.VISIBLE:View.GONE);
        ImageLoaderUtil.displayImage(newsBean.getImgsrc(),mBinding.ivIcon);
    }


}
