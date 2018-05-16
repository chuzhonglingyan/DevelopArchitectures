package com.yuntian.newsframe.ui.ganhuo.list;

import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.SizeUtils;
import com.yuntian.basecomponent.base.BaseBindingViewHolder;
import com.yuntian.baselibs.glide.ImageLoaderUtil;
import com.yuntian.newsframe.databinding.ItemGankWelfareListBinding;
import com.yuntian.newsframe.ui.ganhuo.bean.GankInfo;
import com.yuntian.newsframe.util.GankUitl;

/**
 * description  .
 * Created by ChuYingYan on 2018/5/8.
 */
public class WelfaseViewHolder extends BaseBindingViewHolder<ItemGankWelfareListBinding, GankInfo> {

    // 图片的宽度
    private int mPhotoWidth;

    public WelfaseViewHolder(View itemView) {
        super(itemView);
        int widthPixels = context.getResources().getDisplayMetrics().widthPixels;
        mPhotoWidth = widthPixels / 2 - SizeUtils.dp2px(1);
    }

    @Override
    public void onBindViewData(GankInfo info, int pos) {
        mBinding.setGanWelfareItem(info);

        int photoHeight = GankUitl.calcPhotoHeight(info.getPixel(), mPhotoWidth);
        // 返回的数据有像素分辨率，根据这个来缩放图片大小
        final ViewGroup.LayoutParams params = mBinding.ivWelfareImg.getLayoutParams();
        params.width = mPhotoWidth;
        params.height = photoHeight;
        mBinding.ivWelfareImg.setLayoutParams(params);
        ImageLoaderUtil.displayImage(info.getUrl(),mBinding.ivWelfareImg);
    }

}
