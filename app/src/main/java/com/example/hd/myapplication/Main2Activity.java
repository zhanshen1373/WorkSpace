package com.example.hd.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main2Activity extends Activity {

    @OnClick(R.id.he)
    public void trans(){
        Intent intent=new Intent(this,Main4Activity.class);
        startActivity(intent);
    }

    @OnClick(R.id.ha)
    public void tranp(){
        finish();
    }

    private ListView list;
    private ArrayList listdata=new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ButterKnife.bind(this);
//        getWindow().setEnterTransition(new Slide().setDuration(2000));
//        getWindow().setExitTransition(new Slide().setDuration(2000));

        list= (ListView) findViewById(R.id.list);
        for (int i=0;i<3;i++){
            listdata.add(i+"");
        }
        list.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return listdata.size();
            }

            @Override
            public Object getItem(int position) {
                return listdata.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                ViewHold vh=null;
                if(convertView==null){
                    vh=new ViewHold();
                    convertView=View.inflate(Main2Activity.this
                            ,R.layout.xx,null);
                    vh.textView= (TextView) convertView.findViewById(R.id.xx_text);
                    convertView.setTag(vh);
                }else{
                    vh= (ViewHold) convertView.getTag();
                }
                    vh.textView.setText((CharSequence) listdata.get(position));
                return convertView;
            }
        });
    }

    static class ViewHold{
        TextView textView;

    }

}
