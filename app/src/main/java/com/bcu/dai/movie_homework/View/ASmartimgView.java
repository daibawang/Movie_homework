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
 * Created by dai on 2018/6/11.
 */
@SuppressLint("AppCompatCustomView")
public class ASmartimgView extends ImageView{
    private int id_;

    public ASmartimgView(Context context) {
        super(context);
    }
    public void setId_(int id){
        this.id_ = id;
    }
    public ASmartimgView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ASmartimgView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void  loadImageFromNet(String path) {
        OkHttpUtils.get().url(path).id(id_).build().execute(new BitmapCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }
            @Override
            public void onResponse(Bitmap response, int id) {
                if (id == id_){
                    setImageBitmap(response);
                }
            }
        });
    }

}
