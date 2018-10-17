package com.sainti.blackcard.privilege.moreprivilege.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.coffee.activity.CoffeeActivity;
import com.sainti.blackcard.goodthings.activity.GoodthingsListActivity;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.minterface.MyItemClickListener;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.mwebview.CommenWebActivity;
import com.sainti.blackcard.privilege.moreprivilege.adapter.MoreTeQuanAdapter;
import com.sainti.blackcard.privilege.moreprivilege.bean.MoreTeQuanBean;
import com.umeng.analytics.MobclickAgent;

;import java.util.HashMap;
import java.util.Map;

public class MoreTeQuanActivity extends BaseTitleActivity implements MyItemClickListener, OnNetResultListener {


    private RecyclerView xrvMoretequan;
    private MoreTeQuanAdapter moreTeQuanAdapter;
    private MoreTeQuanBean moreTeQuanBean;
    private String aMapLocationCity;
    private String aMapLocationProvince;

    @Override
    public int setLayout() {
        return R.layout.activity_more_te_quan;
    }

    @Override
    protected void initView() {
        xrvMoretequan = bindView(R.id.xrv_moretequan);

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        setTitle("全部特权");
        aMapLocationCity = getIntent().getStringExtra("aMapLocationCity");
        aMapLocationProvince = getIntent().getStringExtra("aMapLocationProvince");
        getBaseBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        xrvMoretequan.setLayoutManager(new GridLayoutManager(this, 5));
        moreTeQuanAdapter = new MoreTeQuanAdapter(this);
        xrvMoretequan.setAdapter(moreTeQuanAdapter);
        moreTeQuanAdapter.setListener(this);
        TurnClassHttp.putMoreTequan(1, this, this);
    }


    // 全部特權Item點擊事件
    @Override
    public void onItemClick(int position, int code) {
        switch (code) {
            case 9:
                Map<String, String> map_ekv0s = new HashMap<String, String>();
                map_ekv0s.put("name", moreTeQuanBean.getData().get(position).getXp_name());
                MobclickAgent.onEvent(context, "tequanicon",map_ekv0s);// 友盟统计
                String xp_type = moreTeQuanBean.getData().get(position).getXp_type();
                String xp_id = moreTeQuanBean.getData().get(position).getXp_id();
                String     isguanjia = moreTeQuanBean.getData().get(position).getIs_guanjia();
                if (xp_id.equals("1")) {
                    Toast.makeText(this, "机票系统维护中，请联系管家为您服务", Toast.LENGTH_SHORT).show();
                /*    Intent intentss = new Intent(this, ChuXingActivity.class);
                    startActivity(intentss);*/
                }
                if (xp_id.equals("2")) {
                   /* Intent intent1 = new Intent(this, ChatRoomActivity.class);
                    intent1.putExtra("type", "2");
                    intent1.putExtra("isTeQuan", 11);
                    startActivity(intent1);*/
                }
                if (xp_id.equals("5")) {
                    startActivity(new Intent(this, GoodthingsListActivity.class));
                }
                if (xp_id.equals("6")) {
                    if (aMapLocationCity.equals("") && aMapLocationProvince.equals("")) {
                        Toast.makeText(this, "获取位置信息失败！请开启权限", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        Intent intent1 = new Intent(this, CoffeeActivity.class);
                        intent1.putExtra("city", aMapLocationCity);
                        intent1.putExtra("sheng", aMapLocationProvince);
                        startActivity(intent1);
                    }

                }
                // 更多特权
                if (xp_id.equals("0")) {
                        Intent intent1 = new Intent(this, MoreTeQuanActivity.class);

                    startActivity(intent1);

                }
                if (xp_type.equals("1")) {
                    Intent intents_t = new Intent(this, CommenWebActivity.class);
                    intents_t.putExtra("wel_url", moreTeQuanBean.getData().get(position).getXp_url());
                    intents_t.putExtra("title", moreTeQuanBean.getData().get(position).getXp_name());
                    intents_t.putExtra("img_url", moreTeQuanBean.getData().get(position).getXp_icon());
                    intents_t.putExtra("name1", moreTeQuanBean.getData().get(position).getXp_name());
                    intents_t.putExtra("w_id", "");
                    intents_t.putExtra("isguanjia",isguanjia);
                    startActivity(intents_t);
                }
                break;
        }
    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        Gson gson = GsonSingle.getGson();
        moreTeQuanBean = gson.fromJson(stringResult, MoreTeQuanBean.class);
        if (moreTeQuanBean != null) {
            moreTeQuanAdapter.setList(moreTeQuanBean.getData());
        }
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {

    }
}
