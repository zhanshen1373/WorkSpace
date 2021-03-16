package com.example.hd.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dubojian on 2017/10/9.
 */

public class LinShiAdapter extends BaseAdapter {

    private List<String> listdata;
    private Context mContext;

    public LinShiAdapter(Context p, List<String> l) {
        this.listdata = l;
        this.mContext = p;
    }

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

        ViewHold vh = null;
        if (convertView == null) {
            vh = new ViewHold();
            convertView = View.inflate(mContext
                    , R.layout.xx, null);
            vh.textView = (TextView) convertView.findViewById(R.id.xx_text);
            convertView.setTag(vh);
        } else {
            vh = (ViewHold) convertView.getTag();
        }
        vh.textView.setText((CharSequence) listdata.get(position));
        return convertView;
    }

     static class ViewHold {
        TextView textView;

    }
}





