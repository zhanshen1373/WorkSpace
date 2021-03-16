package com.example.hd;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.example.hd.myapplication.R;
import com.example.hd.zhibo.Main29Activity;

public class Main28Activity extends AppCompatActivity {

    private View zm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main28);
        zm = findViewById(R.id.zimage);



    }



    public void ttt(View view) {
        zm.setVisibility(View.INVISIBLE);

    }


}
