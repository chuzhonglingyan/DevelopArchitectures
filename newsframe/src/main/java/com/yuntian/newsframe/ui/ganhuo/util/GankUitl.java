package com.yuntian.newsframe.ui.ganhuo.util;


import android.text.TextUtils;

import com.yuntian.newsframe.util.ViewHolderUtil;

import static com.yuntian.newsframe.storage.AppConstants.GANK_REST;
import static com.yuntian.newsframe.storage.AppConstants.GANK_WELFARE;

/**
 * description  .
 * Created by ChuYingYan on 2018/5/8.
 */
public class GankUitl {


    /**
     *  all |福利 | 休息视频 |
     * @param datyType
     */
    public static int getDataType(String datyType) {
        if (TextUtils.equals(datyType, GANK_WELFARE)) {
            return ViewHolderUtil.ITEM_TYPE_GANK_WELFARE;
        } else if (TextUtils.equals(datyType, GANK_REST)) {
            return ViewHolderUtil.ITEM_TYPE_GANK_REST;
        } else {
            return ViewHolderUtil.ITEM_TYPE_GANK_ARTICLE;
        }
    }


}
