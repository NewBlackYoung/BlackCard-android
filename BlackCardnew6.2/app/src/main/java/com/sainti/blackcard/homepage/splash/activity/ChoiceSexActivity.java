package com.sainti.blackcard.homepage.splash.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseTitleActivity;

public class ChoiceSexActivity extends BaseTitleActivity implements View.OnClickListener {

    private RelativeLayout rlboy, rlgirl;
    private ImageView imgboy, imggirl;

    @Override
    public int setLayout() {
        return R.layout.activity_choice_sex;
    }

    @Override
    protected void initView() {
        rlboy = bindView(R.id.rlboy);
        rlgirl = bindView(R.id.rlgirl);
        imgboy = bindView(R.id.imgboy);
        imggirl = bindView(R.id.imggirl);

    }

    @Override
    protected void initEvent() {
        rlgirl.setOnClickListener(this);
        rlboy.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        setTitle("性别");
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.rlboy:
                setResult(111);
                finish();
                break;
            case R.id.rlgirl:
                setResult(112);
                finish();
                break;
        }
    }
}
