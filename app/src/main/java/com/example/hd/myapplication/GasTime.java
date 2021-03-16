package com.example.hd.myapplication;

import android.os.Handler;

import java.text.DecimalFormat;

/**
 * Created by dubojian on 2017/9/20.
 */

public class GasTime extends Thread {

    private GasDetectAdapter.ViewHold view;
    private String formatsecond;
    private String formatminute;
    private Handler hd = new Handler();


    public void setView(GasDetectAdapter.ViewHold view) {
        this.view = view;
    }


    @Override
    public void run() {
        super.run();

        while (Integer.parseInt((String) view.time_minute.getTag()) > 0 ||
                Integer.parseInt((String) view.time_second.getTag()) > 1) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            doTime(view, Integer.parseInt((String) view.time_minute.getTag()),
                    Integer.parseInt((String) view.time_second.getTag()));


        }

    }

    private void doTime(final GasDetectAdapter.ViewHold view, int minute, int second) {


        second--;
        if (second < 0) {
            minute--;
            second = 59;
        }

        formatsecond = new DecimalFormat("00").format(second);
        formatminute = new DecimalFormat("00").format(minute);
        hd.post(new Runnable() {
            @Override
            public void run() {
                view.time_minute.setText(formatminute);
                view.time_minute.setTag(formatminute);
                view.time_second.setText(formatsecond);
                view.time_second.setTag(formatsecond);


            }
        });


    }


}
