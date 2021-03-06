package com.sainti.blackcard.base.newbase;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sainti.blackcard.R;
import com.sainti.blackcard.commen.mconstant.SpCodeName;
import com.sainti.blackcard.mtuils.MLog;
import com.sainti.blackcard.mtuils.SpUtil;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.widget.LoadingView;
import com.sainti.blackcard.widget.StateLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class MBaseFragment extends Fragment {
    protected Context context;
    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";
    @BindView(R.id.iv_back_base)
    ImageView ivBackBase;
    @BindView(R.id.tv_base_title)
    TextView tvBaseTitle;
    @BindView(R.id.iv_right_base)
    ImageView ivRightBase;
    @BindView(R.id.tv_rightText_base)
    TextView tvRightTextBase;
    @BindView(R.id.base_body)
    LinearLayout baseBody;
    Unbinder unbinder;
    private LoadingView loadingView;
    private LinearLayout linearLayout;
    private StateLayout stateLayout;
    private View layout;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }
    }

    //记录当前Fragment的状态
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newbase, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    /**
     * 引用头部布局
     *
     * @param layoutId 布局id
     */
    public void setBaseContentView(View layoutId, View view) {
        linearLayout = (LinearLayout) view.findViewById(R.id.base_body);
        //获取布局，并在BaseActivity基础上显示
        //关闭键盘
        //hideKeyBoard();
        //给EditText的父控件设置焦点，防止键盘自动弹出
        layoutId.setFocusable(true);
        layoutId.setFocusableInTouchMode(true);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        linearLayout.addView(layoutId, params);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        stateLayout = new StateLayout(context);
        setBaseContentView(stateLayout, view);//引用布局
        stateLayout.bindSuccessView(getsuccessView(setLayout()));
        stateLayout.showLoadingView();
        layout = view.findViewById(R.id.view_title);
        initView(view);
        loadingView = new LoadingView(context);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    public View getsuccessView(int id) {
        View successView = View.inflate(context, id, null);
        return successView;

    }

    public StateLayout getStateLayout() {
        return stateLayout;
    }


    protected abstract int setLayout();

    protected abstract void initView(View view);

    protected abstract void initData();


    //退出时将context对象置空
    @Override
    public void onDestroy() {
        super.onDestroy();

    }


    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);

    }

    public void showToast(String content) {
        ToastUtils.show(context, content);
    }

    /*显示loadding*/
    public void showLoadingView() {
        loadingView.show();
    }

    /*隐藏loading*/
    public void hideLoadingView() {
        loadingView.dismiss();
    }

    @Override
    public void onResume() {
        super.onResume();
        // MobclickAgent.onPageStart(context.getClass().getName());
    }

    @Override
    public void onPause() {
        super.onPause();
        //MobclickAgent.onPageEnd(context.getClass().getName());
    }

    public void showLog(String tag, String content) {
        MLog.d(tag, content);
    }

    public String getSpUid() {
        return SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, "");
    }

    public String getSpToken() {
        return SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, "");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }}
