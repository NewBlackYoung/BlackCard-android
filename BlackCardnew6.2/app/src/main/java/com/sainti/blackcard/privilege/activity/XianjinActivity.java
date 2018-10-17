package com.sainti.blackcard.privilege.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.mwebview.PingAnWebView;

public class XianjinActivity extends BaseTitleActivity implements View.OnClickListener, BaseTitleActivity.OnClickRightTextCallBack {


    private ImageView top;

    @Override
    public int setLayout() {
        return R.layout.activity_xianjin;
    }

    @Override
    protected void initView() {
        top = bindView(R.id.top);

    }

    @Override
    protected void initEvent() {
        top.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.top:
                Intent intent = new Intent(this, PingAnWebView.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void initData() {
        setTitle("黑卡余额");
        setBaseRightText("旧版钱包", this);
    }

    @Override
    public void clickRightText() {
        Intent intent = new Intent(this, QianBaoActivity.class);
       startActivity(intent);
    }
}
