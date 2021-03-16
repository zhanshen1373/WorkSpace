package com.example.hd.treeview;

import android.util.Log;

import java.util.Comparator;
import java.util.List;

public class Bean {


    private List<OrglistBean> orglist;
    private int maxLevel;


    public int getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public List<OrglistBean> getOrglist() {
        return orglist;
    }

    public void setOrglist(List<OrglistBean> orglist) {
        this.orglist = orglist;
    }

    public static class OrglistBean implements Cloneable,Comparable<OrglistBean> {
        /**
         * description : 组织
         * isfinal : false
         * position : 0
         * levelInAll : 0
         */

        private String description;
        private boolean isfinal;
        private int position;
        private int levelInAll;
        private boolean drawed;

        public boolean isDrawed() {
            return drawed;
        }

        public void setDrawed(boolean drawed) {
            this.drawed = drawed;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public boolean isIsfinal() {
            return isfinal;
        }

        public void setIsfinal(boolean isfinal) {
            this.isfinal = isfinal;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public int getLevelInAll() {
            return levelInAll;
        }

        public void setLevelInAll(int levelInAll) {
            this.levelInAll = levelInAll;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }



        @Override
        public int compareTo(OrglistBean o) {
            // 0/1按照加入顺序排序，-1反向排序
//            return -1;
            //this是后面的数据，后减前 自然排序。前减后 倒序排序
            Log.e("b",this.levelInAll+"/"+o.levelInAll);
            return this.levelInAll-o.levelInAll;
        }
    }
}
