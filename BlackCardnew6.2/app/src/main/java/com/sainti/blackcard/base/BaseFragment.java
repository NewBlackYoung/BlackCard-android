package com.sainti.blackcard.base;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sainti.blackcard.commen.mconstant.SpCodeName;
import com.sainti.blackcard.mhttp.okgo.OkGoUtils;
import com.sainti.blackcard.mtuils.MLog;
import com.sainti.blackcard.mtuils.SpUtil;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.umeng.analytics.MobclickAgent;


public abstract class BaseFragment extends Fragment {
    protected Context context;
    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }
    }

    //记录当前Fragment的状态
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(setLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initEvents();

    }


    protected <T extends View> T bindView(int id) {
        return (T) getView().findViewById(id);
    }

    protected <T extends Object> T bindView(View view, int id) {
        return (T) view.findViewById(id);
    }

    protected abstract int setLayout();


    protected abstract void initView(View view);


    protected abstract void initData();

    protected abstract void initEvents();

    //退出时将context对象置空
    @Override
    public void onDestroy() {
        super.onDestroy();

        //根据 Tag 取消请求
//        OkGo.getInstance().cancelAll();
        OkGoUtils.getInstance().callOffNet(context);
        context = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(context.getClass().getName());
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(context.getClass().getName());
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);

    }
    public void showToast(String content) {
        ToastUtils.show(context, content);
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
