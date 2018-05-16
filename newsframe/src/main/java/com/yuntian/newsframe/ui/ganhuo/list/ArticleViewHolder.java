package com.yuntian.newsframe.ui.ganhuo.list;

import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ObjectUtils;
import com.yuntian.basecomponent.base.BaseBindingViewHolder;
import com.yuntian.baselibs.glide.ImageLoaderUtil;
import com.yuntian.newsframe.databinding.ItemGankArticleListBinding;
import com.yuntian.newsframe.ui.ganhuo.bean.GankInfo;
import com.yuntian.newsframe.util.GankUitl;

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
        //http://img.gank.io/90db2f35-2e9d-4d75-b5a9-53ee1719b57b
        //此图我只需要 宽度：100 的图片，而无需原始图片，则在请求图片的参数上带上： ?imageView2/0/w/100 即可
        //http://img.gank.io/6ade6383-bc8e-40e4-9919-605901ad0ca5?imageView2/0/w/100
        ViewGroup.LayoutParams params = mBinding.ivIcon.getLayoutParams();
        String urlImage="";
        if (!ObjectUtils.isEmpty(info.getImages())){
            urlImage=info.getImages().get(0);
        }
        ImageLoaderUtil.displayImage(GankUitl.getRequireImageUrl(urlImage,params.width,params.height),mBinding.ivIcon);
    }


}
