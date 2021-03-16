package com.example.hd.myapplication;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class Main10Activity extends AppCompatActivity {

    private ArrayList<HashMap<String, Object>> mixList = new ArrayList<>();
    private GridView gridView;
    private Handler hd = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);

        gridView = (GridView) findViewById(R.id.main10_gridview);

        for (int i = 0; i < 6; i++) {
            HashMap<String, Object> hm = new HashMap<>();
            hm.put("picture", R.drawable.t);
            hm.put("word", "拖拽" + i);
            mixList.add(hm);
        }


        gridView.setAdapter(new BaseAdapter() {
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
                    convertView = View.inflate(Main10Activity.this, R.layout.mixview, null);
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
        });


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //属性动画第1个和第3个参数都是开始位置，第二个参数是负的就是向左，正是向右。第4个参数正是
//                向下...
                view.setVisibility(View.INVISIBLE);
                createAnimator(view, view.getLeft(), view.getRight(), 0, 0);
                view.setVisibility(View.VISIBLE);
                hd.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        View childAt = gridView.getChildAt(1);
                        childAt.setVisibility(View.INVISIBLE);
                    }
                }, 2000);

            }
        });

    }


    static class ViewHold {
        ImageView imageView;
        TextView textView;
    }


    private void createAnimator(View view, int startX, int endX, int startY, int endY) {
        ObjectAnimator translationX = ObjectAnimator.ofFloat(view, "translationX", startX, endX);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(view, "translationY", startY, endY);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(translationX, translationY);
        animatorSet.start();

    }
}
