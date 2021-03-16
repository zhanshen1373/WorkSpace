package com.example.hd.myapplication;

import android.graphics.Matrix;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class Main2Activity extends AppCompatActivity {


    private ListView list;
    private ArrayList listdata = new ArrayList();
    private LinShiAdapter adapter;
    private Matrix matrix;
    private ImageView headerImage;
    private float downy;
    private float movey;
    private float dy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ButterKnife.bind(this);
        list = (ListView) findViewById(R.id.list);
        for (int i = 0; i < 33; i++) {
            listdata.add(i + "");
        }
        matrix = new Matrix();
        View inflate = getLayoutInflater().inflate(R.layout.headimage, null);
        headerImage = (ImageView) inflate.findViewById(R.id.headerimage);
        headerImage.setScaleType(ImageView.ScaleType.FIT_XY);
//        matrix.postScale()
//        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams();
        list.addHeaderView(inflate);

        adapter = new LinShiAdapter(this, listdata);

        list.setAdapter(adapter);

        list.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        downy = event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        movey = event.getRawY();
                        dy = movey - downy;
                        if (dy > 0) {
//                            dy+headerImage.getHeight()
//                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//
//                            );
                        }
                        break;
                    case MotionEvent.ACTION_UP:

                        break;

                    default:

                        break;
                }

                return false;
            }
        });


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tt = (TextView) view.findViewById(R.id.xx_text);
                if (tt.getText().equals("1"))
                    Toast.makeText(Main2Activity.this, position + "\t" + id, Toast.LENGTH_LONG).show();
            }
        });
    }



}
