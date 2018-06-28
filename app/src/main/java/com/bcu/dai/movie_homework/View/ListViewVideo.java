package com.bcu.dai.movie_homework.View;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by dai on 2018/6/28.
 */

public class ListViewVideo extends ListView{
    public ListViewVideo(Context context) {
        super(context);
    }

    public ListViewVideo(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListViewVideo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
