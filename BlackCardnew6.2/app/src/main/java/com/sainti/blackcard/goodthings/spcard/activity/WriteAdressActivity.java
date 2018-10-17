package com.sainti.blackcard.goodthings.spcard.activity;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.goodthings.spcard.baen.BaseBean;

import org.json.JSONArray;

import java.util.ArrayList;

public class WriteAdressActivity extends BaseTitleActivity implements OnNetResultListener {


    private TextView et_address;
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private boolean isLoaded = false;
    private TextView tv_save;
    private String ad_province;
    private String ad_city;
    private String ad_area;
    private EditText et_nameUser, et_namberUser, tv_xiangxiadress;
    private String name;
    private String number;
    private String adress;
    private String code;
    private String ad_id;
    private String ad_moren;

    @Override
    public int setLayout() {
        return R.layout.activity_write_adress;
    }

    @Override
    protected void initView() {
        et_nameUser = bindView(R.id.et_nameUser);
        et_namberUser = bindView(R.id.et_namberUser);
        tv_xiangxiadress = bindView(R.id.tv_xiangxiadress);
        tv_save = bindView(R.id.tv_save);
        et_address = bindView(R.id.et_address);
    }

    @Override
    protected void initEvent() {
        et_address.setOnClickListener(this);
        tv_save.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        setTitle("增添地址");
        getBaseBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initJsonData();// 解析城市数据
        getIntentDate();
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.et_address:
                if (isLoaded) {
                    showPickerView();
                    closeKeyboard();
                } else {
                    ToastUtils.show(this, "解析地址失败！请重新尝试");
                }
                break;
            case R.id.tv_save:
                if (check()) {
                    if (code.equals("0")) {
                        TurnClassHttp.addDiZhi(ad_province, ad_city, ad_area, adress, name, number, 1,this,this);// 添加地址
                    }
                    if (code.equals("1")) {
                        TurnClassHttp.upDateDiZhi( ad_province, ad_city, ad_area, adress, name, number, ad_id, ad_moren, 2,this,this);// 修改地址
                    }
                }
                break;
        }
    }



    private void getIntentDate() {
        code = getIntent().getStringExtra("code");
        if (code.equals("1")) {
            ad_id = getIntent().getStringExtra("ad_id");
            ad_moren = getIntent().getStringExtra("ad_moren");
            ad_province = getIntent().getStringExtra("province");
            ad_city = getIntent().getStringExtra("city");
            ad_area = getIntent().getStringExtra("area");
            et_address.setText(ad_province + ad_city + ad_area);
            tv_xiangxiadress.setText(getIntent().getStringExtra("address"));
            et_nameUser.setText(getIntent().getStringExtra("user"));
            et_namberUser.setText(getIntent().getStringExtra("tel"));
        }
    }

    private void showPickerView() {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText() +
                        options2Items.get(options1).get(options2) +
                        options3Items.get(options1).get(options2).get(options3);
                ad_province = options1Items.get(options1).getPickerViewText();
                ad_city = options2Items.get(options1).get(options2);
                ad_area = options3Items.get(options1).get(options2).get(options3);
                et_address.setText(ad_province + ad_city + ad_area);

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

    //
    private boolean check() {
        name = et_nameUser.getText().toString();
        number = et_namberUser.getText().toString();
        adress = tv_xiangxiadress.getText().toString();
        String et_addresss = et_address.getText().toString();
        if (name == null || name.equals("")) {
            ToastUtils.show(this, "请输入姓名");
            return false;
        }
        if (number == null || name.equals("")) {
            ToastUtils.show(this, "请输入手机号码");
            return false;
        }
        if (adress == null || name.equals("")) {
            ToastUtils.show(this, "请输入详细地址");
            return false;
        }
        if (et_addresss == null || et_addresss.equals("")) {
            ToastUtils.show(this, "请选择所在地区");
            return false;
        }
        return true;
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

    /*关闭键盘*/
    private void closeKeyboard() {
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        switch (resultCode) {
            case 1:
                Gson gson = GsonSingle.getGson();
                BaseBean baseBean = gson.fromJson(stringResult, BaseBean.class);
                if (baseBean.getResult() == 1) {
                    ToastUtils.show(this, "保存地址成功");
                    setResult(113);
                    finish();
                }
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        switch (resultCode) {
            case 1:
                ToastUtils.show(this, "保存地址失败！请稍后尝试");
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }
}
