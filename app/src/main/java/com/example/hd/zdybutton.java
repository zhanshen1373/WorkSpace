package com.example.hd;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;


public class zdybutton extends LinearLayout {


    public zdybutton(Context context) {
        super(context);
    }

    public zdybutton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public zdybutton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public zdybutton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                Log.e("tag", "viewgruop-dispatch-down");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("tag", "viewgruop-dispatch-move");

                break;
            case MotionEvent.ACTION_UP:
                Log.e("tag", "viewgruop-dispatch-up");

                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e("tag", "viewgruop-dispatch-cancel");

                break;
            default:
                break;

        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                Log.e("tag", "viewgruop-ontouchevent-down");
                break;

//                return true;
            case MotionEvent.ACTION_MOVE:
                Log.e("tag", "viewgruop-ontouchevent-move");

                break;
            case MotionEvent.ACTION_UP:
                Log.e("tag", "viewgruop-ontouchevent-up");

                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e("tag", "viewgruop-ontouchevent-cancel");

                break;
            default:
                break;

        }
        return false;
    }

    @Override
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        Log.e("tag","viewgruop-requestDisallowInterceptTouchEvent");
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {

            case MotionEvent.ACTION_DOWN:
                Log.e("tag", "viewgruop-onInterceptTouchEvent-down");
                break;

            case MotionEvent.ACTION_MOVE:
                Log.e("tag", "viewgruop-onInterceptTouchEvent-move");

//                return true;
                break;
            case MotionEvent.ACTION_UP:
                Log.e("tag", "viewgruop-onInterceptTouchEvent-up");

                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e("tag", "viewgruop-onInterceptTouchEvent-cancel");

                break;
            default:
                break;

        }
        return true;
    }
}
