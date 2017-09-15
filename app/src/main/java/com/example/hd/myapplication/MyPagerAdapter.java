package com.example.hd.myapplication;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by dubojian on 2017/6/30.
 */

public class MyPagerAdapter extends PagerAdapter {

    private ArrayList<View> pagerlist;

    public MyPagerAdapter(ArrayList<View> pagerlist) {
        this.pagerlist = pagerlist;
    }

    @Override
    public int getCount() {
        return pagerlist.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(pagerlist.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
           container.addView(pagerlist.get(position));
        return pagerlist.get(position);
    }
}
