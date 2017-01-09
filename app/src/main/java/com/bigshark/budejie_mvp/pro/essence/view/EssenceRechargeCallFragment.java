package com.bigshark.budejie_mvp.pro.essence.view;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.alibaba.fastjson.JSONObject;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bigshark.budejie_mvp.BaseApplication;
import com.bigshark.budejie_mvp.R;
import com.bigshark.budejie_mvp.bean.CallType;
import com.bigshark.budejie_mvp.bean.PhoneRecharge;
import com.bigshark.budejie_mvp.http.utils.HeaderStringRequest;
import com.bigshark.budejie_mvp.http.utils.JSONUtil;
import com.bigshark.budejie_mvp.pro.base.view.BaseFragment;
import com.bigshark.budejie_mvp.pro.essence.view.adapter.CallTypeAdapter;
import com.bigshark.budejie_mvp.utils.CustomProgress;
import com.bigshark.budejie_mvp.utils.SpaceItemDecoration;
import com.bigshark.budejie_mvp.utils.ToastUtil;
import com.bigshark.budejie_mvp.utils.Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/** 话费充值
 * Created by bigShark on 2016/10/20.
 */

public class EssenceRechargeCallFragment extends BaseFragment {
    private int type = 0;
    private String mTitle;

    private RecyclerView callType;
    private CallTypeAdapter adapter;
    private ImageView contacts;
    private EditText phoneNum;

    private List<CallType> typeTestData;



    public void setType(int type) {
        this.type = type;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }
    @Override
    public int getContentView() {
        return R.layout.fragment_essence_convenience_rechargecall;
    }

    @Override
    public void initContentView(View viewContent) {

        contacts = (ImageView)viewContent.findViewById(R.id.iv_contacts);
        phoneNum = (EditText) viewContent.findViewById(R.id.et_phNum);

        callType = (RecyclerView) viewContent.findViewById(R.id.rlv_call);
        callType.setLayoutManager(new GridLayoutManager(getContext(),3));
        callType.addItemDecoration(new SpaceItemDecoration(15));
          initTestData();
        adapter = new CallTypeAdapter(getContext(),typeTestData);
        callType.setAdapter(adapter);
        adapter.setOnItemClickListener(new CallTypeAdapter.ItemOnClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                //ToastUtil.showToast(getContext(),"");
                if(Util.isMobileNO(phoneNum.getText().toString().trim())){
                    recharge(phoneNum.getText().toString().trim(),typeTestData.get(postion).getPrice());
                }else{
                    ToastUtil.showToast(getContext(),"请输入正确的手机号码");
                }
            }
        });

        //跳转联系人
        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://contacts/people");
                Intent intent = new Intent(Intent.ACTION_PICK, uri);
                startActivityForResult(intent, 0);
            }
        });

    }

    private void recharge(final String phone, final String cardNum){
        CustomProgress.show(getContext(),"正在充值请稍后...",true,null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                postAddHeader(phone,cardNum,onResultListener);
            }
        }).start();

    }

    private String url = "http://op.juhe.cn/ofpay/mobile/onlineorder";

    public  void postAddHeader(final String phoneno,final String cardnum ,final OnResultListener onResultListener){

        StringRequest postsr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                onResultListener.result(s);
                CustomProgress.dismissProgess();
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                CustomProgress.dismissProgess();
                Log.e(TAG, "onResponse: +++" + volleyError.getLocalizedMessage() );
                ToastUtil.showToast(getContext() ,"充值失败");
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> paramMap = new HashMap<>();
                paramMap.put("phoneno",phoneno);
                paramMap.put("cardnum",cardnum);
                paramMap.put("orderid",new Date()+cardnum);
                paramMap.put("key","APPKEY");
                paramMap.put("sign","MD5");
                return paramMap;
            }
        };
        postsr.setTag("recharge");
        BaseApplication.getHttpQueues().add(postsr);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data==null) return;
        Uri uri=data.getData();
        String[] contacts=getPhoneContacts(uri);
        phoneNum.setText(contacts[1]);
    }

    private String[] getPhoneContacts(Uri uri){
        String[] contact=new String[2];
        //得到ContentResolver对象
        ContentResolver cr = getContext().getContentResolver();
        //取得电话本中开始一项的光标
        Cursor cursor=cr.query(uri,null,null,null,null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
            //取得联系人姓名
            int nameFieldColumnIndex=cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            contact[0]=cursor.getString(nameFieldColumnIndex);
            //取得电话号码
            String ContactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            Cursor phone = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + ContactId, null, null);
            if(phone != null){
                phone.moveToFirst();
                contact[1] = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            }
            phone.close();
            cursor.close();
        }
        else
        {
            return null;
        }
        return contact;
    }

   private String[] moenys = new String[] {"10","20","30","50","100","300"};
    private String[] youhui = new String[] {"9.82","19.7","29.5","49.2","99","298"};

    private void initTestData() {
        typeTestData = new ArrayList<>();
        for(int i=0;i<6; i++){
            CallType call = new CallType();
            call.setPrice(moenys[i]);
            call.setDiscountedPrice(youhui[i].concat("元"));
            typeTestData.add(call);
        }
    }

    private OnResultListener onResultListener =  new OnResultListener(){
        @Override
        public void result(String s) {

//            PhoneRecharge  phoneRecharge = JSONUtil.getObject(s,PhoneRecharge.class);
//            if(phoneRecharge.getResult() != null){
//                phoneRecharge.getResult();
//            }
            JSONObject json= JSONObject.parseObject(s);
            JSONObject jsonBody = json.getJSONObject("result");
            if(jsonBody.getJSONObject("game_state").toString().equals("0")){
                ToastUtil.showToast(getContext() ,"正在充值");
            }else if(jsonBody.getJSONObject("game_state").toString().equals("1")){
                ToastUtil.showToast(getContext() ,"充值成功");
            }
            else if(jsonBody.getJSONObject("game_state").toString().equals("9")){
                ToastUtil.showToast(getContext() ,"撤销充值");
            }else{
                ToastUtil.showToast(getContext() ,json.getJSONObject("reason").toString());
            }
        }
    };

    interface  OnResultListener{
        void result(String s);
    }
}
