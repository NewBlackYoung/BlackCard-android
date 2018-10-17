package com.sainti.blackcard.commen.mdialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.sainti.blackcard.R;

/**
 * Created by Chenguohui on 2018/8/22.
 * 适用于activity 固定的view 固定的逻辑
 */

public class CommenDialogUtil {

    private static CommenDialogUtil instance = null;
    private AlertDialog dialog;

    public static CommenDialogUtil getInstance() {
        if (instance == null) {
            instance = new CommenDialogUtil();
        }
        return instance;
    }

    private CommenDialogUtil() {
    }

    public void dismissDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public void showDialog(Context context, String title, String content, final CallBack callBack, final int type) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.view_dialog_comment, null);
        TextView tv_deleteSure = (TextView) view.findViewById(R.id.tv_Sure); // 确定删除
        TextView tv_delelteText = (TextView) view.findViewById(R.id.tv_title);// 删除字
        TextView tv_teleteCso = (TextView) view.findViewById(R.id.tv_Canso);// 取消删除
        TextView tv_del = (TextView) view.findViewById(R.id.tv_content);//
        tv_delelteText.setText(title);
        tv_del.setText(content);
        tv_deleteSure.setText("确定");
        tv_teleteCso.setText("放弃");
        builder.setView(view);
        dialog = builder.create();
        dialog.show();
        tv_deleteSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.onSureClick(type);
                dialog.dismiss();

            }
        });
        tv_teleteCso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.onCansoClick(type);
                  dialog.dismiss();

            }
        });
        dialog.setCancelable(false);
    }

    public interface CallBack {
        void onSureClick(int tpye);

        void onCansoClick(int tpye);
    }

}
