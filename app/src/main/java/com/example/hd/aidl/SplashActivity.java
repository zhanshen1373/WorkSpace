package com.example.hd.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;

import com.example.hd.myapplication.R;

import java.util.List;

public class SplashActivity extends AppCompatActivity {

    private IMyAidlInterface iMyAidlInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Intent intent=new Intent(this,MyService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);


    }

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                Log.e("ww", "");
            } else {
                super.handleMessage(msg);
            }
        }
    };

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
            try {
                List<Food> foodList = iMyAidlInterface.getFoodList();
                if (foodList != null && foodList.size() > 0) {
                    for (int i = 0; i < foodList.size(); i++) {
                        Log.e("ww", foodList.get(i).toString() + "\n");
                    }
                }

            } catch (RemoteException e) {
                e.printStackTrace();
            }
            try {
                iMyAidlInterface.registerListener(iMyNoticelInterface);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private IMyNoticelInterface iMyNoticelInterface=new IMyNoticelInterface.Stub() {
        @Override
        public void onNewFoodArrived(Food food) throws RemoteException {
           handler.obtainMessage(1,food).sendToTarget();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (iMyAidlInterface!=null && iMyAidlInterface.asBinder().isBinderAlive()){
            try {
                iMyAidlInterface.unregisterListener(iMyNoticelInterface);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        unbindService(serviceConnection);
    }
}
