package com.example.hd.myapplication;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ArrayList<View> pagerlist;
    TabLayout.Tab tabAt;
    TabLayout.Tab tabAt1;
    TabLayout.Tab tabAt2;
    private String[] arraylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager= (ViewPager) findViewById(R.id.viewpager);
        tabLayout= (TabLayout) findViewById(R.id.tablayout);

        arraylist=new String[]{"a","b"
        };
        pagerlist=new ArrayList<>();
        View inflate = View.inflate(this, R.layout.firstview, null);
        ListView firstview_listview= (ListView) inflate.findViewById(R.id.firstview_listview);
        firstview_listview.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arraylist));
        View inflate1 = View.inflate(this, R.layout.secondview, null);
        View inflate2 = View.inflate(this, R.layout.thirdview, null);
        pagerlist.add(inflate);
        pagerlist.add(inflate1);
        pagerlist.add(inflate2);


        viewPager.setAdapter(new MyPagerAdapter(pagerlist));
        tabLayout.setupWithViewPager(viewPager);
         tabAt = tabLayout.getTabAt(0);
         tabAt1 = tabLayout.getTabAt(1);
         tabAt2 = tabLayout.getTabAt(2);

        tabAt.setIcon(getResources().getDrawable(R.drawable.nochoice));
        tabAt1.setIcon(getResources().getDrawable(R.drawable.nochoice));
        tabAt2.setIcon(getResources().getDrawable(R.drawable.nochoice));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab==tabAt){
                    tabAt.setIcon(getResources().getDrawable(R.drawable.work));
                    viewPager.setCurrentItem(0);
                }else if (tab==tabAt1){
                    tabAt1.setIcon(getResources().getDrawable(R.drawable.work));
                    viewPager.setCurrentItem(1);
                }else if(tab==tabAt2){
                    tabAt2.setIcon(getResources().getDrawable(R.drawable.work));
                    viewPager.setCurrentItem(2);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if(tab==tabAt){
                    tabAt.setIcon(getResources().getDrawable(R.drawable.nochoice));
                }else if (tab==tabAt1){
                    tabAt1.setIcon(getResources().getDrawable(R.drawable.nochoice));
                }else if(tab==tabAt2){
                    tabAt2.setIcon(getResources().getDrawable(R.drawable.nochoice));
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}
