package com.example.hd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.example.hd.myapplication.R;
import com.example.hd.myapplication.SimpleFragment;

import java.util.EnumMap;

public class Main32Activity extends AppCompatActivity {

    private static final String TAG = "SimpleFragment";
    private SimpleFragment fragment;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main32);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
//        main();



        /*
        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                } else {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
            }
        });

        FragmentManager manager = getSupportFragmentManager();
        Fragment simple_fragment = manager.findFragmentByTag("simple_fragment");
        if (simple_fragment == null) {
            fragment = new SimpleFragment();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(fragment, "simple_fragment").commit();
        }
        Log.d(TAG, "simple_fragment = " + simple_fragment);

        */
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main7menu,menu);
//        return super.onCreateOptionsMenu(menu);
//    }

    //    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        return super.onOptionsItemSelected(item);
//    }
    public enum a {

        A(1), B(3), C(2);

        private int num;

        a(int t) {
            this.num = t;
        }

        @Override
        public String toString() {
            return String.valueOf(num);
        }

    }

    public void main() {

        a[] values = a.values();

        Log.e("xx", "valueof" + a.valueOf("A"));
        for (a t : values) {
            Log.e("xx", "name" + t.name());
            Log.e("xx", "ordinal" + t.ordinal());
            Log.e("xx", "t" + t);
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("xx", "stop");

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {


        switch (ev.getAction()) {

            case MotionEvent.ACTION_DOWN:
                Log.e("tag", "activity-dispatch-down");
                break;

            case MotionEvent.ACTION_MOVE:
                Log.e("tag", "activity-dispatch-move");

                break;
            case MotionEvent.ACTION_UP:
                Log.e("tag", "activity-dispatch-up");

                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e("tag", "activity-dispatch-cancel");

                break;
            default:
                break;

        }
        return super.dispatchTouchEvent(ev);


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {


        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                Log.e("tag", "activity-ontouchevent-down");
                break;

            case MotionEvent.ACTION_MOVE:
                Log.e("tag", "activity-ontouchevent-move");

                break;
            case MotionEvent.ACTION_UP:
                Log.e("tag", "activity-ontouchevent-up");

                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e("tag", "activity-ontouchevent-cancel");

                break;
            default:
                break;

        }
        return super.onTouchEvent(event);

    }


}



