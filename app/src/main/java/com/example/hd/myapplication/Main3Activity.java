package com.example.hd.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.edittext1)
    public EditText editText;
    @BindView(R.id.radio)
    public RadioGroup radioGroup;


    private BroadcastReceiver broadcastReceiver;
    private Button button;
    private Button button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ButterKnife.bind(this);
        editText.setText("1234567");


        button2 = (Button) findViewById(R.id.main3_button2);
        button = (Button) findViewById(R.id.main3_button);
        button.setOnClickListener(this);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //检测是否有同名文件的代码
                Date date = new Date(System
                        .currentTimeMillis());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                        "yyyy-MM-dd");
                String time = simpleDateFormat
                        .format(date);

                File createFile = new File(Environment.getExternalStoragePublicDirectory("/pp/"),
                        "/download/" + time + "/tta.jpg");


                if (!createFile.exists()) {

                    File parentFile = createFile.getParentFile();
                    parentFile.mkdirs();

                    if (parentFile.isDirectory()) {
                        File file = new File(parentFile, "tta.jpg");
                        if (!file.exists()) {
                            try {
                                file.createNewFile();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } else {
                    Toast.makeText(
                            Main3Activity.this,
                            "文件下载成功", Toast.LENGTH_LONG)
                            .show();
                    return;
                }


                //创建下载任务,downloadUrl就是下载链接
                String downurl = "http://img5.imgtn.bdimg.com/it/u=49366202,632101467&fm=27&gp=0.jpg";

                try {
                    // 构造URL
                    URL url = new URL(downurl);
                    // 打开连接
                    URLConnection con = url.openConnection();
                    //获得文件的长度
                    int contentLength = con.getContentLength();
                    System.out.println("长度 :" + contentLength);
                    // 输入流
                    InputStream is = con.getInputStream();

                    // 1K的数据缓冲
                    byte[] bs = new byte[1024];
                    // 读取到的数据长度
                    int len;
                    // 输出的文件流
                    OutputStream os = new FileOutputStream(createFile);
                    // 开始读取
                    while ((len = is.read(bs)) != -1) {
                        os.write(bs, 0, len);
                    }
                    // 完毕，关闭所有链接
                    os.close();
                    is.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int widthPixels = metrics.widthPixels;
        int heightPixels = metrics.heightPixels;

        Log.e("p", metrics + "");
        Log.e("w", widthPixels + "\t" + heightPixels);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == R.id.button1) {
                    Intent intent = new Intent(Main3Activity.this, Main4Activity.class);
                    startActivity(intent);
//                    radioGroup.check(R.id.button1);
//                    Toast.makeText(Main3Activity.this,editText.getText().toString(),Toast.LENGTH_LONG).show();
                } else if (checkedId == R.id.button2) {
                    radioGroup.check(R.id.button2);
                }
            }
        });

        /* 异常
        try {
           throw new Exception("hello");
        } catch (Exception e) {
            Log.e("hh",e.toString());
        }
        */

    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);

            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.main3_button) {
            Toast.makeText(Main3Activity.this, "点击了", Toast.LENGTH_LONG).show();
        }

    }
}
