package com.test.gyzhong.funanimator;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Created by gyzhong on 15/4/11.
 */
public class OriginalFragment extends Fragment {

    private View mActionBar ;
    private OnToggleClickListener mOnToggleClickListener ;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_original, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mActionBar = view.findViewById(R.id.id_actionbar) ;
        mActionBar.findViewById(R.id.id_toggle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnToggleClickListener != null){
                    mOnToggleClickListener.onClick(v);
                }
            }
        });
    }



    public void setOnToggleClickListener(OnToggleClickListener listener){
        mOnToggleClickListener = listener ;
    }


    public interface OnToggleClickListener{
       public void  onClick(View view) ;
    }
}
