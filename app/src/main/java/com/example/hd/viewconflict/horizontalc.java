package com.example.hd.viewconflict;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

/**
 * Created by dubojian on 2019/1/8.
 */

public class horizontalc extends HorizontalScrollView {
    public horizontalc(Context context) {
        super(context);
    }

    public horizontalc(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public horizontalc(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public horizontalc(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean t = false;


        switch (ev.getAction()) {

            case MotionEvent.ACTION_DOWN:
                //如果在这一步拦截了，就需要自己处理
                Log.e("ed", "intercept---down");
                t=false;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("ed", "intercept---move");
                t=false;
                break;
            case MotionEvent.ACTION_UP:
                Log.e("ed", "intercept---up");
                t=false;
                break;
            default:
                break;
        }
        return t;
    }

    float nextx;
    float nexty;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        float rawX = ev.getRawX();
        float rawY = ev.getRawY();
        switch (ev.getAction()) {

            case MotionEvent.ACTION_DOWN:
                Log.e("ed", "touchevent---down");


                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("ed", "touchevent---move");
                float nn=rawX-nextx;
                Log.e("pp", nn+"?==");

                Log.e("pp", rawX+"??---"+nextx);

                break;
            case MotionEvent.ACTION_UP:
                Log.e("ed", "touchevent---up");

                break;

            default:

                break;
        }

        nextx=rawX;
        nexty=rawY;
        return true;
    }



}
