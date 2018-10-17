package com.sainti.blackcard.commen.mdialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sainti.blackcard.R;

/**
 * Created by Chenguohui on 2018/8/22.
 * 适用于activity 固定的view 固定的逻辑
 */

public class LoadingDialog {

    private static LoadingDialog instance = null;
    public Dialog mDialog;
    public static LoadingDialog getInstance() {
        if (instance == null) {
            instance = new LoadingDialog();
        }
        return instance;
    }

    private LoadingDialog() {
    }

    public void dismissDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    public void showDialog(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_dialog_loading, null);
        ImageView progress_view = view.findViewById(R.id.progress_view);
        Glide.with(context).load(R.mipmap.animation2).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(progress_view);
        mDialog = new Dialog(context, R.style.dialog);
        mDialog.setContentView(view);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();
    }

}
