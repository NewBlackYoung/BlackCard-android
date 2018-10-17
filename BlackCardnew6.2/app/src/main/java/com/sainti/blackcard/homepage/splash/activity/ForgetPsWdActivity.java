package com.sainti.blackcard.homepage.splash.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.widget.LoadingView;

import org.json.JSONException;
import org.json.JSONObject;

/*修改密码类*/
public class ForgetPsWdActivity extends BaseTitleActivity implements View.OnClickListener {
    private EditText ccard, cphone;
    private Button btnti;
    private LoadingView loadingView;

    @Override
    public int setLayout() {
        return R.layout.activity_forget_ps_wd;
    }

    @Override
    protected void initView() {
        ccard = bindView(R.id.ccard);
        cphone = bindView(R.id.cphone);
        btnti = bindView(R.id.btnti);
    }

    @Override
    protected void initEvent() {
        btnti.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        setTitle("重置密码");
        loadingView = new LoadingView(this);

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btnti:

                if (ccard.getText().toString().length() != 0 && cphone.getText().toString().length() == 18) {
                    loadingView.show();
                    setPassword();
                } else {
                    ToastUtils.show(this, "请正确输入姓名及身份证号码");
                }
                break;
        }
    }

    public void setPassword() {

        TurnClassHttp.setPassword(ccard.getText().toString(), cphone.getText().toString(), 1, this, new OnNetResultListener() {
            @Override
            public void onSuccessfulListener(String stringResult, int resultCode) {
                loadingView.dismiss();

                try {
                    JSONObject object = new JSONObject(stringResult);

                    if (object.getString("result").equals("1")) {

                        ToastUtils.show(ForgetPsWdActivity.this, "请注意查收短信");
                    } else {
                        ToastUtils.show(ForgetPsWdActivity.this, "信息不匹配，请重新输入");

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailureListener(String errMsg, int resultCode) {
                loadingView.dismiss();
                ToastUtils.show(ForgetPsWdActivity.this, "修改失败！，请联管家");
            }

        });
    }
}
