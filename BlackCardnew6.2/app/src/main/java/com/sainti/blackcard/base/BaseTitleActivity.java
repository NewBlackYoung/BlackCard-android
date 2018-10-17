package com.sainti.blackcard.base;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.sainti.blackcard.R;
import com.sainti.blackcard.commen.mconstant.SpCodeName;
import com.sainti.blackcard.mhttp.okgo.OkGoUtils;
import com.sainti.blackcard.mtuils.ActivityManager;
import com.sainti.blackcard.mtuils.MLog;
import com.sainti.blackcard.mtuils.SpUtil;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.umeng.analytics.MobclickAgent;

public abstract class BaseTitleActivity extends AppCompatActivity implements View.OnClickListener {
    public Activity context;
    private ImageView ivLeftOne, ivRightOne, ivRightTwo;
    private TextView tvTitle, tvTitleRight;
    private OnClickRightTextCallBack onClickRightText;
    private OnClickCenterTextCallBack onClickCenterTextCallBack;
    private OnClickRightIcon1CallBack onClickRightIcon1;
    private OnClickRightIcon2CallBack onClickRightIcon2;
    private RelativeLayout lay_title_base;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //状态栏設定
        setContentView(R.layout.activity_base);
        context = this;
        setBaseContentView(setLayout());//引用布局
        setStatusBar();
        setAty();
        initViewBase();
        initEventBase();
        initView();
        initEvent();
        initData();

    }

    /**
     * ActionBarカラー設定する
     */
    protected void setStatusBar() {
        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.statusBar));
    }

    /**
     * 控件初始化
     */
    private void initViewBase() {
        ivLeftOne = (ImageView) findViewById(R.id.iv_title_leftOne);
        ivRightOne = (ImageView) findViewById(R.id.iv_title_rightOne);
        ivRightTwo = (ImageView) findViewById(R.id.iv_title_rightTwo);
        tvTitle = (TextView) findViewById(R.id.tv_titleText_center);
        tvTitleRight = (TextView) findViewById(R.id.tv_titleText_right);
        lay_title_base = (RelativeLayout) findViewById(R.id.lay_title_base);
    }

    private void initEventBase() {
        ivLeftOne.setOnClickListener(this);
        //tvTitle.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //左侧图片
            case R.id.iv_title_leftOne:
                finish();
                break;
            //右侧第一个图片
            case R.id.iv_title_rightOne:
                onClickRightIcon1.clickRightIcon1();
                break;
            //右侧第二个图片
            case R.id.iv_title_rightTwo:
                onClickRightIcon2.clickRightIcon2();
                break;
            //右侧文本
            case R.id.tv_titleText_right:
                onClickRightText.clickRightText();
                break;
            case R.id.tv_titleText_center:
                //  onClickCenterTextCallBack.clickCenterText();
                break;

            default:
                break;
        }
    }

    /**
     * 外部调用设置标题
     *
     * @param title 标题的文本
     */
    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    /**
     * 隐藏标题栏
     * <p>
     * 标题的文本
     */
    public void hideTitle() {
        lay_title_base.setVisibility(View.GONE);
    }

    /**
     * 隐藏标题栏
     * <p>
     * 标题的文本
     */
    public void showTitle() {
        lay_title_base.setVisibility(View.VISIBLE);
    }

    /**
     * 外部调用设置右侧文本信息
     *
     * @param text 所需要设置的文本
     */
    public void setBaseRightText(String text, OnClickRightTextCallBack onClickRightText) {
        this.onClickRightText = onClickRightText;
        tvTitleRight.setText(text);
        tvTitleRight.setVisibility(View.VISIBLE);
        tvTitleRight.setOnClickListener(this);
    }

    /**
     * 外部调用设置右侧文本信息
     */
    public void setBaseCenterText(OnClickCenterTextCallBack onClickRightText) {
        this.onClickCenterTextCallBack = onClickRightText;
        tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickCenterTextCallBack.clickCenterText();
            }
        });
    }

    /**
     * 引用头部布局
     *
     * @param layoutId 布局id
     */
    public void setBaseContentView(int layoutId) {
        LinearLayout layout = (LinearLayout) findViewById(R.id.base_body);
        //获取布局，并在BaseActivity基础上显示
        final View view = getLayoutInflater().inflate(layoutId, null);
        //关闭键盘
        hideKeyBoard();
        //给EditText的父控件设置焦点，防止键盘自动弹出
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layout.addView(view, params);
    }

    /**
     * 外部调用设置右侧图片1（最右侧）
     *
     * @param resId             图片的资源id
     * @param onClickRightIcon1 点击处理接口
     */
    public void setBaseRightIcon1(int resId, OnClickRightIcon1CallBack onClickRightIcon1) {
        this.onClickRightIcon1 = onClickRightIcon1;
        ivRightOne.setImageResource(resId);
        ivRightOne.setVisibility(View.VISIBLE);
        ivRightOne.setOnClickListener(this);
    }

    /**
     * 外部调用设置右侧图片2（右数第二个图片）
     *
     * @param resId 图片的资源id
     */
    public void setBaseRightIcon2(int resId, OnClickRightIcon2CallBack onClickRightIcon2) {
        this.onClickRightIcon2 = onClickRightIcon2;
        if (resId != 0) {
            ivRightTwo.setImageResource(resId);
        }
        ivRightTwo.setVisibility(View.VISIBLE);
        ivRightTwo.setOnClickListener(this);
        //语音辅助提示的时候读取的信息
//        iv_rightTwo.setContentDescription(alertText);
    }

    /**
     * 隐藏键盘
     */
    public void hideKeyBoard() {
        View view = ((Activity) context).getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * @param id  组件id
     * @param <T> 组件
     * @return View
     */
    protected <T extends View> T bindView(int id) {
        return (T) findViewById(id);
    }

    protected <T extends Object> T bindView(View view, int id) {
        return (T) view.findViewById(id);
    }

    // 布局文件初始化
    public abstract int setLayout();

    //控件初始化
    protected abstract void initView();

    //事件分发
    protected abstract void initEvent();

    // 数据
    protected abstract void initData();

    /**
     * 右侧文字点击回调接口
     */
    public interface OnClickRightTextCallBack {
        void clickRightText();
    }

    /**
     * 中间文字点击回调接口
     */
    public interface OnClickCenterTextCallBack {
        void clickCenterText();
    }

    /**
     * 右侧图片一点击回调接口
     */
    public interface OnClickRightIcon1CallBack {
        void clickRightIcon1();
    }

    /**
     * 右侧图片二点击回调接口
     */
    public interface OnClickRightIcon2CallBack {
        void clickRightIcon2();
    }

    /**
     * 外部调用获取返回键
     */
    public ImageView getBaseBack() {
        return ivLeftOne;
    }

    /**
     * 外部调用隐藏返回键
     */
    private void hideBack() {
        ivLeftOne.setVisibility(View.GONE);
    }

    /**
     * 在绑定布局前如果需要多Activity进行设置
     * 则复写此方法
     */
    protected void setAty() {
        //在Activity创建的时候加入到List中
//        ActivityControl.addAty(this);
        ActivityManager.getInstance().pushOneActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在销毁的时候让Activity绑定一个空的布局,可以有效降低内存
//        ActivityControl.removeAty(this);//从ActivityList中移除
        OkGoUtils.getInstance().callOffNet(this);
        ActivityManager.getInstance().popActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(this.getClass().getName());
        MobclickAgent.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(this.getClass().getName());
        MobclickAgent.onPause(this);
    }

    public void showToast(String content) {
        ToastUtils.show(this, content);
    }

    public void showLog(String tag, String content) {
        MLog.d(tag, content);
    }
    public String getSpUid(String tag, String content) {
       return SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, "");
    }
    public String getSpToken(String tag, String content) {
        return SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, "");
    }
}
