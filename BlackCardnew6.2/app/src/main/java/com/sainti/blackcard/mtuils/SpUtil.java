package com.sainti.blackcard.mtuils;

import android.content.Context;
import android.content.SharedPreferences;


import com.sainti.blackcard.base.MyApp;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 * @author chenguohui
 *         SharedPerfences的封装类
 */
public class SpUtil {
    private static SpUtil spUtil = null;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    private SpUtil() {
        context = MyApp.getInstance().getmContext();
    }

    private static SpUtil getInstance() {
        if (spUtil == null) {
            spUtil = new SpUtil();
        }
        return spUtil;
    }

    public SharedPreferences getSharedPreferences(String spName) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(spName, context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }

    public SharedPreferences.Editor getEditor(String spName) {
        if (editor == null) {
            editor = getInstance().getSharedPreferences(spName).edit();
        }
        return editor;
    }

    //获得Editor对象
    public static SharedPreferences.Editor initEditor(String spName) {
        return getInstance().getEditor(spName);
    }

    //以下是获取Sp中保存的值
    public static String getString(String spName, String key, String defaultValue) {
        return getInstance().getSharedPreferences(spName).getString(key, defaultValue);
    }

    public static boolean getBoolean(String spName, String key, boolean defaultValue) {
        return getInstance().getSharedPreferences(spName).getBoolean(key, defaultValue);
    }

    public static int getInt(String spName, String key, int defaultValue) {
        return getInstance().getSharedPreferences(spName).getInt(key, defaultValue);
    }

    public static float getFloat(String spName, String key, int defaultValue) {
        return getInstance().getSharedPreferences(spName).getFloat(key, defaultValue);
    }

    public static long getLong(String spName, String key, int defaultValue) {
        return getInstance().getSharedPreferences(spName).getLong(key, defaultValue);
    }

    public static Map<String, ?> getAll(String name) {
        return getInstance().getSharedPreferences(name).getAll();
    }

    // 清除Sp
    public static void clear(String spName) {
        Map sharedMap = getInstance().getSharedPreferences(spName).getAll();
        Set set = sharedMap.entrySet();
        Iterator i = set.iterator();
        while (i.hasNext()) {
            Map.Entry<String, String> entry1 = (Map.Entry<String, String>) i.next();
            if (!"isFirst".equals(entry1.getKey())) {
                getInstance().getEditor(spName).remove(entry1.getKey()).commit();
            }
        }
    }
}
