package com.sainti.blackcard.my.activity;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseBean;
import com.sainti.blackcard.base.BaseNoTitleActivity;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.mtuils.MD5Factory;
import com.sainti.blackcard.mtuils.ToastUtils;

public class ChangeActivity extends BaseNoTitleActivity implements OnNetResultListener {
    private ImageView changeback;
    private TextView tvright;
    private EditText etold;
    private EditText etnew;
    private EditText etright;
    private String old = "";
    private String newpws = "";
    private String right = "";
    private Context mContext;
    private String md5Psd = "";
    private String md6Psd = "";
    private String md7Psd = "";


    @Override
    public int setLayout() {
        return R.layout.activity_change;
    }

    @Override
    protected void initView() {

        changeback = (ImageView) findViewById(R.id.changeback);
        etold = (EditText) findViewById(R.id.etold);
        etnew = (EditText) findViewById(R.id.etnew);
        etright = (EditText) findViewById(R.id.etright);
        tvright = (TextView) findViewById(R.id.tvright);
        mContext = ChangeActivity.this;
        changeback.setOnClickListener(mListener);
        tvright.setOnClickListener(mListener);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {

    }

    private View.OnClickListener mListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.changeback:
                    finish();
                    break;
                case R.id.tvright:
                    old = etold.getText().toString();
                    newpws = etnew.getText().toString();
                    right = etright.getText().toString();
                    if (old.equals("")) {
                        ToastUtils.show(mContext, "请输入原密码");
                    } else if (newpws.equals("")) {
                        ToastUtils.show(mContext, "请输入新密码");
                    } else if (right.equals("")) {
                        ToastUtils.show(mContext, "请输入确认密码");
                    } else if (!newpws.equals(right)) {
                        ToastUtils.show(mContext, "两次输入新密码不一致");
                    } else {
                        change();
                    }
                    break;
                default:
                    break;
            }
        }
    };

    public void change() {
        MD5Factory md5Factory = new MD5Factory(old);
        md5Factory.digestStr();
        md5Psd = md5Factory.getResult().toLowerCase();
        MD5Factory md6Factory = new MD5Factory(newpws);
        md6Factory.digestStr();
        md6Psd = md6Factory.getResult().toLowerCase();
        MD5Factory md7Factory = new MD5Factory(right);
        md7Factory.digestStr();
        md7Psd = md6Factory.getResult().toLowerCase();
        TurnClassHttp.pwd_update(md5Psd, md6Psd, md7Psd, 1, this, this);
    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        Gson gson = GsonSingle.getGson();
        BaseBean baseBean = gson.fromJson(stringResult, BaseBean.class);
        if (baseBean.getResult().equals("1")) {
            ToastUtils.show(mContext, "修改密码成功");
            finish();
        } else {
            ToastUtils.show(this, baseBean.getMsg());
        }
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {

    }
}
