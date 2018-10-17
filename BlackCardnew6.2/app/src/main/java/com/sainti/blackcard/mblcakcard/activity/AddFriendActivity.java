package com.sainti.blackcard.mblcakcard.activity;

import android.widget.EditText;

import com.google.gson.Gson;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseBean;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.commen.mconstant.SpCodeName;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.mtuils.SpUtil;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.widget.LoadingView;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.Map;

public class AddFriendActivity extends BaseTitleActivity implements OnNetResultListener {

    private EditText ed_phonenumber, ed_mima, ed_name;
    private LoadingView loadingView;
    private String num;

    @Override
    public int setLayout() {
        return R.layout.activity_add_friend;
    }

    @Override
    protected void initView() {
        ed_phonenumber = bindView(R.id.ed_phonenumber);
        ed_mima = bindView(R.id.ed_mima);
        ed_name = bindView(R.id.ed_name);
    }

    @Override
    protected void initEvent() {
        loadingView = new LoadingView(this);
        //TurnClassHttp.haowuorder("all", "1", 1, this, this);
    }

    @Override
    protected void initData() {
        setTitle("绑定亲友信息");
        num = getIntent().getStringExtra("count");
        setBaseRightText("保存", new OnClickRightTextCallBack() {
            @Override
            public void clickRightText() {
                String name = ed_name.getText().toString();
                String ed_phonen = ed_phonenumber.getText().toString();
                String ed_mim = ed_mima.getText().toString();
                if (name == null || name.equals("")) {
                    ToastUtils.show(AddFriendActivity.this, "请输入姓名");
                    return;
                }
                if (ed_phonen == null || ed_phonen.equals("")) {
                    ToastUtils.show(AddFriendActivity.this, "请输入手机号");
                    return;
                }
                if (ed_mim == null || ed_mim.equals("")) {
                    ToastUtils.show(AddFriendActivity.this, "请输入密码");
                    return;
                }
                loadingView.show();
                TurnClassHttp.setBangding(num,name, ed_mim, ed_phonen, 1, AddFriendActivity.this, AddFriendActivity.this);

            }
        });
    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        Gson gson = GsonSingle.getGson();
        BaseBean baseBean = gson.fromJson(stringResult, BaseBean.class);
        if (baseBean.getResult().equals("1")) {
            Map<String, String> map_ekv0 = new HashMap<String, String>();
            map_ekv0.put("kahao", SpUtil.getString(SpCodeName.SPNAME,SpCodeName.KAHAO,""));
            MobclickAgent.onEvent(context, "bangdingqinyou", map_ekv0);// 友盟统计

            ToastUtils.show(this, "绑定成功");
            setResult(999);
            finish();
        } else {
            ToastUtils.show(this, baseBean.getMsg());
        }
        loadingView.dismiss();
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        loadingView.dismiss();
        ToastUtils.show(this, "绑定失败！请稍后尝试");
    }
}
