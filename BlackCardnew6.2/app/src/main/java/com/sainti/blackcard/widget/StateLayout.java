package com.sainti.blackcard.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.sainti.blackcard.R;

/**
 * Created by guohuichen on 2018/10/12.
 */

public class StateLayout extends FrameLayout {
    private View loadingView;
    private View errorView;
    private View successView;
    private View emptyView;

    public StateLayout(Context context) {
        this(context, null);
    }

    public StateLayout(Context context, AttributeSet attrs) {

        this(context, attrs, 0);

    }

    public StateLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();//初始化View
    }

    /**
     * 添加那4个子View：加载中的，加载成功的，加载没有数据，加载失败的
     */
    private void initView() {        //1.加载loadingView
        loadingView = View.inflate(getContext(), R.layout.view_loading_new, null);
        addView(loadingView);        //2.添加失败的View
        errorView = View.inflate(getContext(), R.layout.view_earrorpage, null);
        Button btn_reload = (Button) errorView.findViewById(R.id.btn_refresh);
        btn_reload.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {                //1.先显示loadingView
                showLoadingView();                //2.点击的时候再一次重新加载数据
                if (listener != null) {
                    listener.onReload();
                }
            }
        });
        addView(errorView);        //3.添加空白的view
        emptyView = View.inflate(getContext(), R.layout.view_empty, null);
        addView(emptyView);        //4.加载成功的View在各界面是不同的，所以提供一个方法bindsucessview动态添加        //一开始隐藏所有的View
        hideAll();
    }

    public void bindSuccessView(View view) {
        successView = view;
        if (successView != null) {
            successView.setVisibility(View.INVISIBLE);//隐藏successView
            addView(successView);
        }
    }

    public void showSuccessView() {
        hideAll();
        if (successView != null) {

            successView.setVisibility(View.VISIBLE);

        }
    }

    public void showEmptyView() {

        //先隐藏其他的

        hideAll();
        emptyView.setVisibility(View.VISIBLE);

    }

    public void showErrorView() {

        //先隐藏其他的

        hideAll();
        errorView.setVisibility(View.VISIBLE);

    }

    public void showLoadingView() {

        //先隐藏其他的

        hideAll();
        loadingView.setVisibility(View.VISIBLE);

    }

    public void hideAll() {
        //设置各界面不可见，同时让他们不重新layout，要用的时候直接show就行了
        loadingView.setVisibility(View.INVISIBLE);
        errorView.setVisibility(View.INVISIBLE);
        emptyView.setVisibility(View.INVISIBLE);
        if (successView != null) {
            successView.setVisibility(View.INVISIBLE);
        }
    }

    private OnReloadListener listener;

    public void setOnReloadListener(OnReloadListener listener) {
        this.listener = listener;


    }

    public interface OnReloadListener {
        void onReload();
    }
}

