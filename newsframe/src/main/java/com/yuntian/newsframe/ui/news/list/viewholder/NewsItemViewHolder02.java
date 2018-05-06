package com.yuntian.newsframe.ui.news.list.viewholder;

import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.ObjectUtils;
import com.yuntian.baselibs.glide.ImageLoaderUtil;
import com.yuntian.basecomponent.base.BaseBindingViewHolder;
import com.yuntian.newsframe.databinding.ItemNewsPhotoSetBinding;
import com.yuntian.newsframe.ui.news.bean.NewsBean;

/**
 * description  ItemNewsPhotoSetBinding.
 * Created by ChuYingYan on 2018/5/4.
 */
public class NewsItemViewHolder02 extends BaseBindingViewHolder<ItemNewsPhotoSetBinding, NewsBean> {


    public NewsItemViewHolder02(View itemView) {
        super(itemView);
    }

    @Override
    public void onBindViewData(NewsBean newsBean, int pos) {
        mBinding.setNewsItem(newsBean);
        mBinding.labelView.setVisibility(newsBean.isPhotoset()?View.VISIBLE:View.GONE);
        ImageView[] newsPhoto = new ImageView[3];
        newsPhoto[0] = mBinding.ivIcon1;
        newsPhoto[1] = mBinding.ivIcon2;
        newsPhoto[2] = mBinding.ivIcon3;
        ImageLoaderUtil.displayImage(newsBean.getImgsrc(), mBinding.ivIcon1);
        if (!ObjectUtils.isEmpty(newsBean.getImgextra())) {
            for (int i = 0; i < Math.min(2, newsBean.getImgextra().size()); i++) {
                newsPhoto[i + 1].setVisibility(View.VISIBLE);
                ImageLoaderUtil.displayImage(newsBean.getImgextra().get(i).getImgsrc(), newsPhoto[i + 1]);
            }
        }
    }

}
