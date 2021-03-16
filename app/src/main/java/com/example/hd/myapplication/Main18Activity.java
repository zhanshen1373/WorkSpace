package com.example.hd.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Main18Activity extends AppCompatActivity {

    private LinearLayout header;
    private ImageView headerImage;
    private TextView headerText;
    private TextView tl1;
    private ListView swipelistview;
    private TextView tl2;
    private ListView swipelistview2;
    private List l;
    private List p;
    private float down_y;
    private float move_y;
    private ZDYonRefreshListener zdYonRefreshListener;
    private int dy;
    private RotateAnimation upRotateAnimation;
    private float temy;
    private boolean ss = true;
    private final int PULL_REFRESH = 0;//下拉刷新的状态
    private final int RELEASE_REFRESH = 1;//松开刷新的状态
    private final int REFRESHING = 2;//正在刷新的状态
    private int currentState = PULL_REFRESH;//默认状态是下拉刷新
    private RotateAnimation downRotateAnimation;


    public void setZdYonRefreshListener(ZDYonRefreshListener zdYonRefreshListener) {
        this.zdYonRefreshListener = zdYonRefreshListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main18);

        l = new ArrayList();
        p = new ArrayList();

        for (int i = 0; i < 20; i++) {
            l.add("sdf" + i);
        }
        for (int w = 0; w < 30; w++) {
            p.add("jjj" + w);
        }

        header = (LinearLayout) findViewById(R.id.header);
        headerImage = (ImageView) findViewById(R.id.header_image);
        headerText = (TextView) findViewById(R.id.header_text);
        tl1 = (TextView) findViewById(R.id.tl1);
        swipelistview = (ListView) findViewById(R.id.swipelistview);
        tl2 = (TextView) findViewById(R.id.tl2);
        swipelistview2 = (ListView) findViewById(R.id.swipelistview2);

        header.measure(0, 0);
        header.setPadding(0, -header.getMeasuredHeight(), 0, 0);

        swipelistview.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                l));
        swipelistview2.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                p));


        swipelistview.setOnTouchListener(new View.OnTouchListener() {


            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        down_y = event.getRawY();

                        break;
                    case MotionEvent.ACTION_MOVE:
                        move_y = event.getRawY();
                        if (currentState == REFRESHING) {
                            //处在正在刷新状态，就不做处理
                            break;
                        }
                        dy = (int) (move_y - down_y);
                        if (dy > 0 && swipelistview.getFirstVisiblePosition() == 0) {
                            //向下滑动，并且是第一项

                            header.setPadding(0, (int) (dy - header.getMeasuredHeight()), 0, 0);
                            Log.e("p","ttt");
                            if (dy - header.getMeasuredHeight() < 0 &&
                                    currentState == PULL_REFRESH) {
                                //未完全显示
//                                headerText.setText("下拉刷新");
//                                headerImage.startAnimation(rotateAnimation);
                            } else if (dy - header.getMeasuredHeight() >= 0
                                    && currentState == PULL_REFRESH) {
                                currentState = RELEASE_REFRESH;
                                headerText.setText("松开刷新");
//                                headerImage.startAnimation(upRotateAnimation);

                            }
                           return true;
                        }


                        break;
                    case MotionEvent.ACTION_UP:
                        if (dy - header.getMeasuredHeight() >= 0 &&
                                currentState == RELEASE_REFRESH) {
                            //完全显示
                            headerText.setText("正在刷新");
//                            headerImage.setImageResource(R.drawable.nochoice);
                            setZdYonRefreshListener(new ZDYonRefreshListener() {
                                @Override
                                public void onRefresh() {
                                    new AsyncTask<Void, Void, Void>() {

                                        @Override
                                        protected void onPreExecute() {
                                            super.onPreExecute();

                                        }

                                        @Override
                                        protected Void doInBackground(Void... params) {
                                            //请求数据
                                            l.clear();
                                            l.add("1");

                                            return null;
                                        }

                                        @Override
                                        protected void onPostExecute(Void aVoid) {
                                            super.onPostExecute(aVoid);
                                            ArrayAdapter adapter = (ArrayAdapter) swipelistview.getAdapter();
                                            adapter.notifyDataSetChanged();
//                                            headerImage.clearAnimation();
                                            header.setPadding(0, -header.getMeasuredHeight(), 0, 0);
                                        }
                                    }.execute();
                                }

                                @Override
                                public void onLoadMore() {

                                }
                            });
                        } else if (dy - header.getMeasuredHeight() < 0 &&
                                currentState == PULL_REFRESH) {


                        }
                        break;
                    default:

                        break;
                }
                return false;

            }

        });

    }

    public void animation() {

        upRotateAnimation = new RotateAnimation(0, 180,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        upRotateAnimation.setDuration(1000);
        upRotateAnimation.setFillAfter(true);

        downRotateAnimation = new RotateAnimation(0, 180,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        downRotateAnimation.setDuration(1000);
        downRotateAnimation.setFillAfter(true);


    }

}
