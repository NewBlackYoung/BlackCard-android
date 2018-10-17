package com.sainti.blackcard.coffee.activity;


import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.coffee.bean.AddSuccessBean;
import com.sainti.blackcard.goodthings.bean.JsonBean;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.mtuils.GetJsonDataUtil;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.widget.LoadingView;

import org.json.JSONArray;

import java.util.ArrayList;


public class AddressSettingActivity extends BaseTitleActivity implements View.OnClickListener, OnNetResultListener {


    private EditText etNameUser, et_namberUser, tv_xiangxiadress;
    private TextView tvSave, tv_choice, tv_adress;
    private int a;
    private LoadingView loadingView;
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private boolean isLoaded = false;
    private String sheng;
    private String shi;
    private String qu;

    @Override
    public int setLayout() {
        return R.layout.activity_coffeeadress;
    }

    @Override
    protected void initView() {
        etNameUser = bindView(R.id.et_nameUser);
        et_namberUser = bindView(R.id.et_namberUser);
        tv_xiangxiadress = bindView(R.id.tv_xiangxiadress);
        tvSave = bindView(R.id.tv_save);
        loadingView = new LoadingView(this);
        tv_choice = bindView(R.id.tv_choice);
        tv_adress = bindView(R.id.tv_adress);



    }

    @Override
    protected void initEvent() {
        tvSave.setOnClickListener(this);
        tv_choice.setOnClickListener(this);
        tv_adress.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        setTitle("设置地址");
        setDate(); // 设置数据
        getBaseBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initJsonData();// 解析城市数据
    }


    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tv_save:
                check();
                break;
            case R.id.tv_choice:
                showPickerView();// 设置地址
                break;
            case R.id.tv_adress:
                showPickerView();// 设置地址
                break;
        }
    }

    private void check() {
        String name = etNameUser.getText().toString();
        String number = et_namberUser.getText().toString();
        String xiangXi = tv_xiangxiadress.getText().toString();
        if (name.equals("")) {
            Toast.makeText(this, "请填写收货人姓名", Toast.LENGTH_SHORT).show();
            return;
        }
        if (number.equals("")) {
            Toast.makeText(this, "请填写收货人电话", Toast.LENGTH_SHORT).show();
            return;
        }
        if (qu.equals("") || shi.equals("")) {
            Toast.makeText(this, "请补全市区信息", Toast.LENGTH_SHORT).show();
            return;
        }

        if (xiangXi.equals("")) {
            Toast.makeText(this, "请填写收货人详细地址", Toast.LENGTH_SHORT).show();
            return;
        }
        loadingView.show();
        TurnClassHttp.AddAdreeFanWei(shi, xiangXi, name, number, sheng, qu, 1, this, this);
    }


    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        loadingView.dismiss();
        Gson gson = GsonSingle.getGson();
        AddSuccessBean successBean = gson.fromJson(stringResult, AddSuccessBean.class);
        if (successBean.getResult() == 1) {
            Intent intent = new Intent();
            // 获取用户计算后的结果
            setResult(200, intent);
            finish(); //结束当前的activity的生命周期
        }
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {

    }

    private void setDate() {
        Intent intent = getIntent();
        String code = intent.getStringExtra("code");
        if (code.equals("1")) {
            String xoName = intent.getStringExtra("xoName");
            String xoTel = intent.getStringExtra("xoTel");
            String xoProvince = intent.getStringExtra("xoProvince");
            String xoCity = intent.getStringExtra("xoCity");
            String xoArea = intent.getStringExtra("xoArea");
            String xoAddress = intent.getStringExtra("xoAddress");
            etNameUser.setText(xoName);
            et_namberUser.setText(xoTel);
            tv_adress.setText(xoProvince + xoCity + xoArea);
            tv_xiangxiadress.setText(xoAddress);
            sheng = xoProvince;
            shi = xoCity;
            qu = xoArea;
        }
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
                tv_adress.setText(sheng + shi + qu);

            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

}
