package com.sainti.blackcard.goodthings.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseBean;
import com.sainti.blackcard.base.BaseFragment;
import com.sainti.blackcard.goodthings.adapter.GoodComentAdapter;
import com.sainti.blackcard.goodthings.bean.GoodThingsBean;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.minterface.TimerListener;
import com.sainti.blackcard.mtuils.CommontUtil;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.mtuils.MLog;
import com.sainti.blackcard.mwebview.CommenWebActivity;
import com.sainti.blackcard.widget.LoadingView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YuZhenpeng on 2018/5/4.
 */

public class CommentFragment extends BaseFragment implements OnNetResultListener, TimerListener {
    private RecyclerView xRecyclerView;
    private int page = 1;
    private String mPage = "1";
    private String id;
    private List<GoodThingsBean.DataBean> list;


    private int pageCount;
    private Gson gson;
    private GoodThingsBean goodThingsBean;
    private LoadingView loadingView;
    private GoodComentAdapter comentAdapter;
    private SmartRefreshLayout refresh_lay;

    @Override
    protected int setLayout() {
        return R.layout.fragment_comment;
    }

    /**
     * 获得当前复用的fraggment对象
     *
     * @parm channelsId 频道id 需要根据频道id请求网络数据
     **/
    public static CommentFragment getCommentFragment(String channelsId) {
        CommentFragment fragment = new CommentFragment();
        // fragment传参
        // Activity 传给 fragment 用setArguments()
        // fragment 得到 Activity 用getArguments()
        Bundle bundle = new Bundle();
        bundle.putString("channelsId", channelsId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initView(View view) {
        xRecyclerView = bindView(R.id.xrv_commentthings);
        refresh_lay = bindView( R.id.refresh_lay);
    }

    @Override
    protected void initData() {
        refresh_lay.setEnableLoadmore(true);
        refresh_lay.setEnableRefresh(true);
        loadingView = new LoadingView(context);
        list = new ArrayList<>();
        Bundle dataId = getArguments();
        id = dataId.getString("channelsId");
        comentAdapter = new GoodComentAdapter(R.layout.item_commentthings);
        comentAdapter.setContext(context);
        xRecyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        xRecyclerView.setAdapter(comentAdapter);
        loadingView.show();
        CommontUtil.lateTime(1000, this);//延时1秒操作
        refresh_lay.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                mPage = String.valueOf(page);
                if (page > pageCount) {
                    refresh_lay.finishLoadmore();
                } else {
                    TurnClassHttp.getHaoWuFenLei(mPage, id, 2, context, CommentFragment.this);
                }
            }
        });
        refresh_lay.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPage = "1";
                page = 1;
                list.clear();
                //   loadingView.show();
                TurnClassHttp.getHaoWuFenLei(mPage, id, 1, context, CommentFragment.this);
            }
        });
        comentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(context, CommenWebActivity.class);
                intent.putExtra("wel_url", list.get(position).getWel_url());
                intent.putExtra("title", list.get(position).getW_title());
                intent.putExtra("img_url", list.get(position).getW_pic());
                intent.putExtra("name1", list.get(position).getW_title2());
                intent.putExtra("w_id", list.get(position).getW_id());
                intent.putExtra("small_title", list.get(position).getW_title3());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initEvents() {

    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        MLog.d("aqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq-------", stringResult);
        loadingView.dismiss();
        gson = GsonSingle.getGson();
        BaseBean baseBean = gson.fromJson(stringResult, BaseBean.class);
        String resultss = baseBean.getResult();
        if (!resultss.equals("1")) {
            return;
        }
        goodThingsBean = gson.fromJson(stringResult, GoodThingsBean.class);
        String results = goodThingsBean.getResult();
        switch (resultCode) {
            case 1:
                if (results.equals("1")) {
                    if (goodThingsBean != null) {
                        list.clear();
                        list.addAll(goodThingsBean.getData());

                        comentAdapter.setNewData(list);
                        pageCount = goodThingsBean.getPagecount();
                    }


                }
                refresh_lay.finishRefresh();
                break;
            case 2:
                if (results.equals("1")) {
                    if (goodThingsBean != null) {
                        list.addAll(goodThingsBean.getData());
                        comentAdapter.setNewData(list);
                        pageCount = goodThingsBean.getPagecount();
                    }


                }
                refresh_lay.finishLoadmore();
                break;
        }
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        refresh_lay.finishRefresh();
        refresh_lay.finishRefresh();
    }


    @Override
    public void onTimerListener() {
        TurnClassHttp.getHaoWuFenLei(mPage, id, 1, context, CommentFragment.this);
        // ToastUtils.show(context, "延时一秒");
    }
}
