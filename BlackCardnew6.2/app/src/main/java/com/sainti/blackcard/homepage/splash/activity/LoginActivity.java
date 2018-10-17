package com.sainti.blackcard.homepage.splash.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseNoTitleActivity;
import com.sainti.blackcard.commen.mconstant.SataicCode;
import com.sainti.blackcard.commen.mconstant.SpCodeName;
import com.sainti.blackcard.meventbus.NormalEventBean;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.minterface.MyItemClickListener;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.mtuils.SpUtil;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.mwebview.NormalWebActivity;
import com.sainti.blackcard.homepage.splash.adapter.MyAdapter;
import com.sainti.blackcard.homepage.splash.bean.GuangGaoBean;
import com.sainti.blackcard.widget.BottomLineLayout;
import com.sainti.blackcard.widget.LoadingView;
import com.sainti.blackcard.widget.ViewPagerTransform;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class LoginActivity extends BaseNoTitleActivity implements ViewPager.OnPageChangeListener, OnNetResultListener, MyItemClickListener {
    private Button bren;
    private Button bjoin;
    private ViewPagerTransform viewPager;
    private long sExitTime = 0;
    private static final int NEWDATACODE = 0;
    private MyAdapter myAdapter;
    private BottomLineLayout bottomLineLayout;
    private LoadingView loadingView;
    private String webjinDiu;

    @Override
    public int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        bren = (Button) findViewById(R.id.bren);
        bjoin = (Button) findViewById(R.id.bjoin);
        bren.setOnClickListener(mListener);
        bjoin.setOnClickListener(mListener);
        bottomLineLayout = (BottomLineLayout) findViewById(R.id.bottomlayoutl);
        viewPager = (ViewPagerTransform) findViewById(R.id.viewpager);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initEvents() {
        viewPager.setOnPageChangeListener(this);

    }

    @Override
    protected void initData() {
        loadingView = new LoadingView(this);
        viewPager.setPageMargin(40);  // 设置页面间距
        myAdapter = new MyAdapter(this);
        myAdapter.setLitener(this);
        viewPager.setOffscreenPageLimit(5);
        viewPager.setAdapter(myAdapter);
        loadingView.show();
        TurnClassHttp.getshanping(NEWDATACODE, this, this);

    }

    private View.OnClickListener mListener = new View.OnClickListener() {

        private Intent intent;

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bren:
                    intent = new Intent(LoginActivity.this, MemberLoginActivity.class);
                    startActivity(intent);
                    // }
                    break;
                case R.id.bjoin:
                    intent = new Intent(LoginActivity.this, NormalWebActivity.class);
                    intent.putExtra("code", "2");
                    intent.putExtra("url",
                            "http://qing-hei.com/mobile.php/Order/index/type/1/?ad=app&channel=weibo");
                    startActivity(intent);
                    break;

                default:
                    break;
            }
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if ((System.currentTimeMillis() - sExitTime) > 2000) {
                ToastUtils.show(this, "再按一次退出程序");
                sExitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (bottomLineLayout != null) {
            bottomLineLayout.changePosition(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        loadingView.dismiss();
        Gson gson = GsonSingle.getGson();
        GuangGaoBean guangGaoBean = gson.fromJson(stringResult, GuangGaoBean.class);
        if (guangGaoBean.getResult() == 1) {
            myAdapter.setWelfareBeanList(guangGaoBean.getData());
            bottomLineLayout.initViews(guangGaoBean.getData().size(), 30, 20);
            webjinDiu = guangGaoBean.getQueryorder();
            SpUtil.initEditor(SpCodeName.SPNAME).putString(SpCodeName.CARDJINDU,webjinDiu).commit();
        }
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        loadingView.dismiss();
        ToastUtils.show(this, errMsg);
    }



    /* 用于接收微信支付结果回调*/
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(NormalEventBean messageEvent) {

        if (messageEvent.getMessageCode().equals(SataicCode.FINISHCHOICELOGIN)) {
            finish();
        }

    }

    @Override
    public void onItemClick(int position, int code) {
        return;
    }
}
