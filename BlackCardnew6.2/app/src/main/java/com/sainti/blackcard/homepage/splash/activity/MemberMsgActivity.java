package com.sainti.blackcard.homepage.splash.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseBean;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.homepage.activity.HomePageActivity;
import com.sainti.blackcard.commen.mconstant.SataicCode;
import com.sainti.blackcard.commen.mconstant.SpCodeName;
import com.sainti.blackcard.meventbus.NormalEventBean;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.mtuils.SpUtil;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.widget.LoadingView;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;

public class MemberMsgActivity extends BaseTitleActivity implements OnNetResultListener, View.OnClickListener {
    private Context mContext;
    private TextView mname, mpwd, mnum, mphone, msex;
    private RelativeLayout rlsex;
    private String sex = "";
    private String phone;
    private LoadingView loadingView;

    @Override
    public int setLayout() {
        return R.layout.activity_member_msg;
    }

    @Override
    protected void initView() {
        mname = bindView(R.id.mname);
        mpwd = bindView(R.id.mpwd);
        mnum = bindView(R.id.mnum);
        mphone = bindView(R.id.mphone);
        msex = bindView(R.id.msex);
        rlsex = bindView(R.id.rlsex);
        loadingView = new LoadingView(this);
    }

    @Override
    protected void initEvent() {
        rlsex.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        setTitle("会员信息绑定");
        setBaseRightText("提交", new OnClickRightTextCallBack() {
            @Override
            public void clickRightText() {
                Msgbang();
            }
        });
        mContext = MemberMsgActivity.this;
        ToastUtils.show(mContext, "请绑定会员信息后再登录");
        // TurnClassHttp.reg_info(10, this, this);

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.rlsex:
                Intent intent = new Intent(this, ChoiceSexActivity.class);
                startActivityForResult(intent, 110);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 110) {
            if (resultCode == 111) {
//                男孩
                msex.setText("男");
            }
            if (resultCode == 112) {
                // 女孩
                msex.setText("女");

            }
        }
    }

    public void Msgbang() {
        String name = mname.getText().toString();
        String nick = mpwd.getText().toString();
        String num = mnum.getText().toString();
        phone = mphone.getText().toString();
        if (msex.getText().toString().equals("男")) {
            sex = "1";
        } else {
            sex = "2";
        }
        if (name.equals("")) {
            ToastUtils.show(mContext, "请输入真实姓名");
        } else if (nick.equals("")) {
            ToastUtils.show(mContext, "请输入昵称");
        } else if (num.equals("")) {
            ToastUtils.show(mContext, "请输入身份证号");
        } else if (phone.equals("")) {
            ToastUtils.show(mContext, "请输入联系电话");
        } else if (msex.getText().toString().equals("性别")) {
            ToastUtils.show(mContext, "请输入性别");
        } else {
            loadingView.show();
            TurnClassHttp.login_bindinfo(name, nick, num, phone, sex, SataicCode.GetDateCode, this, this);
        }

    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        loadingView.dismiss();
        switch (resultCode) {
            case SataicCode.GetDateCode:
                Gson gson = GsonSingle.getGson();
                BaseBean baseBean = gson.fromJson(stringResult, BaseBean.class);
                if (baseBean != null && baseBean.getResult().equals("1")) {
                    EventBus.getDefault().post(new NormalEventBean(SataicCode.FINISHCHOICELOGIN));
                    MobclickAgent.onEvent(this,"appzhuce");
                    ToastUtils.show(this, "会员绑定成功");
                    SpUtil.initEditor(SpCodeName.SPNAME).putBoolean(SpCodeName.ISLOGIN, true).commit();// 用户已经登录
                    SpUtil.initEditor(SpCodeName.SPNAME).putString(SpCodeName.TEL, phone).commit();
                    SpUtil.initEditor(SpCodeName.SPNAME).putString(SpCodeName.KAHAO,phone).commit();
                    SpUtil.initEditor(SpCodeName.SPNAME).putBoolean(SpCodeName.ISNEWPENSO,true).commit();
                    SpUtil.initEditor(SpCodeName.SPNAME).putBoolean(SpCodeName.ISNEWPENSO_Reftresh,true).commit();
                    Intent intent = new Intent(MemberMsgActivity.this,
                            HomePageActivity.class);

                    startActivity(intent);

                    finish();
                } else {
                    ToastUtils.show(this, baseBean.getMsg());
                    return;
                }
                break;
            case 10:
                String b = stringResult;
                break;
        }
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        String a = errMsg;
    }
    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("bangding");
        MobclickAgent.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageStart("bangding");
        MobclickAgent.onPause(this);
    }
}
