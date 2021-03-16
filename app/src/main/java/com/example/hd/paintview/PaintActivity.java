package com.example.hd.paintview;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hd.myapplication.R;

public class PaintActivity extends AppCompatActivity {

    private ProcessLine processLine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);
        processLine= (ProcessLine) findViewById(R.id.processline);

        processLine.setDrawModel(true);
        processLine.invalidate();
    }
}
