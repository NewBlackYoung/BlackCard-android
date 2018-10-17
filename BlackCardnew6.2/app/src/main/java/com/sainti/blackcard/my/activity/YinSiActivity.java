package com.sainti.blackcard.my.activity;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseNoTitleActivity;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.widget.EaseSwitchButton;

import org.json.JSONException;
import org.json.JSONObject;

public class YinSiActivity extends BaseNoTitleActivity implements OnNetResultListener, View.OnClickListener {

    private EaseSwitchButton age_suo, job_suo, bir_suo, area_suo, hobby_suo;
    private TextView tv_save;
    private ImageView moreback;
    private Context context;
    private String age_qx = "0";
    private String birth_qx = "0";
    private String location_qx = "0";
    private String business_qx = "0";
    private String hobby_qx = "0";
    private TextView tv_age, tv_job, tv_bir, tv_area, tv_hobby;
    private String a;

    @Override
    public int setLayout() {
        return R.layout.activity_yin_si;
    }

    @Override
    protected void initView() {
        context = this;
        age_suo = (EaseSwitchButton) findViewById(R.id.age_suo);
        job_suo = (EaseSwitchButton) findViewById(R.id.job_suo);
        bir_suo = (EaseSwitchButton) findViewById(R.id.bir_suo);
        area_suo = (EaseSwitchButton) findViewById(R.id.area_suo);
        hobby_suo = (EaseSwitchButton) findViewById(R.id.hobby_suo);
        tv_save = (TextView) findViewById(R.id.tv_save);
        tv_age = (TextView) findViewById(R.id.tv_age);
        tv_job = (TextView) findViewById(R.id.tv_job);
        tv_bir = (TextView) findViewById(R.id.tv_bir);
        tv_area = (TextView) findViewById(R.id.tv_area);
        tv_hobby = (TextView) findViewById(R.id.tv_hobby);
        moreback = (ImageView) findViewById(R.id.moreback);
        tv_save.setOnClickListener(this);
        moreback.setOnClickListener(this);
        age_suo.setOnClickListener(this);
        job_suo.setOnClickListener(this);
        bir_suo.setOnClickListener(this);
        area_suo.setOnClickListener(this);
        hobby_suo.setOnClickListener(this);
        saveState("0");
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {

    }
    private void saveState(final String action) {
        TurnClassHttp.setYinSi(age_qx, birth_qx, location_qx, business_qx, hobby_qx, action,1,this,this);
        a = action;

    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        try {
            JSONObject object = new JSONObject(stringResult);
            JSONObject data = object.getJSONObject("data");
            if (a.equals("0")) {
                if (data.getString("age_qx").equals("0")) {
                    age_qx = "0";
                    tv_age.setText("保密");
                    age_suo.openSwitch();
                } else {
                    age_qx = "1";
                    tv_age.setText("公开");
                    age_suo.closeSwitch();
                }
                if (data.getString("birth_qx").equals("0")) {
                    birth_qx = "0";
                    tv_bir.setText("保密");
                    bir_suo.openSwitch();
                } else {
                    birth_qx = "1";
                    tv_bir.setText("公开");
                    bir_suo.closeSwitch();
                }
                if (data.getString("location_qx").equals("0")) {
                    location_qx = "0";
                    tv_area.setText("保密");
                    area_suo.openSwitch();
                } else {
                    location_qx = "1";
                    tv_area.setText("公开");
                    area_suo.closeSwitch();
                }
                if (data.getString("business_qx").equals("0")) {
                    business_qx = "0";
                    tv_job.setText("保密");
                    job_suo.openSwitch();
                } else {
                    business_qx = "1";
                    tv_job.setText("公开");
                    job_suo.closeSwitch();
                }
                if (data.getString("hobby_qx").equals("0")) {
                    hobby_qx = "0";
                    tv_hobby.setText("保密");
                    hobby_suo.openSwitch();
                } else {
                    hobby_qx = "1";
                    tv_hobby.setText("公开");
                    hobby_suo.closeSwitch();
                }
            } else if (a.equals("1")) {
                ToastUtils.show(context, "保存成功!");
                finish();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.age_suo:
                if (age_suo.isSwitchOpen()) {
                    age_qx = "1";
                    tv_age.setText("公开");
                    age_suo.closeSwitch();
                } else {
                    age_qx = "0";
                    tv_age.setText("保密");
                    age_suo.openSwitch();
                }
                break;
            case R.id.job_suo:
                if (job_suo.isSwitchOpen()) {
                    business_qx = "1";
                    tv_job.setText("公开");
                    job_suo.closeSwitch();
                } else {
                    business_qx = "0";
                    tv_job.setText("保密");
                    job_suo.openSwitch();
                }
                break;
            case R.id.bir_suo:
                if (bir_suo.isSwitchOpen()) {
                    birth_qx = "1";
                    tv_bir.setText("公开");
                    bir_suo.closeSwitch();
                } else {
                    birth_qx = "0";
                    tv_bir.setText("保密");
                    bir_suo.openSwitch();
                }
                break;
            case R.id.area_suo:
                if (area_suo.isSwitchOpen()) {
                    location_qx = "1";
                    tv_area.setText("公开");
                    area_suo.closeSwitch();
                } else {
                    location_qx = "0";
                    tv_area.setText("保密");
                    area_suo.openSwitch();
                }
                break;
            case R.id.hobby_suo:
                if (hobby_suo.isSwitchOpen()) {
                    hobby_qx = "1";
                    tv_hobby.setText("公开");
                    hobby_suo.closeSwitch();
                } else {
                    hobby_qx = "0";
                    tv_hobby.setText("保密");
                    hobby_suo.openSwitch();
                }
                break;
            case R.id.moreback:
                finish();
                break;
            case R.id.tv_save:
                saveState("1");
                break;
        }
    }


}
