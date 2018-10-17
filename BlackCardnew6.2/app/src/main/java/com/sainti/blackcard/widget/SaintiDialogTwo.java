package com.sainti.blackcard.widget;

import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sainti.blackcard.R;

/**
 * 自定义dialog
 * 
 * @author samson
 * 
 */
public class SaintiDialogTwo extends Dialog implements OnClickListener,
        TextWatcher {
	private static SaintiDialogTwo sSaintiDialog = null;
	private static TextView titleTextView;
	private static TextView mMainTitle;
	private static View contentView;
	private static EditText contentTextView;
	private static Button btndelete;
	private static Button btnright;
	private static View line1;
	private static View line2;
	private static View viewxian;
	private setOnButton1ClickListener sOnButton1ClickListener;
	private setOnButton2ClickListener sOnButton2ClickListener;
	private setTextWatcherListener sOnTextWatcherListener;

	public interface setOnButton1ClickListener {
		public void setOnButton1Clicked(Button left);
	}

	public interface setOnButton2ClickListener {
		public void setOnButton2Clicked(Button right);
	}

	public interface setTextWatcherListener {
		public void setOnEditTextChanged(EditText sEditText);
	}

	public void setOnLeftButtonClickListener(setOnButton1ClickListener listener) {
		sOnButton1ClickListener = listener;
	}

	public void setOnRightButtonClickListener(setOnButton2ClickListener listener) {
		sOnButton2ClickListener = listener;
	}

	public void setOnEditTextChangedListener(setTextWatcherListener listener) {
		sOnTextWatcherListener = listener;
	}

	public SaintiDialogTwo(Context context) {
		super(context);
	}

	public SaintiDialogTwo(Context context, int theme) {
		super(context, theme);
	}

	public static SaintiDialogTwo createDialog(Context context) {
		sSaintiDialog = new SaintiDialogTwo(context,
				R.style.CustomProgressDialog);
		sSaintiDialog.setCancelable(true);
		sSaintiDialog.setContentView(R.layout.sainti_dialogtwo);
		sSaintiDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
		mMainTitle = (TextView) sSaintiDialog.findViewById(R.id.tvtitle);
		titleTextView = (TextView) sSaintiDialog.findViewById(R.id.tvtext);
		btndelete = (Button) sSaintiDialog.findViewById(R.id.btndelete);
		btnright = (Button) sSaintiDialog.findViewById(R.id.btnright);
		sSaintiDialog.setCanceledOnTouchOutside(false);
		return sSaintiDialog;
	}

	public void onWindowFocusChanged(boolean hasFocus) {
		if (sSaintiDialog == null) {
			return;
		}
	}

	/**
	 * 内容栏 
	 */
	public void setTitile(String title) {
		if (titleTextView != null) {
			titleTextView.setVisibility(View.VISIBLE);
			titleTextView.setText(title);
		}
	}
	
	/**
	 * 标题栏 
	 */
	public void setMainTitle(String mtitle){
		if (mMainTitle != null) {
			mMainTitle.setVisibility(View.VISIBLE);
			mMainTitle.setText(mtitle);
		}
	}
	
	/**
	 * 标题栏 
	 */
	public void setMainTitle(int id){
		if (mMainTitle != null) {
			mMainTitle.setVisibility(View.VISIBLE);
			mMainTitle.setText(getContext().getString(id));
		}
	}

	public void setMessage(String strMessage) {
		if (contentTextView != null) {
			contentTextView.setVisibility(View.VISIBLE);
			contentTextView.setText(strMessage);
			contentTextView.addTextChangedListener(this);
		}
	}

	public void setButton(String btnString1, String btnString2) {
		if (btndelete != null && btnright != null) {
			btndelete.setVisibility(View.VISIBLE);
			btnright.setVisibility(View.VISIBLE);
			btndelete.setText(btnString1);
			btnright.setText(btnString2);
		}
		btndelete.setOnClickListener(this);
		btnright.setOnClickListener(this);
	}

	public void setButton1Clickable(boolean clickable) {
		if (btndelete != null) {
			btndelete.setClickable(clickable);
		}
	}

	public void setButton2Clickable(boolean clickable) {
		if (btnright != null) {
			btnright.setClickable(clickable);
		}
	}

	public void setLine() {
		if (titleTextView != null && contentTextView != null
				&& !titleTextView.getText().toString().equals("")
				&& !contentTextView.getText().toString().equals("")) {
			line1.setVisibility(View.VISIBLE);
		} else {
			line1.setVisibility(View.GONE);
		}
		if (contentTextView != null && btndelete != null && btnright != null
				&& !btndelete.getText().toString().equals("")
				&& !btnright.getText().toString().equals("")
				&& !contentTextView.getText().toString().equals("")) {
			line2.setVisibility(View.VISIBLE);
		} else {
			contentView.setVisibility(View.GONE);
			line2.setVisibility(View.GONE);
		}
		if (titleTextView != null
				&& titleTextView.getText().toString().equals("")) {
			titleTextView.setVisibility(View.GONE);
		}
		if (contentTextView != null
				&& contentTextView.getText().toString().equals("")) {
			contentTextView.setVisibility(View.GONE);
		}
		if (btndelete != null && btndelete.getText().toString().equals("")) {
			btndelete.setVisibility(View.GONE);
		}
		if (btnright != null && btnright.getText().toString().equals("")) {
			btnright.setVisibility(View.GONE);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btndelete:
			if (sOnButton1ClickListener != null) {
				sOnButton1ClickListener.setOnButton1Clicked(btndelete);
			}
			break;
		case R.id.btnright:
			if (sOnButton2ClickListener != null) {
				sOnButton2ClickListener.setOnButton2Clicked(btnright);
			}
			break;

		default:
			break;
		}
	}

	public void setOnTouchOutside(boolean bl) {
		if (sSaintiDialog != null) {
			sSaintiDialog.setCanceledOnTouchOutside(false);
			sSaintiDialog.setCancelable(bl);
		}
	}

	@Override
	public void afterTextChanged(Editable arg0) {

	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                  int arg3) {

	}

	@Override
	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
		if (sOnTextWatcherListener != null) {
			sOnTextWatcherListener.setOnEditTextChanged(contentTextView);
		}
	}
}
