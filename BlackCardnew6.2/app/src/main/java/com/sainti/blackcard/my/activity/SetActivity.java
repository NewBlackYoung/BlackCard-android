package com.sainti.blackcard.my.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseBean;
import com.sainti.blackcard.base.BaseNoTitleActivity;
import com.sainti.blackcard.commen.mconstant.SataicCode;
import com.sainti.blackcard.commen.mconstant.SpCodeName;
import com.sainti.blackcard.meventbus.NormalEventBean;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.mtuils.CommontUtil;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.mtuils.SpUtil;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.homepage.splash.activity.MemberLoginActivity;
import com.sainti.blackcard.widget.LoadingView;
import com.sainti.blackcard.widget.ProgDialog;
import com.sainti.blackcard.widget.SaintiDialogTwo;

import org.greenrobot.eventbus.EventBus;

public class SetActivity extends BaseNoTitleActivity implements View.OnClickListener, OnNetResultListener {
    private LinearLayout lyexit;
    private SaintiDialogTwo saintiDialog = null;
    private RelativeLayout qingchuhuancun, lyyinsi,lychange;
    private ProgDialog sProgDialog;
    private BaseBean baseBean;
    private LoadingView loadingView;
    private ImageView moreback;
    private TextView count;

    @Override
    public int setLayout() {
        return R.layout.activity_set;
    }

    @Override
    protected void initView() {
        lyexit = bindView(R.id.lyexit);
        qingchuhuancun = bindView(R.id.qingchuhuancun);
        lyyinsi = bindView(R.id.lyyinsi);
        moreback = bindView(R.id.moreback);
        count = bindView(R.id.count);
        lychange= bindView(R.id.lychange);
    }

    @Override
    protected void initEvents() {
        lyexit.setOnClickListener(this);
        qingchuhuancun.setOnClickListener(this);
        lyyinsi.setOnClickListener(this);
        loadingView = new LoadingView(this);
        moreback.setOnClickListener(this);
        lychange.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        String versionName = CommontUtil.getVersionName(this);
        count.setText(versionName);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lyexit:
                showDialog("确认将要退出登录");
                break;
            case R.id.qingchuhuancun:
                showDialogs("点击确定清除缓存");
                break;
            case R.id.lyyinsi:
                Intent intent = new Intent(this, YinSiActivity.class);
                startActivity(intent);
                break;
            case R.id.moreback:
                finish();
                break;
            case R.id.lychange:
                intent = new Intent(this, ChangeActivity.class);
                startActivity(intent);
                break;
        }

    }

    private void showDialog(String str) {
        saintiDialog = SaintiDialogTwo.createDialog(this);
        saintiDialog.setTitile(str);
        saintiDialog.setMainTitle(R.string.app_name);
        saintiDialog.setButton("取消", "确认");
        saintiDialog.setMessage("");
        saintiDialog
                .setOnLeftButtonClickListener(new SaintiDialogTwo.setOnButton1ClickListener() {

                    @Override
                    public void setOnButton1Clicked(Button left) {
                        if (saintiDialog != null) {
                            saintiDialog.dismiss();
                            saintiDialog = null;
                        }
                    }
                });
        saintiDialog
                .setOnRightButtonClickListener(new SaintiDialogTwo.setOnButton2ClickListener() {

                    @Override
                    public void setOnButton2Clicked(Button right) {
                        saintiDialog.dismiss();
                        saintiDialog = null;
                        getExit();
                        loadingView.show();
                    }
                });
        saintiDialog.show();
    }

    public void getExit() {
        TurnClassHttp.exit(1, this, this);
    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        Gson gson = GsonSingle.getGson();
        baseBean = gson.fromJson(stringResult, BaseBean.class);
        loadingView.dismiss();
        if (baseBean.getResult().equals("1")) {
            ToastUtils.show(SetActivity.this, "退出登录成功");
            SpUtil.initEditor(SpCodeName.SPNAME).putBoolean(SpCodeName.ISLOGIN, false).commit();
            SpUtil.initEditor(SpCodeName.SPNAME).putString(SpCodeName.TOKEN, "").commit();
            SpUtil.initEditor(SpCodeName.SPNAME).putString(SpCodeName.UID, "").commit();

            Intent intent = new Intent(SetActivity.this,
                    MemberLoginActivity.class);
            startActivity(intent);
            EventBus.getDefault().post(new NormalEventBean(SataicCode.EXIT));
            finish();
        } else {
            ToastUtils.show(SetActivity.this, baseBean.getMsg());
        }

    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {

    }

    private void showDialogs(String str) {
        saintiDialog = SaintiDialogTwo.createDialog(this);
        saintiDialog.setTitile(str);
        saintiDialog.setMainTitle(R.string.app_name);
        saintiDialog.setButton("取消", "确认");
        saintiDialog.setMessage("");
        saintiDialog
                .setOnLeftButtonClickListener(new SaintiDialogTwo.setOnButton1ClickListener() {

                    @Override
                    public void setOnButton1Clicked(Button left) {
                        if (saintiDialog != null) {
                            saintiDialog.dismiss();
                            saintiDialog = null;
                        }
                    }
                });
        saintiDialog
                .setOnRightButtonClickListener(new SaintiDialogTwo.setOnButton2ClickListener() {

                    @Override
                    public void setOnButton2Clicked(Button right) {
                        saintiDialog.dismiss();
                        saintiDialog = null;
                        startProgressDialog("正在清除...");
                        stopProgressDialog();
                    }
                });
        saintiDialog.show();
    }

    private void startProgressDialog(String s) {
        if (sProgDialog == null) {
            sProgDialog = ProgDialog.createDialog(this);
            sProgDialog.setMessage(s + "...");
        }
        sProgDialog.show();
    }

    private void stopProgressDialog() {
        if (sProgDialog != null) {
            sProgDialog.dismiss();
            sProgDialog = null;
        }
    }
}
