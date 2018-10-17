package com.sainti.blackcard.my.invitingcourtesy.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hyphenate.chat.ChatClient;
import com.hyphenate.helpdesk.easeui.util.IntentBuilder;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseNoTitleActivity;
import com.sainti.blackcard.commen.mconstant.SataicCode;
import com.sainti.blackcard.commen.mconstant.SpCodeName;
import com.sainti.blackcard.mtuils.SpUtil;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.widget.LoadingView;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.Map;

public class InvitAcitvity extends BaseNoTitleActivity implements View.OnClickListener{


    private ImageView inviy_yao;
    private ImageView invit_look,iv_title_leftOne;
    private LoadingView loadingView;
    private LinearLayout tvToGuanjia;

    @Override
    public int setLayout() {
        return R.layout.activity_invit_acitvity;
    }

    @Override
    protected void initView() {

        inviy_yao = bindView(R.id.inviy_yao);
        invit_look = bindView(R.id.invit_look);
        iv_title_leftOne = bindView(R.id.iv_title_leftOne);
        tvToGuanjia = bindView(R.id.tv_toGuanjia);

    }

    @Override
    protected void initEvents() {
        inviy_yao.setOnClickListener(this);
        invit_look.setOnClickListener(this);
        iv_title_leftOne.setOnClickListener(this);
        tvToGuanjia.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.inviy_yao:
                startActivity(new Intent(this, CourtesyActivity.class));
                break;
            case R.id.invit_look:
                startActivity(new Intent(this, LookCouttesyActivity.class));
                break;
            case R.id.iv_title_leftOne:
                finish();
                break;
            case R.id.tv_toGuanjia:
                if (ChatClient.getInstance().isLoggedInBefore()) {
                    Map<String, String> map_ekv0 = new HashMap<String, String>();
                    map_ekv0.put("kahao", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.KAHAO, ""));
                    map_ekv0.put("rukou", "tequanxaingqingye");
                    MobclickAgent.onEvent(InvitAcitvity.this, "zhaohuanguanjia", map_ekv0);// 友盟统计
                    //已经登录，可以直接进入会话界面
                    Intent intent = new IntentBuilder(this)
                            .setServiceIMNumber("test1") //获取地址：kefu.easemob.com，“管理员模式 > 渠道管理 > 手机APP”页面的关联的“IM服务号”
                            .build();
                    startActivity(intent);
                    SpUtil.initEditor(SpCodeName.SPNAME).putBoolean(SataicCode.ISRENCOIN, false).commit();
                } else {
                    //未登录，需要登录后，再进入会话界面
                    ToastUtils.show(this, "登陆失败");
                }
                break;

        }
    }


}
