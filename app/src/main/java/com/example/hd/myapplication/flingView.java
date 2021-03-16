package com.example.hd.myapplication;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by dubojian on 2017/10/16.
 */

public class flingView extends ListView implements AbsListView.OnScrollListener {


    private Handler hd;
    private float down_y;
    private boolean flag;
    private boolean pag_s;
    private boolean pag_x;
    private float move_y;
    private boolean t_s;
    private boolean t_x;


    public flingView(Context context) {
        super(context);
    }

    public flingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnScrollListener(this);

    }

    public flingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {


        if (getFirstVisiblePosition() == 0) {
            if (down_y > 57 && down_y < 657) {
                flag = true;
                Message msg = Message.obtain();
                msg.obj = flag && pag_s;
                t_s = flag && pag_s;
                msg.what = 0;
                hd.sendMessage(msg);
            } else if (down_y > 714 && down_y < 1314) {
                flag = true;
                Message msg = Message.obtain();
                msg.obj = flag && pag_x;
                t_x = flag && pag_x;
                msg.what = 0;
                hd.sendMessage(msg);
            }

        } else {

            if (down_y > 57 && down_y < 657) {
                flag = false;
                Message msg = Message.obtain();
                msg.obj = flag && pag_s;
                t_s = flag && pag_s;
                msg.what = 0;
                hd.sendMessage(msg);
            } else if (down_y > 714 && down_y < 1314) {
                flag = false;
                Message msg = Message.obtain();
                msg.obj = flag && pag_x;
                t_x = flag && pag_x;
                msg.what = 0;
                hd.sendMessage(msg);
            }


        }

    }


    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {


    }

    public void sethd(Handler hd) {
        this.hd = hd;
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                down_y = ev.getRawY();
                //手指范围在控件内
                if (down_y > 57 && down_y < 657) {

                    if (t_s) {
                        Message msg = Message.obtain();
                        msg.obj = t_s;
                        msg.what = 0;
                        hd.sendMessage(msg);
                    } else {
                        Message msg = Message.obtain();
                        msg.obj = t_s;
                        msg.what = 0;
                        hd.sendMessage(msg);
                    }
                } else if (down_y > 714 && down_y < 1314) {


                    if (t_x) {
                        Message msg = Message.obtain();
                        msg.obj = t_x;
                        msg.what = 0;
                        hd.sendMessage(msg);
                    } else {
                        Message msg = Message.obtain();
                        msg.obj = t_x;
                        msg.what = 0;
                        hd.sendMessage(msg);
                    }
                }


                break;
            case MotionEvent.ACTION_MOVE:
                move_y = ev.getRawY();

                if (move_y != down_y) {
                    //向下滑动
                    if (move_y - down_y > 0) {
                        if (down_y > 57 && down_y < 657) {
                            pag_s = true;
                        } else if (down_y > 714 && down_y < 1314) {
                            pag_x = true;
                        }

                    } else {
                        //向上滑
                        if (down_y > 57 && down_y < 657) {
                            pag_s = false;
                        } else if (down_y > 714 && down_y < 1314) {
                            pag_x = false;
                        }
                    }
                }
                break;
            default:

                break;
        }
        return super.onTouchEvent(ev);

    }


}
