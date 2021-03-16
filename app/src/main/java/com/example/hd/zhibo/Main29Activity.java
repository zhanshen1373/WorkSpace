package com.example.hd.zhibo;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hd.myapplication.R;

import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.interfaces.IVLCVout;

import java.util.ArrayList;

import wseemann.media.FFmpegMediaMetadataRetriever;

public class Main29Activity extends AppCompatActivity implements View.OnClickListener {

    private SurfaceView surfaceView;
    private LibVLC mLibVLC = null;
    private MediaPlayer mMediaPlayer = null;
    private String mUrl = "http://ivi.bupt.edu.cn/hls/cctv1hd.m3u8";
    private IVLCVout vlcVout;
    private ZdyRelativeLayout syView;
    private int height;
    private int width;
    private LinearLayout exitfullping_ll;
    private RelativeLayout enterfullping_rl;
    private ImageView exitfullping_iv;
    private ImageView enterfullping_iv;
    private TextView maskTv;
    private LinearLayout rotateLinearlayout;
    private int exitfullping_llheight;
    private int enterfullping_rlheight;
    private int widthPixels;
    private int heightPixels;
    private TextView topleftTv;
    private TextView toprightTv;
    private TextView bottomleftTv;
    private TextView bottomrightTv;
    private TextView centerTv;
    private Handler hd = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:

                    //隐藏掉条目
                    ObjectAnimator animator = ObjectAnimator.ofFloat(exitfullping_ll, "translationY", 0, -exitfullping_llheight);
                    animator.setDuration(2000);
                    animator.start();

                    ObjectAnimator animatorp = ObjectAnimator.ofFloat(enterfullping_rl, "translationY", 0, enterfullping_rlheight);
                    animatorp.setDuration(2000);
                    animatorp.start();
                    break;

            }
            super.handleMessage(msg);
        }
    };
    private int statusBarHeight;
    private ViewGroup.LayoutParams layoutParams;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        statusBarHeight = getStatusBarHeight(this);

        setContentView(R.layout.activity_main29);




        surfaceView = (SurfaceView) findViewById(R.id.camera_preview);
        syView = (ZdyRelativeLayout) findViewById(R.id.sy_view);
        exitfullping_ll = findViewById(R.id.exitfullping_ll);
        enterfullping_rl = findViewById(R.id.enterfullping_rl);
        exitfullping_iv = findViewById(R.id.exitfullping_iv);
        enterfullping_iv = findViewById(R.id.enterfullping_iv);
//        maskTv=findViewById(R.id.masktv);
        topleftTv = findViewById(R.id.topleft);
        toprightTv = findViewById(R.id.topright);
        bottomleftTv = findViewById(R.id.bottomleft);
        bottomrightTv = findViewById(R.id.bottomright);
        centerTv = findViewById(R.id.center);
//        rotateLinearlayout=findViewById(R.id.rotatelinear);
        preInit();
        init(surfaceView);
        initEvent();
        syView.setHandler(hd);
        hd.sendEmptyMessageDelayed(0, 2000);


        //加载视频第一帧
//        Bitmap bitmap = getNetVideoBitmap(mUrl);
//        qqqqimageview.setImageBitmap(bitmap);//对应的ImageView赋值图片

    }

    private void addTv(LinearLayout li, int num) {
        if (num == 2 || num == 4 || num == 5)
            num = 1;
        if (num == 3)
            num = 2;
        if (num == 6)
            num = 0;
        for (int i = 0; i <= num; i++) {
            TextView tv = new TextView(Main29Activity.this);
            tv.setLayoutParams(new LinearLayout.LayoutParams(width / (num + 1), LinearLayout.LayoutParams.MATCH_PARENT));
            tv.setText("哈哈");
            tv.setGravity(Gravity.CENTER);
            tv.setTextColor(Color.RED);
            li.addView(tv);
        }

    }


    private void preInit() {
        final ArrayList<String> args = new ArrayList<>();
        args.add("-vvv");
        mLibVLC = new LibVLC(this, args);


        Media media = new Media(mLibVLC, Uri.parse(mUrl));
        media.setHWDecoderEnabled(true, true);
        media.addOption(":no-audio");
        media.addOption(":network-caching=150");
        media.addOption(":file-caching=150");
        media.addOption(":sout-mux-caching=150");
        media.addOption(":live-caching=150");
        mMediaPlayer = new MediaPlayer(media);
        mMediaPlayer.setScale(0);

        media.release();

        mMediaPlayer.setEventListener(new MediaPlayer.EventListener() {
            @Override
            public void onEvent(MediaPlayer.Event event) {

                Log.e("wev",event.type+"");
                if (event.type == MediaPlayer.Event.EncounteredError) {
                    Toast.makeText(Main29Activity.this, "网络异常", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }

    @Override
    protected void onRestart() {
        super.onRestart();

        if (!vlcVout.areViewsAttached()){
            SurfaceHolder holder = surfaceView.getHolder();
            holder.setKeepScreenOn(true);
            vlcVout.setVideoSurface(holder.getSurface(), holder);
            if (layoutParams!=null){
                mMediaPlayer.setAspectRatio(layoutParams.width + ":" + layoutParams.height);
                vlcVout.setWindowSize(layoutParams.width, layoutParams.height);
            }else{
                mMediaPlayer.setAspectRatio(width + ":" + height);
                vlcVout.setWindowSize(width,height);
            }
            vlcVout.attachViews();

        }



    }

    public void init(SurfaceView sView) {

        SurfaceHolder holder = sView.getHolder();
        holder.setKeepScreenOn(true);
        vlcVout = mMediaPlayer.getVLCVout();
        vlcVout.setVideoSurface(holder.getSurface(), sView.getHolder());


        sView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                height = sView.getHeight();
                width = sView.getWidth();
                sView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mMediaPlayer.setAspectRatio(width + ":" + height);
                vlcVout.attachViews();
                vlcVout.setWindowSize(width, height);

            }
        });

    }

    private void initEvent() {


        exitfullping_ll.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                exitfullping_llheight = exitfullping_ll.getHeight();

                exitfullping_ll.getViewTreeObserver().removeOnGlobalLayoutListener(this);

            }
        });
        enterfullping_rl.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                enterfullping_rlheight = enterfullping_rl.getHeight();

                enterfullping_rl.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

        exitfullping_iv.setOnClickListener(this);
        enterfullping_iv.setOnClickListener(this);
        syView.setOnClickListener(this);


    }


    public static Bitmap getNetVideoBitmap(String videoUrl) {
        Bitmap bitmap = null;
        try {
            FFmpegMediaMetadataRetriever mmr = new FFmpegMediaMetadataRetriever();
            mmr.setDataSource(videoUrl);
            mmr.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_ALBUM);
            mmr.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_ARTIST);
            bitmap = mmr.getFrameAtTime(); // frame at 2 seconds
            mmr.release();
        } catch (Exception e) {
            Log.e("aa", e.getMessage());
        }


        return bitmap;
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (mMediaPlayer != null&&!mMediaPlayer.isPlaying())
            mMediaPlayer.play();

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mMediaPlayer != null) {
            mMediaPlayer.pause();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMediaPlayer != null) {
            mMediaPlayer.pause();
            mMediaPlayer.stop();
            vlcVout.detachViews();
            mMediaPlayer = null;
            mLibVLC.release();
            mLibVLC = null;
        }

        if (hd != null) {
            hd.removeCallbacksAndMessages(null);
            hd = null;
        }

    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.exitfullping_iv) {
            if (mMediaPlayer != null) {
                mMediaPlayer.pause();
                mMediaPlayer.stop();
                vlcVout.detachViews();
                mMediaPlayer = null;
                mLibVLC.release();
                mLibVLC = null;
            }
            finish();
        } else if (i == R.id.enterfullping_iv) {
            //判断当前屏幕方向
            if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                //切换竖屏
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            } else {
                //切换横屏
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }
        } else if (i == R.id.sy_view) {

            float y = getvh(exitfullping_ll);
            if (y <= -(exitfullping_llheight - statusBarHeight)) {
                showLayoutView();
            }

        }
    }

    private float getvh(View view) {
        int[] location = new int[2];
        view.getLocationInWindow(location);
        return location[1];
    }

    private void showLayoutView() {


        //展示出上下条目
        ObjectAnimator animator = ObjectAnimator.ofFloat(exitfullping_ll, "translationY", -exitfullping_llheight, 0);
        animator.setDuration(2000);
        animator.start();


        ObjectAnimator animatorp = ObjectAnimator.ofFloat(enterfullping_rl, "translationY", enterfullping_rlheight, 0);
        animatorp.setDuration(2000);
        animatorp.start();

    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //变成横屏了
            GetScreenWH(newConfig.orientation);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            //变成竖屏了
            GetScreenWH(newConfig.orientation);
        }

    }


    private void GetScreenWH(int fx) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);


        widthPixels = displayMetrics.widthPixels;
        heightPixels = displayMetrics.heightPixels;

        layoutParams = surfaceView.getLayoutParams();
        layoutParams.width = widthPixels;
        if (fx == Configuration.ORIENTATION_LANDSCAPE) {
            //横屏
            layoutParams.height = heightPixels - statusBarHeight;
        } else if (fx == Configuration.ORIENTATION_PORTRAIT) {
            //竖屏
            layoutParams.height = height;
        }
        surfaceView.setLayoutParams(layoutParams);
        syView.setLayoutParams(layoutParams);

        mMediaPlayer.setAspectRatio(layoutParams.width + ":" + layoutParams.height);
        vlcVout.setWindowSize(layoutParams.width, layoutParams.height);


    }

    /**
     * 获取状态栏高度
     *
     * @param context
     * @return
     */
    public int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }

}
