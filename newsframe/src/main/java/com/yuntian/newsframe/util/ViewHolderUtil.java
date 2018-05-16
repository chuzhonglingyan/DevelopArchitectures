package com.yuntian.newsframe.util;

import com.yuntian.adapterlib.base.ModeViewHolder;
import com.yuntian.adapterlib.base.TypeInterface;
import com.yuntian.adapterlib.base.ViewHolderManager;
import com.yuntian.newsframe.R;
import com.yuntian.newsframe.ui.ganhuo.bean.GankInfo;
import com.yuntian.newsframe.ui.ganhuo.list.ArticleViewHolder;
import com.yuntian.newsframe.ui.ganhuo.list.RestViewHolder;
import com.yuntian.newsframe.ui.ganhuo.list.WelfaseViewHolder;
import com.yuntian.newsframe.ui.news.bean.NewsBean;
import com.yuntian.newsframe.ui.news.list.NewsItemViewHolder01;
import com.yuntian.newsframe.ui.news.list.NewsItemViewHolder02;
import com.yuntian.newsframe.ui.video.bean.VideoInfo;
import com.yuntian.newsframe.ui.video.list.VideoRestViewHolder;

import java.util.HashMap;
import java.util.Map;

/**
 * description  .
 * Created by ChuYingYan on 2018/5/4.
 */
public class ViewHolderUtil {


    public static final int ITEM_TYPE_NORMAL = 0;
    public static final int ITEM_TYPE_PHOTO_SET = ITEM_TYPE_NORMAL+1;

    public static final int ITEM_TYPE_GANK_ARTICLE = ITEM_TYPE_PHOTO_SET+1;
    public static final int ITEM_TYPE_GANK_WELFARE = ITEM_TYPE_GANK_ARTICLE+1;
    public static final int ITEM_TYPE_GANK_REST = ITEM_TYPE_GANK_WELFARE+1;


    static {

        Map<Integer, ModeViewHolder> newsMap = new HashMap<>();
        newsMap.put(ITEM_TYPE_NORMAL, new ModeViewHolder(R.layout.item_news_list, NewsItemViewHolder01.class));
        newsMap.put(ITEM_TYPE_PHOTO_SET, new ModeViewHolder(R.layout.item_news_photo_set, NewsItemViewHolder02.class));
        ViewHolderManager.registerTypeClass(NewsBean.class, newsMap);


        Map<Integer, ModeViewHolder> gankMap = new HashMap<>();
        gankMap.put(ITEM_TYPE_GANK_ARTICLE, new ModeViewHolder(R.layout.item_gank_article_list, ArticleViewHolder.class));
        gankMap.put(ITEM_TYPE_GANK_WELFARE, new ModeViewHolder(R.layout.item_gank_welfare_list, WelfaseViewHolder.class));
        gankMap.put(ITEM_TYPE_GANK_REST, new ModeViewHolder(R.layout.item_gank_rest_list, RestViewHolder.class));
        ViewHolderManager.registerTypeClass(GankInfo.class, gankMap);

        ViewHolderManager.registerTypeClass(VideoInfo.class,R.layout.item_video_rest_list, VideoRestViewHolder.class);
    }

    public static void init(){

    }

    public static int getViewType(Class<? extends TypeInterface> classData, int customKey) {
        return ViewHolderManager.getViewType(classData, customKey);
    }


}
