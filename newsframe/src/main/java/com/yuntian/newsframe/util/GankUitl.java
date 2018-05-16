package com.yuntian.newsframe.util;


import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.blankj.utilcode.util.SDCardUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;

import static com.yuntian.newsframe.storage.AppConstants.GANK_REST;
import static com.yuntian.newsframe.storage.AppConstants.GANK_WELFARE;

/**
 * description  .
 * Created by ChuYingYan on 2018/5/8.
 */
public class GankUitl {


    /**
     * all |福利 | 休息视频 |
     *
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


    /**
     * 计算图片要显示的高度
     *
     * @param pixel 原始分辨率
     * @param width 要显示的宽度
     * @return
     */
    public static int calcPhotoHeight(String pixel, int width) {
        int height = 100;
        try {
            int index = pixel.indexOf("*");
            if (index != -1) {

                int widthPixel = Integer.parseInt(pixel.substring(0, index));
                int heightPixel = Integer.parseInt(pixel.substring(index + 1));
                height = (int) (heightPixel * (width * 1.0f / widthPixel));
            }
        } catch (NumberFormatException e) {
        }
        return height;
    }


    /**
     * https://developer.qiniu.com/dora/manual/1279/basic-processing-images-imageview2
     *
     * @param url:http://img.gank.io/6ade6383-bc8e-40e4-9919-605901ad0ca5?imageView2/0/w/100/h/100
     * @param width
     * @param height
     */
    public static String getRequireImageUrl(String url, int width, int height) {
        if (!TextUtils.isEmpty(url) && url.contains("img.gank.io")) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(url).append("?imageView2/0")
                    .append("/w/").append(width).append("/h/").append(height);
            return stringBuilder.toString();
        }
        return url;
    }


    public static Bitmap createVideoThumbnail(String filePath, int kind) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            if (filePath.startsWith("http://")
                    || filePath.startsWith("https://")
                    || filePath.startsWith("widevine://")) {
                retriever.setDataSource(filePath, new Hashtable<String, String>());
            } else {
                retriever.setDataSource(filePath);
            }
            bitmap = retriever.getFrameAtTime(-1);
        } catch (IllegalArgumentException ex) {
            // Assume this is a corrupt video file
            ex.printStackTrace();
        } catch (RuntimeException ex) {
            // Assume this is a corrupt video file.
            ex.printStackTrace();
        } finally {
            try {
                retriever.release();
            } catch (RuntimeException ex) {
                // Ignore failures while cleaning up.
                ex.printStackTrace();
            }
        }

        if (bitmap == null) return null;

        if (kind == MediaStore.Images.Thumbnails.MINI_KIND) {
            // Scale down the bitmap if it's too large.
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int max = Math.max(width, height);
            if (max > 512) {
                float scale = 512f / max;
                int w = Math.round(scale * width);
                int h = Math.round(scale * height);
                bitmap = Bitmap.createScaledBitmap(bitmap, w, h, true);
            }
        } else if (kind == MediaStore.Images.Thumbnails.MICRO_KIND) {
            bitmap = ThumbnailUtils.extractThumbnail(bitmap, 96, 96,
                    ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        }
        return bitmap;
    }

    public static String saveBitmapFile(Bitmap bitmap, String name){
        File file=new File(SDCardUtils.getSDCardPathByEnvironment(),name+".jpeg");//将要保存图片的路径
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.getAbsolutePath();
    }

}
