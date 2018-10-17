package com.sainti.blackcard.mtuils;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.sainti.blackcard.R;

/**
 * Created by YuZhenpeng on 2018/5/31.
 */

public class AnimationUtil {
    private Animation animationIn, animationOut;
    private Context context;

    public AnimationUtil(Context context) {
        this.context = context;
        animationIn = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        animationOut = AnimationUtils.loadAnimation(context, R.anim.fade_out);
    }

    public void viewAppear(View view) {
        view.setVisibility(View.VISIBLE);
        animationIn = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        view.setAnimation(animationIn);
    }

    public void viewDisappear(View view) {
        view.setVisibility(View.GONE);
        animationOut = AnimationUtils.loadAnimation(context, R.anim.fade_out);
        view.setAnimation(animationOut);
    }

}
