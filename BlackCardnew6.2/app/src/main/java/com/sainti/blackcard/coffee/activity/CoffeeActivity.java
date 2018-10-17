package com.sainti.blackcard.coffee.activity;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.coffee.adapter.LeftCoffeeAdapter;
import com.sainti.blackcard.coffee.adapter.PopuWindowAdapter;
import com.sainti.blackcard.coffee.adapter.PopuwindowListAdapter;
import com.sainti.blackcard.coffee.adapter.RightCoffeeAdapter;
import com.sainti.blackcard.coffee.bean.LeftCoffeeBean;
import com.sainti.blackcard.coffee.bean.RightCoffeeBean;
import com.sainti.blackcard.commen.mconstant.SataicCode;
import com.sainti.blackcard.db.bean.CoffeeCountBean;
import com.sainti.blackcard.db.bean.CoffeeLookBean;
import com.sainti.blackcard.db.dao.CoffeeCountDao;
import com.sainti.blackcard.db.dao.CoffeeLookDao;
import com.sainti.blackcard.meventbus.NormalEventBean;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.glide.GlideManager;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.minterface.CoffeeRightItemClickListener;
import com.sainti.blackcard.minterface.MyItemClickListener;
import com.sainti.blackcard.minterface.MyItemCountListener;
import com.sainti.blackcard.mtuils.ConvertUtil;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.mtuils.TimeUtil;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.widget.LoadingView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class CoffeeActivity extends BaseTitleActivity implements PopupWindow.OnDismissListener, View.OnClickListener, MyItemClickListener, MyItemCountListener, CoffeeRightItemClickListener, OnNetResultListener {

    private RecyclerView xrvLeftCoffee;
    private RecyclerView xrvRightCoffee;
    private LeftCoffeeAdapter leftCoffeeAdapter;
    private RightCoffeeAdapter rightCoffeeAdapter;
    private RightCoffeeBean rightCoffeeBean;
    private TextView tvJiesuan;
    private PopupWindow downPoup;
    private RelativeLayout activityCoffee;
    private PopupWindow popupWindow;
    private RelativeLayout layDibu;
    private LeftCoffeeBean leftCoffeeBean;
    private Gson gson;
    private RecyclerView xrvPopuwindow;
    private PopuWindowAdapter popuWindowAdapter;
    private View grayLayout;
    private PopupWindow lookThingPoup;
    private RecyclerView xRecyclerView;
    private PopuwindowListAdapter popuwindowListAdapter;
    private List<CoffeeLookBean> lookCoffeeBeen;
    private List<CoffeeCountBean> coffeeCountBeen;
    private LinearLayout layBody;
    private int choiceCount = 0;
    private int shangpinCount; // 获取当前点击的商品的数量
    private TextView tvChoiceCount;
    private float shangpinDanjia = 00.00f;
    private Float zongjiaFloat;
    private TextView tvZongjia;
    private TextView tvChoiceOk;
    private TextView tvCoffeeYiXuan;
    private TextView tvCoffeePrice, tv_coffeeName;
    private ImageView ivImage;
    private String city;
    private String citityCode;
    private String sheng;
    private boolean isDianJi = false;// 判断是否已经选择商品
    private int counts;
    private RelativeLayout layMine;
    private LoadingView loadingView;
    private int choiceOkPS;


    @Override
    public int setLayout() {
        return R.layout.activity_coffee;
    }

    @Override
    protected void initView() {
        xrvLeftCoffee = bindView(R.id.xrv_leftCoffee);
        xrvRightCoffee = bindView(R.id.xrv_rightCoffee);
        tvJiesuan = bindView(R.id.tv_jiesuan);
        activityCoffee = bindView(R.id.activity_coffee);
        layDibu = bindView(R.id.lay_dibu);
        grayLayout = bindView(R.id.gray_layout);
        layBody = bindView(R.id.lay_body);
        tvChoiceCount = bindView(R.id.tv_choice_count); // 已选商品数量
        tvZongjia = bindView(R.id.tv_zongjia);
        layMine = bindView(R.id.lay_mine);
        EventBus.getDefault().register(this);

    }

    @Override
    protected void initEvent() {
        tvJiesuan.setOnClickListener(this);
        layDibu.setOnClickListener(this);
        tvJiesuan.setOnClickListener(this);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /* 用于接收微信支付结果回调*/
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(NormalEventBean messageEvent) {
        if (messageEvent.getMessageCode().equals(SataicCode.PAYSUCESSCOFFEE)) {
            finish();
        }

    }

    @Override
    protected void initData() {
        setTitle("咖啡配送");
        Intent intent = getIntent();
        city = intent.getStringExtra("city");
        sheng = intent.getStringExtra("sheng");
        if (sheng.equals("江苏省") || sheng.equals("浙江省") || city.equals("上海市")) {
            citityCode = "0";
        } else {
            citityCode = "1";
        }
        loadingView = new LoadingView(this);
        leftCoffeeAdapter = new LeftCoffeeAdapter(this);
        rightCoffeeAdapter = new RightCoffeeAdapter(this);
        xrvLeftCoffee.setLayoutManager(new LinearLayoutManager(this));
        xrvRightCoffee.setLayoutManager(new LinearLayoutManager(this));
        xrvRightCoffee.setAdapter(rightCoffeeAdapter);
        xrvLeftCoffee.setAdapter(leftCoffeeAdapter);
        leftCoffeeAdapter.setListener(this);
        rightCoffeeAdapter.setListener(this);
        rightCoffeeAdapter.setMyItemCountListener(this);
        loadingView.show();
        TurnClassHttp.getLeftCoffeeDate(citityCode, 1, this, this);
        downPopupWindow();
        lookPopupWindow();
        lookCoffeeBeen = new ArrayList<>();
        CoffeeLookDao.getsInstance(context).deleteAll();
        CoffeeCountDao.getsInstance(context).deleteAll();
        getBaseBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }


    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        switch (resultCode) {
            case 1:
                gson = GsonSingle.getGson();
                leftCoffeeBean = gson.fromJson(stringResult, LeftCoffeeBean.class);
                if (leftCoffeeBean != null) {
                    leftCoffeeAdapter.setListBeen(leftCoffeeBean.getItemsList());
                    TurnClassHttp.getRightffeeDate(String.valueOf(leftCoffeeBean.getItemsList().get(0).getCg_id()), 2, this, this);
                }
                break;
            case 2:
                rightCoffeeBean = gson.fromJson(stringResult, RightCoffeeBean.class);
                if (rightCoffeeBean != null) {
                    rightCoffeeAdapter.setListBeen(rightCoffeeBean.getItemsList());
                }
                break;
        }
        loadingView.dismiss();

    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        loadingView.dismiss();
        showToast(errMsg);
    }

    /*************
     * 选择商品分类列表downPopupWindow
     ***************/
    private void downPopupWindow() {
        downPoup = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        downPoup.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        View view = LayoutInflater.from(context).inflate(R.layout.pupuwindow_coffee, null);
        xrvPopuwindow = (RecyclerView) view.findViewById(R.id.xrv_popuwindow);
        tvChoiceOk = (TextView) view.findViewById(R.id.tv_choiceOk);// 已选好了
        tvCoffeeYiXuan = (TextView) view.findViewById(R.id.tv_coffeeYiXuan);  // 已选种类
        tvCoffeePrice = (TextView) view.findViewById(R.id.tv_coffeePrice); // 选择种类的价格
        tv_coffeeName = (TextView) view.findViewById(R.id.tv_coffeeName); // 选择名字
        ivImage = (ImageView) view.findViewById(R.id.iv_image); // 选择coffee图片
        tvChoiceOk.setOnClickListener(this);
        xrvPopuwindow.setLayoutManager(new GridLayoutManager(this, 3));
        popuWindowAdapter = new PopuWindowAdapter(this);
        xrvPopuwindow.setAdapter(popuWindowAdapter);
        popuWindowAdapter.setListener(this);
        downPoup.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        downPoup.setAnimationStyle(R.style.popwin_home_style);
        downPoup.setContentView(view);
        downPoup.setOnDismissListener(this);
        downPoup.setFocusable(true);
    }

    /*************
     * 查看商品分类列表downPopupWindow
     ***************/
    private void lookPopupWindow() {
        lookThingPoup = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT,
                380);
        lookThingPoup.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        View view = LayoutInflater.from(context).inflate(R.layout.popuwindow_lookcoffee, null);
        xRecyclerView = (RecyclerView) view.findViewById(R.id.xrv_popuwindow_look);
        xRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        popuwindowListAdapter = new PopuwindowListAdapter(this);
        xRecyclerView.setAdapter(popuwindowListAdapter);
        popuwindowListAdapter.setListener(this);
        lookThingPoup.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        lookThingPoup.setAnimationStyle(R.style.popwin_home_style);
        lookThingPoup.setContentView(view);
        lookThingPoup.setOnDismissListener(this);
        lookThingPoup.setFocusable(true);
    }

    // popuwindo消失事件
    @Override
    public void onDismiss() {
        grayLayout.setVisibility(View.GONE);
    }

    // View 点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_jiesuan:
                if (isDianJi) {
                    if (TimeUtil.getTIme().size() > 1) {
                        Intent intent = new Intent(this, CoffeeOrderDetailActiviity.class);
                        intent.putExtra("zongPrice", tvZongjia.getText());
                        intent.putExtra("zongCount", tvChoiceCount.getText());
                        startActivityForResult(intent, 101);
                    } else {
                        ToastUtils.show(this, "配送时间已过！请明天再来");
                    }
                }

                break;
            case R.id.lay_dibu: // 点击下部查看已选商品列表
                if (isDianJi) {
                    if (lookThingPoup.isShowing()) {
                        lookThingPoup.dismiss();
                    } else {
                        lookCoffeeBeen.clear();
                        lookCoffeeBeen = CoffeeLookDao.getsInstance(this).quaryAll();
                        popuwindowListAdapter.setLookCoffeeBeen(lookCoffeeBeen);
                        grayLayout.setVisibility(View.VISIBLE);
                        lookThingPoup.showAtLocation(layMine, Gravity.BOTTOM, 0, layDibu.getLayoutParams().height);
                    }
                }
                break;
            case R.id.tv_choiceOk: // 选好了点击事件
                downPoup.dismiss();
                addCoffeeHasKind(tv_coffeeName.getText().toString(), tvCoffeeYiXuan.getText().toString(), tvCoffeePrice.getText().toString(), "", rightCoffeeBean.getItemsList().get(choiceOkPS).getImageUrl()
                );
                addCountAndPrices(ConvertUtil.convertToFloat(tvCoffeePrice.getText().toString()));
                isHasThing();// 判断UI是否有商品
                coffeeCountBeen = CoffeeCountDao.getsInstance(this).quaryAll();
                rightCoffeeAdapter.setLookBeen(coffeeCountBeen);
                rightCoffeeAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == 102) {
            zhiBack();
        }
    }

    @Override
    public void onCoffeeRightItemClick(int position, int code, int wai) {
        switch (code) {
            case 1: //popuwindow   Item点击事件
                tvCoffeeYiXuan.setText(rightCoffeeBean.getItemsList().get(wai).getOrdSpecificationsList().get(position).getSftName());
                tvCoffeePrice.setText(rightCoffeeBean.getItemsList().get(wai).getOrdSpecificationsList().get(position).getSftPrice() + "");
                popuWindowAdapter.setCode(position);
                break;
        }
    }

    @Override
    public void onItemClick(int position, int code) {
        switch (code) {
            case 5:// 点击左侧侧列表
                loadingView.show();
                rightCoffeeAdapter.setLookBeen(coffeeCountBeen);
                leftCoffeeAdapter.setDianjiCode(position);
                TurnClassHttp.getRightffeeDate(String.valueOf(leftCoffeeBean.getItemsList().get(position).getCg_id()), 2, this, this);
                break;

        }
    }

    @Override
    public void onItemClick(int position, int code, String count) {
        switch (code) {
            case 0: //点击加号
                choiceOkPS = position;
                String clickName = rightCoffeeBean.getItemsList().get(position).getGdsName();
                if (rightCoffeeBean.getItemsList().get(position).getOrdSpecificationsList() != null && rightCoffeeBean.getItemsList().get(position).getOrdSpecificationsList().size() > 0) {
                    popuWindowAdapter.setListBeen(rightCoffeeBean.getItemsList().get(position).getOrdSpecificationsList(), position);
                    // 有分类 ，进入弹出框
                    if (downPoup.isShowing()) {
                        downPoup.dismiss();
                    } else {
                        popuWindowAdapter.setCode(0);
                        shangpinCount = Integer.parseInt(count);// 获取当前点击的商品的数量
                        shangpinCount--;
                        grayLayout.setVisibility(View.VISIBLE);
                        sheZhiWindowDate(rightCoffeeBean.getItemsList().get(position).getImageUrl(), rightCoffeeBean.getItemsList().get(position).getGdsName(), position); // 设置弹出框头像和coffee名字
                        downPoup.showAtLocation(activityCoffee, Gravity.BOTTOM, 0, 0);
                    }
                } else {
                    addCoffeeNoKind(clickName, "", rightCoffeeBean.getItemsList().get(position).getGdsPirce() + "", count, rightCoffeeBean.getItemsList().get(position).getImageUrl());
                    String danjia = rightCoffeeBean.getItemsList().get(position).getGdsPirce().toString(); // 获取商品单价
                    Float danjia_float = ConvertUtil.convertToFloat(danjia);
                    addCountAndPrices(danjia_float);
                    isHasThing();// 判断UI是否有商品
                }
                break;
            case 1:// 减号
                String clickNames = rightCoffeeBean.getItemsList().get(position).getGdsName();
                if (rightCoffeeBean.getItemsList().get(position).getOrdSpecificationsList() != null && rightCoffeeBean.getItemsList().get(position).getOrdSpecificationsList().size() > 0) {
                    popuWindowAdapter.setListBeen(rightCoffeeBean.getItemsList().get(position).getOrdSpecificationsList(), position);
                    // 有分类 ，进入商品列表弹出框
                    if (lookThingPoup.isShowing()) {
                        lookThingPoup.dismiss();
                    } else {
                        lookCoffeeBeen = CoffeeLookDao.getsInstance(this).quaryAll();
                        popuwindowListAdapter.setLookCoffeeBeen(lookCoffeeBeen);
                        grayLayout.setVisibility(View.VISIBLE);
                        lookThingPoup.showAtLocation(activityCoffee, Gravity.BOTTOM, 0, layDibu.getLayoutParams().height);
                    }
                } else {
                    // 直接商品减一
                    addCoffeeNoKind(clickNames, "", rightCoffeeBean.getItemsList().get(position).getGdsPirce() + "", count, rightCoffeeBean.getItemsList().get(position).getImageUrl());
                    String danjia = rightCoffeeBean.getItemsList().get(position).getGdsPirce().toString(); // 获取商品单价
                    Float danjia_float = ConvertUtil.convertToFloat(danjia);
                    deleteCountAndPrices(danjia_float);
                    isHasThing();// 判断UI是否有商品
                }
                break;
            case 6: // 弹出框加号
                String kind = lookCoffeeBeen.get(position).getCoffeeKind();
                if (kind.equals("") || kind == null) {
                    addCoffeeNoKind(lookCoffeeBeen.get(position).getCoffeeName(), "", lookCoffeeBeen.get(position).getCoffeePrice(), count, lookCoffeeBeen.get(position).getImageUrl());
                } else {
                    addCoffeeHasKind(lookCoffeeBeen.get(position).getCoffeeName(), kind, lookCoffeeBeen.get(position).getCoffeePrice(), "", lookCoffeeBeen.get(position).getImageUrl());

                }
                refreshRightDate();
                addCountAndPrices(ConvertUtil.convertToFloat(lookCoffeeBeen.get(position).getCoffeePrice())); // 添加底部商品价格和总数
                break;
            case 7: // 弹出框减
                String kinds = lookCoffeeBeen.get(position).getCoffeeKind();
                deleteCountAndPrices(ConvertUtil.convertToFloat(lookCoffeeBeen.get(position).getCoffeePrice()));
                if (count.equals("0")) {
                    deleteCoffee(lookCoffeeBeen.get(position).getCoffeeName(), kinds);//
                    lookCoffeeBeen = CoffeeLookDao.getsInstance(this).quaryAll();
                    popuwindowListAdapter.setLookCoffeeBeen(lookCoffeeBeen);

                } else {

                    deleteCoffeeHasCount(lookCoffeeBeen.get(position).getCoffeeName(), kinds, lookCoffeeBeen.get(position).getCoffeePrice(), count, lookCoffeeBeen.get(position).getImageUrl());
                }

                refreshRightDate();
                break;
        }
    }

    // 减号 当count等于零时
    private void deleteCoffee(String name, String kind) {
        if (kind == null || kind.equals("")) {
            CoffeeCountDao.getsInstance(this).deleteOne(name);// 没有分类 商品数量清零
            CoffeeLookDao.getsInstance(this).deleteOne(name);
        } else {
            List<CoffeeCountBean> coffeeCountBeen = CoffeeCountDao.getsInstance(this).queryForContent(name);
            CoffeeLookDao.getsInstance(this).deleteOneForTwo(name, kind);// 删除查看列表 不在替换数据
            CoffeeCountDao.getsInstance(this).deleteOne(name);
            if (!coffeeCountBeen.get(0).getChoiceCount().equals("1")) {
                counts = Integer.parseInt(coffeeCountBeen.get(0).getChoiceCount());
                newDateCount(name, String.valueOf(counts));
            }
        }

    }

    private void deleteCoffeeHasCount(String name, String kind, String prce, String count, String url) {
        if (kind == null || kind.equals("")) {
            CoffeeCountDao.getsInstance(this).deleteOne(name);// 没有分类 商品数量清零
            CoffeeLookDao.getsInstance(this).deleteOne(name);
            newDateCount(name, count);
            newDate(name, kind, prce, count, url);
        } else {
            CoffeeLookDao.getsInstance(this).deleteOneForTwo(name, kind);// 先把当前对象删除
            newDate(name, kind, prce, count, url);// 添加新的数量对象
            List<CoffeeCountBean> coffeeCountBeanList = CoffeeCountDao.getsInstance(this).queryForContent(name);
            CoffeeCountDao.getsInstance(this).deleteOne(name);// 清除当前对象
            counts = Integer.parseInt(coffeeCountBeanList.get(0).getChoiceCount()) - 1;
            newDateCount(name, String.valueOf(counts));
        }

    }

    // 添加弹出框的数据
    private void sheZhiWindowDate(String imageUrl, String coffeeName, int wai) {
        tvCoffeeYiXuan.setText(rightCoffeeBean.getItemsList().get(wai).getOrdSpecificationsList().get(0).getSftName());
        tvCoffeePrice.setText(rightCoffeeBean.getItemsList().get(wai).getOrdSpecificationsList().get(0).getSftPrice() + "");
        tv_coffeeName.setText(coffeeName);
        GlideManager.getsInstance().loadImageToUrL(this, imageUrl, ivImage);
    }


    private void refreshRightDate() {
        coffeeCountBeen = CoffeeCountDao.getsInstance(this).quaryAll(); //重新刷新右侧列表数据
        rightCoffeeAdapter.setLookBeen(coffeeCountBeen);
        rightCoffeeAdapter.notifyDataSetChanged();
    }


    // 刷新结算UI
    private void isHasThing() {
        if (choiceCount == 0) {
            isDianJi = false;
            tvJiesuan.setBackgroundResource(R.drawable.shap_jiesuan_un);

            tvJiesuan.setTextColor(getResources().getColor(R.color.d_b));
            if (lookThingPoup.isShowing()) {
                lookThingPoup.dismiss();
            }
        }
        if (choiceCount == 1) {
            isDianJi = true;
            tvJiesuan.setBackgroundResource(R.drawable.shap_jiesuan);

            tvJiesuan.setTextColor(getResources().getColor(R.color.e_eight));
        }
    }

    private void zhiBack() {
        isDianJi = false;
        CoffeeLookDao.getsInstance(this).deleteAll();
        CoffeeCountDao.getsInstance(this).deleteAll();
        refreshRightDate();
        tvJiesuan.setBackgroundResource(R.drawable.shap_jiesuan_un);
        tvJiesuan.setTextColor(getResources().getColor(R.color.d_b));
        choiceCount = 0;
        tvChoiceCount.setText(String.valueOf(choiceCount)); // 添加商品数量相减
        zongjiaFloat = 00.00f;
        tvZongjia.setText("¥" + ConvertUtil.convertToString(zongjiaFloat));
    }

    /*........................................................................................................................................................*/
    // 商品数量和总价叠加
    private void addCountAndPrices(Float danjia_float) {
        choiceCount++;
        tvChoiceCount.setText(String.valueOf(choiceCount)); // 添加商品数量叠加
        zongjiaFloat = shangpinDanjia + danjia_float;
        shangpinDanjia = zongjiaFloat;
        tvZongjia.setText("¥" + ConvertUtil.convertToString(zongjiaFloat));

    }

    // 商品数量和总价相减
    private void deleteCountAndPrices(Float danjia_float) {
        choiceCount--;

        if (choiceCount >= 0) {
            tvChoiceCount.setText(String.valueOf(choiceCount)); // 添加商品数量叠加
        }
        zongjiaFloat = shangpinDanjia - danjia_float;
        shangpinDanjia = zongjiaFloat;
        if (shangpinDanjia >= 00.00f) {
            tvZongjia.setText("¥" + ConvertUtil.convertToString(zongjiaFloat));
        }
        isHasThing();

    }

    // 添加和删除coffee 没有分类
    private void addCoffeeNoKind(String name, String kind, String price, String count, String url) {
        CoffeeLookDao.getsInstance(this).deleteOne(name); // 先删除当前对象
        CoffeeCountDao.getsInstance(this).deleteOne(name);// 先删除当前对象
        if (Integer.parseInt(count) > 0) {
            newDate(name, kind, price, count, url);// 重新添加对象 替换count
            newDateCount(name, count);
        }
    }


    // 添加coffee 有分类
    private void addCoffeeHasKind(String name, String kind, String price, String count, String url) {
        List<CoffeeLookBean> lookCoffeeBeanList = CoffeeLookDao.getsInstance(this).queryForTwoContent(name, kind); // 首先 先去查里面一样有没有点前分类的coffee
        if (lookCoffeeBeanList == null || lookCoffeeBeanList.size() == 0) {
            counts = 1;
            newDate(name, kind, price, String.valueOf(counts), url);
        }
        if (lookCoffeeBeanList.size() > 0) {
            CoffeeLookDao.getsInstance(this).deleteOneForTwo(name, kind);//
            counts = Integer.parseInt(lookCoffeeBeanList.get(0).getChoiceCount()) + 1;
            newDate(name, kind, price, String.valueOf(counts), url);
        }

        List<CoffeeCountBean> coffeeCountBeanList = CoffeeCountDao.getsInstance(this).queryForContent(name);
        if (coffeeCountBeanList == null || coffeeCountBeanList.size() == 0) {
            newDateCount(name, "1");
        }
        if (coffeeCountBeanList.size() > 0) {
            String yixuanCount = coffeeCountBeanList.get(0).getChoiceCount();
            CoffeeCountDao.getsInstance(this).deleteOne(name);
            counts = Integer.parseInt(yixuanCount) + 1;
            newDateCount(name, String.valueOf(counts));
        }

    }

    // 添加 有分类coffee对象
    private void newDate(String name, String kind, String price, String count, String imageUrl) {
        CoffeeLookBean coffeeLookBean = new CoffeeLookBean();
        coffeeLookBean.setCoffeeName(name);
        coffeeLookBean.setCoffeeKind(kind);
        coffeeLookBean.setCoffeePrice(price);
        coffeeLookBean.setChoiceCount(count);
        coffeeLookBean.setImageUrl(imageUrl);
        CoffeeLookDao.getsInstance(this).add(coffeeLookBean); // 重新添加当前对象 替换数量

    }

    // 添加 没有分类coffee对象
    private void newDateCount(String name, String count) {
        CoffeeCountBean coffeeCountBean = new CoffeeCountBean();
        coffeeCountBean.setChoiceCount(count);
        coffeeCountBean.setCoffeeName(name);
        CoffeeCountDao.getsInstance(this).add(coffeeCountBean); // 重新添加当前对象 替换数量
    }


}
