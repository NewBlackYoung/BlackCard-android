package com.sainti.blackcard.my.activity;

import android.content.Context;
import android.content.Intent;
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
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.widget.LoadingView;

public class ChangeTrainActivity extends BaseNoTitleActivity implements OnNetResultListener {

    private ImageView changetrainback;
    private TextView tvtrainbao;
    private EditText ettrain;
    private EditText ettrainpwd;
    private Intent intent;
    private Gson gson;
    private Context mContext;
    private LoadingView loadingView;
    private String car;

    @Override
    public int setLayout() {
        return R.layout.activity_change_train;
    }

    @Override
    protected void initView() {

        mContext = ChangeTrainActivity.this;
        changetrainback = (ImageView) findViewById(R.id.changetrainback);
        ettrain = (EditText) findViewById(R.id.ettrain);
        ettrainpwd = (EditText) findViewById(R.id.ettrainpwd);
        tvtrainbao = (TextView) findViewById(R.id.tvtrainbao);
        intent = getIntent();
    }

    @Override
    protected void initEvents() {

    }


    private View.OnClickListener mListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.changetrainback:
                    finish();
                    break;
                case R.id.tvtrainbao:
                    String car = ettrain.getText().toString();
                    String pwd = ettrainpwd.getText().toString();
                    if (car.equals("")) {
                        ToastUtils.show(mContext, "12306账号不能为空");
                    } else if (pwd.equals("")) {
                        ToastUtils.show(mContext, "密码不能为空");
                    } else {
                        changename(car, pwd);
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void initData() {
        loadingView =new LoadingView(this);
        car = intent.getStringExtra("car");
        if (car != null) {
            if (!car.equals("")) {
                ettrain.setText(car);
            }
        }
        changetrainback.setOnClickListener(mListener);
        tvtrainbao.setOnClickListener(mListener);
    }

    public void changename(final String car, String pwd) {
        loadingView.show();
        TurnClassHttp.train_update(car, pwd, 1, this, this);

    }


    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {

        gson = GsonSingle.getGson();
        BaseBean baseBean = gson.fromJson(stringResult, BaseBean.class);
        if (baseBean.getResult().equals("1")) {
            ToastUtils.show(mContext, "12306账号修改成功");
            intent = new Intent();
            intent.putExtra("car", car);
            setResult(15, intent);
            finish();
        } else {
            ToastUtils.show(this, "12306账号修改失败！请稍后尝试");
        }
        loadingView.dismiss();
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        loadingView.dismiss();
        ToastUtils.show(this, "12306账号修改失败！请稍后尝试");
    }
}
