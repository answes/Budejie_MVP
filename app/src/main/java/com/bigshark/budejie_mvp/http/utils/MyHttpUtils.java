package com.bigshark.budejie_mvp.http.utils;

import android.text.TextUtils;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bigshark.budejie_mvp.BaseApplication;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by bigShark on 2016/5/29.
 */
public class MyHttpUtils {
    // 测试用的
    public static final String URL_STR = "http://192.168.57.1:8080/Dream_4_23_PhoneGapServer/PhoneGapServlet";

    public static String get(String urlStr) {
        String result = null;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setReadTimeout(5000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            if (connection.getResponseCode() == 200) {
                InputStream inStream = connection.getInputStream();
                result = new String(StreamTool.readInputStream(inStream));
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 测试用的
     *
     * @param urlStr
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    public static String post(String urlStr, String username, String password)
            throws Exception {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("username", username);
        paramMap.put("password", password);
        return post(urlStr, paramMap);
    }

    /**
     * post请求
     *
     * @param urlStr
     * @param paramMap
     * @return
     * @throws Exception
     */
    public static String post(String urlStr, Map<String, Object> paramMap)
            throws Exception {
        StringBuffer sb = null;
        //拼接参数
        StringBuilder params = new StringBuilder();
        int i = 0;
        for (String key : paramMap.keySet()) {
            Object value = paramMap.get(key);
            params.append(key);
            params.append("=");
            params.append(value);
            if (i < paramMap.size() - 1) {
                params.append("&");
            }
            i++;
        }
        //创建请求地址
        URL url = new URL(urlStr);
        //打开连接
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        // 设置参数
        httpConn.setDoOutput(true); // 需要输出
        httpConn.setDoInput(true); // 需要输入
        httpConn.setUseCaches(false); // 不允许缓存
        httpConn.setRequestMethod("POST"); // 设置POST方式连接
        // 设置请求属性
        httpConn.setRequestProperty("Charset", "UTF-8");
        httpConn.setRequestProperty("Authorization", "APPCODE f70bc37e0af049ff881c8287121ae458");
        // 连接,也可以不用明文connect，使用下面的httpConn.getOutputStream()会自动connect
        httpConn.connect();
        // 建立输入流，向指向的URL传入参数
        DataOutputStream dos = new DataOutputStream(httpConn.getOutputStream());
        dos.writeBytes(params.toString());
        dos.flush();
        dos.close();
        // 获得响应状态
        int resultCode = httpConn.getResponseCode();
        for (int j = 0; j < httpConn.getHeaderFields().size(); j++) {
            Log.e(TAG, "headers: "+ httpConn.getHeaderField(j));
        }

        Log.e(TAG, "post: "+ resultCode);
        sb = new StringBuffer();
        if (HttpURLConnection.HTTP_OK == resultCode) {
            //解析服务器返回的数据
            String readLine = new String();
            BufferedReader responseReader = new BufferedReader(
                    new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
            while ((readLine = responseReader.readLine()) != null) {
                sb.append(readLine).append("\n");
            }
            responseReader.close();
            return sb.toString();
        }
        return null;
    }


    public static String postAddHeader(String urlStr, final Map<String, String> paramMap){

        HeaderStringRequest postsr = new HeaderStringRequest(Request.Method.POST, urlStr, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e(TAG, "onResponse: " + s );
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(TAG, "onResponse: +++" + volleyError.getLocalizedMessage() );
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return paramMap;
            }
        };
        postsr.setTag("illgal");
        BaseApplication.getHttpQueues().add(postsr);
        return null;
    }

    public static String getAliAlligal(String urlStr, final Map<String, String> paramMap){
        String host = "http://ali-carlaw.showapi.com";
        String path = "/break-rules";
        String method = "POST";
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "APPCODE f70bc37e0af049ff881c8287121ae458");
        Map<String, String> querys = new HashMap<>();
        Map<String, String> bodys = new HashMap<>();
        bodys.put("carCode", paramMap.get("carCode"));
        bodys.put("carEngineCode", paramMap.get("carLaunchNum"));
        bodys.put("carNumber", paramMap.get("carNumber"));
        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            //获取response的body
            String json = EntityUtils.toString(response.getEntity());
                return json;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    public interface OnHttpResultListener {
        public void onResult(String result);
    }
}
