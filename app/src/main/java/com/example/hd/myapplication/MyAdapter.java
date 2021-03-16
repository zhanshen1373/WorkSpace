package com.example.hd.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dubojian on 2018/2/27.
 */

public class MyAdapter extends BaseAdapter {

    private Context mcontext;
    private List<String> data;

    public MyAdapter(Context mcontext) {
        this.mcontext = mcontext;

    }

    public void setdata(List<String> data){
        this.data = data;
        notifyDataSetChanged();
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
            convertView = View.inflate(mcontext, R.layout.li, null);
            vh.tv = (TextView) convertView.findViewById(R.id.li_textview);
            convertView.setTag(vh);
        } else {
            vh = (ViewHold) convertView.getTag();
        }

        vh.tv.setText(data.get(position));

        return convertView;
    }

    static class ViewHold {
        private TextView tv;

    }
}
