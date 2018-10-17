package com.sainti.blackcard.goodthings.activity;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.goodthings.bean.JsonBean;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.mtuils.GetJsonDataUtil;
import com.sainti.blackcard.mtuils.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class XinZengDiZhiActivity extends BaseTitleActivity implements View.OnClickListener, OnNetResultListener {

    private View view_yuan, view_yinying;
    private Context context;
    private EditText ed_name, rd_phone, ed_xiangxi;
    private TextView tv_diqu, tv_baocun;
    private String sheng, shi, qu, xiangxi;
    private int type;
    private String ad_id, xingming, dianhua;
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private boolean isLoaded = false;

    @Override
    public int setLayout() {
        return R.layout.activity_xin_zeng_di_zhi;
    }

    @Override
    protected void initView() {
        context = this;
        view_yuan = findViewById(R.id.view_yuan);
        view_yinying = findViewById(R.id.view_yinying);
        ed_name = (EditText) findViewById(R.id.ed_name);
        rd_phone = (EditText) findViewById(R.id.rd_phone);
        ed_xiangxi = (EditText) findViewById(R.id.ed_xiangxi);
        tv_diqu = (TextView) findViewById(R.id.tv_diqu);
        tv_baocun = (TextView) findViewById(R.id.tv_baocun);

    }

    @Override
    protected void initEvent() {

        tv_baocun.setOnClickListener(this);
        tv_diqu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPickerView();// 设置地址
            }
        });
    }

    @Override
    protected void initData() {
        setTitle("地址填写");
        type = getIntent().getIntExtra("type", 0);
        if (type == 1) {
            sheng = getIntent().getStringExtra("sheng");
            shi = getIntent().getStringExtra("shi");
            qu = getIntent().getStringExtra("qu");
            xiangxi = getIntent().getStringExtra("xiangxi");
            dianhua = getIntent().getStringExtra("dianhua");
            xingming = getIntent().getStringExtra("xingming");
            ad_id = getIntent().getStringExtra("ad_id");
            ed_name.setText(xingming);
            rd_phone.setText(dianhua);
            tv_diqu.setText(sheng + shi + qu);
            ed_xiangxi.setText(xiangxi);
        }

        initJsonData();// 解析城市数据
    }

    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市
                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {
                    City_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }

        isLoaded = true;

    }

    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            isLoaded = false;
        }
        return detail;
    }

    private void showPickerView() {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText() +
                        options2Items.get(options1).get(options2) +
                        options3Items.get(options1).get(options2).get(options3);
                sheng = options1Items.get(options1).getPickerViewText();
                shi = options2Items.get(options1).get(options2);
                qu = options3Items.get(options1).get(options2).get(options3);
                tv_diqu.setText(sheng + shi + qu);
                ed_xiangxi.requestFocus();
            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_baocun:
                if (ed_name.getText().toString() != null && ed_name.getText().toString().length() > 0) {
                    if (rd_phone.getText().toString() != null && rd_phone.getText().toString().length() == 11) {
                        if (sheng != null && shi != null && qu != null && sheng.length() * shi.length() * qu.length() > 0) {
                            if (ed_xiangxi.getText().toString() != null && ed_xiangxi.getText().toString().length() > 0) {
                                if (type == 0) {
                                    addDiZhi();
                                } else if (type == 1) {
                                    upDateDiZhi();
                                }
                                view_yinying.setVisibility(View.VISIBLE);
                                view_yuan.setVisibility(View.VISIBLE);

                            } else {
                                ToastUtils.show(context, "请输入详细地址");
                            }
                        } else {
                            ToastUtils.show(context, "请输入所在地区");
                        }
                    } else {
                        ToastUtils.show(context, "请输入正确的联系方式");
                    }
                } else {
                    ToastUtils.show(context, "请输入收件人姓名");
                }
                break;
            case R.id.orderback:
                setResult(200);
                finish();
                break;
        }
    }

    public void addDiZhi() {
        TurnClassHttp.addDiZhi(sheng, shi, qu, ed_xiangxi.getText().toString(), ed_name.getText().toString(), rd_phone.getText().toString(), 1, this, this);
    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        switch (resultCode) {
            case 1:
                try {
                    Log.i("shouhuodizhi", "新增地址结果" + stringResult);
                    JSONObject object = new JSONObject(stringResult);
                    if (object.getString("result").equals("1")) {
                        setResult(100);
                        finish();
                    } else {
                        ToastUtils.show(context, object.getString("msg"));
                    }
                    view_yinying.setVisibility(View.GONE);
                    view_yuan.setVisibility(View.GONE);

                } catch (JSONException e) {
                    view_yinying.setVisibility(View.GONE);

                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    Log.i("shouhuodizhi", "修改地址结果" + stringResult);
                    JSONObject object = new JSONObject(stringResult);
                    if (object.getString("result").equals("1")) {
                        setResult(100);
                        finish();
                    } else {
                        ToastUtils.show(context, object.getString("msg"));
                    }
                    view_yinying.setVisibility(View.GONE);
                    view_yuan.setVisibility(View.GONE);

                } catch (JSONException e) {
                    view_yinying.setVisibility(View.GONE);
                    view_yuan.setVisibility(View.GONE);

                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        switch (resultCode) {
            case 1:
                view_yinying.setVisibility(View.GONE);
                view_yuan.setVisibility(View.GONE);
                break;
            case 2:
                view_yinying.setVisibility(View.GONE);
                view_yuan.setVisibility(View.GONE);
                break;
        }
    }

    public void upDateDiZhi() {
        TurnClassHttp.upDateDiZhi(sheng, shi, qu, ed_xiangxi.getText().toString(), ed_name.getText().toString(), rd_phone.getText().toString(), ad_id, "0", 2, this, this);
    }
}
