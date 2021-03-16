package com.example.hd.myapplication;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Main12Activity extends AppCompatActivity {

    private zListivew lv;
    private List l;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉信息栏
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main12);



        l = new ArrayList();
        for (int i = 0; i < 50; i++) {
            l.add("a" + i);
        }

        lv = (zListivew) findViewById(R.id.mmp_listview);


        lv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return l.size();
            }

            @Override
            public Object getItem(int position) {
                return l.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHold vh = null;
                if (convertView == null) {
                    vh = new ViewHold();
                    convertView = View.inflate(Main12Activity.this, R.layout.jj, null);
                    vh.tv = (TextView) convertView.findViewById(R.id.jj_tv);
                    convertView.setTag(vh);
                } else {
                    vh = (ViewHold) convertView.getTag();
                }

                vh.tv.setText(l.get(position).toString());
                return convertView;
            }
        });

    }

    static class ViewHold {
        TextView tv;
    }

}
