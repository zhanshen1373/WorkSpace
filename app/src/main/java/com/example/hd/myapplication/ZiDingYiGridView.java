package com.example.hd.myapplication;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dubojian on 2017/9/12.
 */

public class ZiDingYiGridView extends GridView implements AdapterView.OnItemLongClickListener {


    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mLayoutParams;
    private View mStartDragView;
    private ImageView mImageView;
    private int mStatusHeight;
    private int mOffset2Left;//gv距屏幕左
    private int mOffset2Top;
    private float down_x;
    private float down_y;
    private float move_x;
    private float move_y;
    private int clickPosition;
    private int tempPosition;
    private Vibrator mVibrator;
    private boolean isDrag;
    private int centerPosition;

    public ZiDingYiGridView(Context context) {
        this(context, null);
    }

    public ZiDingYiGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZiDingYiGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mVibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mStatusHeight = getWindowStatusHeight(context);
//        mStatusHeight   72
        setOnItemLongClickListener(this);
    }

    private int getWindowStatusHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;

    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                down_x = ev.getX();
                down_y = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (isDrag && mStartDragView != null) {
                    move_x = ev.getX();
                    move_y = ev.getY();
                    //镜像跟随移动
                    onDragItem();
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                //移除镜像让点击项移动到指定位置并且显示
                onStopDrag();



//                //第几行
//                int targetRow = tempPosition / 3;
//                int startRow = centerPosition / 3;
//
//                //第几列
//                int targetColumn = tempPosition % 3;
//                int startColumn = centerPosition % 3;
//
//                View view = getChildAt(centerPosition);
//                if (startRow == targetRow) {
//                    if (targetColumn > startColumn) {
//                        //右
//                    endAnimator(view, 0, (targetColumn - startColumn) * view.getWidth(), 0, 0);
//
//                    } else if (targetColumn < startColumn) {
//                        //左
//                        createAnimator(view, 0, (targetColumn - startColumn) * view.getWidth(), 0, 0);
//                    }
//                } else if (startRow < targetRow) {
//                    if (targetColumn > startColumn) {
//                        //右下
//                        createAnimator(view, 0, (targetColumn - startColumn) * view.getWidth(), 0,
//                                (targetRow - startRow) * view.getHeight());
//                    } else if (targetColumn < startColumn) {
//                        //左下
//                        createAnimator(view, 0, (targetColumn - startColumn) * view.getWidth(), 0,
//                                (targetRow - startRow) * view.getHeight());
//                    } else {
//                        //下
//                        createAnimator(view, 0, 0, 0, (targetRow - startRow) * view.getHeight());
//                    }
//                } else if (startRow > targetRow) {
//                    if (targetColumn > startColumn) {
//                        //右上
//                        createAnimator(view, 0, (targetColumn - startColumn) * view.getWidth(), 0,
//                                (targetRow - startRow) * view.getHeight());
//                    } else if (targetColumn < startColumn) {
//                        //左上
//                        createAnimator(view, 0, (targetColumn - startColumn) * view.getWidth(), 0,
//                                (targetRow - startRow) * view.getHeight());
//                    } else {
//                        //上
//                        createAnimator(view, 0, 0, 0, (targetRow - startRow) * view.getHeight());
//                    }
//                }
                View view=getChildAt(centerPosition);
                view.setVisibility(VISIBLE);
//                MyGridViewAdapter adapter = (MyGridViewAdapter) getAdapter();
//                adapter.notifyDataSetChanged();
                break;
            default:

                break;
        }

        return super.onTouchEvent(ev);
    }

    private void onStopDrag() {
        if (isDrag) {
            mWindowManager.removeView(mImageView);
            mImageView = null;
            mStartDragView = null;
            isDrag = false;
        }

    }

    private void onDragItem() {
        mLayoutParams.x += move_x - down_x;
        mLayoutParams.y += move_y - down_y;
        mWindowManager.updateViewLayout(mImageView, mLayoutParams);
        down_x = move_x;
        down_y = move_y;
        //根据点击的位置和移动到的位置，添加动画
        onSwapItem();
    }

    private void onSwapItem() {
        List<Animator> list = new ArrayList<Animator>();
        View mainView=getChildAt(centerPosition);
//        Log.e("l",mainView.getX()+"..."+mainView.getY());
        tempPosition = pointToPosition((int) move_x, (int) move_y);
        if (tempPosition != AbsListView.INVALID_POSITION) {
            //点击的位置小于到达的位置
            if (clickPosition < tempPosition) {
                for (int i = clickPosition; i < tempPosition; i++) {
                    View view = getChildAt(i + 1);
                    //每一项自己的移动
                    if ((i + 1) % 3 != 0) {
                        endAnimator(mainView,(int) mainView.getX(),(int) move_x,(int) mainView.getY(),(int) move_y);
                        list.add(createAnimator(view, 0, -view.getWidth(), 0, 0));
                    } else {
                        list.add(createAnimator(view, 0, 2 * (view.getWidth()), 0, -view.getHeight()));
                    }
                }
                clickPosition = tempPosition;
            } else if (clickPosition > tempPosition) {
                for (int k = clickPosition; k > tempPosition; k--) {
                    View view = getChildAt(k - 1);
                    if ((k % 3 != 0)||(k-1)==0) {
                        list.add(createAnimator(view, 0, view.getWidth(), 0, 0));
                    } else {
                        list.add(createAnimator(view, 0, 2 * (-view.getWidth()), 0, view.getHeight()));

                    }
                }
                clickPosition=tempPosition;
            } else if (clickPosition == tempPosition) {

            }
            AnimatorSet set = new AnimatorSet();
            set.playTogether(list);
            set.setInterpolator(new AccelerateDecelerateInterpolator());
            set.setDuration(300);
            set.start();
        }
    }

    private AnimatorSet createAnimator(View view, int startX, int endX, int startY, int endY) {
        ObjectAnimator translationX = ObjectAnimator.ofFloat(view, "translationX", startX, endX);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(view, "translationY", startY, endY);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(translationX, translationY);
        return animatorSet;

    }

    private void endAnimator(View view,int startX,int endX, int startY, int endY){
        ObjectAnimator translationX = ObjectAnimator.ofFloat(view, "translationX", startX, endX);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(view, "translationY", startY, endY);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(translationX, translationY);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.setDuration(300);
        animatorSet.start();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        //创建镜像，把点击的item隐藏
        mVibrator.vibrate(50); //震动一下
        mStartDragView = view;
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        createDragImage(bitmap);
        view.destroyDrawingCache();
        view.setDrawingCacheEnabled(false);

        isDrag = true;
        mStartDragView.setVisibility(View.INVISIBLE);//隐藏该item
        clickPosition = position;
        centerPosition = clickPosition;

        return false;
    }

    private void createDragImage(Bitmap bitmap) {
        mLayoutParams = new WindowManager.LayoutParams();
        mLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        mLayoutParams.x = mStartDragView.getLeft();
        mLayoutParams.y = mStartDragView.getTop() - mStatusHeight;
        mLayoutParams.format = PixelFormat.TRANSLUCENT; //图形其它区透明
        mLayoutParams.alpha = 0.55f;
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
        mLayoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mImageView = new ImageView(getContext());
        mImageView.setImageBitmap(bitmap);
//        mImageView.setBackgroundColor(Color.RED);
        mWindowManager.addView(mImageView, mLayoutParams);
    }


}
