package com.example.hd.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Main17Activity extends BaseActivity{
    private ImageView dot1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main17);

        dot1 = (ImageView) findViewById(R.id.dot1);

        scrollDirection("left");
        dot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main17Activity.this,"xxx",Toast.LENGTH_LONG).show();
            }
        });

    }
}
