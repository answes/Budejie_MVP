package com.bigshark.budejie_mvp.pro.essence.view;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.bigshark.budejie_mvp.R;
import com.bigshark.budejie_mvp.mvp.presenter.impl.MvpBasePresenter;
import com.bigshark.budejie_mvp.pro.base.view.BaseActivity;
import com.bigshark.budejie_mvp.pro.essence.view.navigation.EssenceActivityNavigationBuilder;
import com.bigshark.budejie_mvp.pro.essence.view.navigation.EssenceNavigationBuilder;
import com.bigshark.budejie_mvp.pro.essence.view.views.DetectionFirstTipsDialog;
import com.bigshark.budejie_mvp.utils.BitmapCache;
import com.bigshark.budejie_mvp.utils.MapUtils;
import com.bigshark.budejie_mvp.utils.ToastUtil;
import com.bigshark.budejie_mvp.utils.VolleyUtils;

import java.net.URISyntaxException;


/**
 * 汽车检测
 * Created by luyanhong on 16/10/9.
 */
public class EssenceDetectionActivity extends BaseActivity implements View.OnClickListener {
    private SharedPreferences preferences;

   // private NetworkImageView businessPhoto;
    private TextView businessName;
    private TextView businessItdction;
    private TextView businessDistance;
    private ImageView map;

    private ImageView payIv;
    private ImageView reservationIv;
    private Button payBt;
    private Button reservationBt;

    private boolean checkPay = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_essence_detection);
        findOnBindView();
        initToolbar();
        firstIn();
    }

    private void initToolbar() {
        LinearLayout contentLayout = (LinearLayout) findViewById(R.id.ll_content);
        EssenceActivityNavigationBuilder bar = new EssenceActivityNavigationBuilder(this);
        bar.setTitle("汽车检测站").setLeftIcon(R.drawable.left_back)
                .setLeftIconOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
        bar.createAndBind(contentLayout);
    }

    private void findOnBindView() {
       // businessPhoto = (NetworkImageView) findViewById(R.id.iv_business_photo);
        businessName = (TextView) findViewById(R.id.tv_name);
        businessItdction = (TextView) findViewById(R.id.tv_introduction);
        map = (ImageView) findViewById(R.id.iv_map);
        businessDistance = (TextView) findViewById(R.id.tv_distance);

        payIv = (ImageView) findViewById(R.id.iv_pay);
        reservationIv = (ImageView) findViewById(R.id.iv_reservation);
        payBt = (Button) findViewById(R.id.bt_pay);
        reservationBt = (Button) findViewById(R.id.bt_reservation);

        payIv.setOnClickListener(this);
        reservationIv.setOnClickListener(this);
        payBt.setOnClickListener(this);
        reservationBt.setOnClickListener(this);
        map.setOnClickListener(this);

      //  VolleyUtils.loadImage(this, businessPhoto, "http://1.img.home77.com/G1/M00/18/56/dB9kMVPYSBmATYrUAAbDn2CAKPE213.jpg");

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_pay:
                break;
            case R.id.bt_reservation:
                if(checkPay) {
//                    new MaterialDialog.Builder(EssenceDetectionActivity.this)
//                            .content("预约成功")
//                            .positiveText("确定")
//                            .show();
                    EssenceDetectionOverActivity.openEssenceDetectionOverActivity(this);
                    reservationBt.setText("您已预约，前面有0人等待");
                    checkPay= false;
                }else{
                    ToastUtil.showToast(this,"您已经预约成功");
                }
                break;
            case R.id.iv_pay:
//                clearCheck();
//                checkPay = true;
//                payIv.setImageResource(R.drawable.check_true);
//                payBt.setBackgroundResource(R.drawable.essence_check_true_bg);
                break;
            case R.id.iv_reservation:   //选择到店支付
//                clearCheck();
//                reservationIv.setImageResource(R.drawable.check_true);
//                reservationBt.setBackgroundResource(R.drawable.essence_check_true_bg);
                break;
            case R.id.iv_map:   //启动地图
                //ToastUtil.showToast(this, "跳转地图进行导航");
//            if(MapUtils.isAvilible(this,"com.baidu.BaiduMap")){//传入指定应用包名
//                try {
////                          intent = Intent.getIntent("intent://map/direction?origin=latlng:34.264642646862,108.95108518068|name:我家&destination=大雁塔&mode=driving®ion=西安&src=yourCompanyName|yourAppName#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
//                    Intent  intent = Intent.getIntent("intent://map/direction?" +
//                            //"origin=latlng:"+"34.264642646862,108.95108518068&" +   //起点  此处不传值默认选择当前位置
//                            "destination=latlng:"+34.264642646862+","+108.95108518068+"|name:我家"+        //终点
//                            "&mode=driving&" +          //导航路线方式
//                            "region=北京" +           //
//                            "&src=慧医#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
//                    this.startActivity(intent); //启动调用
//                } catch (URISyntaxException e) {
//
//                }
//            }else{//未安装
//                //market为路径，id为包名
//                //显示手机上所有的market商店
//              ToastUtil.showToast(this,"您还没有安装百度地图，请先安装");
//                Uri uri = Uri.parse("market://details?id=com.baidu.BaiduMap");
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                this.startActivity(intent);
//            }
                break;
        }
    }

    private void clearCheck() {
        checkPay = false;
        payIv.setImageResource(R.drawable.check_false);
        reservationIv.setImageResource(R.drawable.check_false);
        payBt.setBackgroundResource(R.drawable.essence_check_false_bg);
        reservationBt.setBackgroundResource(R.drawable.essence_check_false_bg);
    }

    /**
     * 判断是否是第一次进入这个功能，如果是讲将跳出这个提示，如果不是 则不用，
     * 当不同意的时候会退出这个功能
     */
    private void firstIn() {
        if (isOneStart()) {
            final DetectionFirstTipsDialog dialog = new DetectionFirstTipsDialog(this);
            dialog.setOnAgreeListener(new DetectionFirstTipsDialog.OnAgreeListenner() {
                @Override
                public void agree() {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("detecation", 5);
                    editor.commit();
                    dialog.dismiss();
                }
            });

            //取消退出窗口
            dialog.setCancelListener(new DetectionFirstTipsDialog.OnCancleListenner() {
                @Override
                public void cancel() {
                    dialog.dismiss();
                    EssenceDetectionActivity.this.finish();
                }
            });

            dialog.show();
        }
    }

    /**
     * 判断是否是第一次启动这个功能
     *
     * @return
     */
    private boolean isOneStart() {
        preferences = getSharedPreferences("detecation",
                MODE_WORLD_READABLE);
        int count = preferences.getInt("detecation", 0);
        if (count == 0) {
            return true;
        }
        return false;
    }

    @Override
    public MvpBasePresenter bindPresenter() {
        return null;
    }

    public static void openEssenceDetectionActivity(Activity context) {
        Intent intent = new Intent(context, EssenceDetectionActivity.class);
        context.startActivity(intent);
    }

    public static void openEssenceDetectionActivityForResult(Activity context, int reslutCode) {
        Intent intent = new Intent(context, EssenceDetectionActivity.class);
        context.startActivityForResult(intent, reslutCode);
    }
}
