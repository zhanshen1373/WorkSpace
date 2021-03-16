package com.example.hd.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.text.DecimalFormat;

/**
 * Created by dubojian on 2017/9/19.
 */

public class Time extends Thread {


    private String formatsecond;
    private String formatminute;
    private int minute;
    private int second;
    private Handler handle;
    private int id;


    @Override
    public void run() {
        super.run();

        while (minute > 0 ||
                second > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            doTime(minute,second);


        }

    }

    private void doTime(int minute, int second) {


        second--;
        if (second < 0) {
            minute--;
            second = 59;
        }
        this.second=second;
        this.minute=minute;

        formatsecond = new DecimalFormat("00").format(second);
        formatminute = new DecimalFormat("00").format(minute);


        Message message=Message.obtain();
        Bundle bundle=new Bundle();
        bundle.putString("minute",formatminute);
        bundle.putString("second",formatsecond);
        bundle.putInt("id",id);
        message.setData(bundle);
        handle.sendMessage(message);



    }


    public void setTimeValue(String d, String q) {
        this.second=Integer.parseInt(d);
        this.minute=Integer.parseInt(q);
    }

    public void setHandle(Handler handle) {
        this.handle = handle;
    }

    public void setId(int id) {
        this.id = id;
    }
}
