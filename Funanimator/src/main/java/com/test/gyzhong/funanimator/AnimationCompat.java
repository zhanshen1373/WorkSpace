package com.test.gyzhong.funanimator;

import android.content.Context;

import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;
import android.widget.Scroller;

import androidx.core.view.ViewCompat;

/**
 * Created by moon.zhong on 2015/4/12.
 */
public class AnimationCompat implements Runnable {
    private Scroller mScroller;

    private View mAnimationView;
    private int mViewHeight;

    private final int DURATION = 350;

    public AnimationCompat(Context context, View view) {
        mScroller = new Scroller(context, new LinearInterpolator());
        mAnimationView = view;
        mViewHeight = mAnimationView.getMeasuredHeight();
        if (mViewHeight == 0) {
            mAnimationView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    mAnimationView.getViewTreeObserver().removeOnPreDrawListener(this);
                    mViewHeight = mAnimationView.getMeasuredHeight();
                    return false;
                }
            });
        }
    }

    public void startHideAnimation(boolean scrollUp) {
        int dy = (int) ViewCompat.getTranslationY(mAnimationView) + mViewHeight;
        if (!scrollUp) {
            dy = (int) ViewCompat.getTranslationY(mAnimationView) - mViewHeight;
        }
        int duration = (int) Math.abs(DURATION * ViewCompat.getTranslationY(mAnimationView) * 1.0f / mViewHeight);
        duration = duration == 0 ? DURATION : duration;
        mScroller.startScroll(0, (int) ViewCompat.getTranslationY(mAnimationView), 0, -dy, duration);
        ViewCompat.postOnAnimation(mAnimationView, this);
    }

    public void startShawAnimation() {
        int dy = (int) ViewCompat.getTranslationY(mAnimationView);
        int duration = (int) Math.abs(DURATION);
        mScroller.startScroll(0, dy, 0, -dy, duration);
        ViewCompat.postOnAnimation(mAnimationView, this);
    }

    public void abortAnimation() {
        mScroller.abortAnimation();
    }

    @Override
    public void run() {
        if (mScroller.computeScrollOffset()) {

            ViewCompat.postOnAnimation(mAnimationView, this);
            ViewCompat.setTranslationY(mAnimationView, mScroller.getCurrY());

        }

    }
}
