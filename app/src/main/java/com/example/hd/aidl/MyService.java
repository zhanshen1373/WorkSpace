package com.example.hd.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;


import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class MyService extends Service {

    private CopyOnWriteArrayList<Food> list = new CopyOnWriteArrayList<>();
    private RemoteCallbackList<IMyNoticelInterface> iMyNoticelInterfaces = new RemoteCallbackList<>();
    private AtomicBoolean mIsServiceDestoryed = new AtomicBoolean(false);

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mbinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        list.add(new Food(2, "大米"));
        list.add(new Food(3, "面粉"));
        new Thread(new ServiceWorker()).start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mIsServiceDestoryed.set(true);
    }



    private class ServiceWorker implements Runnable {


        @Override
        public void run() {
            while (!mIsServiceDestoryed.get()) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Food f = new Food(list.size() + 1, "zdj1");
                try {
                    newAddFood(f);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void newAddFood(Food f) throws RemoteException {
        list.add(f);
        int w = iMyNoticelInterfaces.beginBroadcast();
        for (int i = 0; i < w; i++) {
            IMyNoticelInterface broadcastItem = iMyNoticelInterfaces.getBroadcastItem(i);
            if (broadcastItem!=null){
                broadcastItem.onNewFoodArrived(f);
            }
        }
        iMyNoticelInterfaces.finishBroadcast();
    }



    private Binder mbinder = new IMyAidlInterface.Stub() {
        @Override
        public List<Food> getFoodList() throws RemoteException {
            return list;
        }

        @Override
        public void addFood(Food food) throws RemoteException {
            list.add(food);
        }

        @Override
        public void registerListener(IMyNoticelInterface i) throws RemoteException {

            iMyNoticelInterfaces.register(i);
        }

        @Override
        public void unregisterListener(IMyNoticelInterface t) throws RemoteException {
            iMyNoticelInterfaces.unregister(t);

        }


    };


}
