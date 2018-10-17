package com.sainti.blackcard.chat.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.chat.adapter.LiyouAdapter;
import com.sainti.blackcard.chat.bean.LiyouBean;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.widget.LoadingView;

public class LiyouActivity extends BaseTitleActivity implements OnNetResultListener, BaseQuickAdapter.OnItemClickListener {


    private RecyclerView rv_liyou;
    private LiyouAdapter liyouAdapter;
    private LoadingView loadingView;
    private LiyouBean liyouBean;

    @Override
    public int setLayout() {
        return R.layout.activity_liyou;
    }

    @Override
    protected void initView() {
        loadingView = new LoadingView(this);
        rv_liyou = bindView(R.id.rv_liyou);
        rv_liyou.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        setTitle("投诉理由");
        loadingView.show();
        TurnClassHttp.complaintlist(1, this, this);
        liyouAdapter = new LiyouAdapter(R.layout.item_liyou);
        rv_liyou.setAdapter(liyouAdapter);
        liyouAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        loadingView.dismiss();
        liyouBean = GsonSingle.getGson().fromJson(stringResult, LiyouBean.class);
        liyouAdapter.setNewData(liyouBean.getData());
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        String a = errMsg;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        String str = liyouBean.getData().get(position).getName();
        String id = liyouBean.getData().get(position).getComplaintslist_id();
        Intent intent = new Intent();
        intent.putExtra("str", str);
        intent.putExtra("id", id);
        setResult(10, intent);
        finish();
    }
}
