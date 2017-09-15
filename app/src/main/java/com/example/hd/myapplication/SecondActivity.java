package com.example.hd.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    private ListView listview;
    private static ArrayList<String> arrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initData();

        listview= (ListView) findViewById(R.id.listview);
        listview.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return arrayList.size();
            }

            @Override
            public Object getItem(int position) {
                return arrayList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                ViewHold viewHold=null;
               if(convertView==null){
                   viewHold=new ViewHold();
                   convertView=View.inflate(SecondActivity.this,R.layout.zdyview,null);
                   viewHold.textView= (TextView) convertView.findViewById(R.id.textview);
                   convertView.setTag(viewHold);
               }else{
                   viewHold = (ViewHold) convertView.getTag();
               }
               if(arrayList.get(position).equals("w")){
                   arrayList.remove(position);
                   notifyDataSetChanged();
               }
               viewHold.textView.setText(arrayList.get(position));
                return convertView;
            }
        });
    }

    private void initData() {
        for (int i=0;i<3;i++){
            if(i==1){
                arrayList.add("w");
            }else{
                arrayList.add("hello"+i);
            }

        }

    }

    static class ViewHold{
        TextView textView;
    }
}
