package com.example.hd.scrolltextview;

import android.content.Context;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by dubojian on 2018/6/7.
 */

public class ScrollTextView extends AppCompatTextView {

    public ScrollTextView(Context context) {
        super(context);
    }

    public ScrollTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
