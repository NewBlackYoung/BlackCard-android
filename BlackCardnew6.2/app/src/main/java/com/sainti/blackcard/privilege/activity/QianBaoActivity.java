package com.sainti.blackcard.privilege.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sainti.blackcard.R;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.mtuils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class QianBaoActivity extends AppCompatActivity implements OnNetResultListener, View.OnClickListener {
    private ImageView friendback;
    private TextView tv_money;
    private RelativeLayout rela_jilu, rela_tixian;
    private Context context;
    private double money = 0;
    private String name = "";
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qian_bao);
        context = this;
        friendback = (ImageView) findViewById(R.id.friendback);
        tv_money = (TextView) findViewById(R.id.tv_money);
      /*  Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/ZoomlaYasong-A001.ttf");
        tv_money.setTypeface(typeFace);*/
        rela_jilu = (RelativeLayout) findViewById(R.id.rela_jilu);
        rela_tixian = (RelativeLayout) findViewById(R.id.rela_tixian);
        friendback.setOnClickListener(this);
        rela_jilu.setOnClickListener(this);
        rela_tixian.setOnClickListener(this);
        getData();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.friendback:
                finish();
                break;
            case R.id.rela_jilu:
                intent = new Intent(context, TixianjiluActivity.class);
                startActivity(intent);
                break;
            case R.id.rela_tixian:
               if (!tv_money.getText().toString().equals("¥0.00")) {
                    intent = new Intent(context, TiXianActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("money", money);
                    startActivityForResult(intent, 100);
                } else {
                    ToastUtils.show(context, "抱歉，您暂无可提现金额");
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 200) {
            getData();
        }
    }

    private void getData() {
        TurnClassHttp.getMoney("1", "", "", "", "",1,this,this);
    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        try {
            JSONObject object = new JSONObject(stringResult);
            if (object.getString("result").equals("1")) {
                JSONObject data = object.getJSONObject("data");
                money = data.getDouble("money");
                name = data.getString("user_name");
                tv_money.setText("¥" + data.getString("money"));
            } else {
                ToastUtils.show(context, "网络错误，请稍后再试");
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
