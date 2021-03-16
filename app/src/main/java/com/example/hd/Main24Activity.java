package com.example.hd;

import android.util.Log;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;


public class Main24Activity extends Main25Activity {

    private TabLayout mytab;
    private RecyclerView myRecyclerview;
    String data[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};


   /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main24);
        mytab = findViewById(R.id.mytab);
        myRecyclerview = findViewById(R.id.mylistview);
        myRecyclerview.setLayoutManager(new LinearLayoutManager(this));



        myRecyclerview.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View inflate = View.inflate(Main24Activity.this, R.layout.likezfb, null);

                return new MyViewHolder(inflate);
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                  if (holder instanceof MyViewHolder){
                      if (position == 0) {
                          ((MyViewHolder) holder).textView.setVisibility(View.GONE);
                      } else {
                          ((MyViewHolder) holder).textView.setVisibility(View.VISIBLE);

                      }
                      ((MyViewHolder) holder).textView.setText(mytab.getTabAt(position).getText());


                      ((MyViewHolder) holder).gridview.setAdapter(new BaseAdapter() {
                          @Override
                          public int getCount() {
                              return data.length;
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
                                  convertView = View.inflate(Main24Activity.this, R.layout.tt, null);
                                  vh.textView = (TextView) convertView.findViewById(R.id.tt);
                                  convertView.setTag(vh);
                              } else {
                                  vh = (ViewHold) convertView.getTag();
                              }

                              vh.textView.setText(data[position]);
                              return convertView;
                          }
                      });

                  }

            }

            @Override
            public int getItemCount() {
                return mytab.getTabCount();
            }



            class MyViewHolder extends RecyclerView.ViewHolder{

                private TextView textView;
                private GridView gridview;

                public MyViewHolder(View itemView) {
                    super(itemView);
                    textView = (TextView) itemView.findViewById(R.id.inner_textview);
                    gridview = (InnerGridView) itemView.findViewById(R.id.inner_gridview);
                }

            }

        });


        mytab.addTab(mytab.newTab().setText("选项卡一").setIcon(R.mipmap.ic_launcher));
        mytab.addTab(mytab.newTab().setText("选项卡二").setIcon(R.mipmap.ic_launcher));
        mytab.addTab(mytab.newTab().setText("选项卡三").setIcon(R.mipmap.ic_launcher));
        mytab.addTab(mytab.newTab().setText("选项卡四").setIcon(R.mipmap.ic_launcher));
        mytab.addTab(mytab.newTab().setText("选项卡五").setIcon(R.mipmap.ic_launcher));
        mytab.addTab(mytab.newTab().setText("选项卡六").setIcon(R.mipmap.ic_launcher));


//        mytab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                int selectedTabPosition = mytab.getSelectedTabPosition();
//                myListview.smoothScrollToPosition(selectedTabPosition);
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//
//        myListview.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                mytab.getTabAt(firstVisibleItem).select();
//            }
//        });
    }
*/


    class ViewHold {

        TextView textView;
        InnerGridView gridView;

    }

}



