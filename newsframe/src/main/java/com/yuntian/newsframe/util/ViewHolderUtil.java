package com.yuntian.newsframe.util;

import com.yuntian.adapterlib.base.ModeViewHolder;
import com.yuntian.adapterlib.base.TypeInterface;
import com.yuntian.adapterlib.base.ViewHolderManager;
import com.yuntian.newsframe.R;
import com.yuntian.newsframe.ui.news.bean.NewsBean;
import com.yuntian.newsframe.ui.news.list.viewholder.NewsItemViewHolder01;
import com.yuntian.newsframe.ui.news.list.viewholder.NewsItemViewHolder02;

import java.util.HashMap;
import java.util.Map;

/**
 * description  .
 * Created by ChuYingYan on 2018/5/4.
 */
public class ViewHolderUtil {


    public static final int ITEM_TYPE_NORMAL = 0;
    public static final int ITEM_TYPE_PHOTO_SET = ITEM_TYPE_NORMAL+1;

    static {

        Map<Integer, ModeViewHolder> newsMap = new HashMap<>();
        newsMap.put(ITEM_TYPE_NORMAL, new ModeViewHolder(R.layout.item_news_list, NewsItemViewHolder01.class));
        newsMap.put(ITEM_TYPE_PHOTO_SET, new ModeViewHolder(R.layout.item_news_photo_set, NewsItemViewHolder02.class));
        ViewHolderManager.registerTypeClass(NewsBean.class, newsMap);

    }

    public static void init(){

    }

    public static int getViewType(Class<? extends TypeInterface> classData, int customKey) {
        return ViewHolderManager.getViewType(classData, customKey);
    }


}
