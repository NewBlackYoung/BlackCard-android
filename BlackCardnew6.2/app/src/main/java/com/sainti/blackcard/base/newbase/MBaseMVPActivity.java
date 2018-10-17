package com.sainti.blackcard.base.newbase;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.sainti.blackcard.R;
import com.sainti.blackcard.commen.mconstant.SpCodeName;
import com.sainti.blackcard.mtuils.ActivityManager;
import com.sainti.blackcard.mtuils.MLog;
import com.sainti.blackcard.mtuils.SpUtil;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.widget.LoadingView;
import com.sainti.blackcard.widget.StateLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by guohuichen on 2018/9/11.
 */

public abstract class MBaseMVPActivity<V extends  IBaseView,P extends IBasePresenter<V>> extends AppCompatActivity implements IBaseDelegate<V,P> {
    @BindView(R.id.base_body)
    LinearLayout baseBody;
    public Activity context;
    @BindView(R.id.tv_base_title)
    TextView tvBaseTitle;
    @BindView(R.id.tv_error)
    TextView tvError;
    @BindView(R.id.btn_refresh)
    Button btnRefresh;
    @BindView(R.id.iv_back_base)
    ImageView ivBackBase;
    @BindView(R.id.iv_right_base)
    ImageView ivRightBase;
    @BindView(R.id.tv_rightText_base)
    TextView tvRightTextBase;

    private View layout;
    private LoadingView loadingView;
    private OnEarrorPageRefreshClicklistenner earrorPageRefreshClicklistenner;
    private OnBaseRightTextClicklistenner rightTextClicklistenner;
    private OnBaseRightImageClicklistenner rightImageClicklistenner;
    protected P mPresenter;
    private LinearLayout linearLayout;
    private StateLayout stateLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newbase);
        context = this;
        stateLayout = new StateLayout(this);
        setBaseContentView(stateLayout);//引用布局
        stateLayout.bindSuccessView(getsuccessView(setLayout()));
        // stateLayout.showLoadingView();
        layout = findViewById(R.id.view_title);
        mPresenter = createPresenter();
        ButterKnife.bind(this);
        // ImmersionBar.with(this).keyboardEnable(true).statusBarDarkFont(true, 0.2f).init();// 透明状态栏
        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.statusBar));
        loadingView = new LoadingView(this);
        setAty();
        initView();
        initData();


    }

    public StateLayout getStateLayout() {
        return stateLayout;
    }

    public View getsuccessView(int id) {
        View successView = View.inflate(this, id, null);
        return successView;

    }

    /**
     * 引用头部布局
     *
     * @param layoutId 布局id
     */
    public void setBaseContentView(View layoutId) {
        linearLayout = (LinearLayout) findViewById(R.id.base_body);
        //获取布局，并在BaseActivity基础上显示
        //关闭键盘
        hideKeyBoard();
        //给EditText的父控件设置焦点，防止键盘自动弹出
        layoutId.setFocusable(true);
        layoutId.setFocusableInTouchMode(true);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        linearLayout.addView(layoutId, params);
    }


    public abstract int setLayout();

    protected abstract void initView();

    protected abstract void initData();


    /**
     * 显示loadding
     */
    public void showLoadingView() {
        loadingView.show();
    }


    /**
     * 隐藏loading
     */
    public void hideLoadingView() {
        loadingView.dismiss();
    }


    /**
     * 弹出toast
     */
    public void showToast(String content) {
        ToastUtils.show(this, content);
    }

    /**
     * 打印log
     */
    public void showLog(String tag, String content) {
        MLog.d(tag, content);
    }

    @OnClick({ R.id.iv_back_base, R.id.iv_right_base, R.id.tv_rightText_base})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.iv_back_base:
                finish();
                break;
            case R.id.iv_right_base:
                rightImageClicklistenner.OnBaseRightImageClicklistenner();
                break;
            case R.id.tv_rightText_base:
                rightTextClicklistenner.OnBaseTextClicklistenner();
                break;

        }
    }


    /**
     * 显示并设置标题
     */
    public void setPageTtile(String title) {
        layout.setVisibility(View.VISIBLE);
        tvBaseTitle.setText(title);
    }

    /**
     * 显示并设置标题,是否隐藏返回键
     */
    public void setPageTtile(String title, boolean vs) {
        layout.setVisibility(View.VISIBLE);
        tvBaseTitle.setText(title);
        if (vs) {
            ivBackBase.setVisibility(View.GONE);
        }
    }

    /**
     * 展示请求错误界面
     */
    public void showErrorPage(OnEarrorPageRefreshClicklistenner listener) {

        this.earrorPageRefreshClicklistenner = listener;
    }


    /**
     * 右侧文字点击事件
     */
    public void setRightTextClicklistenner(String text, OnBaseRightTextClicklistenner listener) {
        tvRightTextBase.setText(text);
        tvRightTextBase.setVisibility(View.VISIBLE);
        this.rightTextClicklistenner = listener;
    }


    /**
     * 右侧图片点击事件
     */
    public void setRightImageClicklistenner(int resId, OnBaseRightImageClicklistenner listener) {
        ivRightBase.setImageResource(resId);
        ivRightBase.setVisibility(View.VISIBLE);
        this.rightImageClicklistenner = listener;
    }

    /**
     * 获取用户Uid
     */
    public String getSpUid() {
        return SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, "");
    }

    /**
     * 获取用户Token
     */
    public String getSpToken() {
        return SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, "");
    }


    @Override
    protected void onResume() {
        super.onResume();
     /*   MobclickAgent.onPageStart(this.getClass().getName());
        MobclickAgent.onResume(this);*/
    }

    @Override
    public void onPause() {
        super.onPause();
      /*  MobclickAgent.onPageEnd(this.getClass().getName());
        MobclickAgent.onPause(this);*/
    }

    /**
     * 错误界面接口
     */
    public interface OnEarrorPageRefreshClicklistenner {
        void OnEarrorPageRefreshClicklistenner();
    }

    /**
     * 右侧文字点击事件接口
     */
    public interface OnBaseRightTextClicklistenner {
        void OnBaseTextClicklistenner();
    }

    /**
     * 右侧图片点击事件接口
     */
    public interface OnBaseRightImageClicklistenner {
        void OnBaseRightImageClicklistenner();
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

    @NonNull
    @Override
    public P getPresenter() {
        return mPresenter;
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
        //OkGoUtils.getInstance().callOffNet(this);
        ActivityManager.getInstance().popActivity(this);
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
}
