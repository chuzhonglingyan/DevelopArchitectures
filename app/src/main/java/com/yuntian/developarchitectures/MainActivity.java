package com.yuntian.developarchitectures;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.blankj.utilcode.util.SDCardUtils;
import com.yuntian.baselibs.glide.ImageLoaderUtil;

import java.io.File;

public class MainActivity extends AppCompatActivity {


    ImageView iv;
    ImageView iv1;

    private Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                try {
                    Bitmap bitmap= BitmapFactory.decodeFile(SDCardUtils.getSDCardPathByEnvironment()+File.separator+"D046015255134077DDB3ACA0D7E68D45.jpeg");//将要保存图片的路径);
                    if (bitmap!=null){
                        iv.setImageBitmap(bitmap);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv=findViewById(R.id.iv);
        iv1=findViewById(R.id.iv1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap=GankUitl.createVideoThumbnail("http://112.253.22.157/17/z/z/y/u/zzyuasjwufnqerzvyxgkuigrkcatxr/hc.yinyuetai.com/D046015255134077DDB3ACA0D7E68D45.flv", MediaStore.Images.Thumbnails.MINI_KIND);
                GankUitl.saveBitmapFile(bitmap,"D046015255134077DDB3ACA0D7E68D45");
                handler.sendEmptyMessage(1);

            }
        }).start();

        String url="https://v.qq.com/x/page/m0377ib544o.html?start=1";
        ImageLoaderUtil.displayImage(url,iv1);
    }

}


