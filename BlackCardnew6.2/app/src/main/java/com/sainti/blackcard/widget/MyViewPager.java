package com.sainti.blackcard.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by YuZhenpeng on 2018/4/10.
 */

public class MyViewPager extends ViewPager {
    private boolean isCanScroll = true;
    public MyViewPager(Context context) {
        super(context);
    }
    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScanScroll(boolean isCanScroll) {
        this.isCanScroll = isCanScroll;
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return this.isCanScroll && super.onInterceptTouchEvent(ev);
    }

    public boolean onTouchEvent(MotionEvent ev) {
        return this.isCanScroll && super.onTouchEvent(ev);
    }
}
