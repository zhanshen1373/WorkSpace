package com.example.hd.thread;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.example.hd.myapplication.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 结论是从一个有主线程和子线程的类跳到另一个类，第一个类中的子线程不会停止
 */
public class ThreadActivity extends AppCompatActivity {

    private File file;
    private String num[] = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i",
            "j", "k", "l", "m", "n"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        if (hasStorage()) {
            File directory = new File(Environment.getExternalStorageDirectory() +
                    File.separator + "workspace");
            file = new File(directory, "a.txt");

            if (!directory.exists()) {
                directory.mkdirs();
            }
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    PrintWriter pw = new PrintWriter(fos);

                    for (int i = 0; i < num.length; i++) {
                        pw.write(num[i]);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    pw.flush();
                    if (pw != null) {
                        pw.close();
                    }
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public boolean hasStorage() {
        String state = android.os.Environment.getExternalStorageState();
        if (android.os.Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public void threadclick(View view) {
        Intent intent = new Intent(ThreadActivity.this, AcceptActivity.class);
        startActivity(intent);
    }
}
