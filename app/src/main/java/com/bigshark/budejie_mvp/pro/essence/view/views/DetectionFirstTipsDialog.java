package com.bigshark.budejie_mvp.pro.essence.view.views;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import com.bigshark.budejie_mvp.R;

/**
 * Created by bigShark on 2016/10/11.
 */

public class DetectionFirstTipsDialog  extends Dialog {
    private Button btAgree;
    private Context context;
    private Button btCancel;

    private OnAgreeListenner onAgreeListenner;
    private OnCancleListenner onCancelLsitener;

    public DetectionFirstTipsDialog(Context context) {
        super(context, R.style.CustomDialogStyle);
        this.context = context;
        this.setCancelable(false);
        initDialog();
        setParams();
    }

    private void setParams()
    {
        WindowManager m = this.getWindow().getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽高
        WindowManager.LayoutParams p = getWindow().getAttributes(); // 获取对话框当前的参数值
        p.width = (int) (d.getWidth() * 0.85);
    }

    private void initDialog() {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_essence_detection_toast,null);
        setContentView(view, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        btAgree = (Button) view.findViewById(R.id.bt_agree);
        btCancel = (Button) view.findViewById(R.id.bt_cancel);
        btAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onAgreeListenner != null){
                    onAgreeListenner.agree();
                }
            }
        });
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onCancelLsitener != null) {
                    onCancelLsitener.cancel();
                }
            }
        });
    }
    public void setOnAgreeListener(OnAgreeListenner onAgreeListener){
        this.onAgreeListenner = onAgreeListener;
    }
    public void setCancelListener(OnCancleListenner onCancelListner){
        this.onCancelLsitener = onCancelListner;
    }
    public interface  OnAgreeListenner{
        void agree();
    }
    public interface  OnCancleListenner{
        void cancel();
    }

}
