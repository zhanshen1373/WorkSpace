package com.example.hd.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Main13Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private Domain domain;
    private List<Domain.DefaultDomain.InnerDomain> innerDomains;
    private List<Domain.DefaultDomain.TitleDomain> titleDomains;
    private Domain.DefaultDomain.TitleDomain titleDomain;
    private Domain.DefaultDomain.InnerDomain innerDomain;
    private Domain.DefaultDomain defaultDomain;
    private Domain.LonelyDomain lonelyDomain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main13);

        domain=new Domain();
        defaultDomain=new Domain.DefaultDomain();
        lonelyDomain=new Domain.LonelyDomain();
        lonelyDomain.setTitle("消息通知");
        lonelyDomain.setContent("本次主要升级内容包括：1.首页更新....\n 2." +
                "增加消息提醒功能，可及时掌握最近消息");
        innerDomains=new ArrayList<>();
        titleDomains=new ArrayList<>();
        for (int j=0;j<3;j++){
            //自己造的数据有问题，有一条会用不上
            titleDomain=new Domain.DefaultDomain.TitleDomain();
            titleDomain.setTitle("标题"+j);
            titleDomains.add(titleDomain);
        }
        for (int k=0;k<6;k++){
            innerDomain=new Domain.DefaultDomain.InnerDomain();
            innerDomain.setmContent("主内容"+k);
            innerDomain.setTiemMinute(k+"");
            innerDomain.setTimeSecond(k+"");
            innerDomains.add(innerDomain);
        }
        defaultDomain.setInnerDomains(innerDomains);
        defaultDomain.setTitleDomains(titleDomains);
        domain.setDefaultDomain(defaultDomain);
        domain.setLonelyDomain(lonelyDomain);

        adapter=new RecyclerViewAdapter(this,domain);
        recyclerView= (RecyclerView) findViewById(R.id.main13_recyclerview);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL,false
        );
        /**
         * LinearLayoutManager
         * GridLayoutManager
         *StaggeredGridLayoutManager 瀑布流
         *
         */
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);


    }
}
