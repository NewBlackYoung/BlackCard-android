package com.sainti.blackcard.goodthings.spcard.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.goodthings.spcard.adapter.AdressListAdapter;
import com.sainti.blackcard.goodthings.spcard.baen.AddressBean;
import com.sainti.blackcard.goodthings.spcard.baen.BaseBean;
import com.sainti.blackcard.widget.LoadingView;

import java.util.HashMap;
import java.util.Map;

public class AdressListActivity extends BaseTitleActivity implements BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.OnItemClickListener, OnNetResultListener {


    private RecyclerView rvAdressList;
    private AdressListAdapter adressListAdapter;
    private TextView tvAddRess, tv_delete;
    private LoadingView progressDialog;
    private AddressBean addressBean;
    private Map<String, String> checkMap;
    private Gson gson;
    private int p;
    private BaseBean baseBean;

    @Override
    public int setLayout() {
        return R.layout.activity_adress_list;
    }

    @Override
    protected void initView() {
        rvAdressList = bindView(R.id.rv_adressList);
        tvAddRess = bindView(R.id.tv_addRess);
    }

    @Override
    protected void initEvent() {
        tvAddRess.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        progressDialog = new LoadingView(this);
        setTitle("地址列表");
        getBaseBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        adressListAdapter = new AdressListAdapter(R.layout.item_adresslist);
        adressListAdapter.setContext(this);
        rvAdressList.setLayoutManager(new LinearLayoutManager(this));
        rvAdressList.setAdapter(adressListAdapter);
        progressDialog.show();
        TurnClassHttp.getDiZhi(1, this, this);// 获取当前用户是否添写过地址
        adressListAdapter.setOnItemChildClickListener(this);
        adressListAdapter.setOnItemClickListener(this);
        checkMap = new HashMap<>();
    }


    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tv_addRess:
                Intent intent = new Intent(this, WriteAdressActivity.class);
                intent.putExtra("code", "0");// 添加地址
                startActivityForResult(intent, 112);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 112 && resultCode == 113) {
            TurnClassHttp.getDiZhi(1, this, this);// 获取当前用户是否添写过地址
        }
    }

    /*item 子View点击事件*/
    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
        p = position;
        switch (view.getId()) {
            case R.id.tv_delete:
                progressDialog.show();
                TurnClassHttp.deleteDiZhi(addressBean.getData().get(position).getAd_id(), 2, this, this);
                break;
            case R.id.lly_text:
                Intent intent = new Intent(this, WriteAdressActivity.class);
                intent.putExtra("code", "1");// 编辑地址
                intent.putExtra("ad_moren", addressBean.getData().get(position).getAd_moren());// 添加地址
                intent.putExtra("ad_id", addressBean.getData().get(position).getAd_id());// 添加地址
                intent.putExtra("province", addressBean.getData().get(position).getAd_province());//省
                intent.putExtra("city", addressBean.getData().get(position).getAd_city());//市
                intent.putExtra("area", addressBean.getData().get(position).getAd_area());//区
                intent.putExtra("address", addressBean.getData().get(position).getAd_address());//地址
                intent.putExtra("user", addressBean.getData().get(position).getAd_user());//姓名
                intent.putExtra("tel", addressBean.getData().get(position).getAd_tel());//电话
                startActivityForResult(intent, 112);
                break;
            case R.id.ck_adress:
                TurnClassHttp.upDateDiZhi(addressBean.getData().get(position).getAd_province(), addressBean.getData().get(position).getAd_city(), addressBean.getData().get(position).getAd_area(), addressBean.getData().get(position).getAd_address(), addressBean.getData().get(position).getAd_user(), addressBean.getData().get(position).getAd_tel(), addressBean.getData().get(position).getAd_id(), "1", 3, this, this);
                break;
        }


    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent i = new Intent();
        i.putExtra("tel", addressBean.getData().get(position).getAd_tel());
        i.putExtra("name", addressBean.getData().get(position).getAd_user());
        i.putExtra("adress", addressBean.getData().get(position).getAd_province() + addressBean.getData().get(position).getAd_city() + addressBean.getData().get(position).getAd_area() + addressBean.getData().get(position).getAd_address());
        setResult(115, i);
        finish();

    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        gson = GsonSingle.getGson();
        switch (resultCode) {
            case 1:
                addressBean = gson.fromJson(stringResult, AddressBean.class);
                if (addressBean.getData() == null || addressBean.getData().size() == 0) {

                } else {
                    for (int i = 0; i < addressBean.getData().size(); i++) {
                        if (addressBean.getData().get(i).getAd_moren().equals("1")) {
                            adressListAdapter.setMorenCode(addressBean.getData().get(i).getAd_id());
                        }
                    }

                    adressListAdapter.setNewData(addressBean.getData());
                }
                progressDialog.dismiss();
                break;
            case 2:

                baseBean = gson.fromJson(stringResult, BaseBean.class);
                if (baseBean.getResult() == 1) {
                    progressDialog.dismiss();
                    adressListAdapter.remove(p);
                }
                break;
            case 3:
                Gson gson = GsonSingle.getGson();
                baseBean = gson.fromJson(stringResult, BaseBean.class);
                if (baseBean.getResult() == 1) {
                    adressListAdapter.setMorenCode(addressBean.getData().get(p).getAd_id());
                }
                break;
        }
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        switch (resultCode) {
            case 1:
                progressDialog.dismiss();
                ToastUtils.show(this, "获取地址列表失败！请稍后尝试");
                break;
            case 2:
                progressDialog.dismiss();
                ToastUtils.show(AdressListActivity.this, "操作失败！请稍后尝试");
                break;
            case 3:
                ToastUtils.show(AdressListActivity.this, "操作失败！请稍后尝试");
                break;
        }
    }
}
