package com.example.hd.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by dubojian on 2017/9/15.
 */

public class MyExpandableAdapter extends BaseExpandableListAdapter {

    private HashMap<String,List<String>> hm;
    private Context mContet;
    private String title;
    private String content;



    public MyExpandableAdapter(Context context, String a,
                               String b) {
        this.mContet=context;
        this.title=a;
        this.content=b;
    }

    @Override
    public int getGroupCount() {
        return 1;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        TextView tv;
        if (convertView==null){
            convertView=View.inflate(mContet,R.layout.expandmian,null);
             tv= (TextView) convertView.findViewById(R.id.content);
            convertView.setTag(tv);
        }else{
            tv= (TextView) convertView.getTag();
        }
        tv.setText(title);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
          ViewHold viewHold=null;
        if (convertView==null){
            viewHold=new ViewHold();
            convertView=View.inflate(mContet,R.layout.expandchild,null);
            viewHold.title1= (TextView) convertView.findViewById(R.id.title1);
            convertView.setTag(viewHold);
        }else{
            viewHold= (ViewHold) convertView.getTag();
        }
        viewHold.title1.setText(content);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    static class ViewHold{
        TextView title1;
    }
}
