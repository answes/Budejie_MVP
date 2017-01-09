package com.bigshark.budejie_mvp.http.utils;

import android.os.AsyncTask;

import com.bigshark.budejie_mvp.http.IHttpCommand;
import com.bigshark.budejie_mvp.http.IRequestParam;

/**
 * Created by bigShark on 2016/5/29.
 */
public class HttpTask extends AsyncTask<String, Void, String> {

    private String url;
    private IRequestParam requestParam;
    private MyHttpUtils.OnHttpResultListener onHttpResultListener;
    private IHttpCommand httpCommand;

    public HttpTask(String url, IRequestParam requestParam, IHttpCommand httpCommand,
                    MyHttpUtils.OnHttpResultListener onHttpResultListener) {
        this.url = url;
        this.requestParam = requestParam;
        this.httpCommand = httpCommand;
        this.onHttpResultListener = onHttpResultListener;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            return httpCommand.execute(url, requestParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        if (this.onHttpResultListener != null) {
            this.onHttpResultListener.onResult(s);
        }
    }
}
