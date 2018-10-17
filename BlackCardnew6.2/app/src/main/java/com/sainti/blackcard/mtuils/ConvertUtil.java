package com.sainti.blackcard.mtuils;

import android.text.TextUtils;

import java.text.DecimalFormat;



/**
 * Created by YuZhenpeng on 2018/3/16.
 */

public class ConvertUtil {

    //把String转化为float
    public static float convertToFloat(String number) {
        if (!"".equals(number) && number != null) {
            return Float.parseFloat(number);
        }
        return 0;

    }

    public static String convertToString(float number) {

        DecimalFormat decimalFormat = new DecimalFormat("00.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        String p = decimalFormat.format(number);//format 返回的是字符串
        return p;


    }
    public static String convertToStrings(float number) {

        DecimalFormat decimalFormat = new DecimalFormat("00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        String p = decimalFormat.format(number);//format 返回的是字符串
        return p;


    }
    //把String转化为double
    public static double convertToDouble(String number, double defaultValue) {
        if (TextUtils.isEmpty(number)) {
            return defaultValue;
        }
        try {
            return Double.parseDouble(number);
        } catch (Exception e) {
            return defaultValue;
        }

    }

    //把String转化为int
    public static int convertToInt(String number, int defaultValue) {
        if (TextUtils.isEmpty(number)) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(number);
        } catch (Exception e) {
            return defaultValue;
        }

    }

    public static String getTime() {

        long timeStamp = System.currentTimeMillis();

        String str = String.valueOf(timeStamp);

        return str;

    }
    /**
     * double转String,保留小数点后1位
     * @param num
     * @return
     */
    public static String doubleToString(double num){
        //使用0.00不足位补0，#.##仅保留有效位
        return new DecimalFormat("0.00").format(num);
    }
    /**
     * double转String,保留小数点后1位
     * @param num
     * @return
     */
    public static String doubleToSt(double num){
        //使用0.00不足位补0，#.##仅保留有效位
        return new DecimalFormat("0.00").format(num);
    }
}
