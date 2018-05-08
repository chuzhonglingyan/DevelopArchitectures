package com.yuntian.newsframe.ui.ganhuo.list;

import android.view.View;
import android.widget.LinearLayout;

import com.yuntian.basecomponent.base.BaseBindingViewHolder;
import com.yuntian.baselibs.glide.ImageLoaderUtil;
import com.yuntian.newsframe.databinding.ItemGankWelfareListBinding;
import com.yuntian.newsframe.ui.ganhuo.bean.GankInfo;

import java.util.Random;

/**
 * description  .
 * Created by ChuYingYan on 2018/5/8.
 */
public class WelfaseViewHolder extends BaseBindingViewHolder<ItemGankWelfareListBinding, GankInfo> {


    public WelfaseViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void onBindViewData(GankInfo info, int pos) {
        mBinding.setGanWelfareItem(info);

        LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) mBinding.ivWelfareImg.getLayoutParams();
        //设置高
        Random random=new Random();
        params.height=(100+random.nextInt(9)*50);
        int picWidth=params.width;
        int picHeight=params.height;
        mBinding.ivWelfareImg.setLayoutParams(params);
        //http://img.gank.io/90db2f35-2e9d-4d75-b5a9-53ee1719b57b
        //此图我只需要 宽度：100 的图片，而无需原始图片，则在请求图片的参数上带上： ?imageView2/0/w/100 即可
        //http://img.gank.io/6ade6383-bc8e-40e4-9919-605901ad0ca5?imageView2/0/w/100
        ImageLoaderUtil.displayImage(info.getUrl(),mBinding.ivWelfareImg);
    }

}
