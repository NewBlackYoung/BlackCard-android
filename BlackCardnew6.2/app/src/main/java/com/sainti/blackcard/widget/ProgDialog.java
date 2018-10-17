package com.sainti.blackcard.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

import com.sainti.blackcard.R;


public class ProgDialog extends Dialog {
	private static ProgDialog customProgressDialog = null;

	public ProgDialog(Context context) {
		super(context);
	}

	public ProgDialog(Context context, int theme) {
		super(context, theme);
	}

	public static ProgDialog createDialog(Context context) {
		customProgressDialog = new ProgDialog(context,
				R.style.CustomProgressDialog);
		customProgressDialog.setCancelable(true);//设置为false，按返回键不能退出。默认为true。
		customProgressDialog.setContentView(R.layout.pro_bg);
		customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;

		return customProgressDialog;
	}

	public void onWindowFocusChanged(boolean hasFocus) {

		if (customProgressDialog == null) {
			return;
		}

	}

	/**
	 * 
	 * [Summary] setTitile 标题
	 * 
	 * @param strTitle
	 * @return
	 * 
	 */
	public ProgDialog setTitile(String strTitle) {
		return customProgressDialog;
	}

	/**
	 * 
	 * [Summary] setMessage 提示内容
	 * 
	 * @param strMessage
	 * @return
	 * 
	 */
	public ProgDialog setMessage(String strMessage) {
		TextView tvMsg = (TextView) customProgressDialog
				.findViewById(R.id.prog_tv);
		if (tvMsg != null) {
			tvMsg.setText(strMessage);
		}
		return customProgressDialog;
	}
}
