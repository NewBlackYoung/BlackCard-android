package com.sainti.blackcard.mtuils;

import android.util.Log;
import android.widget.EditText;

import com.sainti.blackcard.coffee.coffeeorder.bean.CoffeelistBean;
import com.sainti.blackcard.db.bean.CoffeeLookBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Chenguohui on 2018/8/22.
 */

public class CoffeelistToArryUtil {
    /* 咖啡列表订单数据*/
    public static String listToJosnString(List<CoffeelistBean.DataBean> list, int ps) {
        JSONArray jsonArray;
        JSONObject object;
        JSONObject object2;
        String jsonString;
        JSONArray jsonArray1;
        //把一个集合转换成json格式的字符串
        String xf_price = list.get(ps).getXf_price();
        String fi_name = list.get(ps).getXf_list().getOrders().get(0).getFi_name();
        String fi_area = list.get(ps).getXf_list().getOrders().get(0).getFi_area();
        String fi_address = list.get(ps).getXf_list().getOrders().get(0).getFi_address();
        String fi_mobile = list.get(ps).getXf_list().getOrders().get(0).getFi_mobile();
        String xf_order_sn = list.get(ps).getXf_order_sn();
        jsonArray = null;
        object = null;
        jsonArray = new JSONArray();
        object = new JSONObject();
        for (int i = 0; i < list.get(ps).getXf_list().getOrderdetails().size(); i++) {  //遍历上面初始化的集合数据，把数据加入JSONObject里面
            //一个user对象，使用一个JSONObject对象来装
            object2 = new JSONObject();
            try {
                object2.put("or_num", list.get(ps).getXf_list().getOrderdetails().get(i).getOr_num());  //从集合取出数据，放入JSONObject里面 JSONObject对象和map差不多用法,以键和值形式存储数据
                object2.put("or_price", list.get(ps).getXf_list().getOrderdetails().get(i).getOr_price());
                object2.put("or_name", list.get(ps).getXf_list().getOrderdetails().get(i).getOr_name());
                object2.put("or_tid", xf_order_sn);
                jsonArray.put(object2); //把JSONObject对象装入jsonArray数组里面
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        jsonArray1 = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("fi_start_deliverytime", list.get(ps).getXf_list().getOrders().get(0).getFi_start_deliverytime());// 需送达时间
            jsonObject.put("fi_pay_amt", xf_price); //总金额
            jsonObject.put("fi_name", fi_name);// 姓名
            jsonObject.put("fi_area", fi_area); // 城市
            jsonObject.put("fi_address", fi_address); // 地址
            jsonObject.put("fi_note", ""); // 备注:发票，-其他
            jsonObject.put("fi_tid", xf_order_sn); // 订单号
            jsonObject.put("fi_mobile", fi_mobile);// 手机号码
            jsonArray1.put(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            object.put("orders", jsonArray1);
            object.put("orderdetails", jsonArray); //再把JSONArray数据加入JSONObject对象里面(数组也是对象)
            //object.put("time", "2013-11-14"); //这里还可以加入数据，这样json型字符串，就既有集合，又有普通数据
        } catch (JSONException e) {
            e.printStackTrace();
        }
        jsonString = null;
        jsonString = object.toString(); //把JSONObject转换成json格式的字符串
        Log.i("hck", "转换成json字符串: " + jsonString);
        return jsonString;
    }
    /* 咖啡列表订单数据*/
    public static String detailToJosnString(List<CoffeeLookBean> lookBeen, String fi_start_deliverytime, float zongPrice, String xoName, String xoCity , String xoArea, String xoAddress, EditText ed_beizhu ,String orderId,String xoTel) {
        JSONArray jsonArray;
        JSONObject object;
        JSONObject object2;
        String jsonString;
        JSONArray jsonArray1;
        jsonArray = null;
        object = null;
        jsonArray = new JSONArray();
        object = new JSONObject();
        for (int i = 0; i < lookBeen.size(); i++) {  //遍历上面初始化的集合数据，把数据加入JSONObject里面
            //一个user对象，使用一个JSONObject对象来装
            object2 = new JSONObject();
            try {
                object2.put("or_num", lookBeen.get(i).getChoiceCount());  //从集合取出数据，放入JSONObject里面 JSONObject对象和map差不多用法,以键和值形式存储数据
                object2.put("or_price", lookBeen.get(i).getCoffeePrice());
                object2.put("or_name", lookBeen.get(i).getCoffeeName() + " " + lookBeen.get(i).getCoffeeKind());
                object2.put("or_tid", orderId);
                jsonArray.put(object2); //把JSONObject对象装入jsonArray数组里面
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        jsonArray1 = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("fi_start_deliverytime", fi_start_deliverytime);// 需送达时间
            jsonObject.put("fi_pay_amt", ConvertUtil.convertToString(zongPrice)); //总金额
            jsonObject.put("fi_name", xoName);// 姓名
            jsonObject.put("fi_area", xoCity + "市"); // 城市
            jsonObject.put("fi_address", xoArea + "区" + xoAddress); // 地址
            jsonObject.put("fi_note", ed_beizhu.getText().toString().trim()); // 备注:发票，其他
            jsonObject.put("fi_tid", orderId); // 订单号
            jsonObject.put("fi_mobile", xoTel);// 手机
            // 号码
            jsonArray1.put(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            object.put("orders", jsonArray1);
            object.put("orderdetails", jsonArray); //再把JSONArray数据加入JSONObject对象里面(数组也是对象)
            //object.put("time", "2013-11-14"); //这里还可以加入数据，这样json型字符串，就既有集合，又有普通数据
        } catch (JSONException e) {
            e.printStackTrace();
        }
        jsonString = null;
        jsonString = object.toString(); //把JSONObject转换成json格式的字符串
        Log.i("hck", "转换成json字符串: " + jsonString);
        return jsonString;
    }
}
