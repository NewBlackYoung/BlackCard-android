package com.sainti.blackcard.homepage.splash.activity;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseNoTitleActivity;
import com.sainti.blackcard.homepage.activity.HomePageActivity;
import com.sainti.blackcard.commen.mconstant.SataicCode;
import com.sainti.blackcard.commen.mconstant.SpCodeName;
import com.sainti.blackcard.meventbus.NormalEventBean;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.minterface.TimerListener;
import com.sainti.blackcard.mtuils.CommontUtil;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.mtuils.SpUtil;
import com.sainti.blackcard.commen.puth.XingeBean;
import com.sainti.blackcard.widget.LoadingView;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushManager;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class SplashScreenActivity extends BaseNoTitleActivity implements OnNetResultListener, View.OnClickListener, TimerListener {
    private String m_szAndroidID;
    private LoadingView loadingView;
    private TextView tv_tiaoguo;
    private XGPushClickedResult click;
    private XingeBean xingBean;

    @Override
    public int setLayout() {
        return R.layout.activity_splash_screen;
    }

    @Override
    protected void initView() {
        tv_tiaoguo = bindView(R.id.tv_tiaoguo);
    }

    @Override
    protected void initEvents() {
        tv_tiaoguo.setOnClickListener(this);
    }

    @Override
    protected void initData() {

        loadingView = new LoadingView(this);
        m_szAndroidID = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        puthDate();

    }


    public void getData() {
        tv_tiaoguo.setVisibility(View.GONE);
        timers.start();

    }

    /*登录逻辑*/
    private void login() {
        boolean isLogin = SpUtil.getBoolean(SpCodeName.SPNAME, SpCodeName.ISLOGIN, false);// 用户是否已经登录过
        if (isLogin) {
            Intent intent = new Intent(this, HomePageActivity.class);
            if (click != null) {
                SpUtil.initEditor(SpCodeName.SPNAME).putBoolean(SataicCode.ISXINGE, true).commit();
                String l_title = xingBean.getL_title();
                String l_title2 = xingBean.getL_title2();
                String l_haowuid = xingBean.getL_haowuid();
                String l_img = xingBean.getL_img();
                String l_link = xingBean.getL_link();
                String is_guanjia =xingBean.getIs_guanjia();
                if (l_title.equals("blackcard")) {
                    intent.putExtra("l_title", "blackcard");
                    intent.putExtra("l_title2", "");
                    intent.putExtra("l_haowuid", "");
                    intent.putExtra("l_img", "");
                    intent.putExtra("l_link", "");
                    intent.putExtra("is_guanjia", is_guanjia);
                } else if (l_title.equals("sign")) {
                    intent.putExtra("l_title", "sign");
                    intent.putExtra("l_title2", "");
                    intent.putExtra("l_haowuid", "");
                    intent.putExtra("l_img", "");
                    intent.putExtra("l_link", l_link);
                    intent.putExtra("is_guanjia", is_guanjia);
                } else {
                    if (l_haowuid.equals("qhsh")) {
                        intent.putExtra("l_haowuid", "");
                    } else {
                        intent.putExtra("l_haowuid", l_haowuid);
                    }
                    intent.putExtra("l_title", l_title);
                    intent.putExtra("l_title2", l_title2);
                    intent.putExtra("l_img", l_img);
                    intent.putExtra("l_link", l_link);
                    intent.putExtra("is_guanjia", is_guanjia);
                }
            } else {
                SpUtil.initEditor(SpCodeName.SPNAME).putBoolean(SataicCode.ISXINGE, false).commit();
            }
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(this, LoginActivity
                    .class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        String a = stringResult;

    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        String a = errMsg;
    }


    CountDownTimer timers = new CountDownTimer(2000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
        }

        @Override
        public void onFinish() {
            login();
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_tiaoguo:
                login();
                timer.cancel();
                break;
        }
    }

    CountDownTimer timer = new CountDownTimer(5000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            tv_tiaoguo.setText("跳过(" + millisUntilFinished / 1000 + ")");
        }

        @Override
        public void onFinish() {
            login();
        }
    };

    private void puthDate() {
        click = XGPushManager.onActivityStarted(this);
        if (click != null) {
            String cusContent = click.getCustomContent();
            Gson gson = GsonSingle.getGson();
            xingBean = gson.fromJson(cusContent, XingeBean.class);
            if (isExsitMianActivity(HomePageActivity.class)) {
                EventBus.getDefault().post(new NormalEventBean(SataicCode.EXIT));
                CommontUtil.lateTime(500, this);
            }
        } else {
            getData();
        }
    }

    private boolean isExsitMianActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        ComponentName cmpName = intent.resolveActivity(getPackageManager());
        boolean flag = false;
        if (cmpName != null) { // 说明系统中存在这个activity
            ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> taskInfoList = am.getRunningTasks(10);
            for (ActivityManager.RunningTaskInfo taskInfo : taskInfoList) {
                if (taskInfo.baseActivity.equals(cmpName)) { // 说明它已经启动了
                    flag = true;
                    break;  //跳出循环，优化效率
                }
            }
        }
        return flag;
    }

    @Override
    public void onTimerListener() {
        getData();
    }
}
