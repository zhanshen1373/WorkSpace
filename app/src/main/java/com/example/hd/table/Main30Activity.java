package com.example.hd.table;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hd.myapplication.R;

import java.text.DecimalFormat;

public class Main30Activity extends AppCompatActivity {


    private Zdyview zdyview;
    private String[][] b = new String[][]{{"宁夏石化公司regerger", "一般作业", "高处作业","wdf"}, {"单位", "10", "6"},{"宁夏石化公司regerger", "一般作业", "高处作业"}, {"单位", "10", "6"},{"宁夏石化公司regerger", "一般作业", "高处作业"}, {"单位", "10", "6"}};
    private TextView tv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main30);


        zdyview = findViewById(R.id.zdy_view);


        zdyview.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int width = zdyview.getWidth();
                zdyview.getViewTreeObserver().removeOnGlobalLayoutListener(this);


                //行长度
                for (int i = 0; i < b.length; i++) {

                    //列长度
                    LinearLayout row = new LinearLayout(Main30Activity.this);
                    row.setOrientation(LinearLayout.HORIZONTAL);
                    String format = new DecimalFormat("0").format(1.5 * width);
                    int realWidth = Integer.parseInt(format);
                    row.setLayoutParams(new LinearLayout.LayoutParams(realWidth, 150));
                    for (int t = 0; t < b[i].length; t++) {
                        tv = new TextView(Main30Activity.this);
                        tv.setText(b[i][t]);
                        tv.setTextColor(Color.WHITE);
                        tv.setBackgroundResource(R.drawable.tvbackground);
                        tv.setGravity(Gravity.CENTER);
//width / b[i].length
                        tv.setLayoutParams(new LinearLayout.LayoutParams(realWidth/b[i].length, LinearLayout.LayoutParams.MATCH_PARENT));


                        if (i > 0 && t > 0) {
                            tv.setTextColor(Color.BLUE);
                            tv.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
                            tv.setBackgroundResource(R.drawable.tvnobackground);
                        }
                        row.addView(tv);
                    }
                    zdyview.addView(row);
                    zdyview.initEvent();
                }

            }
        });


//        zdyview.setWillNotDraw(false);





    }

}
