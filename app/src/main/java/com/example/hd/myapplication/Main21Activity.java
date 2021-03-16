package com.example.hd.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Main21Activity extends AppCompatActivity {

    private ListView listView;
    private List<String> changelist;
    private List<String> strings;
    private MyAdapter myAdapter;
    private LinearLayout linearLayout;
    private int w;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main21);

        linearLayout = (LinearLayout) findViewById(R.id.main21_linear);

        changelist = new ArrayList<>();
        for (int i = 0; i < 31; i++) {
            changelist.add("wa" + i);
        }

        strings = changelist.subList(0, 6);


        myAdapter = new MyAdapter(this);
        myAdapter.setdata(strings);
        listView = (ListView) findViewById(R.id.main21_listview);

        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                changelist.remove(w * 6 + position);
                List<String> dxdata;
                if ((w + 1) * 6 > changelist.size()) {
                    dxdata = changelist.subList(w * 6, changelist.size());
                } else {
                    dxdata = changelist.subList((w * 6 + position) / 6 * 6, (w * 6 + position) / 6 * 6 + 6);
                }
                myAdapter.setdata(dxdata);

                linearLayout.removeAllViews();
                fenye(changelist.size());
            }
        });


        fenye(changelist.size());
    }

    private void fenye(final int num) {

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

//        layoutParams.setMargins(100, 0, 20, 0);

        if (num != 0) {

            for (int i = 0; i < ((num - 1) / 6) + 1; i++) {
                TextView tv = new TextView(this);
                tv.setText(i + 1 + "");
                tv.setTextSize(20);
                tv.setPadding(30, 10, 30, 10);
//                tv.setBackgroundColor(null);
                linearLayout.addView(tv, layoutParams);


                final int finalI = i;
                linearLayout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int p = 0; p < ((num - 1) / 6) + 1; p++) {
                            linearLayout.getChildAt(p).setBackground(null);
                        }
                        linearLayout.getChildAt(finalI).setBackgroundColor(Color.parseColor("#bbbbbb"));

                        w = finalI;
                        if ((finalI + 1) * 6 > num) {
                            strings = changelist.subList(finalI * 6, num);
                            myAdapter.setdata(strings);
                        } else {
                            strings = changelist.subList(finalI * 6, finalI * 6 + 6);
                            myAdapter.setdata(strings);
                        }
                    }
                });
            }
        }


    }


}
