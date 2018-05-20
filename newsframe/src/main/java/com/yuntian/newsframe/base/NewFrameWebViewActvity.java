package com.yuntian.newsframe.base;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.yuntian.basecomponent.base.BaseWebViewActivity;
import com.yuntian.basecomponent.util.ToolBarUtil;
import com.yuntian.newsframe.R;
import com.yuntian.newsframe.route.RoutePaths;

/**
 * description  .
 * Created by ChuYingYan on 2018/5/19.
 */
@Route(path = RoutePaths.WEB_PATH)
public class NewFrameWebViewActvity  extends BaseWebViewActivity{


    @Autowired
    public String url;

    @Autowired
    public String title;


    @Override
    protected void initView() {
        LinearLayout linearLayout=new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        Toolbar toolBar=new Toolbar(this);
        toolBar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,SizeUtils.dp2px(50)));
        toolBar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        toolBar.setTitleTextColor(Color.WHITE);
        toolBar.setMinimumHeight(SizeUtils.dp2px(50));
        toolBar.setNavigationIcon(R.mipmap.icon_back);

        toolBar.setTitleTextAppearance(this,R.style.Toolbar);
        ToolBarUtil.initToolBar(mActivity, toolBar, true, title);

        toolBar.setNavigationOnClickListener((v)-> {
                finish();//返回
        });

        linearLayout.addView(toolBar);
        linearLayout.addView(smartRefreshLayout);
        setContentView(linearLayout);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

       // mWebView.loadUrl(url);
        smartRefreshLayout.autoRefresh();
        // ARouter会自动对字段进行赋值，无需主动获取
        LogUtils.d("url:", url +",title:"+title);
    }


    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mWebView.loadUrl(url);
    }


    @Override
    protected void initWebChromeClient() {
        super.initWebChromeClient();


        mWebView.setWebViewClient(new BaseWebViewClient(){

            private String startUrl;
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                startUrl = url;
                LogUtils.d("startUrl:"+startUrl);
            }

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(url == null) return false;

                try {
                    if (url.startsWith("http:") || url.startsWith("https:"))
                    {
                        view.loadUrl(url);
                        return true;
                    }
                    else
                    {
//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                    startActivity(intent);
                        return true;
                    }
                } catch (Exception e) { //防止crash (如果手机上没有安装处理某个scheme开头的url的APP, 会导致crash)
                    return false;
                }
            }

        });
    }

}
