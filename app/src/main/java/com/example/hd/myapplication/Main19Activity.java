package com.example.hd.myapplication;

import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.example.hd.myapplication.R.drawable.circle;


public class Main19Activity extends AppCompatActivity {


    private ListView zzz;
    private List<String> l;
    private ImageView headerImage;
    private TextView headerText;
    private final int PULL_REFRESH = 0;//下拉刷新状态
    private final int RELEASE_REFRESH = 1;//松手刷新状态
    private final int REFRESHING = 2;//正在刷新状态
    private int currentState = PULL_REFRESH;//默认状态为下拉刷新
    private float down_y;
    private float move_y;
    private int dy;
    private int paddingTop;
    private RotateAnimation upRotateAnimation;
    private RotateAnimation downRotateAnimation;
    private ZDYonRefreshListener zdYonRefreshListener;
    private View header;
    private int measuredHeight;
    private Adapter adapter;
    private RotateAnimation circleRotateAnimation;
    private boolean isLoadingMore;
    private View footer;
    private TextView footerText;
    private int footerMeasuredHeight;


    public void setZdYonRefreshListener(ZDYonRefreshListener zdYonRefreshListener) {
        this.zdYonRefreshListener = zdYonRefreshListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main19);
        animation();
        PackageManager pm =getPackageManager();
        header = View.inflate(this, R.layout.listview_header, null);
        headerText = (TextView) header.findViewById(R.id.header_text);
        headerImage = (ImageView) header.findViewById(R.id.header_image);
        footer = View.inflate(this, R.layout.listview_footer, null);
        footerText = (TextView) footer.findViewById(R.id.footer_text);
        footerText.setText("正在加载...");
        adapter = new Adapter();
        zzz = (ListView) findViewById(R.id.zzz);
        l = new ArrayList();


        for (int i = 0; i < 20; i++) {
            l.add("sdf" + i);
        }

        setZdYonRefreshListener(new ZDYonRefreshListener() {
            @Override
            public void onRefresh() {
                new AsyncTask<Void, Void, Void>() {

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        headerImage.startAnimation(circleRotateAnimation);

                    }

                    @Override
                    protected Void doInBackground(Void... params) {
                        //请求数据
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        l.clear();
                        l.add("1");
                        adapter.notifyDataSetChanged();
                        header.setPadding(0, -measuredHeight, 0, 0);
                        headerImage.setImageResource(R.drawable.arrow);
                        currentState = PULL_REFRESH;
                    }
                }.execute();
            }

            @Override
            public void onLoadMore() {
                for (int j = 0; j < 10; j++) {
                    l.add("新增" + j);
                }
                adapter.notifyDataSetChanged();

                footer.setPadding(0, -footerMeasuredHeight, 0, 0);
            }
        });
        header.measure(0, 0);
        measuredHeight = header.getMeasuredHeight();
        Log.e("xcvxc",measuredHeight+"");
        header.setPadding(0, -measuredHeight, 0, 0);
        zzz.addHeaderView(header);

        footer.measure(0, 0);
        footerMeasuredHeight = footer.getMeasuredHeight();
        footer.setPadding(0, -footerMeasuredHeight, 0, 0);
        zzz.addFooterView(footer);
        //setAdapter最好在addHeaderView或者addFooterView之后
        zzz.setAdapter(adapter);

        zzz.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (currentState != REFRESHING) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            down_y = event.getRawY();
                            break;
                        case MotionEvent.ACTION_MOVE:
                            move_y = event.getRawY();
                            dy = (int) (move_y - down_y);
                            if (dy > 0) {
                                isLoadingMore = false;
                            } else {
                                isLoadingMore = true;
                            }
                            if (dy > 0 && zzz.getFirstVisiblePosition() == 0) {
                                //可以解决下滑再上滑listview的位置问题
                                zzz.setSelection(0);
                                paddingTop = dy - measuredHeight;
                                //getMeasuredheight如果滑动出来，会变
//                            Log.e("u", inflate.getMeasuredHeight() + "");
                                header.setPadding(0, paddingTop, 0, 0);

                                if (paddingTop < 0 && currentState == PULL_REFRESH) {
                                    headerText.setText("下拉刷新");
                                } else if (paddingTop >= 0 && currentState == PULL_REFRESH) {
                                    currentState = RELEASE_REFRESH;
                                    headerText.setText("松开刷新");
                                    headerImage.startAnimation(upRotateAnimation);

                                } else if (paddingTop < 0 && currentState == RELEASE_REFRESH) {
                                    currentState = PULL_REFRESH;
                                    headerText.setText("下拉刷新");
                                    headerImage.startAnimation(downRotateAnimation);
                                }

                            }

                            break;
                        case MotionEvent.ACTION_UP:
                            if (paddingTop >= 0 &&
                                    currentState == RELEASE_REFRESH) {
                                //完全显示
                                currentState = REFRESHING;
                                headerText.setText("正在刷新");
                                headerImage.setImageResource(circle);
                                headerImage.clearAnimation();
                                if (zdYonRefreshListener != null) {
                                    zdYonRefreshListener.onRefresh();
                                }
                            } else if (paddingTop < 0 &&
                                    currentState == PULL_REFRESH) {
                                header.setPadding(0, -measuredHeight, 0, 0);
                            }
                            break;
                        default:
                            break;
                    }
                }
                return false;

            }

        });

        zzz.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (zzz.getLastVisiblePosition() == zzz.getCount() - 1 &&
                        scrollState == SCROLL_STATE_IDLE && isLoadingMore) {
                    isLoadingMore = false;
                    footer.setPadding(0, 0, 0, 0);
                    zzz.setSelection(zzz.getCount());
                    if (zdYonRefreshListener != null) {
                        zdYonRefreshListener.onLoadMore();
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

    }

    public void animation() {

        upRotateAnimation = new RotateAnimation(0, 180,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        upRotateAnimation.setDuration(300);
        upRotateAnimation.setFillAfter(true);

        downRotateAnimation = new RotateAnimation(180, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        downRotateAnimation.setDuration(300);
        downRotateAnimation.setFillAfter(true);

        circleRotateAnimation = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        circleRotateAnimation.setDuration(300);


    }

    class Adapter extends BaseAdapter {
        @Override
        public int getCount() {
            return l.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHold vh = null;
            if (convertView == null) {
                vh = new ViewHold();
                convertView = View.inflate(Main19Activity.this, R.layout.xx, null);
                vh.tv = (TextView) convertView.findViewById(R.id.xx_text);
                convertView.setTag(vh);
            } else {
                vh = (ViewHold) convertView.getTag();
            }

            vh.tv.setText(l.get(position).toString());
            return convertView;
        }
    }

    static class ViewHold {
        TextView tv;

    }

}
