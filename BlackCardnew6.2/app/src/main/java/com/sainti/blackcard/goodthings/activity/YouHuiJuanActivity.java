package com.sainti.blackcard.goodthings.activity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.mtuils.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;

public class YouHuiJuanActivity extends BaseTitleActivity {

    private LinearLayout lin_youhuijuan;
    private JSONArray youhuijuan = new JSONArray();
    private int type = 0;
    private TextView tv_youhuiquan;


    @Override
    public int setLayout() {
        return R.layout.activity_you_hui_juan;
    }

    @Override
    protected void initView() {

        lin_youhuijuan = (LinearLayout) findViewById(R.id.lin_youhuijuan);
        tv_youhuiquan = (TextView) findViewById(R.id.tv_youhuiquan);
    }

    @Override
    protected void initEvent() {
        getBaseBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(200);
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        setTitle("优惠券");
        if (getIntent().getIntExtra("you", 0) == 0) {
            try {
                youhuijuan = new JSONArray(getIntent().getStringExtra("youhuijuan"));
                type = getIntent().getIntExtra("type", 0);
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
            for (int i = 0; i < youhuijuan.length(); i++) {
                View view = LayoutInflater.from(this).inflate(R.layout.item_youhuijuan, null);
                TextView tv_mingcheng = (TextView) view.findViewById(R.id.tv_mingcheng);
                TextView tv_time = (TextView) view.findViewById(R.id.tv_time);
                TextView tv_jiage = (TextView) view.findViewById(R.id.tv_jiage);
                View view_yinying = view.findViewById(R.id.view_yinying);
                try {
                    tv_mingcheng.setText(youhuijuan.getJSONObject(i).getString("co_name"));
                    tv_time.setText(youhuijuan.getJSONObject(i).getString("cl_time"));
                    tv_jiage.setText(youhuijuan.getJSONObject(i).getString("co_dizhi"));
                    if (type == 0) {
                        if (youhuijuan.getJSONObject(i).getString("cl_keyong").equals("1")) {
                            view_yinying.setVisibility(View.VISIBLE);
                        }
                        final int finalI = i;
                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                try {
                                    if (youhuijuan.getJSONObject(finalI).getString("cl_keyong").equals("0")) {
                                        Intent intent = new Intent();
                                        intent.putExtra("position", finalI);
                                        setResult(100, intent);
                                        finish();
                                    } else {
                                        ToastUtils.show(YouHuiJuanActivity.this, "抱歉，使用此优惠券条件不满足");
                                    }
                                } catch (JSONException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        });
                    }
                    lin_youhuijuan.addView(view);
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        } else {
            tv_youhuiquan.setVisibility(View.VISIBLE);
        }
    }
}
