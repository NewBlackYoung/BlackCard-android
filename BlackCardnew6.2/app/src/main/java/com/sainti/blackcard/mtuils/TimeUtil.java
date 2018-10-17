package com.sainti.blackcard.mtuils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by YuZhenpeng on 2018/5/16.
 */

public class TimeUtil {



    /* 获取当前系统时间加上你想要的时间*/
    public static String getNowTime(int time) {
        long currentTime = System.currentTimeMillis();
        currentTime += time * 60 * 1000;
        Date da = new Date(currentTime);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "HH:mm");
        System.out.println(dateFormat.format(da));
        return dateFormat.format(da);
    }

    /* 获取当前系统时间加上你想要的时间*/
    public static String getNowDate() {
        long currentTime = System.currentTimeMillis();
        Date da = new Date(currentTime);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd");
        return dateFormat.format(da);
    }
    /* 获取当前系统时间加上你想要的时间*/
    public static String getNowTime() {
        long currentTime = System.currentTimeMillis();
        Date da = new Date(currentTime);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm");
        return dateFormat.format(da);
    }

    public static boolean boAddTime(String endTimes) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "HH:mm");
        try {
            Date enddate = dateFormat.parse(endTimes);//开始时间
            long currentTime = System.currentTimeMillis();
            currentTime += 50 * 60 * 1000;
            Date begindate = dateFormat.parse(dateFormat.format(currentTime));//开始时间
            MLog.d("aaaaaaaaa", "开始时间" + begindate.getTime() + "");
            MLog.d("aaaaaaaaa", "结束时间" + enddate.getTime() + "");
            if (begindate.getTime() > enddate.getTime()) {
                return false;
            } else {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            MLog.d("erro", "错误信息" + e + "");
        }

        return false;
    }

    /*获取配送时间列表*/
    public static List<String> getTIme() {
        List<String> list = new ArrayList<>();
        list.add("09:00");
        list.add("09:30");
        list.add("10:00");
        list.add("11:30");
        list.add("12:00");
        list.add("12:30");
        list.add("13:00");
        list.add("13:30");
        list.add("14:00");
        list.add("14:30");
        list.add("15:00");
        list.add("15:30");
        list.add("16:00");
        list.add("16:30");
        list.add("17:00");
        list.add("17:30");
        List<String> datelist = new ArrayList<>();
        datelist.add("立即送出");
        for (int i = 0; i < list.size(); i++) {
            if (boAddTime(list.get(i))) {
                datelist.add(list.get(i));
            }
        }
        return datelist;
    }

    /**
     * 2015-01-07 15:05:34
     *
     * @param strDate
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static Date strToDateHHMMSS(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    // 将毫秒转换为小时：分钟：秒格式
    public static String ms2HMS(int _ms) {
        String HMStime;
        _ms /= 1000;
        int hour = _ms / 3600;
        int mint = (_ms % 3600) / 60;
        int sed = _ms % 60;
        String hourStr = String.valueOf(hour);
        if (hour < 10) {
            hourStr = "0" + hourStr;
        }
        String mintStr = String.valueOf(mint);
        if (mint < 10) {
            mintStr = "0" + mintStr;
        }
        String sedStr = String.valueOf(sed);
        if (sed < 10) {
            sedStr = "0" + sedStr;
        }
        HMStime = hourStr + ":" + mintStr + ":" + sedStr;
        return HMStime;
    }

    /* 获取当前系统时间加上你想要的时间*/
    public static String getNowDates() {
        long currentTime = System.currentTimeMillis();
        Date da = new Date(currentTime);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd");
        return dateFormat.format(da);
    }
}
