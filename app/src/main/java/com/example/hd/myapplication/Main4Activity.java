package com.example.hd.myapplication;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;




public class Main4Activity extends AppCompatActivity {

//    @BindView(R.id.mmp)
    public ListView listView;

//    @OnClick(R.id.mmp_button3)
    public void transform(){
        Intent intent=new Intent(this,Main3Activity.class);
        startActivity(intent);
    }

//    @OnClick(R.id.mmp_button5)
    public void trans(){
        Intent intent=new Intent(this,Main2Activity.class);
        startActivity(intent);
    }


//    @BindView(R.id.mmp_button5)
    public Button button5;

    private ArrayList<String> list;
    private MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ButterKnife.bind(this);
        setContentView(R.layout.activity_main4);
        listView= (ListView) findViewById(R.id.mmp);

        ActivityManager am = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfoList = am.getRunningTasks(10);
        for (ActivityManager.RunningTaskInfo runningTaskInfo : runningTaskInfoList) {
            Log.e("id: ",runningTaskInfo.id+"");
            Log.e("description: ",runningTaskInfo.description+"");
            Log.e("number of activities: " ,runningTaskInfo.numActivities+"");
            Log.e("topActivity: " ,runningTaskInfo.topActivity+"");
            Log.e("baseActivity: ", runningTaskInfo.baseActivity.toString());
        }


        list=new ArrayList<>();
        for (int i=0;i<10;i++){
            list.add("nihao"+i);
        }
        list.add("hello");
        list.add("name");

        adapter=new MyAdapter();


        listView.setAdapter(adapter);


    }



    class MyAdapter extends  BaseAdapter{



        @Override
        public int getCount() {
            return list.size();
        }



        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHold vh = null;
            if (convertView == null) {
                vh = new ViewHold();
                convertView = View.inflate(Main4Activity.this, R.layout.dzf_listview_item, null);
                vh.textView = (EditText) convertView.findViewById(R.id.dzf_textview);
                vh.radioGroup= (RadioGroup) convertView.findViewById(R.id.radiogroup);
                vh.button1= (RadioButton) convertView.findViewById(R.id.radiobutton1);
                vh.button2= (RadioButton) convertView.findViewById(R.id.radiobutton2);
                convertView.setTag(vh);
            } else {
                vh = (ViewHold) convertView.getTag();
            }

            vh.textView.setHint(list.get(position).toString());

            vh.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                    if (checkedId==R.id.radiobutton1){
                        group.check(R.id.radiobutton1);
                        RadioButton rb= (RadioButton) group.findViewById(R.id.radiobutton1);
                        if (rb.isChecked()){
                            Log.e("qq","hahah");
                        }
                    }else if (checkedId==R.id.radiobutton2){
                        group.check(R.id.radiobutton2);
                        RadioButton rb2= (RadioButton) group.findViewById(R.id.radiobutton2);
                        if (rb2.isChecked()){
                            Log.e("bbvb","pppp");
                        }
                    }
                }
            });

            return convertView;
        }




    }


    static class ViewHold{
        EditText textView;
        RadioGroup radioGroup;
        RadioButton button1;
        RadioButton button2;
    }
}
