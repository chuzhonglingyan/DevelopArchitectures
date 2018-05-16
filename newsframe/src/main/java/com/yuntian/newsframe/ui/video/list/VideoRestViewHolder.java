package com.yuntian.newsframe.ui.video.list;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.yuntian.basecomponent.base.BaseBindingViewHolder;
import com.yuntian.baselibs.glide.ImageLoaderUtil;
import com.yuntian.newsframe.databinding.ItemVideoRestListBinding;
import com.yuntian.newsframe.ui.video.bean.VideoInfo;

/**
 * description  ItemNewsListBinding.
 * Created by ChuYingYan on 2018/5/4.
 */
public class VideoRestViewHolder extends BaseBindingViewHolder<ItemVideoRestListBinding, VideoInfo> {

    public final static String TAG = "VideoRestViewHolder";
    private GSYVideoOptionBuilder gsyVideoOptionBuilder;
    ImageView imageView;

    public VideoRestViewHolder(View itemView) {
        super(itemView);
        imageView = new ImageView(context);
        gsyVideoOptionBuilder = new GSYVideoOptionBuilder();
        gsyVideoOptionBuilder
                .setIsTouchWiget(false)
                .setSetUpLazy(true)//lazy可以防止滑动卡顿
                .setCacheWithPlay(true)
                .setRotateViewAuto(true)
                .setLockLand(true)
                .setPlayTag(TAG)
                .setShowFullAnimation(true)
                .setNeedLockFull(true)
                .setVideoAllCallBack(new GSYSampleCallBack() {
                    @Override
                    public void onPrepared(String url, Object... objects) {
                        super.onPrepared(url, objects);
                        if (!mBinding.videoItemPlayer.isIfCurrentIsFullscreen()) {
                            //静音
                            GSYVideoManager.instance().setNeedMute(true);
                        }
                    }

                    @Override
                    public void onQuitFullscreen(String url, Object... objects) {
                        super.onQuitFullscreen(url, objects);
                        //全屏不静音
                        GSYVideoManager.instance().setNeedMute(true);
                    }

                    @Override
                    public void onEnterFullscreen(String url, Object... objects) {
                        super.onEnterFullscreen(url, objects);
                        GSYVideoManager.instance().setNeedMute(false);
                        mBinding.videoItemPlayer.getCurrentPlayer().getTitleTextView().setText((String) objects[0]);
                    }
                });
    }

    @Override
    public void onBindViewData(VideoInfo info, int pos) {
        mBinding.setVideoInfoItem(info);


        //增加封面
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        ImageLoaderUtil.displayImage(info.getCover(),imageView);
        if (imageView.getParent() != null) {
            ViewGroup viewGroup = (ViewGroup) imageView.getParent();
            viewGroup.removeView(imageView);
        }

        //防止错位，离开释放
        //gsyVideoPlayer.initUIState();
        gsyVideoOptionBuilder
                .setThumbImageView(imageView)
                .setUrl(info.getMp4_url())
                .setVideoTitle(info.getTitle())
                .setPlayPosition(pos).build(mBinding.videoItemPlayer);

        //增加title
        mBinding.videoItemPlayer.getTitleTextView().setVisibility(View.GONE);
        //设置返回键
        mBinding.videoItemPlayer.getBackButton().setVisibility(View.GONE);
        //设置全屏按键功能
        mBinding.videoItemPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resolveFullBtn(mBinding.videoItemPlayer);
            }
        });
    }

    /**
     * 全屏幕按键处理
     */
    private void resolveFullBtn(final StandardGSYVideoPlayer standardGSYVideoPlayer) {
        standardGSYVideoPlayer.startWindowFullscreen(context, true, true);
    }


}
