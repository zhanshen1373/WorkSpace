package com.example.hd.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.util.Log;

import androidx.viewpager.widget.ViewPager;


/**
 * Created by dubojian on 2017/9/7.
 */

public class MyViewPager extends ViewPager {

    private int measuredWidth;
    private int measuredHeight;
    private float rotateDegree;
    private ZiDingYiImageView view;
    private int a = 1;
    private float down_x;
    private float move_x;
    private int currentPage = 0;
    Camera ca = new Camera();


    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measuredWidth = this.getMeasuredWidth();
        measuredHeight = this.getMeasuredHeight();

    }


    @Override
    protected void onPageScrolled(int position, float offset, int offsetPixels) {
        super.onPageScrolled(position, offset, offsetPixels);
        Log.e("sdf", "onpagescrolled" + position + "\t" + offset + "\t" + offsetPixels);
        ca.save();
        ca.rotateY(offsetPixels / 6);

        Matrix mt = new Matrix();
        ca.getMatrix(mt);
        ca.restore();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.t);
        mt.preTranslate(-bitmap.getWidth() / 2, -bitmap.getHeight() / 2);
        mt.postTranslate(bitmap.getWidth() / 2, bitmap.getHeight() / 2);
        Bitmap bm = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        Canvas cv = new Canvas(bm);

        cv.drawBitmap(bitmap, mt, null);

        view.setImageBitmap(bm);

    }


//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//        ca.save();
//        switch (ev.getAction()) {
//
//            case MotionEvent.ACTION_DOWN:
//                down_x = ev.getX();
//                Log.e("p", "donw");
//                break;
//            case MotionEvent.ACTION_MOVE:
//                move_x = ev.getX();
//                if (down_x != 0) {
//                    rotateDegree = (move_x - down_x) * (180) / measuredWidth;
//
//                }
//                ca.rotateY(rotateDegree);
//                Log.e("p", "move");
//
//                break;
//            case MotionEvent.ACTION_UP:
//                Log.e("x", "up");
//                if (rotateDegree < -90) {
//                    rotateDegree = -180;
//                    if (a == 3) {
//                        view.setProcess(3);
//                    } else {
//                        view.setProcess(++a);
//                    }
//                    if (currentPage == 2) {
//                        setCurrentItem(2);
//                    } else {
//                        setCurrentItem(++currentPage);
//                    }
//                } else if (rotateDegree > 90) {
//                    rotateDegree = 180;
//                    if (a == 1) {
//                        view.setProcess(1);
//                    } else {
//                        view.setProcess(--a);
//
//                    }
//                    if (currentPage == 0) {
//                        setCurrentItem(0);
//                    } else {
//                        setCurrentItem(--currentPage);
//                    }
//                } else {
//                    rotateDegree = 0;
//                }
//                ca.rotateY(rotateDegree);
//                break;
//            default:
//
//                break;
//        }
//
//
//        Matrix mt = new Matrix();
//        ca.getMatrix(mt);
//        ca.restore();
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.t);
//        mt.preTranslate(-bitmap.getWidth() / 2, -bitmap.getHeight() / 2);
//        mt.postTranslate(bitmap.getWidth() / 2, bitmap.getHeight() / 2);
//        Bitmap bm = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
//        Canvas cv = new Canvas(bm);
//
//        cv.drawBitmap(bitmap, mt, null);
//
//        view.setImageBitmap(bm);
//        return super.onTouchEvent(ev);
//    }

    public void setView(ZiDingYiImageView view) {
        this.view = view;
    }
}
