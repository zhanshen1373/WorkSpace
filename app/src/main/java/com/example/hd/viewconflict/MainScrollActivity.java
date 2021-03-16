package com.example.hd.viewconflict;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

import com.example.hd.myapplication.InnerListView;
import com.example.hd.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class MainScrollActivity extends AppCompatActivity {

    private InnerListView innerlistview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_scroll);


//        innerlistview = (InnerListView) findViewById(R.id.innerlistview);




        final List<String> h = new ArrayList<>();
        for (int i = 0; i < 33; i++) {
            h.add("n" + i);
        }

    /*
        innerlistview.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return h.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                ViewHold vh = null;
                if (convertView == null) {
                    vh = new ViewHold();
                    convertView = View.inflate(MainScrollActivity.this,R.layout.onlytextview , null);
                    vh.textView = (TextView) convertView.findViewById(R.id.onlytextview_tv);
                    convertView.setTag(vh);
                } else {
                    vh = (ViewHold) convertView.getTag();
                }

                vh.textView.setText(h.get(position));
                return convertView;
            }
        });
   */
    }


    static class ViewHold{

        private TextView textView;

    }


}
