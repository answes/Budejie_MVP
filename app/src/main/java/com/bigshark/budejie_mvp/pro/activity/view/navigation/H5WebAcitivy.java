package com.bigshark.budejie_mvp.pro.activity.view.navigation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.bigshark.budejie_mvp.R;
import com.bigshark.budejie_mvp.mvp.presenter.impl.MvpBasePresenter;
import com.bigshark.budejie_mvp.pro.base.view.BaseActivity;

import java.io.File;

/**
 * Created by bigShark on 2016/10/24.
 */

public class H5WebAcitivy extends BaseActivity {
    private static final String APP_CACAHE_DIRNAME = "/webcache";

    private WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h5web);
        initToolBar();
        webView = (WebView) findViewById(R.id.wv_content);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://www.digitaling.com/projects/14604.html");
        webView.setWebChromeClient(new MyWebViewClient());
    }

    class MyWebViewClient extends WebChromeClient
    {
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {
            view.loadUrl(url);
            return true;
        }

    }


    private void initToolBar() {
        LinearLayout viewContent = (LinearLayout) findViewById(R.id.ll_content);
        ActivityListNavigationBuilder builder = new ActivityListNavigationBuilder(this);
        builder.setLeftIcon(R.drawable.left_back)
                .setLeftIconOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();;
                    }
                });
        builder.setTitle("网页");
        builder.createAndBind( viewContent);
    }

    @Override
    public MvpBasePresenter bindPresenter() {
        return null;
    }

    public static void openH5WebAcitivy(Activity activity){
        Intent intent = new Intent(activity,H5WebAcitivy.class);
        activity.startActivity(intent);
    }

    /**
     * 清除WebView缓存
     */
    public void clearWebViewCache()
    {
        // 清理Webview缓存数据库
        try
        {
            deleteDatabase("webview.db");
            deleteDatabase("webviewCache.db");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        // WebView 缓存文件
        File appCacheDir = new File(getFilesDir().getAbsolutePath()
                + APP_CACAHE_DIRNAME);

        File webviewCacheDir = new File(getCacheDir().getAbsolutePath()
                + "/webviewCache");

        // 删除webview 缓存目录
        if (webviewCacheDir.exists())
        {
            deleteFile(webviewCacheDir);
        }
        // 删除webview 缓存 缓存目录
        if (appCacheDir.exists())
        {
            deleteFile(appCacheDir);
        }
    }

    /**
     * 删除指定文件夹里的文件
     * @param file
     */
    private void deleteFile(File file)
    {

        if (file.exists())
        {
            if (file.isFile())
            {
                file.delete();
            }
            else if (file.isDirectory())
            {
                File files[] = file.listFiles();
                for (int i = 0; i < files.length; i++)
                {
                    deleteFile(files[i]);
                }
            }
            file.delete();
        }
        else
        {
            Log.e("TAG", "delete file no exists " + file.getAbsolutePath());
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        clearWebViewCache();
    }
}
