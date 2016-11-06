package com.bigshark.budejie_mvp.pro.essence.view;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bigshark.budejie_mvp.R;
import com.bigshark.budejie_mvp.bean.CallType;
import com.bigshark.budejie_mvp.pro.base.view.BaseFragment;
import com.bigshark.budejie_mvp.pro.essence.view.adapter.CallTypeAdapter;
import com.bigshark.budejie_mvp.utils.SpaceItemDecoration;
import com.bigshark.budejie_mvp.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

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
                ToastUtil.showToast(getContext(),"选择支付");
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

    private void initTestData() {
        typeTestData = new ArrayList<>();
        for(int i=0;i<7; i++){
            CallType call = new CallType();
            call.setPrice("20");
            call.setDiscountedPrice("19.98元");
            typeTestData.add(call);
        }
    }
}
