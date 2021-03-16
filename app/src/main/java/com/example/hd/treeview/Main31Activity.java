package com.example.hd.treeview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.hd.myapplication.R;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import p.vv;

public class Main31Activity extends AppCompatActivity {

    private OrganizationView organizationView;
    private Bean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main31);
        organizationView=findViewById(R.id.organ);

        initData();
        organizationView.setData(bean);
        organizationView.setWillNotDraw(false);
    }



    public void initData() {
        bean = new Bean();
        List<Bean.OrglistBean> orglistBeans=new ArrayList<>();
        Bean.OrglistBean orglistBean=new Bean.OrglistBean();
        orglistBean.setDescription("组织");
        orglistBean.setIsfinal(false);
        orglistBean.setLevelInAll(0);
        orglistBean.setPosition(0);
        orglistBeans.add(orglistBean);
        try {
            Bean.OrglistBean clone = (Bean.OrglistBean) orglistBean.clone();
            clone.setDescription("中国石油宁夏石化公司");
            clone.setIsfinal(false);
            clone.setLevelInAll(1);
            clone.setPosition(1);
            Bean.OrglistBean clone1 = (Bean.OrglistBean) clone.clone();
            clone1.setDescription("直属部门");
            clone1.setIsfinal(false);
            clone1.setLevelInAll(2);
            clone1.setPosition(2);
            Bean.OrglistBean clone2 = (Bean.OrglistBean) clone.clone();
            clone2.setDescription("信息管理部(0项)");
            clone2.setIsfinal(false);
            clone2.setLevelInAll(3);
            clone2.setPosition(3);

            Bean.OrglistBean clone3 = (Bean.OrglistBean) clone.clone();
            clone3.setDescription("工程管理部(0项)");
            clone3.setIsfinal(true);
            clone3.setLevelInAll(3);
            clone3.setPosition(4);


            Bean.OrglistBean clone4 = (Bean.OrglistBean) clone.clone();
            clone4.setDescription("二级单位(25项)");
            clone4.setIsfinal(false);
            clone4.setLevelInAll(2);
            clone4.setPosition(5);

            Bean.OrglistBean clone5 = (Bean.OrglistBean) clone.clone();
            clone5.setDescription("电仪部(10项)");
            clone5.setIsfinal(true);
            clone5.setLevelInAll(3);
            clone5.setPosition(6);

            Bean.OrglistBean clone6 = (Bean.OrglistBean) clone.clone();
            clone6.setDescription("炼油厂(15项)");
            clone6.setIsfinal(false);
            clone6.setLevelInAll(3);
            clone6.setPosition(7);

            Bean.OrglistBean clone7 = (Bean.OrglistBean) clone.clone();
            clone7.setDescription("水气车间(10项)");
            clone7.setIsfinal(true);
            clone7.setLevelInAll(2);
            clone7.setPosition(8);


            Bean.OrglistBean clone8 = (Bean.OrglistBean) clone.clone();
            clone8.setDescription("一联合车间(5项)");
            clone8.setIsfinal(true);
            clone8.setLevelInAll(3);
            clone8.setPosition(9);

            orglistBeans.add(clone);
            orglistBeans.add(clone1);
            orglistBeans.add(clone2);
            orglistBeans.add(clone3);
            orglistBeans.add(clone4);
            orglistBeans.add(clone5);
            orglistBeans.add(clone6);
            orglistBeans.add(clone7);
            orglistBeans.add(clone8);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }


        Collections.sort(orglistBeans);


        bean.setOrglist(orglistBeans);
        bean.setMaxLevel(3);



    }

}



