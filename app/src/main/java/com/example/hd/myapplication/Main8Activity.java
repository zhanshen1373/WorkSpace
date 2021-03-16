package com.example.hd.myapplication;

import android.graphics.Bitmap;
import android.graphics.Camera;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main8Activity extends AppCompatActivity {

    @BindView(R.id.main8_viewpager)
    public MyViewPager viewPager;

    private ZiDingYiImageView imageView;
    private int width;
    private int height;
    private Bitmap bitmap;
    private ArrayList<View> pagerlist;
    private String[] arraylist;
    private LinearLayout linear;
    Camera ca = new Camera();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        ButterKnife.bind(this);

        arraylist=new String[]{"a","b"
        };
        pagerlist=new ArrayList<>();
        View inflate = View.inflate(this, R.layout.firstview, null);
        ListView firstview_listview= (ListView) inflate.findViewById(R.id.firstview_listview);
        firstview_listview.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arraylist));
        View inflate1 = View.inflate(this, R.layout.secondview, null);
        View inflate2 = View.inflate(this, R.layout.thirdview, null);
        pagerlist.add(inflate);
        pagerlist.add(inflate1);
        pagerlist.add(inflate2);

        PagerAdapter p=new MyPagerAdapter(pagerlist);

        imageView = (ZiDingYiImageView) findViewById(R.id.ImageView);
        linear= (LinearLayout) findViewById(R.id.main8_linear);
        viewPager.setView(imageView);



//        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tim).copy(Bitmap.Config.RGB_565, true);
//        width = bitmap.getWidth();
//        height = bitmap.getHeight();

//        Canvas cv=new Canvas(bitmap);
//        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.water);
//        int width1 = bitmap1.getWidth();
//        int height1 = bitmap1.getHeight();
//        cv.drawBitmap(bitmap1,width-width1-20,height-height1-20,null);
//
//
//        cv.save( Canvas.ALL_SAVE_FLAG );//保存
//
//        cv.restore();//存储


    }

    public void bt(View view){
        View inflate = getLayoutInflater().inflate(R.layout.exercise, null);
        PopupWindow popupWindow=new PopupWindow();
        popupWindow.setOutsideTouchable(true);
        popupWindow.setContentView(inflate);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(450);
        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(linear,0,linear.getHeight());
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
              backgroundAlpha(1f);
            }
        });
        backgroundAlpha(0.6f);

    }

    private void backgroundAlpha(float v) {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha=v;
        getWindow().setAttributes(attributes);
    }


}
