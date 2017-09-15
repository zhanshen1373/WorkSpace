package com.example.hd.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by dubojian on 2017/9/15.
 */

public class MyGridViewAdapter extends BaseAdapter {

    private ArrayList<HashMap<String, Object>> mixList;
    private Context mContext;

    public MyGridViewAdapter(Context context, ArrayList<HashMap<String, Object>> mixList) {
        this.mixList = mixList;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mixList.size();
    }

    @Override
    public Object getItem(int position) {
        return mixList.get(position);
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
            convertView = View.inflate(mContext, R.layout.mixview, null);
            vh.textView = (TextView) convertView.findViewById(R.id.mixview_textview);
            vh.imageView = (ImageView) convertView.findViewById(R.id.mixview_imageview);
            convertView.setTag(vh);
        } else {
            vh = (ViewHold) convertView.getTag();
        }

        vh.textView.setText(mixList.get(position).get("word").toString());
        vh.imageView.setImageResource((Integer) mixList.get(position).get("picture"));


        return convertView;
    }


    static class ViewHold {
        ImageView imageView;
        TextView textView;
    }

}
