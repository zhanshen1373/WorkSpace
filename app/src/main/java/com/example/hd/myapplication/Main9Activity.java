package com.example.hd.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main9Activity extends AppCompatActivity {


    private ArrayList<HashMap<String, Object>> mixList = new ArrayList<>();
    @BindView(R.id.zidingyigv)
    public ZiDingYiGridView gridView;
    private MyGridViewAdapter gridViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);

        ButterKnife.bind(this);
        for (int i = 0; i < 6; i++) {
            HashMap<String, Object> hm = new HashMap<>();
            hm.put("picture", R.drawable.t);
            hm.put("word", "拖拽" + i);
            mixList.add(hm);
        }
        gridViewAdapter = new MyGridViewAdapter(this, mixList);
        gridView.setAdapter(gridViewAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Main9Activity.this, mixList.get(position).get("word").toString(), Toast.LENGTH_LONG).show();
            }
        });
    }


}
