package com.example.hd.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;

import java.io.File;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {



        File createFile = new File(Environment.getExternalStoragePublicDirectory("/zyxkapp/"),
                "/download/");

        deleteAllFilesOfDir(createFile);
        return super.onStartCommand(intent, flags, startId);
    }

    public static void deleteAllFilesOfDir(File path) {
        if (!path.exists())
            return;
        if (path.isFile()) {
            path.delete();
            return;
        }
        File[] files = path.listFiles();
        for (int i = 0; i < files.length; i++) {
            deleteAllFilesOfDir(files[i]);
        }
        path.delete();
    }

}
