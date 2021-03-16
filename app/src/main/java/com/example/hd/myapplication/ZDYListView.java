package com.example.hd.myapplication;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * Created by dubojian on 2017/9/21.
 */

public class ZDYListView extends ListView {


    private ImageView imageView;
    private int measuredHeight;
    private int intrinsicHeight;

    public ZDYListView(Context context) {
        super(context);
    }

    public ZDYListView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        View inflate = View.inflate(getContext(), R.layout.headview, null);
        imageView = (ImageView) inflate.findViewById(R.id.headview_imageview);
        addHeaderView(inflate);

        imageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                imageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //获取imageview的初始高度
                measuredHeight = imageView.getMeasuredHeight();
                //获取imageview中图片的高度
                intrinsicHeight = imageView.getDrawable().getIntrinsicHeight();


            }
        });
        //去掉下拉到头部后的蓝色线
        setOverScrollMode(OVER_SCROLL_NEVER);

    }

    public ZDYListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {

        if (isTouchEvent && deltaY < 0) {
            if (imageView.getHeight() <= intrinsicHeight) {
                int newHeight = (int) (imageView.getHeight() + Math.abs(deltaY / 3.0f));//这里除以3是为了达到视差的效果
                imageView.getLayoutParams().height = newHeight;
                //此方法必须调用,调用后会重新调用onMeasure和onLayout方法进行测量和定位
                imageView.requestLayout();
            }

        }

        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                int height = imageView.getHeight();
                ValueAnimator valueAnimator = ValueAnimator.ofInt(height, measuredHeight);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int value = (int) animation.getAnimatedValue();
                        imageView.getLayoutParams().height = value;
                        //此方法必须调用,调用后会重新调用onMeasure和onLayout方法进行测量和定位
                        imageView.requestLayout();
                    }
                });
                valueAnimator.setInterpolator(new OvershootInterpolator());
                valueAnimator.setDuration(500);
                valueAnimator.start();
                break;
            default:

                break;
        }

        return super.onTouchEvent(ev);
    }
}
