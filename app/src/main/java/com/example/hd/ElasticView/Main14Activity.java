package com.example.hd.ElasticView;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.hd.myapplication.R;

import java.util.List;

public class Main14Activity extends AppCompatActivity {


    private ElasticListView flexiListView;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main14);
        String data[] = {"1","2","3","4","5","6","7","8","9","10","11","12","13","12","13"};

        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);

        flexiListView= (ElasticListView) findViewById(R.id.flexilistview);
        flexiListView.setAdapter(arrayAdapter);


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
