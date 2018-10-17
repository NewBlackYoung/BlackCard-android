package com.sainti.blackcard.circle.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseBean;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.circle.adapter.DaKaAdapter;
import com.sainti.blackcard.circle.bean.DakaBean;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.minterface.TimerListenerHasCode;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.mtuils.TimeUtil;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.widget.LoadingView;

public class DakaActivity extends BaseTitleActivity implements OnNetResultListener, View.OnClickListener, TimerListenerHasCode {


    private RecyclerView rv_daka;
    private LoadingView loadingView;
    private DaKaAdapter daKaAdapter;
    private TextView daka;

    @Override
    public int setLayout() {
        return R.layout.activity_daka;
    }

    @Override
    protected void initView() {
        loadingView = new LoadingView(this);
        rv_daka = bindView(R.id.rv_daka);
        rv_daka.setLayoutManager(new GridLayoutManager(this, 7));
        daka = bindView(R.id.daka);

    }

    @Override
    protected void initEvent() {
        daka.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        setTitle("打卡");
        loadingView.show();
        TurnClassHttp.signIn(1, this, this);
        daKaAdapter = new DaKaAdapter(R.layout.item_daka);
        daKaAdapter.setContext(context);
        rv_daka.setAdapter(daKaAdapter);

    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {

        switch (resultCode) {
            case 1:
                loadingView.dismiss();
                DakaBean dakaBean = GsonSingle.getGson().fromJson(stringResult, DakaBean.class);
                daKaAdapter.setNewData(dakaBean.getData().getDays());
                for (int i = 0; i < dakaBean.getData().getDays().size(); i++) {
                    if (dakaBean.getData().getDays().get(i).getSign_date().equals(TimeUtil.getNowDates())){
                        if (dakaBean.getData().getDays().get(i).getIs_sign().equals("1")){
                            daka.setClickable(false);
                            daka.setBackgroundResource(R.mipmap.daka_s);
                        }else {
                            daka.setClickable(true);
                            daka.setBackgroundResource(R.mipmap.iv_daka);
                        }
                    }
                }
                break;
            case 2:
                BaseBean baseBean = GsonSingle.getGson().fromJson(stringResult, BaseBean.class);
                if (baseBean.getResult().equals("1")) {
                    initData();

                } else {
                    loadingView.dismiss();
                    ToastUtils.show(this, baseBean.getMsg());
                }
                break;
        }
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        loadingView.dismiss();
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.daka:
                loadingView.show();
                TurnClassHttp.doSignIn(2, this, this);
                break;
        }
    }

    @Override
    public void onTimerListener(int code) {
        TurnClassHttp.signIn(1, this, this);
    }
}
