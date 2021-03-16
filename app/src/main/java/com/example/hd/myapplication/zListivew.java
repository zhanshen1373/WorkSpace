package com.example.hd.myapplication;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.ListView;


/**
 * Created by dubojian on 2017/9/22.
 */

public class zListivew extends ListView implements AbsListView.OnScrollListener {


    private float down_y;
    private float move_y;
    private boolean flag;
    private int dely;
    private Rect normal = new Rect();
    private float middle_y;

    public zListivew(Context context) {
        super(context);

    }

    public zListivew(Context context, AttributeSet attrs) {
        super(context, attrs);

        setOnScrollListener(this);

    }

    public zListivew(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
//        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY+500, isTouchEvent);
        flag = true;
        return true;
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                down_y = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float v = middle_y == 0 ? down_y : move_y;
                move_y = ev.getY();
                dely = (int) (move_y - v);
                if (flag && dely > 0) {
                    if (normal.isEmpty()) {
                        // 保存正常的布局位置
                        normal.set(getLeft(), getTop(), getRight(), getBottom());
                    }
                    // 移动布局
                    layout(getLeft(), getTop() + dely, getRight(), getBottom() + dely);

                }
                if (flag && dely < 0) {
                    if (normal.isEmpty()) {
                        // 保存正常的布局位置
                        normal.set(getLeft(), getTop(), getRight(), getBottom());
                    }
                    // 移动布局
                    layout(getLeft(), getTop() + dely, getRight(), getBottom() + dely);
                }

                break;
            case MotionEvent.ACTION_UP:
                if (isNeedAnimation()) {
                    animation();
                }
                flag = false;
                middle_y = 0;
                break;
            default:

                break;
        }
        if (flag) {
            return true;
        } else {
            return super.onTouchEvent(ev);
        }
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }


    public void animation() {
        // 开启移动动画
        TranslateAnimation ta = new TranslateAnimation(0, 0, 0, normal.top - getTop());
        ta.setDuration(200);
        ta.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                clearAnimation();
                // 设置回到正常的布局位置
                layout(normal.left, normal.top, normal.right, normal.bottom);
                normal.setEmpty();
            }
        });
        startAnimation(ta);

    }

    //     是否需要开启动画
    public boolean isNeedAnimation() {
        return !normal.isEmpty();
    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

        if (scrollState == SCROLL_STATE_IDLE) {
            flag = false;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}
