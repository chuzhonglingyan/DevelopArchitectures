package com.yuntian.basecomponent.base;

import android.annotation.TargetApi;
import android.net.http.SslError;
import android.os.Build;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.blankj.utilcode.util.NetworkUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yuntian.basecomponent.listener.MyWebViewDownLoadListener;
import com.yuntian.baselibs.base.BaseActivity;

/**
 *
 * link @(https://juejin.im/post/59a56b2151882524424a1862)
 *       https://www.jianshu.com/p/a800e66c8949
 * description WebView基本用法 .
 * Created by ChuYingYan on 2018/5/19.
 */
public  abstract class BaseWebViewActivity extends BaseActivity implements OnRefreshListener{


    protected WebView mWebView;

    protected SmartRefreshLayout smartRefreshLayout;
    private String mErrorUrl="file:///android_asset/" + "404.html";

    private boolean isSuccess = false;
    private boolean isError = false;


    protected void init() {
        smartRefreshLayout = new SmartRefreshLayout(this);

        smartRefreshLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        smartRefreshLayout.setPrimaryColorsId(android.R.color.holo_blue_light, android.R.color.white);

        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(this));
        smartRefreshLayout.setEnableLoadMore(false);

        mWebView = new WebView(this);
        smartRefreshLayout.addView(mWebView);

        initWebViewSetting();
        smartRefreshLayout.setOnRefreshListener(this);
    }


    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initView() {
        setContentView(smartRefreshLayout);
    }


    private void initWebViewSetting() {
        mWebView.removeJavascriptInterface("searchBoxjavaBridge_");//解决 CVE-2014-1939 漏洞
        mWebView.removeJavascriptInterface("accessibility");//解决  CVE-2014-7224  漏洞
        mWebView.removeJavascriptInterface("accessibilityTraversal");//解决  CVE-2014-7224  漏洞
        settingWebView();
    }

    protected void settingWebView() {


        //支持获取手势焦点，输入用户名、密码或其他
        mWebView.requestFocusFromTouch();

        WebSettings settings = mWebView.getSettings();

        settings.setDefaultTextEncodingName("utf-8");//设置编码格式

        settings.setJavaScriptCanOpenWindowsAutomatically(true);//支持通过JS打开新窗口
        settings.setJavaScriptEnabled(true); //设置可以与js交互,是否支持Javascript，默认值false


        // 安全(security)
        settings.setSavePassword(false); //不使用明文密码
        settings.setAllowFileAccess(false);// 移动版的Chrome默认禁止加载file协议的文件。
        isAllowAccessFile(false);


        // UI适配(UI Adaptive)
        settings.setSupportZoom(false); //不设置缩放
        settings.setBuiltInZoomControls(false);//不设置缩放按钮
        settings.setUseWideViewPort(true);// 页面通过`<meta name="viewport" ... />`自适应手机屏幕
        settings.setLoadWithOverviewMode(true);// 当页面宽度大于WebView宽度时，缩小使页面宽度等于WebView宽度


        // 存储(storage)
        settings.setDomStorageEnabled(true);    // 默认值 false,开启 DOM storage API 功能
        settings.setDatabaseEnabled(true);      // 默认值 false,开启 database storage API 功能


        // 缓存(cache)
        settings.setAppCacheEnabled(true);      // 默认值 false
        settings.setAppCachePath(mContext.getCacheDir().getAbsolutePath());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 5.0以上允许加载http和https混合的页面(5.0以下默认允许，5.0+默认禁止)
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        if (NetworkUtils.isConnected()) {
            // 根据cache-control决定是否从网络上取数据
            settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        } else {
            // 没网，离线加载，优先加载缓存(即使已经过期)
            settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }

        //支持下载
        mWebView.setDownloadListener(new MyWebViewDownLoadListener(this));//支持下载

        initWebViewClient();
        initWebChromeClient();
    }



    /**
     * 是否允许js通过file://读取其他域名的资源(http/https),跨域请求基于同源策略
     * HTML5 允许 AJAX 跨域向其他域名发起请求，但是不能获取服务器端响应。
     *
     * @param allow
     */
    public void isAllowAccessFile(boolean allow) {
        WebSettings settings = mWebView.getSettings();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            //通过此API可以设置是否允许通过file url加载的Javascript读取其他的本地文件，
            // 这个设置在JELLY_BEAN以前的版本默认是允许，在JELLY_BEAN及以后的版本中默认是禁止的。
            settings.setAllowFileAccessFromFileURLs(allow);
            //由此允许通过 file url 加载的 Javascript 可以访问其他的源，包括其他的文件和 http/https 等源。
            // 这个设置在 JELLY_BEAN 以前的版本默认是允许，在 JELLY_BEAN 及以后的版本中默认是禁止的。
            settings.setAllowUniversalAccessFromFileURLs(allow);
        }
    }


    /**
     * js中执行natvie方法
     */
    private void addJSInterface() {
        // 注入对象'jsobj'，在网页中通过`jsobj.back(...)`调用
        mWebView.addJavascriptInterface(new TestObj(), "jsobj");
    }

    private class TestObj {

        @JavascriptInterface
        public void back() {

        }
    }


    /**
     * natvie执行JS表达式
     */
    private void executeJS() {
        //在API19后可异步执行JS表达式，并通过回调返回值
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mWebView.evaluateJavascript("111+222", new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String value) {
                    // value => "333"
                }
            });
        } else {
            // 弹出提示框
            mWebView.loadUrl("javascript:alert('hello')");
            // 调用注入的jsobj.say方法
            mWebView.loadUrl("javascript:jsobj.say('hello')");
        }
    }


    protected void initWebViewClient() {
        mWebView.setWebViewClient(new BaseWebViewClient());
    }



    public    class  BaseWebViewClient extends WebViewClient{

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


        //忽略https的证书问题,可以支持html的https请求,可能有安全问题
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }


        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            isError = true;
            isSuccess = false;
            // 断网或者网络连接超时
            if (errorCode == ERROR_HOST_LOOKUP || errorCode == ERROR_CONNECT || errorCode == ERROR_TIMEOUT) {
                //view.loadUrl("about:blank"); // 避免出现默认的错误界面
                //view.loadUrl(mErrorUrl);
            }
            if (isError){
                smartRefreshLayout.finishRefresh();
            }

        }

        @TargetApi(android.os.Build.VERSION_CODES.M)
        @Override
        public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
            super.onReceivedHttpError(view, request, errorResponse);
            isError = true;
            isSuccess = false;
            // 这个方法在6.0才出现
            int statusCode = errorResponse.getStatusCode();
            System.out.println("onReceivedHttpError code = " + statusCode);
            if (404 == statusCode || 500 == statusCode) {
               // view.loadUrl("about:blank");// 避免出现默认的错误界面
               // view.loadUrl(mErrorUrl);
            }
            if (isError){
                smartRefreshLayout.finishRefresh();
            }

        }

        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (!isError) {
                isSuccess = true;
                //回调成功后的相关操作
                smartRefreshLayout.finishRefresh();
            }
            isError = false;
        }

    }


    protected void initWebChromeClient() {
        mWebView.setWebChromeClient(new BaseWebChromeClient());
    }


    public  class  BaseWebChromeClient extends WebChromeClient{
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            isError = true;
            isSuccess = false;
            // android 6.0 以下通过title获取
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                if (title.contains("404") || title.contains("500") || title.contains("Error")) {
                    //view.loadUrl("about:blank");// 避免出现默认的错误界面
                   // view.loadUrl(mErrorUrl);
                }
            }

            if (isError){
                smartRefreshLayout.finishRefresh();
            }

        }
    }


    public void clearWebViewResource() {
        if (mWebView != null) {
            mWebView.removeAllViews();
            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.clearHistory();
            mWebView.destroy();
        }
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



    @Override
    protected void onDestroy() {
        clearWebViewResource();
        super.onDestroy();
    }


}
