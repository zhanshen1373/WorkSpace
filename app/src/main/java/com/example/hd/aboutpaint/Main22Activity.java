package com.example.hd.aboutpaint;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;


public class Main22Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawingWithBezier(this));
    }
}
