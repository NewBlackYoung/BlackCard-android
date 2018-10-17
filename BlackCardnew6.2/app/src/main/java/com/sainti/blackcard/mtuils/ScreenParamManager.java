package com.sainti.blackcard.mtuils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;


/**
 * Created on 2017/2/23.
 * 陈国辉
 * 封装的设置视图宽高类
 */

public class ScreenParamManager {
    public ScreenParamManager(Context context) {
        this.context = context;
    }

    //  获取屏幕宽度
    public int getScreenWidth() {
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    //  获取屏幕高度
    public int getScreenHeight() {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }

    // 给视图设置想要的宽高
    public void settingViewParam(int width, int heiht, View view) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.width = getScreenWidth() / width;
        lp.height = getScreenHeight() / heiht;
        view.setLayoutParams(lp);

    }

    // 屏幕的宽度减去参数宽度
    public void settingViewWidth(float width, View view) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.width = (int) (getScreenWidth() - width);
        view.setLayoutParams(lp);

    }

    public void settingViewHeiht(View view) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.height = getScreenHeight()-getHeight(context);
        view.setLayoutParams(lp);

    }
    public void _settingViewHeiht(View view) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.height = getScreenHeight();
        view.setLayoutParams(lp);

    }
    // 给视图设置想要的高
    public void settingViewHeigt(float height, View view) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.height = (int) (getScreenHeight() / height);
        view.setLayoutParams(lp);

    }

    /**
     * 获取状态栏高度——方法1
     */
    int statusBarHeight1 = -1;
    //获取status_bar_height资源的ID
    private Context context;

    public int getHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = context.getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight1;
    }


}
