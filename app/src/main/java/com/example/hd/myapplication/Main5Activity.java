package com.example.hd.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class Main5Activity extends SwipeBackActivity {

    @BindView(R.id.eidt_text)
    public EditText et;

    @OnClick(R.id.qiu_dian)
    public void what() {
        Intent intent=new Intent(this,Main6Activity.class);
        startActivity(intent);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main5);

        ButterKnife.bind(this);

        MyTextWatcher myTextWatcher = new MyTextWatcher();
        MyOnTouch myOnTouch = new MyOnTouch();
        et.addTextChangedListener(myTextWatcher);
        et.setOnTouchListener(myOnTouch);

    }


    class MyOnTouch implements View.OnTouchListener {


        @Override
        public boolean onTouch(View v, MotionEvent event) {

            Drawable[] compoundDrawables = et.getCompoundDrawables();
            Drawable drawables = compoundDrawables[2];
            if (drawables == null) {
                return false;
            }
            if (event.getAction() == MotionEvent.ACTION_UP) {

                if (event.getRawX() > et.getWidth() - drawables.getIntrinsicWidth()) {

                    LayoutInflater layoutInflater = (LayoutInflater) getSystemService(
                            Context.LAYOUT_INFLATER_SERVICE);
                    View inflate = layoutInflater.inflate(R.layout.view, null);
                    PopupWindow popupWindow = new PopupWindow(inflate);
                    popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                    Point p = new Point();
                    getWindowManager().getDefaultDisplay().getSize(p);
                    popupWindow.setWidth(p.x);
                    popupWindow.setFocusable(true);
                    popupWindow.setOutsideTouchable(true);
                    popupWindow.showAsDropDown(v);

                    popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            WindowManager.LayoutParams attributes = getWindow().getAttributes();
                            attributes.alpha = 1.0f;
                            getWindow().setAttributes(attributes);
                        }
                    });

                    WindowManager.LayoutParams attributes = getWindow().getAttributes();
                    attributes.alpha = 0.6f;
                    getWindow().setAttributes(attributes);


                }
                return true;
            }
            return false;
        }
    }

    class MyTextWatcher implements TextWatcher {


        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
