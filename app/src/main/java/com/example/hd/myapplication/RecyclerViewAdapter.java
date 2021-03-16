package com.example.hd.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by dubojian on 2017/9/29.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int Type_first = 0;
    private int Type_default = 1;
    private Context mContext;
    private Domain domain;
    private MyExpandableAdapter myExpandableAdapter;

    public RecyclerViewAdapter(Context mContext, Domain d) {
        this.mContext = mContext;
        this.domain = d;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == Type_first) {
            myExpandableAdapter = new MyExpandableAdapter(mContext,
                    domain.getLonelyDomain().getTitle(), domain.getLonelyDomain().getContent());
            View inflate = View.inflate(mContext, R.layout.lonelyview, null);
            return new ViewHolderFirst(inflate);
        } else if (viewType == Type_default) {
            View inflate = View.inflate(mContext, R.layout.defaultview, null);
            return new ViewHolderDefault(inflate);

        }
        return null;

    }

    static class ViewHolderFirst extends RecyclerView.ViewHolder {
        private ExpandableListView lonely_expandview;


        public ViewHolderFirst(View itemView) {
            super(itemView);
            lonely_expandview = (ExpandableListView) itemView.findViewById(R.id.lonely_expandview);
        }
    }

    static class ViewHolderDefault extends RecyclerView.ViewHolder {
        private TextView default_content;
        private InnerListView default_listview;


        public ViewHolderDefault(View itemView) {
            super(itemView);

            default_content = (TextView) itemView.findViewById(R.id.default_content);
            default_listview = (InnerListView) itemView.findViewById(R.id.default_listview);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolderFirst) {
            ((ViewHolderFirst) holder).lonely_expandview.setAdapter(myExpandableAdapter);
            ((ViewHolderFirst) holder).lonely_expandview.expandGroup(0);

        } else if (holder instanceof ViewHolderDefault) {

            ((ViewHolderDefault) holder).default_content.setText(domain.getDefaultDomain().getTitleDomains().get(position).getTitle());
            ((ViewHolderDefault) holder).default_listview.setAdapter(new BaseAdapter() {
                @Override
                public int getCount() {
                    return domain.getDefaultDomain().getInnerDomains().size();
                }

                @Override
                public Object getItem(int position) {
                    return null;
                }

                @Override
                public long getItemId(int position) {
                    return position;
                }

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {

                    ViewHolddd vh = null;
                    if (convertView == null) {
                        vh = new ViewHolddd();
                        convertView = View.inflate(mContext, R.layout.mmp, null);
                        vh.mmp_tv_content = (TextView) convertView.findViewById(R.id.mmp_tv_content);
                        vh.mmp_tv_minute = (TextView) convertView.findViewById(R.id.mmp_tv_minute);
                        vh.mmp_tv_second = (TextView) convertView.findViewById(R.id.mmp_tv_second);
                        convertView.setTag(vh);
                    } else {
                        vh = (ViewHolddd) convertView.getTag();
                    }

                    vh.mmp_tv_content.setText(domain.getDefaultDomain().getInnerDomains().get(position).getmContent());
                    vh.mmp_tv_minute.setText(domain.getDefaultDomain().getInnerDomains().get(position).getTiemMinute());
                    vh.mmp_tv_second.setText(domain.getDefaultDomain().getInnerDomains().get(position).getTimeSecond());
                    return convertView;
                }
            });


        }


    }

    @Override
    public int getItemCount() {
        return 3;
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

    class ViewHolddd {
        TextView mmp_tv_content;
        TextView mmp_tv_minute;
        TextView mmp_tv_second;
    }
}
