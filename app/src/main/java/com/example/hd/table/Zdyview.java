package com.example.hd.table;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hd.myapplication.R;
import com.example.hd.utils.Dimens;

public class Zdyview extends LinearLayout {

    private Context mContext;
    private Paint p;

    public Zdyview(Context context) {
        super(context);
    }


    public Zdyview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

        this.mContext = context;
    }

    public Zdyview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int width = getMeasuredWidth();


        //子view
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            int measuredHeight = childAt.getMeasuredHeight();
            getChildAt(i).layout(0, 0 + measuredHeight * i, width, measuredHeight * (i + 1));
        }



    }


    private void init() {
        setOrientation(VERTICAL);
        p = new Paint();
        p.setColor(Color.BLUE);
        p.setStyle(Paint.Style.STROKE);
        p.setAntiAlias(true);
    }

    public void initEvent() {

        for (int jj = 0; jj < getChildCount(); jj++) {
            ViewGroup childAt = (ViewGroup)getChildAt(jj);
            for (int ww = 0; ww < childAt.getChildCount(); ww++) {
                TextView childAt1 = (TextView) childAt.getChildAt(ww);
                if (ww > 0 && jj > 0) {
                    childAt1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(mContext,childAt1.getText().toString(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        //画框
//        for (int i = 0; i < getChildCount(); i++) {
//            ViewGroup childAt = (ViewGroup) getChildAt(i);
//            for (int t = 0; t < childAt.getChildCount(); t++) {
//                View childAt1 = childAt.getChildAt(t);
//                int measuredHeight = childAt1.getMeasuredHeight();
//                int measuredWidth = childAt1.getMeasuredWidth();
//                canvas.drawRect(new Rect(0 + t * measuredWidth, 0 + i * measuredHeight, (t + 1) * measuredWidth, (i + 1) * measuredHeight), p);
//            }
//        }


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

//        setMeasuredDimension(i,heightMeasureSpec);
//        getChildAt(0).measure(getChildAt(0).getWidth(),getChildAt(0).getHeight());
    }



}
