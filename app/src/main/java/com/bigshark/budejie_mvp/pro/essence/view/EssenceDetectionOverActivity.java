package com.bigshark.budejie_mvp.pro.essence.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigshark.budejie_mvp.R;
import com.bigshark.budejie_mvp.pro.essence.view.navigation.EssenceActivityNavigationBuilder;
import com.pitt.library.fresh.FreshDownloadView;

public class EssenceDetectionOverActivity extends AppCompatActivity {

    private FreshDownloadView freshDownloadView;
    private TextView tv;

    private final int FLAG_SHOW_OK = 10;
    private final int FLAG_SHOW_ERROR = 11;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int progress = (int) msg.obj;
            freshDownloadView.upDateProgress(progress);
            if(progress == 100){
                tv.setVisibility(View.VISIBLE);
            }
            switch (msg.what) {
                case FLAG_SHOW_OK:
                    break;
                case FLAG_SHOW_ERROR:
                    freshDownloadView.showDownloadError();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_essence_detection_over);
        initToolbar();
        freshDownloadView = (FreshDownloadView) findViewById(R.id.pitt);
        tv = (TextView) findViewById(R.id.tv);
        initData();
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 10; i <= 100; i++) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Message message = Message.obtain();
                    message.obj = i;
                    handler.sendMessage(message);
                }
            }
        }).start();
    }

    private void initToolbar() {
        LinearLayout contentLayout = (LinearLayout) findViewById(R.id.ll_content);
        EssenceActivityNavigationBuilder bar = new EssenceActivityNavigationBuilder(this);
        bar.setTitle("预约结果").setLeftIcon(R.drawable.left_back)
                .setLeftIconOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
        bar.createAndBind(contentLayout);
    }

    public static void openEssenceDetectionOverActivity(Activity activity){
        activity.startActivity(new Intent(activity,EssenceDetectionOverActivity.class));
    }
}
