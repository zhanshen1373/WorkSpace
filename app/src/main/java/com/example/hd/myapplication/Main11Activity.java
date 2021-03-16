package com.example.hd.myapplication;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Main11Activity extends AppCompatActivity {

    private FrameLayout flayout;
    private ZDYListView listView;
    private GridView gridView;
    private List<String> list;
    private int Type_first = 0;
    private int Type_default = 1;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView imageView5;
    private List<String> gridviewList=new ArrayList<>();
    private Drawable d;
//    private zListivew ll;
    private ListView ll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11);

        ll= (ListView) findViewById(R.id.ww);

//        d = getResources().getDrawable(R.drawable.drawview);
//        d.setBounds(0, 0, d.getMinimumWidth(), d.getMinimumHeight());
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(20,10,20,10);

        imageView1 = new ImageView(Main11Activity.this);
        imageView1.setImageResource(R.drawable.nochoice);
        imageView1.setLayoutParams(params);

        imageView2 = new ImageView(Main11Activity.this);
        imageView2.setImageResource(R.drawable.nochoice);
        imageView2.setLayoutParams(params);

        imageView3 = new ImageView(Main11Activity.this);
        imageView3.setImageResource(R.drawable.nochoice);
        imageView3.setLayoutParams(params);

        imageView4 = new ImageView(Main11Activity.this);
        imageView4.setImageResource(R.drawable.nochoice);
        imageView4.setLayoutParams(params);

        imageView5 = new ImageView(Main11Activity.this);
        imageView5.setImageResource(R.drawable.ellipsis);
        imageView5.setLayoutParams(params);

//        listView = (ZDYListView) findViewById(R.id.test_listview);
//        ll= (zListivew) findViewById(R.id.ss);
        list = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            list.add("content" + i);
        }


        for (int k=0;k<5;k++){
            gridviewList.add("neirong"+k);
        }
//        View headview=getLayoutInflater().inflate(R.layout.headview,null);
        View footview=getLayoutInflater().inflate(R.layout.footview,null);
//        listView.addHeaderView(headview);
//        listView.addFooterView(footview);



        ll.setAdapter(new BaseAdapter() {

            @Override
            public int getViewTypeCount() {
                return 2;
            }

            @Override
            public int getItemViewType(int position) {
                switch (position) {
                    case 0:
                        return Type_first;
                    default:
                        return Type_default;
                }
            }

            @Override
            public int getCount() {
                return list.size();
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

                int itemViewType = getItemViewType(position);
                if (itemViewType == Type_first) {
                    ViewHolderFirst viewHolderFirst = null;
                    if (convertView == null) {
                        viewHolderFirst = new ViewHolderFirst();
                        convertView = View.inflate(Main11Activity.this, R.layout.viewholderfirst, null);
                        viewHolderFirst.tvContent = (TextView) convertView.findViewById(R.id.viewholderfirst_tvcontent);
                        viewHolderFirst.linearLayout = (LinearLayout) convertView.findViewById(R.id.viewholderfirst_linearlayout);
                        viewHolderFirst.tvOperation = (TextView) convertView.findViewById(R.id.viewholderfirst_tvoperation);
                        convertView.setTag(viewHolderFirst);
                    } else {
                        viewHolderFirst = (ViewHolderFirst) convertView.getTag();
                    }
                    viewHolderFirst.tvContent.setText("首页应用");

                    viewHolderFirst.linearLayout.removeView(imageView1);
                    viewHolderFirst.linearLayout.removeView(imageView2);
                    viewHolderFirst.linearLayout.removeView(imageView3);
                    viewHolderFirst.linearLayout.removeView(imageView4);
                    viewHolderFirst.linearLayout.removeView(imageView5);
                    viewHolderFirst.linearLayout.addView(imageView1);
                    viewHolderFirst.linearLayout.addView(imageView2);
                    viewHolderFirst.linearLayout.addView(imageView3);
                    viewHolderFirst.linearLayout.addView(imageView4);
                    viewHolderFirst.linearLayout.addView(imageView5);

                    viewHolderFirst.tvOperation.setText("编辑");
                    viewHolderFirst.tvOperation.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                } else if(itemViewType==Type_default){
                    ViewHolderDefault viewHolderDefault=null;
                    if (convertView==null){
                     viewHolderDefault=new ViewHolderDefault();
                     convertView=View.inflate(Main11Activity.this,R.layout.viewholderdefault,null);
                     viewHolderDefault.tvContent= (TextView) convertView.findViewById(R.id.viewholderdefault_tvContent);
                     viewHolderDefault.gridView= (GridView) convertView.findViewById(R.id.viewholderdefault_gridview);
                     convertView.setTag(viewHolderDefault);
                    }else{
                     viewHolderDefault= (ViewHolderDefault) convertView.getTag();
                    }

//                    viewHolderDefault.tvContent.setCompoundDrawables(d,null,null,null);
                    viewHolderDefault.gridView.setAdapter(new ArrayAdapter<>(Main11Activity.this,
                            android.R.layout.simple_list_item_1,gridviewList));

                }

                return convertView;
        }
    });


}

    public void ck(View view) {
        GridView gridView = new GridView(this);

        gridView.setNumColumns(3);
        gridView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                list));
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                500);
        gridView.setLayoutParams(params);
        flayout.removeView(view);
        flayout.addView(gridView);
//      view.setVisibility(View.INVISIBLE);
//
//        gridView.setVisibility(View.VISIBLE);

    }

static class ViewHolderFirst {
    TextView tvContent;
    LinearLayout linearLayout;
    TextView tvOperation;

}

static class ViewHolderDefault {
    TextView tvContent;
    GridView gridView;

}
}
