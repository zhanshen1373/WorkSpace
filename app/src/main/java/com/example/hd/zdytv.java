package com.example.hd;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.TextViewCompat;

public class zdytv extends AppCompatTextView {


    public zdytv(Context context) {
        super(context);
    }

    public zdytv(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public zdytv(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                Log.e("tag", "view-ontouchevent-down");
                break;

            case MotionEvent.ACTION_MOVE:
                Log.e("tag", "view-ontouchevent-move");

                break;
            case MotionEvent.ACTION_UP:
                Log.e("tag", "view-ontouchevent-up");

                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e("tag", "view-ontouchevent-cancel");

                break;
            default:
                break;

        }
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {


        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                Log.e("tag", "view-dispatchTouchEvent-down");
                break;

            case MotionEvent.ACTION_MOVE:
                Log.e("tag", "view-dispatchTouchEvent-move");

                break;
            case MotionEvent.ACTION_UP:
                Log.e("tag", "view-dispatchTouchEvent-up");

                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e("tag", "view-dispatchTouchEvent-cancel");

                break;
            default:
                break;

        }
        return super.dispatchTouchEvent(event);
    }



}
