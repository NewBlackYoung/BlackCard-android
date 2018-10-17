package com.sainti.blackcard.commen.mdialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Chenguohui on 2018/8/22.
 * 动态替换view 的dialog
 */

public class CommenDialog {

    private static CommenDialog instance = null;
    private AlertDialog dialog;


    public static CommenDialog getInstance() {
        if (instance == null) {
            instance = new CommenDialog();
        }
        return instance;
    }

    private CommenDialog() {
    }
    public void showDialog(Context context, int layout, ViewInterface listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(layout, null);
        builder.setView(view);
        dialog = builder.create();
        dialog.show();
        dialog.setCancelable(false);
        listener.getChildView(view);
    }
    public void dismissDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
    public interface ViewInterface {
        void getChildView(View view);
    }
}
