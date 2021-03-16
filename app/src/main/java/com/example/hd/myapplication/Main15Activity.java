package com.example.hd.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;
//刷新两个listview的下拉刷新
public class Main15Activity extends AppCompatActivity {

    private SwipeRefreshLayout swiperefresh;
    private flingView swipelistview;
    private flingView swipelistview2;
    private List l;
    private List p;
    private Handler hd = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    boolean temp = (boolean) msg.obj;
                    if (temp) {
                        swiperefresh.setEnabled(true);
                    } else {
                        swiperefresh.setEnabled(false);

                    }
                    break;
                default:

                    break;
            }
            super.handleMessage(msg);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main15);

        l = new ArrayList();
        p = new ArrayList();

        for (int i = 0; i < 20; i++) {
            l.add("sdf" + i);
        }
        for (int w = 0; w < 30; w++) {
            p.add("jjj" + w);
        }


        swiperefresh = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        swipelistview = (flingView) findViewById(R.id.swipelistview);
        swipelistview2 = (flingView) findViewById(R.id.swipelistview2);


        swipelistview.sethd(hd);
        swipelistview2.sethd(hd);
        swipelistview.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                l));
        swipelistview2.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                p));


        swipelistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Main15Activity.this, position + "", Toast.LENGTH_LONG).show();
            }
        });


        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swiperefresh.setRefreshing(true);
                //请求数据
                Toast.makeText(Main15Activity.this, "hello", Toast.LENGTH_LONG).show();
                swiperefresh.setRefreshing(false);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hd.removeCallbacksAndMessages(null);
    }
}
