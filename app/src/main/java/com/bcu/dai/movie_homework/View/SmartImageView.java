package com.bcu.dai.movie_homework.View;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;

import okhttp3.Call;

/**
 * Created by byy on 2018/5/31.
 */

@SuppressLint("AppCompatCustomView")
public class SmartImageView extends ImageView {


    public SmartImageView(Context context) {
        super(context);
    }

    public SmartImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SmartImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void loadImageFromNet(String path){
        OkHttpUtils.get().url(path).build().execute(new BitmapCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                //放一个默认图片
            }
            @Override
            public void onResponse(Bitmap response, int id) {
                setImageBitmap(response);
            }
        });
    }
}
