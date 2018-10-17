package com.sainti.blackcard.privilege.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sainti.blackcard.R;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.widget.CashierInputFilter;

import org.json.JSONException;
import org.json.JSONObject;

public class TiXianActivity extends AppCompatActivity implements View.OnClickListener, OnNetResultListener {
    private String name = "";
    private double money = 0.0;
    private Context context;
    private ImageView friendback, iv_zixun;
    private TextView name1, tv_zongjine, tv_quanbu, tv_tixian;
    private EditText zhifubao, jine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ti_xian);
        context = this;
        name = getIntent().getStringExtra("name");
        money = getIntent().getDoubleExtra("money", 0.0);
        friendback = (ImageView) findViewById(R.id.friendback);
        iv_zixun = (ImageView) findViewById(R.id.iv_zixun);
        name1 = (TextView) findViewById(R.id.name1);
        tv_zongjine = (TextView) findViewById(R.id.tv_zongjine);
        tv_quanbu = (TextView) findViewById(R.id.tv_quanbu);
        tv_tixian = (TextView) findViewById(R.id.tv_tixian);
        zhifubao = (EditText) findViewById(R.id.zhifubao);
        jine = (EditText) findViewById(R.id.jine);
        InputFilter[] filters = {new CashierInputFilter()};
        jine.setFilters(filters);
        name1.setText(name);
        tv_zongjine.setText("当前可提现金额:" + money);
        friendback.setOnClickListener(this);
        tv_quanbu.setOnClickListener(this);
        tv_tixian.setOnClickListener(this);
    }

    private void setData() {
        TurnClassHttp.getMoney( "2", jine.getText().toString(), "支付宝", zhifubao.getText().toString(), name,1,this,this);


    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.friendback:
                finish();
                break;
            case R.id.tv_quanbu:
                jine.setText(money + "");
                break;
            case R.id.tv_tixian:
                if (zhifubao.getText().toString().length() != 0) {
                    if (jine.getText().toString().length() != 0) {
                        if (Double.parseDouble(jine.getText().toString()) <= money) {
                            setData();
                        } else {
                            ToastUtils.show(context, "抱歉，您的提现金额大于账户余额");
                        }
                    } else {
                        ToastUtils.show(context, "请输入提现金额");
                    }
                } else {
                    ToastUtils.show(context, "请输入支付宝账号");
                }
                break;
        }
    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        try {
            JSONObject object = new JSONObject(stringResult);
            if (object.getString("result").equals("1")) {
                ToastUtils.show(context, "提现成功");
                setResult(200);
                finish();
            } else {
                ToastUtils.show(context, object.getString("msg"));
            }
        } catch (JSONException e) {
            ToastUtils.show(context, "网络错误，请稍后再试");
            e.printStackTrace();
        }
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        ToastUtils.show(context, "网络错误，请稍后再试");
    }
}
