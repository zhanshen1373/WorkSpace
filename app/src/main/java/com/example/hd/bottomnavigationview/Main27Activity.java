package com.example.hd.bottomnavigationview;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hd.ElasticView.Main14Activity;
import com.example.hd.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.reflect.Field;
import java.util.List;

public class Main27Activity extends AppCompatActivity {

    private BottomNavigationView bottomnavigationview;
    private int t=99;


 private   class a {
   //成员内部类
    int m=t;

}

private static class b {
    //静态内部类
    int p=5;

}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main27);

        int m = new a().m;
        int p = new b().p;
        Log.e("we",m+"/"+p);
        bottomnavigationview = (BottomNavigationView) findViewById(R.id.bottomnavigationview);


//        disableShiftMode(bottomnavigationview);

 class c{
     //局部内部类
  int ww=t;

}

Log.e("xcv",new c().ww+"");
    }

    public void o(View view){
        Intent intent=new Intent(this, Main14Activity.class);
        startActivity(intent);


    }

    public void disableShiftMode(BottomNavigationView navigationView) {

        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigationView.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);

            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView itemView = (BottomNavigationItemView) menuView.getChildAt(i);
                itemView.setShifting(false);
//                itemView.setShiftingMode(false);
                itemView.setChecked(itemView.getItemData().isChecked());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("taskid",getTaskId()+"");
//        ActivityManager am = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
//        List<ActivityManager.RunningTaskInfo> runningTaskInfoList = am.getRunningTasks(10);
//        for (ActivityManager.RunningTaskInfo runningTaskInfo : runningTaskInfoList) {
//            Log.e("id: ",runningTaskInfo.id+"");
//            Log.e("description: ",runningTaskInfo.description+"");
//            Log.e("number of activities: " ,runningTaskInfo.numActivities+"");
//            Log.e("topActivity: " ,runningTaskInfo.topActivity+"");
//            Log.e("baseActivity: ", runningTaskInfo.baseActivity.toString());
//        }
    }
}
