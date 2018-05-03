package com.yuntian.dragger2databinding;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.yuntian.baselibs.net.core.ApiManager;
import com.yuntian.baselibs.net.entity.rep.NewsBean;
import com.yuntian.baselibs.net.result.CustomObserver;
import com.yuntian.baselibs.net.service.NewsService;
import com.yuntian.baselibs.net.result.RxHandleResult;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.yuntian.dragger2databinding", appContext.getPackageName());
    }

    @Test
    public void testApi() {
        NewsService newsService = ApiManager.getApi().create(NewsService.class);
        newsService.getNewsList("1")
                .compose(RxHandleResult.<List<NewsBean>>handleResult())
                .subscribe(new CustomObserver<List<NewsBean>>() {

                    @Override
                    protected void _onNext(List<NewsBean> newsBeans) {
                        System.out.println(newsBeans.toString());
                    }

                    @Override
                    protected void _onError(String message, int code) {
                        System.out.println("code:" + code + ",message" + message);
                    }
                });
    }
}
