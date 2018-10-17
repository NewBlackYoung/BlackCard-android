package com.sainti.blackcard.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sainti.blackcard.R;


// 自定义简单的 LoadingView 加载的gif图片
public class LoadingView {
  //  private GoReview goReview;

    public Dialog mDialog;
    private AnimationDrawable animationDrawable = null;
 /*   private final RelativeLayout lay_earro;
    private final RelativeLayout lay_sure;*/

    public LoadingView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.progress_view,null);
/*        lay_sure = (RelativeLayout) view.findViewById(R.id.lay_sure);
        lay_earro = (RelativeLayout) view.findViewById(R.id.lay_earro);
        ImageView iv_earro = (ImageView) view.findViewById(R.id.iv_earro);
        iv_earro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goReview.review();
            }
        });*/
        ImageView loadingImage = (ImageView) view.findViewById(R.id.progress_view);
        Glide.with(context).load(R.mipmap.animation2).asGif().diskCacheStrategy(DiskCacheStrategy.RESULT).into(loadingImage);
        mDialog = new Dialog(context, R.style.dialog);
        mDialog.setContentView(view);
        mDialog.setCanceledOnTouchOutside(false);
    }

    public void show() {
        mDialog.show();
    }

 /*   public void setCanceledOnTouchOutside(boolean cancel) {
        mDialog.setCanceledOnTouchOutside(cancel);
    }
*/
    public void dismiss() {
        if (mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

/*    public void showErroView() {
        lay_earro.setVisibility(View.VISIBLE);
        lay_sure.setVisibility(View.GONE);
    }

    public boolean isShowing() {
        if (mDialog.isShowing()) {
            return true;
        }
        return false;
    }*/

/*    *//* 重新加载的接口*//*
    public interface GoReview {
        void review();
    }

    //    从新加载的方法
    public void ReView(GoReview goReview) {
        this.goReview = goReview;

    }*/
}
