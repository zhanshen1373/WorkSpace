package com.example.hd.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dubojian on 2017/9/18.
 */

public class GasDetectAdapter extends BaseAdapter {

    private List<String> gslist;
    private Context mContext;
    private GasTime time;
    private List<String> gasMinute;
    private List<String> gasSecond;


    public GasDetectAdapter(Context mContext, List<String> list,
                            List<String> d, List<String> q) {
        this.gslist = list;
        this.mContext = mContext;
        this.gasMinute = d;
        this.gasSecond = q;
    }

    @Override
    public int getCount() {
        return gslist.size();
    }

    @Override
    public Object getItem(int position) {
        return gslist.get(position);
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
            convertView = View.inflate(mContext, R.layout.gasview, null);
            vh.tv_status = (TextView) convertView.findViewById(R.id.tv_gasstatus);
            vh.tv_content = (TextView) convertView.findViewById(R.id.tv_gascontent);
            vh.tv_distance = (TextView) convertView.findViewById(R.id.tv_gasdistance);
            vh.time_minute = (TextView) convertView.findViewById(R.id.time_gasminute);
            vh.time_second = (TextView) convertView.findViewById(R.id.time_gassecond);
            convertView.setTag(vh);
        } else {
            vh = (ViewHold) convertView.getTag();
        }

//        vh.tv_status.setText();
        vh.tv_content.setText(gslist.get(position));


        vh.time_minute.setTag(gasMinute.get(position));
        vh.time_second.setTag(gasSecond.get(position));


        time = new GasTime();
        time.setView(vh);


        time.start();

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
