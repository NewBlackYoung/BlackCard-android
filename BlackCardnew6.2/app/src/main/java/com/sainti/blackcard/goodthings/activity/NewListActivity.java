package com.sainti.blackcard.goodthings.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.goodthings.adapter.LeftAdapter;
import com.sainti.blackcard.goodthings.adapter.RightAdapter;
import com.sainti.blackcard.goodthings.bean.NewListBean;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.goodthings.spcard.activity.ShoppingCardActivity;
import com.sainti.blackcard.widget.LoadingView;

public class NewListActivity extends BaseTitleActivity implements OnNetResultListener, AbsListView.OnScrollListener, AdapterView.OnItemClickListener, BaseTitleActivity.OnClickRightIcon1CallBack {

    private LoadingView loadingView;
    private ListView leftList;
    private ListView rightList;
    private LeftAdapter leftAdapter;
    private RightAdapter rightAdapter;
    private int scrollPosition = -1;

    @Override
    public int setLayout() {
        return R.layout.activity_new_list;
    }

    @Override
    protected void initView() {
        setTitle("全部分类");
        setBaseRightIcon1(R.mipmap.card,this);
        loadingView = new LoadingView(this);
        leftList = bindView(R.id.leftList);
        leftAdapter = new LeftAdapter(this);
        leftList.setAdapter(leftAdapter);
        leftList.setOnItemClickListener(this);
        rightList = bindView(R.id.rightList);
        rightAdapter = new RightAdapter(this);
        rightList.setAdapter(rightAdapter);
        rightList.setOnScrollListener(this);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        TurnClassHttp.welfareCate(1, this, this);
    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        loadingView.dismiss();
        switch (resultCode) {
            case 1:
                NewListBean newListBean = GsonSingle.getGson().fromJson(stringResult, NewListBean.class);
                leftAdapter.setList(newListBean.getData());
                rightAdapter.setList(newListBean.getData());
                break;
        }
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        loadingView.dismiss();
        showToast(errMsg);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (scrollPosition != firstVisibleItem) {
            leftAdapter.setPs(firstVisibleItem);
            scrollPosition = firstVisibleItem;

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        rightList.setSelection(position);
        leftAdapter.setPs(position);

    }

    @Override
    public void clickRightIcon1() {
        startActivity(new Intent(this, ShoppingCardActivity.class));
    }
}
