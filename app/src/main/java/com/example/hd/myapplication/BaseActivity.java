package com.example.hd.myapplication;

import android.os.Bundle;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;


public class BaseActivity extends SwipeBackActivity {

    public SwipeBackLayout mSwipeBackLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        mSwipeBackLayout = getSwipeBackLayout();

    }

    public void scrollDirection(String direction) {
        int edgeFlag;
        switch (direction) {
            case "left":
                edgeFlag = SwipeBackLayout.EDGE_LEFT;
                break;
            case "right":
                edgeFlag = SwipeBackLayout.EDGE_RIGHT;
                break;
            default:
                edgeFlag = SwipeBackLayout.EDGE_ALL;
        }
        mSwipeBackLayout.setEdgeTrackingEnabled(edgeFlag);

    }


}
