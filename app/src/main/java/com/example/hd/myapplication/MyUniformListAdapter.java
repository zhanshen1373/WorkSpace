package com.example.hd.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dubojian on 2017/9/18.
 */

public class MyUniformListAdapter extends BaseAdapter {


    private List<String> data;
    private Context mContext;
    private Time time;
    private List<String> d;
    private List<String> q;
    private List<String> idList = new ArrayList<>();
    private HashMap<Integer, String> hmMinute = new HashMap<>();
    private HashMap<Integer, String> hmSecond = new HashMap<>();
    private Handler hd = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle data = msg.getData();
            String minute = data.getString("minute");
            String second = data.getString("second");
            int id = data.getInt("id");
            hmMinute.put(id, minute);
            hmSecond.put(id, second);
            notifyDataSetChanged();


        }
    };


    public MyUniformListAdapter(Context mContext, List<String> pzlist,
                                List<String> t, List<String> o, List<String> id) {
        this.data = pzlist;
        this.mContext = mContext;
        this.d = t;
        this.q = o;
        for (int i = 0; i < id.size(); i++) {
            if (!(idList.contains(id.get(i)))) {
                idList.add(id.get(i));
                time = new Time();
                time.setTimeValue(d.get(i), q.get(i));
                time.setHandle(hd);
                time.setId(i);
                time.start();
            }
        }


    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
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
            convertView = View.inflate(mContext, R.layout.uniformview, null);
            vh.tv_status = (TextView) convertView.findViewById(R.id.tv_status);
            vh.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            vh.tv_distance = (TextView) convertView.findViewById(R.id.tv_distance);
            vh.time_minute = (TextView) convertView.findViewById(R.id.time_minute);
            vh.time_second = (TextView) convertView.findViewById(R.id.time_second);
            convertView.setTag(vh);
        } else {
            vh = (ViewHold) convertView.getTag();
        }

//        vh.tv_status.setText();
        vh.tv_content.setText(data.get(position));

        vh.time_minute.setText(hmMinute.get(position));
        vh.time_second.setText(hmSecond.get(position));


        return convertView;
    }

    static class ViewHold {
        TextView tv_status;
        TextView tv_content;
        TextView tv_distance;
        TextView time_minute;
        TextView time_second;
    }
}
