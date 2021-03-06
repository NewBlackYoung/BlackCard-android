package com.sainti.blackcard.widget;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;


public class BannerViewPagerTransform extends ViewPager {
    private static final float MIN_SCALE = 0.8f;
    private static final float MIN_ALPHA = 0.8f;

    public BannerViewPagerTransform(Context context) {
        this(context, null);
    }

    public BannerViewPagerTransform(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setPageTransformer(false, new DepthPageTransformer());
//        this.setOffscreenPageLimit(2);  // 避免卡顿
        this.setOnHierarchyChangeListener(new OnHierarchyChangeListener() {
            @Override
            public void onChildViewAdded(View parent, View child) {
                child.setScaleY(MIN_SCALE);
                child.setAlpha(MIN_ALPHA);
            }

            @Override
            public void onChildViewRemoved(View parent, View child) {

            }
        });
    }

    public class DepthPageTransformer implements PageTransformer {

        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        @SuppressLint("NewApi")
        public void transformPage(View view, float position) {
            float scaleFactor = MIN_SCALE + (1 - MIN_SCALE)* (1 - Math.abs(position));
            float alphaFactor = MIN_ALPHA + (1 - MIN_ALPHA)* (1 - Math.abs(position));
            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setScaleY(scaleFactor);
                view.setAlpha(alphaFactor);
            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
               // float scaleFactor = MIN_SCALE + (1 - MIN_SCALE)* (1 - Math.abs(position));
                //float alphaFactor = MIN_ALPHA + (1 - MIN_ALPHA)* (1 - Math.abs(position));
                view.setScaleY(scaleFactor);
                view.setAlpha(alphaFactor);
            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                // Counteract the default slide transition

                // Scale the page down (between MIN_SCALE and 1)
             //   float scaleFactor = MIN_SCALE + (1 - MIN_SCALE)* (1 - Math.abs(position));
                // float alphaFactor = MIN_ALPHA + (1 - MIN_ALPHA)* (1 - Math.abs(position));
                view.setScaleY(scaleFactor);
                view.setAlpha(alphaFactor);
            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setScaleY(scaleFactor);
                view.setAlpha(alphaFactor);
            }
        }
    }

}
