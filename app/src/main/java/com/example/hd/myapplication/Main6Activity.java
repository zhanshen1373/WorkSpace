package com.example.hd.myapplication;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TimePicker;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main6Activity extends AppCompatActivity {

    private static final String TAG=Main6Activity.class.getSimpleName();


    @BindView(R.id.timepicker)
    public TimePicker timePicker;

    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        ButterKnife.bind(this);
        timePicker.setIs24HourView(true);
        toolbar= (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
//        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
//        String format = sdf.format(new Date(System.currentTimeMillis()));
//        Log.e(TAG,format);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.action_search:
                Toast.makeText(Main6Activity.this, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_notifications:
                break;
            case R.id.action_settings:
                break;
        }

        return super.onOptionsItemSelected(item);


    }
}
