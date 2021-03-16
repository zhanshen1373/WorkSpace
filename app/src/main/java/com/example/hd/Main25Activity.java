package com.example.hd;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hd.bottomnavigationview.Main27Activity;
import com.example.hd.myapplication.R;

import java.util.List;

public abstract class Main25Activity extends AppCompatActivity {

    private HandlerThread mHandlerThread;
    private Handler mHandler;
    private Thread a;
    private TextView mmm;
    private Handler hd;
    private Handler p = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main25);
        mmm = findViewById(R.id.mmm);
        init();
        a uu = new a();
        uu.main();


        mHandlerThread = new HandlerThread("MyHandlerThread");
        mHandlerThread.start();

        mHandler = new Handler(mHandlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                Log.e("ll", "收到消息 : " + Thread.currentThread().getId() + ":" + Thread.currentThread().getName());
            }
        };


        p.post(new Runnable() {
            @Override
            public void run() {
                Log.e("mvbn", Thread.currentThread() + ",,,,," + Thread.currentThread().getName());
            }
        });

    }


    protected void init() {
        Log.e("ss", Thread.currentThread().getId() + ":" + Thread.currentThread().getName());
    }

    public void q(View view) {
//        hd.sendEmptyMessage(0);
        Intent intent = new Intent(this, Main27Activity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("q", "onResume");

        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfoList = am.getRunningTasks(10);
        for (ActivityManager.RunningTaskInfo runningTaskInfo : runningTaskInfoList) {
            Log.e("id: ", runningTaskInfo.id + "");
            Log.e("description: ", runningTaskInfo.description + "");
            Log.e("number of activities: ", runningTaskInfo.numActivities + "");
            Log.e("topActivity: ", runningTaskInfo.topActivity + "");
            Log.e("baseActivity: ", runningTaskInfo.baseActivity.toString());
        }

        new Thread(new Runnable() {
            @Override
            public void run() {

                Looper.prepare();


                hd = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);

                        mmm.setText("22");

                    }
                };

                Log.e("qqqqq", Thread.currentThread() + ",,,,," + Thread.currentThread().getName());
                Toast.makeText(Main25Activity.this, "uuuu", Toast.LENGTH_LONG).show();
//                    Looper.myLooper().quit();

                Looper.loop();
//                Looper.myLooper().quitSafely();


                Log.e("ll", "发送消息 : " + Thread.currentThread().getId() + ":" + Thread.currentThread().getName());
                mHandler.sendEmptyMessage(0);

            }
        }).start();
    }

}

class a extends b {


    public void l() {

        Log.e("vbnnb", "cvb");
    }


}

abstract class b {

    private void l() {
        Log.e("xx", "vvv");
    }


    void main() {

        l();
        Log.e("nn", "mmm");
    }

}

