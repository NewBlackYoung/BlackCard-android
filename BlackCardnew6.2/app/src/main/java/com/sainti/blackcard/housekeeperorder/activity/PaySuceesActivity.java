package com.sainti.blackcard.housekeeperorder.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseBean;
import com.sainti.blackcard.base.BaseNoTitleActivity;
import com.sainti.blackcard.db.bean.GoodthingsBean;
import com.sainti.blackcard.db.dao.GoodThingsDao;
import com.sainti.blackcard.goodthings.bean.GetKeyCountBean;
import com.sainti.blackcard.goodthings.goodtingsorder.activity.GoodThingOsderActivity;
import com.sainti.blackcard.commen.mconstant.SataicCode;
import com.sainti.blackcard.commen.mconstant.SpCodeName;
import com.sainti.blackcard.meventbus.NormalEventBean;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.mtuils.SpUtil;
import com.sainti.blackcard.widget.LoadingView;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class PaySuceesActivity extends BaseNoTitleActivity implements View.OnClickListener, OnNetResultListener {

    private LinearLayout ll_paysucess;
    private TextView tv_keycount;
    private ImageView iv_back_home, iv_look_order;
    private LoadingView loadingView;
    private String type;
    private String json;
    private boolean tolist = false;

    @Override
    public int setLayout() {
        return R.layout.activity_pay_sucees;
    }

    @Override
    protected void initView() {
        ll_paysucess = bindView(R.id.ll_paysucess);
        tv_keycount = bindView(R.id.tv_keycount);
        iv_back_home = bindView(R.id.iv_back_home);
        iv_look_order = bindView(R.id.iv_look_order);
    }

    @Override
    protected void initEvents() {
        iv_back_home.setOnClickListener(this);
        iv_look_order.setOnClickListener(this);
        loadingView = new LoadingView(this);
    }

    @Override
    protected void initData() {
        type = getIntent().getStringExtra("type");
        String order_sn = getIntent().getStringExtra("order_sn");
        loadingView.show();
        TurnClassHttp.getBlackkey(order_sn, type, 1, this, this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_home:
                if (type.equals("haowu")) {
                    tolist = false;
                    toService();
                }
                if (type.equals("guanjia")) {
                    SpUtil.initEditor(SpCodeName.SPNAME).putString(SpCodeName.GUANJIA, SpCodeName.FINISH).commit();
                    finish();
                }
                if (type.equals("jiazhao")) {
                    SpUtil.initEditor(SpCodeName.SPNAME).putString(SpCodeName.GUANJIA, SpCodeName.FINISH).commit();
                    finish();
                }
                break;
            case R.id.iv_look_order:
                if (type.equals("haowu")) {
                    tolist = true;
                    toService();
                }
                if (type.equals("guanjia")) {
                    SpUtil.initEditor(SpCodeName.SPNAME).putString(SpCodeName.GUANJIA, SpCodeName.REFRESH).commit();
                    finish();

                }
                if (type.equals("jiazhao")) {
                    SpUtil.initEditor(SpCodeName.SPNAME).putString(SpCodeName.GUANJIA, SpCodeName.REFRESH).commit();
                    finish();
                }
                break;
        }
    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        Gson gson = GsonSingle.getGson();
        switch (resultCode) {
            case 1:
                GetKeyCountBean countBean = gson.fromJson(stringResult, GetKeyCountBean.class);
                String count = countBean.getBlackkey();
                if (count.equals("0.00")) {
                    tv_keycount.setVisibility(View.INVISIBLE);
                } else {
                    tv_keycount.setVisibility(View.VISIBLE);
                    tv_keycount.setText("恭喜您获得黑钥匙" + count + "把");
                }
                break;
            case 2:
                BaseBean baseBean = gson.fromJson(stringResult, BaseBean.class);
                if (baseBean.getResult().equals("1")) {
                    EventBus.getDefault().post(new NormalEventBean(SataicCode.CARDPAYSUCESS));
                    if (tolist) {
                        startActivity(new Intent(this, GoodThingOsderActivity.class));
                        finish();
                    } else {
                        finish();
                    }

                }
                break;
        }

        loadingView.dismiss();
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        loadingView.dismiss();

    }

    private void toService() {
        loadingView.show();
        List<GoodthingsBean> allList = GoodThingsDao.getsInstance(this).queryForPayState("0");
        try {
            json = changeArrayDateToJson(allList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        TurnClassHttp.addWelcart(json, 2, this, this);
    }

    private String changeArrayDateToJson(List<GoodthingsBean> allList) throws JSONException {
        JSONArray j = new JSONArray();
        for (int i = 0; i < allList.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("w_id", allList.get(i).getwId());
            jsonObject.put("w_num", allList.get(i).getTingsCount());
            jsonObject.put("w_kindid", allList.get(i).getTingsKindId());
            j.put(jsonObject);
        }
        return j.toString();
    }
}
