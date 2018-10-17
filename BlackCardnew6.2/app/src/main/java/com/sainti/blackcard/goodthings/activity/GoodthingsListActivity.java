package com.sainti.blackcard.goodthings.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.commen.mconstant.SataicCode;
import com.sainti.blackcard.db.dao.GoodThingsDao;
import com.sainti.blackcard.goodthings.adapter.GoodThingAdapter;
import com.sainti.blackcard.goodthings.bean.GoodThingsBean;
import com.sainti.blackcard.goodthings.spcard.activity.ShoppingCardActivity;
import com.sainti.blackcard.meventbus.NormalEventBean;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.mtuils.GlideImageLoader;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.mwebview.CommenWebActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.umeng.analytics.MobclickAgent;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoodthingsListActivity extends BaseTitleActivity implements OnNetResultListener, View.OnClickListener, OnBannerListener {
    private RecyclerView xrvGoodThing;
    private int mpage = 1;
    private String page = "1";
    private List<GoodThingsBean.DataBean> list;
    private GoodThingsBean goodThingsBean;
    private ImageView ivHeader;
    private ImageView ivChakan;
    private ImageView ivBack, iv_isHaiCard;
    private RelativeLayout layShopping;
    private Gson gson;
    private SmartRefreshLayout refreshLayout;
    private GoodThingAdapter goodThingAdapter;
    private ImageView card_to,red_poin;
    private Banner banner;

    @Override
    public int setLayout() {
        return R.layout.activity_goodthings_list;
    }

    @Override
    protected void initView() {
        xrvGoodThing = (RecyclerView) findViewById(R.id.xrv_goodThing);
        iv_isHaiCard = (ImageView) findViewById(R.id.iv_isHaiCard);
        refreshLayout = (SmartRefreshLayout) findViewById(R.id.refresh_lay); // 刷新控件
        layShopping = (RelativeLayout) findViewById(R.id.lay_shopping);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initEvent() {
        layShopping.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        refreshLayout.setEnableRefresh(true);
        refreshLayout.setEnableLoadmore(true);
        refreshLayout.setHeaderHeight(100);
        setTitle("好物甄选");
        setBaseCenterText(new OnClickCenterTextCallBack() {
            @Override
            public void clickCenterText() {
                xrvGoodThing.scrollToPosition(0);
            }
        });
        GoodThingsDao.getsInstance(this).deleteAll();// 删除全部数据
        list = new ArrayList<>();
        goodThingAdapter = new GoodThingAdapter(R.layout.item_goodthings);
        goodThingAdapter.setContext(this);
        xrvGoodThing.setLayoutManager(new LinearLayoutManager(this));// 设置管理器
        xrvGoodThing.setAdapter(goodThingAdapter);
        View view = LayoutInflater.from(this).inflate(R.layout.view_header_goodthings, xrvGoodThing, false);
        goodThingAdapter.addHeaderView(view);
        ivHeader = (ImageView) view.findViewById(R.id.iv_header);
        card_to = bindView(view, R.id.card_to);
        red_poin = bindView(view,R.id.red_poin);
        banner = bindView(view, R.id.banner);
        banner.setOnBannerListener(this);
        card_to.setOnClickListener(this);
        //banner.setOnClickListener(this);
        ivHeader.setOnClickListener(this);
        View view1 = LayoutInflater.from(this).inflate(R.layout.item_end, xrvGoodThing, false);
        goodThingAdapter.addFooterView(view1);
        ivChakan = (ImageView) view1.findViewById(R.id.iv_chakan);
        ivChakan.setOnClickListener(this);
        TurnClassHttp.getFuLiList(page, 2, this, this);
        // 获取好物购物车数据
        getBaseBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refres();
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                loadMore();
            }
        });
        goodThingAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(GoodthingsListActivity.this, CommenWebActivity.class);
                String a = list.get(position).getW_title3();
                intent.putExtra("small_title", a);
                intent.putExtra("wel_url", list.get(position).getWel_url());
                intent.putExtra("title", list.get(position).getW_title());
                intent.putExtra("img_url", list.get(position).getW_pic());
                intent.putExtra("name1", list.get(position).getW_title3());
                intent.putExtra("w_id", list.get(position).getW_id());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        GoodThingsDao.getsInstance(this).deleteAll();// 删除全部数据
        EventBus.getDefault().unregister(this);
    }

    private void refres() {
        mpage = 1;
        page = "1";
        list.clear();
        TurnClassHttp.getFuLiList(page, 2, this, this);
    }

    private void loadMore() {
        mpage++;
        if (mpage >= 6) {
            // goodThingAdapter.set
            refreshLayout.finishLoadmore();
        } else {
            page = String.valueOf(mpage);
            TurnClassHttp.getFuLiList(page, 3, this, this);
        }

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_header:
                startActivity(new Intent(this, NewListActivity.class));
                break;
            case R.id.iv_chakan:
                startActivity(new Intent(this, SelectionChoiceActivity.class));
                break;
            case R.id.lay_shopping:
                startActivity(new Intent(this, ShoppingCardActivity.class));
                break;
            case R.id.card_to:
                startActivity(new Intent(this, ShoppingCardActivity.class));
                break;
        }
    }


    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        gson = GsonSingle.getGson();
        switch (resultCode) {

            case 2:
                goodThingsBean = gson.fromJson(stringResult, GoodThingsBean.class);
                if (goodThingsBean.getData() != null) {
                    list.clear();
                    list.addAll(goodThingsBean.getData());
                    goodThingAdapter.setNewData(list);
                }

                List<String> imageList = new ArrayList<>();
                List<String> titleList = new ArrayList<>();
                for (int i = 0; i < goodThingsBean.getBannres().size(); i++) {
                    imageList.add(goodThingsBean.getBannres().get(i).getB_img());
                    titleList.add(goodThingsBean.getBannres().get(i).getB_name());
                }
                setBanner(imageList, titleList);//轮播图

                refreshLayout.finishRefresh();
                break;
            case 3:
                goodThingsBean = gson.fromJson(stringResult, GoodThingsBean.class);
                if (goodThingsBean != null) {
                    list.addAll(goodThingsBean.getData());
                    goodThingAdapter.setNewData(list);

                }
                refreshLayout.finishLoadmore();
                break;
        }
    }


    @Override
    public void onFailureListener(String errMsg, int resultCode) {

    }

    /* 用于接收微信支付结果回调*/
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(NormalEventBean messageEvent) {
        if (messageEvent.getMessageCode().equals(SataicCode.CARDPAYSUCESS)) {
            finish();
        }
    }
    // 设置轮播图
    public void setBanner(List<String> imageList, List<String> titleList) {
        //设置banner样式
        banner.setBannerStyle(BannerConfig.NOT_INDICATOR);//  控制是否显示指示器
        //设置图片加载器//
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(imageList);
        //设置banner动画效果
        //  banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(titleList);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
       // banner.setIndicatorGravity(BannerConfig.RIGHT);
        //banner设置方法全部调用完毕时最后调用
        banner.start();


    }

    @Override
    public void OnBannerClick(int position) {
        String is = goodThingsBean.getBannres().get(position).getB_gone();
        String title = goodThingsBean.getBannres().get(position).getB_name();
        String web_url = goodThingsBean.getBannres().get(position).getB_url();
        String w_id = goodThingsBean.getBannres().get(position).getB_param();
        String iamgeUrl = goodThingsBean.getBannres().get(position).getB_img();

        Map<String, String> map_ekv0 = new HashMap<String, String>();
        map_ekv0.put("linkname",title);
        MobclickAgent.onEvent(context, "lunbotudianji", map_ekv0);// 友盟统计
        Intent intent = new Intent(this, CommenWebActivity.class);
        intent.putExtra("wel_url",web_url);
        intent.putExtra("title",title);
        intent.putExtra("img_url",iamgeUrl);
        intent.putExtra("name1",title);
        intent.putExtra("small_title", title);
        if ((is.equals("1"))) {
            intent.putExtra("w_id", w_id);
        } else {
            intent.putExtra("w_id", "");
        }
        startActivity(intent);
    }


}
