package com.test.gyzhong.funanimator;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;


import android.os.Bundle;

import android.view.View;
import android.view.ViewTreeObserver;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private View mHoverView;
    private OriginalFragment mOriginalFragment;
    private AnimFragment mAnimFragment;
    private View mOriginalView;
    private boolean mIsMove = true;
    private View mBottomView;
    private AnimationCompat mAnimationCompat;
    private OnToggleClickImpl mOnToggleClick ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        mOnToggleClick = new OnToggleClickImpl() ;
        mHoverView = findViewById(R.id.id_hover_view);
        mOriginalFragment = (OriginalFragment) getSupportFragmentManager().findFragmentById(R.id.id_original_fragment);
        mOriginalFragment.setOnToggleClickListener(mOnToggleClick);
        mAnimFragment = (AnimFragment) getSupportFragmentManager().findFragmentById(R.id.id_fragment_instruct);
        mAnimFragment.setOnToggleClickListener(mOnToggleClick);
        mOriginalView = mOriginalFragment.getView();
        mBottomView = findViewById(R.id.id_bottom_view);
        mAnimationCompat = new AnimationCompat(this, mBottomView);
        mBottomView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                mBottomView.getViewTreeObserver().removeOnPreDrawListener(this);
                mBottomView.setTranslationY(mBottomView.getMeasuredHeight());
                return false;
            }
        });
    }


    private void setFragmentMove() {
        mHoverView.setClickable(true);
        /*设置背景透明度动画*/
        ObjectAnimator animator = ObjectAnimator.ofFloat(mHoverView, "alpha", 0, 0.5f);
        /*放小X 的动画*/
        ObjectAnimator scaleXAnim = ObjectAnimator.ofFloat(mOriginalView, "scaleX", 1.0f, .8f);
        /*放小Y 的 动画*/
        ObjectAnimator scaleYAnim = ObjectAnimator.ofFloat(mOriginalView, "scaleY", 1.0f, .8f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animator, scaleXAnim, scaleYAnim);
        animatorSet.setDuration(350);
        animatorSet.start();
        /*先让X 旋转20度的动画*/
        ObjectAnimator transX = ObjectAnimator.ofFloat(mOriginalView,"RotationX",20.0f);
        transX.setDuration(150);
        transX.start();
        /*等一定时间让X 旋转角度复位*/
        ObjectAnimator resumeX = ObjectAnimator.ofFloat(mOriginalView,"RotationX",0.0f);
        resumeX.setDuration(200);
        resumeX.setStartDelay(150);
        resumeX.start();
    }

    private void setFragmentBack() {
        mHoverView.setClickable(false);
        ObjectAnimator animator = ObjectAnimator.ofFloat(mHoverView, "alpha", 0.5f, 0);
        ObjectAnimator scaleXAnim = ObjectAnimator.ofFloat(mOriginalView, "scaleX", .8f, 1.0f);
        ObjectAnimator scaleYAnim = ObjectAnimator.ofFloat(mOriginalView, "scaleY", .8f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animator, scaleXAnim, scaleYAnim);
        animatorSet.setDuration(350);
        animatorSet.start();
        ObjectAnimator transX = ObjectAnimator.ofFloat(mOriginalView,"RotationX",20.0f);
        transX.setDuration(150);
        transX.start();
        ObjectAnimator resumeX = ObjectAnimator.ofFloat(mOriginalView,"RotationX",0.0f);
        resumeX.setDuration(200);
        resumeX.setStartDelay(150);
        resumeX.start();
    }

    @Override
    public void onBackPressed() {
        if (!mIsMove) {
            setFragmentBack();
            mAnimationCompat.startHideAnimation(false);
            mIsMove = !mIsMove;
        } else
            super.onBackPressed();
    }

    private class OnToggleClickImpl implements OriginalFragment.OnToggleClickListener{
        @Override
        public void onClick(View view) {
            if (mIsMove) {

                mAnimationCompat.startShawAnimation();

                setFragmentMove();
            } else {
                setFragmentBack();
                mAnimationCompat.startHideAnimation(false);
            }
            mIsMove = !mIsMove;
        }
    }

}
