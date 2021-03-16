package com.example.hd.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dubojian on 2017/9/15.
 */

public class WorkerInitialPageActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;
    private ExpandableListView expandListView;
    private ListView pzNoticeListview;
    private ListView gasDetectNoticeListview;
    private TextView pzNoticeTextview;
    private TextView gasDetectNoticeTextview;
    private MyExpandableAdapter expandableAdapter;

    //ExpandableListView的数据
    private HashMap<String, List<String>> hm = new HashMap<>();
    private String s[] = {"first", "second"};
    private MyUniformListAdapter uniformListAdapter;
    private List<String> pzlist;
    private GasDetectAdapter gasDetectAdapter;
    private List<String> gdlist = new ArrayList<>();
    private List<String> uniformSecond = new ArrayList<>();
    private List<String> uniformMinute = new ArrayList<>();
    private List<String> gasMinute = new ArrayList<>();
    private List<String> gasSecond = new ArrayList<>();
    private List<String> idList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hd_hse_main_phone_app_layout_dalian_main_worker);
        expandListView= (ExpandableListView) findViewById(R.id.worker_expand);
        pzNoticeListview = (ListView) findViewById(R.id.worker_pz_notice_listview);
        gasDetectNoticeListview= (ListView) findViewById(R.id.worker_gasdetect_notice_listview);
        pzNoticeTextview = (TextView) findViewById(R.id.worker_pz_notice_textview);
        gasDetectNoticeTextview= (TextView) findViewById(R.id.worker_gasdetect_notice_textview);
        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.worker_swipeLayout);

        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(android.R.color.holo_blue_bright)
        ,getResources().getColor(android.R.color.holo_green_light),getResources().getColor(android.R.color.holo_red_light));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
              swipeRefreshLayout.setRefreshing(true);
                //请求数据

                swipeRefreshLayout.setRefreshing(false);
            }
        });
        ArrayList list = new ArrayList();

        list.add("first_1");
        list.add("first_2");

        hm.put(s[0], list);
//        hm.put(s[1],arrayList);
        pzlist = new ArrayList<>();
        pzlist.add("催化烟气脱硫1层区域3项作业");
        pzlist.add("30万吨气分东1层区域2项作业");
        pzlist.add("催化吸收地面区域5项作业");

        gdlist.add("催化热油泵房区域2项作业");

        uniformSecond.add("20");
        uniformSecond.add("8");
        uniformSecond.add("10");

        uniformMinute.add("8");
        uniformMinute.add("1");
        uniformMinute.add("2");

        idList.add("id1");
        idList.add("id2");
        idList.add("id3");

        gasMinute.add("2");
        gasSecond.add("8");


//        expandableAdapter=new MyExpandableAdapter(this,hm,s);
//        expandListView.setAdapter(expandableAdapter);
//        expandListView.expandGroup(0);
        uniformListAdapter = new MyUniformListAdapter(this, pzlist, uniformSecond, uniformMinute,idList);
        pzNoticeListview.setAdapter(uniformListAdapter);


        gasDetectAdapter=new GasDetectAdapter(this,gdlist,gasMinute,gasSecond);
        gasDetectNoticeListview.setAdapter(gasDetectAdapter);


        swipeRefreshLayout.setEnabled(false);
        expandListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                if (expandListView.isGroupExpanded(0)){
                    swipeRefreshLayout.setEnabled(true);
                }else{
                    swipeRefreshLayout.setEnabled(false);
                }

                return false;



            }
        });
    }
}
