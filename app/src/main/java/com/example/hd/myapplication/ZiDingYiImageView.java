package com.example.hd.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by dubojian on 2017/9/11.
 */

public class ZiDingYiImageView extends android.support.v7.widget.AppCompatImageView {

    private Paint paint;
    private int process=1;

    public ZiDingYiImageView(Context context) {
        this(context,null);
    }

    public ZiDingYiImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ZiDingYiImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setTextSize(30);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(process+"/"+"5",getWidth()/2-20,getHeight()/2,paint);
    }

    public void setProcess(int process) {
        this.process = process;
    }
}
