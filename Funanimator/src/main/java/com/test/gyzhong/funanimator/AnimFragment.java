package com.test.gyzhong.funanimator;

import android.graphics.Color;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Created by gyzhong on 15/4/11.
 */
public class AnimFragment extends Fragment {
    private View mActionBar ;
    private OriginalFragment.OnToggleClickListener mOnToggleClickListener ;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_instruct, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mActionBar = view.findViewById(R.id.id_actionbar) ;
        mActionBar.setBackgroundColor(Color.DKGRAY);
        ((TextView)mActionBar.findViewById(R.id.id_actionbar_title)).setText("简介");
        ((TextView)mActionBar.findViewById(R.id.id_toggle)).setText("取消");
        mActionBar.findViewById(R.id.id_toggle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnToggleClickListener != null){
                    mOnToggleClickListener.onClick(v);
                }
            }
        });
    }
    public void setOnToggleClickListener(OriginalFragment.OnToggleClickListener listener){
        mOnToggleClickListener = listener ;
    }
}
