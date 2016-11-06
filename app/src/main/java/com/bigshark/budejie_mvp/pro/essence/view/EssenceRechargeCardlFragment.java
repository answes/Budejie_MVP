package com.bigshark.budejie_mvp.pro.essence.view;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bigshark.budejie_mvp.R;
import com.bigshark.budejie_mvp.pro.base.view.BaseFragment;
import com.bigshark.budejie_mvp.utils.ToastUtil;

/** 油卡充值
 * Created by bigShark on 2016/10/20.
 */

public class EssenceRechargeCardlFragment  extends BaseFragment implements View.OnClickListener {
    private int type = 1;
    private String mTitle;

    private EditText cardName;
    private EditText cardNum;
    private EditText price;
    private Button  recharge;



    public void setType(int type) {
        this.type = type;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }
    @Override
    public int getContentView() {
        return R.layout.fragment_essence_convenience_rechargecard;
    }

    @Override
    public void initContentView(View viewContent) {
        findAndBind(viewContent);

    }

    private void findAndBind(View viewContent) {
        cardName = (EditText) viewContent.findViewById(R.id.et_cardName);
        cardNum = (EditText) viewContent.findViewById(R.id.et_cardNum);
        price = (EditText) viewContent.findViewById(R.id.et_price);
        recharge = (Button) viewContent.findViewById(R.id.bt_recharge);
        recharge.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ToastUtil.showToast(getContext(),"付款成功");
    }
}
