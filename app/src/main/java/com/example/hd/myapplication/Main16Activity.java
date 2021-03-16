package com.example.hd.myapplication;

import android.content.Intent;
import android.os.Bundle;

import me.imid.swipebacklayout.lib.SwipeBackLayout;

public class Main16Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main16);

        scrollDirection("right");

        mSwipeBackLayout.addSwipeListener(new SwipeBackLayout.SwipeListener() {
            @Override
            public void onScrollStateChange(int state, float scrollPercent) {
               if (state==0){
                   Intent intent=new Intent(Main16Activity.this,Main17Activity.class);
                   startActivity(intent);
               }
            }

            @Override
            public void onEdgeTouch(int edgeFlag) {

            }

            @Override
            public void onScrollOverThreshold() {

            }
        });
    }


}
