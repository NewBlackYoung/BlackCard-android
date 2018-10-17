package com.sainti.blackcard.commen.popup;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sainti.blackcard.R;
import com.sainti.blackcard.commen.mconstant.SataicCode;
import com.sainti.blackcard.mtuils.AnimationUtil;


/**
 * 固定View的poup 可以把业务逻辑一样的popu写在这里CommonPopupWindow
 * <p>
 * 这个是支付的通用界面
 */

public class CommenPayPopup extends PopupWindow implements View.OnClickListener, PopupWindow.OnDismissListener {

    private View mMenuView;

    private OnPopWindowClickListener listener;

    private Activity activity;
    private TextView shuo;
    private TextView tv_price;
    private int paycode;
    private ImageView iv_choceFour;
    private ImageView iv_choceOne;
    private ImageView iv_choceTwo;
    private ImageView iv_choceThree;
    private AnimationUtil animationUtil;
    private View id_line_vb;

    public CommenPayPopup(Activity activity, OnPopWindowClickListener listener, int Code) {
        this.activity = activity;
        initView(activity, listener, Code);
    }

    public void show() {
        Rect rect = new Rect();
          /*
           * getWindow().getDecorView()得到的View是Window中的最顶层View，可以从Window中获取到该View，
           * 然后该View有个getWindowVisibleDisplayFrame()方法可以获取到程序显示的区域，
           * 包括标题栏，但不包括状态栏。
           */
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int winHeight = activity.getWindow().getDecorView().getHeight();
        this.showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0, winHeight - rect.bottom);
    }

    private void initView(Activity activity, OnPopWindowClickListener listener, int code) {

        //设置按钮监听
        this.listener = listener;
        initViewSetting(activity, code);

        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.popwin_liebiao_style);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(activity.getResources().getColor(R.color.eight_zero));
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        this.setOnDismissListener(this);
        // 设置背景颜色变暗
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = 0.5f;
        activity.getWindow().setAttributes(lp);

        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int height = mMenuView.findViewById(R.id.pop_layout).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });

    }

    //弹窗（关于、修改资料、退出登录）
    private void initViewSetting(Activity context, int code) {
        animationUtil = new AnimationUtil(context);
        RelativeLayout vb, wchp, alp, yue;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.view_popu_commenpay, null);
        wchp = (RelativeLayout) mMenuView.findViewById(R.id.lay_weixin);
        alp = (RelativeLayout) mMenuView.findViewById(R.id.lay_zhifubao);
        yue = (RelativeLayout) mMenuView.findViewById(R.id.lay_yue);

        switch (code) {
            case SataicCode.PayCode.ALL_SHOW:
                break;
            case SataicCode.PayCode.HIDE_YUE:
                yue.setVisibility(View.GONE);
               // id_line_yue.setVisibility(View.GONE);
                break;
            case SataicCode.PayCode.HIDE_APIPAY:
                alp.setVisibility(View.GONE);
               // id_line_alpay.setVisibility(View.GONE);
                break;
            case SataicCode.PayCode.HIDE_WXPAY:
                wchp.setVisibility(View.GONE);
               //id_line_wx.setVisibility(View.GONE);
                break;
        }
        // 通过code 判断显示和隐藏哪个支付

        wchp.setOnClickListener(this);
        alp.setOnClickListener(this);
        yue.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.lay_weixin:
                // 微信支付
                paycode = SataicCode.PayCode.TWO;
                break;
            case R.id.lay_zhifubao:
                paycode =SataicCode.PayCode.ONE;
                break;
            case R.id.lay_yue:
                // 余额支付
                paycode = SataicCode.PayCode.ZERO;
                break;

        }
        listener.onPopWindowClickListener(v, paycode);
        dismiss();

    }

    @Override
    public void onDismiss() {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = 1f;
        activity.getWindow().setAttributes(lp);
    }

    //接口
    public interface OnPopWindowClickListener {
        void onPopWindowClickListener(View view, int code);
    }
}
