package p;

import android.util.Log;

import java.lang.ref.WeakReference;

public class vv {



    vv[] t;
    protected void t() {
        Log.e("q", "qqqq");
        t=new vv[2];

        //一个引用两对象
        Object o=new Object();
        o=new vv(1,2);
        //二个引用一对象
        Object t=new Object();

        WeakReference<Object> objectWeakReference = new WeakReference<>(t);

    }

    public void main(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                t();
            }
        }).start();
    }


    public vv(int a,int b) {
        this.a = a;
    }

    protected int a;

    protected static  class bb{

    }

}
