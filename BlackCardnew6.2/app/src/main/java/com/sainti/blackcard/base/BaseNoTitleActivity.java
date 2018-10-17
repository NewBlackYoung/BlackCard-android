package com.sainti.blackcard.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jaeger.library.StatusBarUtil;
import com.sainti.blackcard.R;
import com.sainti.blackcard.commen.mconstant.SpCodeName;
import com.sainti.blackcard.mtuils.ActivityManager;
import com.sainti.blackcard.mtuils.MLog;
import com.sainti.blackcard.mtuils.SpUtil;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.umeng.analytics.MobclickAgent;


/**
 * Created by $ Chenguohui on 2017/11/13.
 */

public abstract class BaseNoTitleActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //スクリーン設定
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(setLayout());
        setStatusBar();
        setAty();
        initView();
        initEvents();
        initData();

    }

    /**
     * @param id  组件id
     * @param <T> 组件
     * @return View
     */
    protected <T extends View> T bindView(int id) {
        return (T) findViewById(id);
    }

    protected <T extends Object> T bindView(View view, int id) {
        return (T) view.findViewById(id);
    }
    /**
     * ActionBarカラー設定する
     */
    protected void setStatusBar() {
        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.statusBar));
    }

    public abstract int setLayout();


    protected abstract void initView();


    protected abstract void initEvents();


    protected abstract void initData();
    /**
     * 在绑定布局前如果需要多Activity进行设置
     * 则复写此方法
     */
    protected void setAty() {
        //在Activity创建的时候加入到List中
//        ActivityControl.addAty(this);
        ActivityManager.getInstance().pushOneActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       // ActivityManager.getInstance().popActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(this.getClass().getName());
        MobclickAgent.onResume(this);
    }
    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(this.getClass().getName());
        MobclickAgent.onPause(this);
    }
    public void showToast(String content) {
        ToastUtils.show(this, content);
    }

    public void showLog(String tag, String content) {
        MLog.d(tag, content);
    }
    public String getSpUid(String tag, String content) {
        return SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, "");
    }
    public String getSpToken(String tag, String content) {
        return SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, "");
    }
}
