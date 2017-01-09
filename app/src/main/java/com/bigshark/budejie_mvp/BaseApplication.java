package com.bigshark.budejie_mvp;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.Volley;

/**
 * Created by bigShark on 2017/1/1.
 */

public class BaseApplication extends Application {
    public static RequestQueue queues;

    @Override
    public void onCreate() {
        super.onCreate();
        queues = Volley.newRequestQueue(getApplicationContext(), (HttpStack) null);
    }

    public static RequestQueue getHttpQueues() {
        return queues;
    }

}
