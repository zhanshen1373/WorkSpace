package com.example.hd.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by dubojian on 2017/9/30.
 */

public class InnerListView extends ListView {

    public InnerListView(Context context) {
        super(context);
    }

    public InnerListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InnerListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //long
        //big
        //comfortable
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        Integer.MAX_VALUE >> 2

//        setMeasuredDimension();


        int height = MeasureSpec.makeMeasureSpec(200, MeasureSpec.AT_MOST);

        super.onMeasure(height, heightMeasureSpec);

    }
}
