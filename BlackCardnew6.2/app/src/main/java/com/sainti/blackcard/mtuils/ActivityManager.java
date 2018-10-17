package com.sainti.blackcard.mtuils;

import android.app.Activity;

import java.util.Stack;

/**
 * **********************************************************************
 * **********************************************************************
 */
public class ActivityManager {

    private static ActivityManager instance;
    private Stack<Activity> activityStack; // activity栈

    private ActivityManager() {
    }

    // 单例模式
    public static ActivityManager getInstance() {
        if (instance == null) {
            instance = new ActivityManager();
        }
        return instance;
    }

    /**
     * 压栈
     *
     * @param actvity
     */
    public void pushOneActivity(Activity actvity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(actvity);
    }

    /**
     * 出栈，移除某个activity
     *
     * @param activity
     * @return
     */
    public void popActivity(Activity activity) {
        activityStack.remove(activity);
    }

    /**
     * 出栈
     *
     * @return
     */
    public void popActivity() {
        if (!activityStack.isEmpty()) {
            activityStack.pop();
        }
    }

    // 获取栈顶的activity，先进后出原则
    public Activity getLastActivity() {
        return activityStack.lastElement();
    }

    // 移除一个activity
    public void finishActivity(Activity activity) {
        if (activityStack != null && activityStack.size() > 0) {
            if (activity != null) {
                activity.finish();
                activityStack.remove(activity);
                activity = null;
            }

        }
    }

    // 退出所有activity
    public void finishAllActivity() {
        if (activityStack != null) {
            while (activityStack.size() > 0) {
                Activity activity = getLastActivity();
                if (activity == null)
                    break;
                finishActivity(activity);
            }
        }
    }

    // 取得Activity数量
    public int getActivitySize() {
        if (activityStack == null) {
            return 0;
        }
        return activityStack.size();
    }

    /**
     * 取当前activity
     *
     * @return
     */
    public Activity getCurrentActivity() {
        if (activityStack.isEmpty()) {
            return null;
        }
        return activityStack.peek();
    }

    /**
     * 关闭除指定activity以外的其他activity
     *
     * @param activity
     */
    public void finishOtherActivity(Activity activity) {
        if (activity == null) {
            return;
        }
        for (int i = 0; i < activityStack.size(); i++) {
            Activity a = activityStack.get(0);
            if (a != null && !a.equals(activity)) {
                a.finish();
            }
        }
        activityStack.clear();
        activityStack.push(activity);
    }

    /**
     * 根据类名关闭activity
     *
     * @param obj
     */
    public void finishActivityByClassName(Class obj) {
        int i = 0;
        while (i < activityStack.size()) {
            Activity a = activityStack.get(i);
            if (a.getClass().getName().equals(obj.getName())) {
                activityStack.remove(i);
                a.finish();
                i--;
            }
            i++;
        }
    }

    /**
     * 关闭当前activity
     */
    public void finishCurrentActivity() {
        finishActivity(getCurrentActivity());
    }


    /**
     * activity跳转动画
     */
    public void enterAnimation(Activity activity) {
//        activity.overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }

    /**
     * activity结束动画
     */
    public void exitAnimation(Activity activity) {
//        activity.overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
    }
}