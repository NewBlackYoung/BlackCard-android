package com.sainti.blackcard.goodthings.spcard.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseNoTitleActivity;
import com.sainti.blackcard.db.bean.GoodthingsBean;
import com.sainti.blackcard.db.dao.GoodThingsDao;
import com.sainti.blackcard.goodthings.activity.GoodthingsListActivity;
import com.sainti.blackcard.commen.mconstant.SataicCode;
import com.sainti.blackcard.meventbus.NormalEventBean;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.minterface.TimerListenerHasCode;
import com.sainti.blackcard.mtuils.CommontUtil;
import com.sainti.blackcard.mtuils.ConvertUtil;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.goodthings.spcard.adapter.ShoppingCardAdapter;
import com.sainti.blackcard.goodthings.spcard.baen.ShoppingCardBaseBean;
import com.sainti.blackcard.goodthings.spcard.baen.ShoppingCardBean;
import com.sainti.blackcard.widget.LoadingView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCardActivity extends BaseNoTitleActivity implements BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.OnItemLongClickListener, View.OnClickListener, BaseQuickAdapter.OnItemClickListener, OnNetResultListener, TimerListenerHasCode {


    private RecyclerView recyclerView;
    private ShoppingCardAdapter shoppingCardAdapter;
    private TextView tvBianjiaAlll, tvJsOrdelete, tvZongjias, tvHejiSp;
    private CheckBox ckAllselsectSp;
    private Map<String, String> cbxMap;
    private Map<String, String> cbxMapNew;
    private Map<String, String> cbxMapComplete;
    private ShoppingCardBaseBean baseBean;
    private ShoppingCardBean shoppingCardBean;
    private CheckBox ckItemSp;
    private LoadingView pd;
    private Float zongjia = 0.00f;
    private int canPayCount = 0;
    private ImageView ivBack;
    private TextView tvCountItem;
    private String count;
    private String json;
    private int counts;
    private TextView tvCountItemSp;
    private ImageView ivJianhaoSp;
    private List<GoodthingsBean> goodthingsBeanListDB;
    private boolean isAllDelete = false;
    private List<GoodthingsBean> goodListAllCheckDB;
    private List<GoodthingsBean> allList;
    private String countOld;
    private Float priceOld;
    private Float price = 0.00f;
    private boolean isPaySucess = true;
    private boolean canJieSuan = true;
    private boolean isBianjiAll = false;
    private RelativeLayout rl_div, rll_null;
    private ImageView tv_null;
    private boolean bj = false;


    @Override
    public int setLayout() {
        return R.layout.activity_shopping_card;
    }

    @Override
    protected void initView() {
        recyclerView = bindView(R.id.xrv_sp);
        tvBianjiaAlll = bindView(R.id.tv_bianjiaAlll); // 顶部编辑
        tvJsOrdelete = bindView(R.id.tv_jsOrdelete); // 底部结算和删除
        ckAllselsectSp = bindView(R.id.ck_allselsect_sp); // 全选按钮
        tvZongjias = bindView(R.id.tv_zongjias);     // 总价
        tvHejiSp = bindView(R.id.tv_heji_sp);
        ivBack = bindView(R.id.iv_back);
        rl_div = bindView(R.id.rl_div);
        tv_null = bindView(R.id.tv_null);
        rll_null = bindView(R.id.rll_null);





        
        EventBus.getDefault().register(this);




    }

    @Override
    protected void initEvents() {
        tvBianjiaAlll.setOnClickListener(this);
        tvJsOrdelete.setOnClickListener(this);
        ckAllselsectSp.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        tv_null.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        pd = new LoadingView(this);
        cbxMap = new HashMap<>();
        cbxMapNew = new HashMap<>();
        cbxMapComplete = new HashMap<>();// 判断是否有正在单个编辑状态的数据
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        shoppingCardAdapter = new ShoppingCardAdapter(R.layout.item_shooping_card);
        recyclerView.setAdapter(shoppingCardAdapter);
        shoppingCardAdapter.setContext(this);
        shoppingCardAdapter.setOnItemChildClickListener(this);
        shoppingCardAdapter.setOnItemLongClickListener(this);
        shoppingCardAdapter.setOnItemClickListener(this);
        pd.show();
        TurnClassHttp.getWelcartDate(2, this, this);
        tvJsOrdelete.setBackgroundResource(R.drawable.shap_jiesuan_un);
        tvJsOrdelete.setClickable(false);

    }


    /*Item子Vioew点击事件*/
    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        View lay_jiaJian = shoppingCardAdapter.getViewByPosition(recyclerView, position, R.id.lay_jiaJian);// 加号和减号
        View lay_countPrice = shoppingCardAdapter.getViewByPosition(recyclerView, position, R.id.lay_countPrice);// 价格和数量
        View tv_wancheng_sp = shoppingCardAdapter.getViewByPosition(recyclerView, position, R.id.tv_wancheng_sp);// 完成按钮
        View iv_bianji = shoppingCardAdapter.getViewByPosition(recyclerView, position, R.id.iv_bianji);// 编辑按钮
        ivJianhaoSp = (ImageView) shoppingCardAdapter.getViewByPosition(recyclerView, position, R.id.iv_jianhao_sp); // 减号btn
        tvCountItemSp = (TextView) shoppingCardAdapter.getViewByPosition(recyclerView, position, R.id.tv_countItem_sp); // 显示的商品个数 x 2
        ckItemSp = (CheckBox) shoppingCardAdapter.getViewByPosition(recyclerView, position, R.id.ck_item_sp); // CHECK按钮
        tvCountItem = (TextView) shoppingCardAdapter.getViewByPosition(recyclerView, position, R.id.tv_countItem);// 商品数量
        switch (view.getId()) {
            case R.id.iv_bianji: // 点击编辑
                cbxMapComplete.put(goodthingsBeanListDB.get(position).getTingsId(), "0"); // 添加正在编辑的数据
                lay_jiaJian.setVisibility(View.VISIBLE);// 显示加号和减号
                lay_countPrice.setVisibility(View.GONE); // 隐藏价格和数量
                tv_wancheng_sp.setVisibility(View.VISIBLE);// 显示完成
                tvJsOrdelete.setBackgroundResource(R.drawable.shap_jiesuan_un);
                tvJsOrdelete.setClickable(false);
                countOld = tvCountItemSp.getText().toString();
                canJieSuan = false;
                break;
            case R.id.tv_wancheng_sp:
                cbxMapComplete.remove(goodthingsBeanListDB.get(position).getTingsId());// 移除正在编辑的数据
                lay_jiaJian.setVisibility(View.GONE);// 显示加号和减号
                lay_countPrice.setVisibility(View.VISIBLE); // 隐藏价格和数量
                tv_wancheng_sp.setVisibility(View.GONE);// 显示编辑
                iv_bianji.setVisibility(View.VISIBLE);
                String conutNew = tvCountItemSp.getText().toString();
                if (!countOld.equals(conutNew)) {
                    toService(); // 只有当数据变化时在向后台发送数据
                }
                if (price != 0.00f) {
                    tvJsOrdelete.setBackgroundResource(R.drawable.shap_jiesuan_sp);

                    tvJsOrdelete.setClickable(true);
                    canJieSuan = true;
                }
                break;
            case R.id.ck_item_sp: // 单选事件
                singleCheck(position);
                break;
            case R.id.iv_jiahao_sp:
                String countView = tvCountItem.getText().toString();// 当前显示的商品数量)
                if (countView.equals("1")) {
                    ivJianhaoSp.setImageResource(R.mipmap.jianhao_sp);

                }
                addShopCard(goodthingsBeanListDB.get(position).getTingsId(), countView, "0", goodthingsBeanListDB.get(position).getTingsPrice(), goodthingsBeanListDB.get(position).getTingsYuanjia(), goodthingsBeanListDB.get(position).getTingsName(), goodthingsBeanListDB.get(position).getImageurl());// 数量
                isBianjiAll = true;
                break;
            case R.id.iv_jianhao_sp:
                String countViews = tvCountItem.getText().toString();// 当前显示的商品数量
                if (countViews.equals("1")) {
                    ivJianhaoSp.setImageResource(R.mipmap.jianhao_sph);
                    return; // 当商品为1时禁止减
                }
                addShopCard(goodthingsBeanListDB.get(position).getTingsId(), countViews, "1", goodthingsBeanListDB.get(position).getTingsPrice(), goodthingsBeanListDB.get(position).getTingsYuanjia(), goodthingsBeanListDB.get(position).getTingsName(), goodthingsBeanListDB.get(position).getImageurl());// 数量
                isBianjiAll = true;
                break;

        }

    }

    /* 商品数量相加或减*/
    private void addShopCard(String w_id, String count, String code, String price, String OldPrice, String name, String imageUrl) {

        // GoodThingsDao.getsInstance(this).deleteOne(w_id); // 删除当前好物商品
        if (code.equals("0")) {
            // 增加商品数量
            counts = Integer.parseInt(count) + 1;
        }
        if (code.equals("1")) {
            // 增加商品数量
            counts = Integer.parseInt(count) - 1;
        }
        tvCountItem.setText(String.valueOf(counts));// 设置当前数量
        GoodThingsDao.getsInstance(this).update(w_id, String.valueOf(counts)); // 更新当前数据count
        tvCountItemSp.setText(String.valueOf(counts));
        showPrice();
    }


    /*Item 长按点击事件*/
    @Override
    public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
        showDialog("确定删除该宝贝吗?", "1", goodthingsBeanListDB.get(position).getTingsId(), position);
        return true;
    }


    /*View  点击事件*/
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_bianjiaAlll:
                if (cbxMapComplete.size() == 0) {
                    bAndj(); // 编辑按钮
                } else {
                    return;
                }

                break;
            case R.id.tv_jsOrdelete:
                deleteOrJisuan(); // 结算按钮
                break;
            case R.id.ck_allselsect_sp:// 全选按钮
                if (ckAllselsectSp.isChecked()) {
                    allCheck();
                    tvJsOrdelete.setBackgroundResource(R.drawable.shap_jiesuan_sp);

                    tvJsOrdelete.setClickable(true);
                } else {
                    clearMap();
                    tvJsOrdelete.setBackgroundResource(R.drawable.shap_jiesuan_un);
                    tvJsOrdelete.setClickable(false);
                }
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_null:
                startActivity(new Intent(this, GoodthingsListActivity.class));
                finish();
                break;

        }
    }

    private void clearMap() {
        List<GoodthingsBean> goodList = GoodThingsDao.getsInstance(this).queryForPayState("0");
        cbxMap.clear();
        shoppingCardAdapter.setCbxMap(cbxMap, "0");
        zongjia = 0.00f;
        tvZongjias.setText("¥" + ConvertUtil.convertToString(zongjia));
        for (int i = 0; i < goodList.size(); i++) {
            if (goodList.get(i).getTingsType().equals("0")) {
                GoodThingsDao.getsInstance(this).updateChoice(goodList.get(i).getTingsId() + goodList.get(i).getTingsKindId(), "0");
            }
        }

    }

    /* 单选事件*/
    private void singleCheck(int position) {
        if (ckItemSp.isChecked()) {
            cbxMap.put((goodthingsBeanListDB.get(position).getTingsId()), goodthingsBeanListDB.get(position).getTingsPrice());
            cbxMapNew.put((goodthingsBeanListDB.get(position).getTingsId()), goodthingsBeanListDB.get(position).getTingsPrice());
            if (cbxMap.size() == canPayCount) {
                ckAllselsectSp.setChecked(true); // 可编辑的变为全选的时候 全选true
            }
            GoodThingsDao.getsInstance(this).updateChoice((goodthingsBeanListDB.get(position).getTingsId()), "1");
        } else {
            cbxMap.remove(goodthingsBeanListDB.get(position).getTingsId());
            cbxMapNew.remove(goodthingsBeanListDB.get(position).getTingsId());
            ckAllselsectSp.setChecked(false);
            GoodThingsDao.getsInstance(this).updateChoice((goodthingsBeanListDB.get(position).getTingsId()), "0");
        }
        shoppingCardAdapter.setCbxMap(cbxMap, "1"); // 只有传0时 刷新整个适配器
        showPrice();//显示当前以选择的总价
    }

    /* 根书库里的数据遍历已选择的上篇map 核算价格*/
    private void showPrice() {
        priceOld = 0.00f;
        price = 0.00f;

        for (String key : cbxMap.keySet()) {
            String priceStr = cbxMap.get(key);
            List<GoodthingsBean> priceListDB = GoodThingsDao.getsInstance(this).queryForContent(key);
            String cuontStr = priceListDB.get(0).getTingsCount();
            price = price + ConvertUtil.convertToFloat(priceStr) * Integer.parseInt(cuontStr);

            priceStr = priceListDB.get(0).getTingsYuanjia();
            priceOld = priceOld + ConvertUtil.convertToFloat(priceStr) * Integer.parseInt(cuontStr);

        }
        if (canJieSuan && cbxMapComplete.size() == 0) {
            if (price == 0.00f) {
                tvJsOrdelete.setBackgroundResource(R.drawable.shap_jiesuan_un);
                tvJsOrdelete.setClickable(false);
            } else {
                tvJsOrdelete.setBackgroundResource(R.drawable.shap_jiesuan_sp);
                tvJsOrdelete.setClickable(true);
            }
        }

        tvZongjias.setText("¥" + ConvertUtil.convertToString(price));

    }


    /* 全选事件*/
    private void allCheck() {
        //goodListAllCheckDB = GoodThingsDao.getsInstance(this).quaryAll();
        goodListAllCheckDB = GoodThingsDao.getsInstance(this).queryForPayState("0");
        cbxMap.clear();
        cbxMapNew.clear();
        if (tvBianjiaAlll.getText().equals("编辑")) {
            for (int i = 0; i < goodListAllCheckDB.size(); i++) {
                if (goodListAllCheckDB.get(i).getTingsType().equals("0")) {
                    cbxMap.put(goodListAllCheckDB.get(i).getTingsId(), goodListAllCheckDB.get(i).getTingsPrice());
                    cbxMapNew.put(goodListAllCheckDB.get(i).getTingsId(), goodListAllCheckDB.get(i).getTingsPrice());
                    GoodThingsDao.getsInstance(this).updateChoice(goodListAllCheckDB.get(i).getTingsId(), "1");
                }
            }

            showPrice();//显示当前以选择的总价
        } else {
            for (int i = 0; i < goodListAllCheckDB.size(); i++) {
                cbxMap.put(goodListAllCheckDB.get(i).getTingsId(), goodListAllCheckDB.get(i).getTingsPrice());// 0 为未点击的状态
                cbxMapNew.put(goodListAllCheckDB.get(i).getTingsId(), goodListAllCheckDB.get(i).getTingsPrice());// 0 为未点击的状态
            }
        }
        shoppingCardAdapter.setCbxMap(cbxMap, "0");
    }

    //  编辑和结算变化
    private void bAndj() {

        List<GoodthingsBean> allList = GoodThingsDao.getsInstance(this).queryForPayState("0");
        if (tvBianjiaAlll.getText().equals("编辑")) {
            tvJsOrdelete.setText("删除");
            tvBianjiaAlll.setText("完成");
           /* tvZongjias.setVisibility(View.GONE);
            tvHejiSp.setVisibility(View.GONE);*/
            shoppingCardAdapter.setCompleteCode("0");
            shoppingCardAdapter.setNewData(allList);
            /*显示全部编辑*/
            isBianjiAll = false;

        } else {
            tvJsOrdelete.setText("结算");
            tvBianjiaAlll.setText("编辑");
          /*  tvZongjias.setVisibility(View.VISIBLE);
            tvHejiSp.setVisibility(View.VISIBLE);*/
            shoppingCardAdapter.setCompleteCode("1");
            shoppingCardAdapter.setNewData(allList);
            if (isBianjiAll) {
                toService();
            }
        }
        isAllDelete = false;
        clearMap();
        ckAllselsectSp.setChecked(false);
    }

    //  编辑和结算变化
    private void deleteOrJisuan() {
        if (tvJsOrdelete.getText().equals("删除")) {
            // 当现实状态为删除时
            showDialog("确定删除\n已选择的宝贝吗？", "2", "", 8888888);
        } else {
            List<GoodthingsBean> orderBean = GoodThingsDao.getsInstance(this).queryForChoice("1");
            // 当现实状态为结算时
            if (cbxMapComplete.size() == 0 && orderBean.size() > 0) {
                List<GoodthingsBean> orderBeans = GoodThingsDao.getsInstance(this).queryForChoice("0");
                GoodThingsDao.getsInstance(this).updatePay(orderBean, "1");// 将选中的更新为 1
                GoodThingsDao.getsInstance(this).updatePay(orderBeans, "0"); // 未选中的跟新为
                Intent intent = new Intent();
                intent.setClass(this, CardOrderActivity.class);
                intent.putExtra("tvZongjias", tvZongjias.getText().toString());
                intent.putExtra("tvYuanjia", ConvertUtil.convertToString(priceOld));
                startActivityForResult(intent, 110);
            } else {

                return;
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 110 && resultCode == 111) {
            String isOrder = data.getStringExtra("isOrder"); // 0  未支付回来重新选择商品   //  支付了
            if (isOrder.equals("0")) {
                List<GoodthingsBean> orderBean = GoodThingsDao.getsInstance(this).queryForChoice("1");
                List<GoodthingsBean> orderBeans = GoodThingsDao.getsInstance(this).queryForChoice("0");
                GoodThingsDao.getsInstance(this).updatePay(orderBean, "0");// 将选中的更新为 0
                GoodThingsDao.getsInstance(this).updatePay(orderBeans, "0"); // 未选中的跟新为
                //   Toast.makeText(this, "未提交订单返回重新选择商品", Toast.LENGTH_SHORT).show();
            } else {
          /*      goodthingsBeanListDB = GoodThingsDao.getsInstance(this).queryForPayState("0");// 在数据库里面查询最新数据
                shoppingCardAdapter.setNewData(goodthingsBeanListDB);
                isPaySucess = false;*/
                //toService();
                // Toast.makeText(this, "已经选择完订单", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void toService() {
        pd.show();
        List<GoodthingsBean> allList = GoodThingsDao.getsInstance(this).queryForPayState("0");
        if (allList == null || allList.size() == 0) {
            tvBianjiaAlll.setVisibility(View.INVISIBLE);
            rll_null.setVisibility(View.VISIBLE);

        }
        try {
            json = changeArrayDateToJson(allList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        TurnClassHttp.addWelcart(json, 1, this, this);

    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        switch (resultCode) {
            case 1:
                //pd.dismiss();
                if (isPaySucess) {
                    CommontUtil.lateTimeHasCode(500, this, 1);
                    //ToastUtils.show(ShoppingCardActivity.this, "编辑成功");
                    bj = true;
                }
                if (isAllDelete) {
                    bAndj();
                }
                break;
            case 2:
                Gson gson = GsonSingle.getGson();
                baseBean = gson.fromJson(stringResult, ShoppingCardBaseBean.class);
                if (baseBean.getData() == null || baseBean.getData().equals("")) {
                    rll_null.setVisibility(View.VISIBLE);
                    tvBianjiaAlll.setVisibility(View.INVISIBLE);
                    pd.dismiss();
                    return;
                } else {
                    shoppingCardBean = gson.fromJson(stringResult, ShoppingCardBean.class);
                    if (shoppingCardBean.getData() == null || shoppingCardBean.getData().size() == 0) {
                        pd.dismiss();
                        tvBianjiaAlll.setVisibility(View.INVISIBLE);
                        rll_null.setVisibility(View.VISIBLE);
                        return;
                    }
                    rl_div.setVisibility(View.VISIBLE);
                    canPayCount = 0;
                    for (int i = 0; i < shoppingCardBean.getData().size(); i++) {
                        if (shoppingCardBean.getData().get(i).getW_sale().equals("0")) {
                            canPayCount++; // 合计一共有多少个未下架的好物商品
                        }
                    }
                    addCardDate(shoppingCardBean);// 将数据库的购物车刷新
                    goodthingsBeanListDB = GoodThingsDao.getsInstance(this).queryForPayState("0");// 在数据库里面查询最新数据
                    shoppingCardAdapter.setNewData(goodthingsBeanListDB);
                }
                pd.dismiss();
                if (bj) {
                    ToastUtils.show(ShoppingCardActivity.this, "编辑成功");
                    bj = false;
                }
                break;
            case 3:
                break;


        }
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        switch (resultCode) {
            case 1:
                pd.dismiss();
                ToastUtils.show(ShoppingCardActivity.this, "修改失败！请稍后尝试");
                break;
            case 2:
                Toast.makeText(this, "请求错误", Toast.LENGTH_SHORT).show();
                pd.dismiss();
                break;
            case 3:
                break;
        }
    }

    private String changeArrayDateToJson(List<GoodthingsBean> allList) throws JSONException {
        JSONArray j = new JSONArray();
        for (int i = 0; i < allList.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("w_id", allList.get(i).getwId());
            jsonObject.put("w_num", allList.get(i).getTingsCount());
            jsonObject.put("w_kindid", allList.get(i).getTingsKindId());
            j.put(jsonObject);
        }
        return j.toString();
    }

    private void showDialog(String text, final String code, final String w_idAndKindId, final int p) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.view_dialog_shopcard, null);
        TextView tv_deleteSure = (TextView) view.findViewById(R.id.tv_deleteSure); // 确定删除
        TextView tv_delelteText = (TextView) view.findViewById(R.id.tv_delelteText);// 删除字
        tv_delelteText.setText(text);
        TextView tv_teleteCso = (TextView) view.findViewById(R.id.tv_teleteCso);// 取消删除
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.show();
        tv_deleteSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (code.equals("1")) {
                    GoodThingsDao.getsInstance(ShoppingCardActivity.this).deleteOne(w_idAndKindId);
                    goodthingsBeanListDB.remove(p);
                    shoppingCardAdapter.setNewData(goodthingsBeanListDB);

                    toService();
                    if (cbxMap.containsKey(w_idAndKindId)) {
                        cbxMap.remove(w_idAndKindId);
                    }
                }
                if (code.equals("2")) {
                    for (String key : cbxMap.keySet()) {
                        GoodThingsDao.getsInstance(ShoppingCardActivity.this).deleteOne(key);
                    }
                    for (String key : cbxMapNew.keySet()) {
                        if (cbxMap.containsKey(key)) {
                            cbxMap.remove(key);
                        }
                    }
                    List<GoodthingsBean> goodthings = GoodThingsDao.getsInstance(ShoppingCardActivity.this).queryForPayState("0");
                    shoppingCardAdapter.setNewData(goodthings);

                    toService();
                    isAllDelete = true;
                }
                dialog.dismiss();
                showPrice();
                //  pd.show();

            }
        });
        tv_teleteCso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });
        dialog.setCancelable(false);
    }

    // 添加好物商品
    private void addCardDate(ShoppingCardBean shoppingCardBean) {
        GoodThingsDao.getsInstance(this).deleteAll();// 删除全部数据 重新添加
        for (int i = 0; i < shoppingCardBean.getData().size(); i++) {
            GoodthingsBean s = new GoodthingsBean();
            s.setwId(shoppingCardBean.getData().get(i).getW_id());
            s.setTingsCount(shoppingCardBean.getData().get(i).getW_num());
            s.setTingsId(shoppingCardBean.getData().get(i).getW_id() + shoppingCardBean.getData().get(i).getW_kindid()); // 将好物id和好物分类id 拼接
            s.setTingsType(shoppingCardBean.getData().get(i).getW_sale());
            s.setTingsPrice(shoppingCardBean.getData().get(i).getW_price());
            s.setTingsYuanjia(shoppingCardBean.getData().get(i).getW_o_price()); // 添加原价
            s.setTingsName(shoppingCardBean.getData().get(i).getW_name());// 添加添加姓名
            s.setImageurl(shoppingCardBean.getData().get(i).getW_img());
            s.setIsChoice("0");// 0 未被选择状态
            s.setIsPay("0");// 0 未支付的状态
            s.setTingsKind(shoppingCardBean.getData().get(i).getW_kind());
            s.setTingsKindId(shoppingCardBean.getData().get(i).getW_kindid());
            GoodThingsDao.getsInstance(this).add(s);
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        String type = goodthingsBeanListDB.get(position).getTingsType();
        if (type.equals("1")) {
            return;
        }
        ckItemSp = (CheckBox) shoppingCardAdapter.getViewByPosition(recyclerView, position, R.id.ck_item_sp); // CHECK按钮
        if (ckItemSp.isChecked()) {
            ckItemSp.setChecked(false);
        } else {
            ckItemSp.setChecked(true);
            canJieSuan = true;
        }
        singleCheck(position);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /* 用于接收微信支付结果回调*/
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(NormalEventBean messageEvent) {
        if (messageEvent.getMessageCode().equals(SataicCode.CARDPAYSUCESS)) {
            finish();
        }
        if (messageEvent.getMessageCode().equals(SataicCode.PAYSUCESSHAOWU)) {
            finish();
        }
    }

    @Override
    public void onTimerListener(int code) {
        TurnClassHttp.getWelcartDate(2, this, this);
    }
}
