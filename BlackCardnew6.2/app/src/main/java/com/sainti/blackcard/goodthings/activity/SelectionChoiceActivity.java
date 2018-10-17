package com.sainti.blackcard.goodthings.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseNoTitleActivity;
import com.sainti.blackcard.goodthings.adapter.SelectionViewPagerAdapter;
import com.sainti.blackcard.goodthings.bean.SelectionChoiceBean;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.minterface.TimerListener;
import com.sainti.blackcard.mtuils.CommontUtil;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.goodthings.spcard.activity.ShoppingCardActivity;
import com.sainti.blackcard.goodthings.spcard.baen.ShoppingCardBean;

public class SelectionChoiceActivity extends BaseNoTitleActivity implements OnNetResultListener, View.OnClickListener, TimerListener {

    private TabLayout tabGoodthing;
    private ViewPager vpGoodthings;
    private SelectionViewPagerAdapter selectionViewPagerAdapter;
    private ImageView iv_ishasCard, iv_shopping_card;
    private RelativeLayout back;

    @Override
    public int setLayout() {
        return R.layout.activity_selection_choice;
    }

    @Override
    protected void initView() {
        iv_ishasCard = bindView(R.id.iv_ishasCard);
        iv_shopping_card = bindView(R.id.iv_shopping_card);
        back = bindView(R.id.back);
        tabGoodthing = (TabLayout) findViewById(R.id.tab_goodthing);
        vpGoodthings = (ViewPager) findViewById(R.id.vp_goodthings);
    }

    @Override
    protected void initEvents() {
        TurnClassHttp.getFuListFenLei(1, this, this);
        selectionViewPagerAdapter = new SelectionViewPagerAdapter(getSupportFragmentManager());
        vpGoodthings.setAdapter(selectionViewPagerAdapter);
        tabGoodthing.setupWithViewPager(vpGoodthings);
        back.setOnClickListener(this);
        iv_shopping_card.setOnClickListener(this);
    }


    @Override
    protected void initData() {
        CommontUtil.lateTime(1000,this);

    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        Gson gson = GsonSingle.getGson();
        SelectionChoiceBean selectionChoiceBean = gson.fromJson(stringResult, SelectionChoiceBean.class);
        int results = selectionChoiceBean.getResult();
        if (results == 1) {
            if (selectionChoiceBean != null) {
                selectionViewPagerAdapter.setChannelsBeen(selectionChoiceBean.getData());
            }
        }
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_shopping_card:
                startActivity(new Intent(SelectionChoiceActivity.this, ShoppingCardActivity.class));
                break;
            case R.id.back:
                finish();
                break;
        }
    }

    @Override
    public void onTimerListener() {
        TurnClassHttp.getWelcartDate(2, this, new OnNetResultListener() {
            @Override
            public void onSuccessfulListener(String stringResult, int resultCode) {
                Gson gson = GsonSingle.getGson();
                ShoppingCardBean shoppingCardBean = gson.fromJson(stringResult, ShoppingCardBean.class);
                if (shoppingCardBean != null && shoppingCardBean.getData() != null && shoppingCardBean.getData().size() > 0) {
                    iv_ishasCard.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailureListener(String errMsg, int resultCode) {

            }
        });
    }
}
