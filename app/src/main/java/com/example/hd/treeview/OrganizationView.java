package com.example.hd.treeview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.hd.myapplication.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

//ondraw中可以for循环画线
public class OrganizationView extends LinearLayout {

    private ImageView imageView;
    private TextView textView;
    private Bean bean;
    private Context mContext;
    private List<Bean.OrglistBean> orglist;
    private Paint pt;
    private int maxLevel;

    public OrganizationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init(context);
    }

    private void init(Context context) {
        setOrientation(VERTICAL);
        pt = new Paint();
        pt.setColor(Color.BLACK);
        pt.setStrokeWidth(2);
        pt.setAntiAlias(true);
    }

    public void IvResource(boolean open) {
        if (open) {
            imageView.setImageResource(R.drawable.he_hse_common_component_closed);
        } else {
            imageView.setImageResource(R.drawable.he_hse_common_component_opened);
        }
    }

    public void TvContent(String content) {
        textView.setText(content);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        for (int j = 0; j < getChildCount(); j++) {
            //控制上下位置
            int position = orglist.get(j).getPosition();
            //控制左右位置
            int levelInAll = orglist.get(j).getLevelInAll();
            getChildAt(j).layout((levelInAll + 1) * 50, position * getChildAt(j).getHeight(), getChildAt(j).getWidth(), (position + 1) * getChildAt(j).getHeight());

        }

    }

    private HashMap<Integer, List<Integer>> hm = new HashMap();
    private List<Integer> list = new ArrayList<>();
    private List<Integer> hlist = new ArrayList<>();

    private void a(HashMap mm, List<Integer> CurrentLevel, List<Integer> NextLevel) {
        for (int ww = CurrentLevel.size() - 1; ww >= 0; ww--) {
            List<Integer> nmd = new ArrayList<>();
            for (int pp = 0; pp < NextLevel.size(); pp++) {
                if (NextLevel.get(pp) > CurrentLevel.get(ww)) {
                    nmd.add(NextLevel.get(pp));
                    NextLevel.remove(pp);
                    pp--;
                }
                mm.put(ww, nmd);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        //当前级别向下一级别画线
        for (int w = 0; w < maxLevel; w++) {
            //当前级别纵坐标
            List<Integer> CurrentLevel = hm.get(w);
            //下一级别纵坐标
            List<Integer> NextLevel = hm.get(w + 1);

            Collections.sort(CurrentLevel);
            Collections.sort(NextLevel);
            HashMap<Integer, List<Integer>> mm = new HashMap<>();

            List<Integer> temC = new ArrayList<>();
            List<Integer> temN = new ArrayList<>();
            temC.addAll(CurrentLevel);
            temN.addAll(NextLevel);

            //下一级的position
            a(mm, temC, temN);

            Object t = mm;

            for (int u = 0; u < CurrentLevel.size(); u++) {

                //当前级别position
                int start = CurrentLevel.get(u);
                orglist.get(start).setDrawed(false);
                if (orglist.get(start).isIsfinal()) {
                    continue;
                }


                if (!orglist.get(start).isDrawed()) {
                    Integer max = Collections.max(mm.get(u));
                    //画竖线
                    canvas.drawLine(20 + (w + 1) * 50, (float) ((start + 1) * getChildAt(start).getHeight() - 10), 20 + (w + 1) * 50, (float) ((max + 0.5) * getChildAt(max).getHeight()), pt);

                    //画横线
                    for (int j = 0; j < NextLevel.size(); j++) {
                        int end = NextLevel.get(j);
                        canvas.drawLine(20 + (w + 1) * 50, (float) ((end + 0.5) * getChildAt(end).getHeight()), 20 + (w + 1 + 1) * 50, (float) ((end + 0.5) * getChildAt(end).getHeight()), pt);
                    }
                    orglist.get(start).setDrawed(true);
                }


            }

        }

    }


    public void setData(Bean p) {
        this.bean = p;
        maxLevel = bean.getMaxLevel();
        orglist = bean.getOrglist();
        setAdapter();
    }

    private void setAdapter() {

        for (int i = 0; i < orglist.size(); i++) {
            LinearLayout linearLayout = new LinearLayout(mContext);
            linearLayout.setOrientation(HORIZONTAL);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            linearLayout.setGravity(Gravity.CENTER_VERTICAL);

            imageView = new ImageView(mContext);
            textView = new TextView(mContext);
            TvContent(orglist.get(i).getDescription());
            IvResource(orglist.get(i).isIsfinal());

            linearLayout.addView(imageView);
            linearLayout.addView(textView);


            LinearLayout.LayoutParams layoutParams1 = (LayoutParams) textView.getLayoutParams();
            layoutParams1.leftMargin = 20;
            textView.setLayoutParams(layoutParams1);
            addView(linearLayout);

            //控制左右位置
            int levelInAll = orglist.get(i).getLevelInAll();
            //控制上下位置
            int position = orglist.get(i).getPosition();

            List<Integer> integers = null;
            if (list.contains(levelInAll)) {
                hlist = hm.get(levelInAll);
                hlist.add(position);
                hm.put(levelInAll, hlist);

            } else {
                integers = new ArrayList<>();
                integers.add(position);
                hm.put(levelInAll, integers);
            }
            list.add(levelInAll);


        }
    }
}
