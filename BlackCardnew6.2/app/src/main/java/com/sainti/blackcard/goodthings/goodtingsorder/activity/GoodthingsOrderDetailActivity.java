package com.sainti.blackcard.goodthings.goodtingsorder.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseBean;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.commen.mconstant.SataicCode;
import com.sainti.blackcard.commen.mconstant.SpCodeName;
import com.sainti.blackcard.commen.mdialog.CommenDialogUtil;
import com.sainti.blackcard.goodthings.activity.HaoWuZhiFuActivity;
import com.sainti.blackcard.goodthings.goodtingsorder.bean.HaoWDetaileBean;
import com.sainti.blackcard.meventbus.NormalEventBean;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.glide.GlideManager;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.minterface.TimerListener;
import com.sainti.blackcard.mtuils.CommontUtil;
import com.sainti.blackcard.mtuils.ConvertUtil;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.mtuils.SpUtil;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.widget.LoadingView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class GoodthingsOrderDetailActivity extends BaseTitleActivity implements OnNetResultListener, TimerListener, CommenDialogUtil.CallBack {

    private LoadingView loadingView;
    private TextView tv_bottom, tv_product_name, tv_copy, tv_delete_s, tv_spec_name, tv_buy_number, tv_names, tv_add_time, tv_number, tv_order_sn, tv_product_price, tv_youhq_money, tv_addresss, tv_istimer, tv_order_amount, tv_wo_blackkey;
    private ImageView iv_photo, iv_satte;
    private View bg;
    private Gson gson;
    private HaoWDetaileBean haoWDetaileBean;


    @Override
    public int setLayout() {
        return R.layout.activity_goodthings_order_detail;
    }

    @Override
    protected void initView() {
        tv_bottom = bindView(R.id.tv_bottom);// 底部按钮
        tv_product_name = bindView(R.id.tv_product_name);// 好物名称
        tv_spec_name = bindView(R.id.tv_spec_name);// 型号
        tv_buy_number = bindView(R.id.tv_buy_number);//购买个数
        tv_names = bindView(R.id.tv_names);//姓名
        tv_add_time = bindView(R.id.tv_add_time);//创建时间
        tv_number = bindView(R.id.tv_number); // 手机号
        tv_order_sn = bindView(R.id.tv_order_sn); // 订单编号
        tv_product_price = bindView(R.id.tv_product_price);// 订单单价
        tv_youhq_money = bindView(R.id.tv_youhq_money);// 优惠券
        tv_addresss = bindView(R.id.tv_addresss);// 地址
        tv_istimer = bindView(R.id.tv_istimer);// 底部是否失效
        tv_order_amount = bindView(R.id.tv_order_amount); // 实付款
        tv_wo_blackkey = bindView(R.id.tv_wo_blackkey);// 黑钥匙
        iv_photo = bindView(R.id.iv_photo); //好物头像
        iv_satte = bindView(R.id.iv_satte);  //右侧好物状态
        bg = bindView(R.id.bg);
        tv_delete_s = bindView(R.id.tv_delete_s);
        EventBus.getDefault().register(this);
        tv_copy = bindView(R.id.tv_copy);

    }

    @Override
    protected void initEvent() {
        tv_bottom.setOnClickListener(this);
        tv_copy.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tv_bottom:
                int state = haoWDetaileBean.getData().getState();
                if (state == 0) {
                    //代付款
                    //ToastUtils.show(context, "去付款");
                    poPya();
                } else if (state == 1) {// 已付款
                    return;
                } else if (state == 2) {
                    ToastUtils.show(context, "查看物流");
                    // 已发货iv_loo
                } else {
                    //   ToastUtils.show(context, "删除");
                    // 已失效
                    showDialog();

                }
                break;
            case R.id.tv_copy:
                CommontUtil.onClickCopy(tv_order_sn, this);
                break;
        }
    }

    @Override
    protected void initData() {
        loadingView = new LoadingView(this);
        setTitle("订单详情");
        String id = getIntent().getStringExtra("id");
        loadingView.show();
        TurnClassHttp.haowuorderdetail(id, 1, this, this);

    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        gson = GsonSingle.getGson();
        loadingView.dismiss();
        switch (resultCode) {
            case 1:
                haoWDetaileBean = gson.fromJson(stringResult, HaoWDetaileBean.class);
                tv_names.setText(haoWDetaileBean.getData().getConsignee());
                tv_number.setText(haoWDetaileBean.getData().getMobile());
                tv_addresss.setText(haoWDetaileBean.getData().getAddress());
                GlideManager.getsInstance().loadImageToUrLb(this, haoWDetaileBean.getData().getProduct_img(), iv_photo);
                tv_product_name.setText(haoWDetaileBean.getData().getProduct_name());
                tv_spec_name.setText("型号:  " + haoWDetaileBean.getData().getSpec_name());
                tv_buy_number.setText("x" + haoWDetaileBean.getData().getBuy_number());
                tv_add_time.setText(haoWDetaileBean.getData().getAdd_time());
                tv_order_sn.setText(haoWDetaileBean.getData().getOrder_sn());
                tv_product_price.setText("      ¥"+haoWDetaileBean.getData().getProduct_price());
                String youhui = haoWDetaileBean.getData().getYouhq_money();
                if (!youhui.equals("0.00")) {
                    tv_youhq_money.setText("优惠券 :   -¥" + youhui);
                } else {
                    tv_youhq_money.setVisibility(View.GONE);
                }
                tv_wo_blackkey.setText(" -¥" + haoWDetaileBean.getData().getWo_blackkey());
                tv_order_amount.setText("¥" + haoWDetaileBean.getData().getOrder_amount());
                int state = haoWDetaileBean.getData().getState();
                if (state == 0) {
                    //代付款
                    iv_satte.setVisibility(View.INVISIBLE);
                    tv_bottom.setBackgroundResource(R.mipmap.gopay_b);
                    tv_istimer.setTextColor(getResources().getColor(R.color.eight_f));
                    tv_order_amount.setTextColor(getResources().getColor(R.color.eight_f));
                } else if (state == 1) {// 已付款
                    iv_satte.setBackgroundResource(R.mipmap.yif);
                    tv_bottom.setVisibility(View.INVISIBLE);
                    tv_istimer.setVisibility(View.INVISIBLE);
                    tv_order_amount.setTextColor(getResources().getColor(R.color.eight_f));
                } else if (state == 2) {// 已发货iv_loo
                    tv_istimer.setVisibility(View.INVISIBLE);
                    iv_satte.setBackgroundResource(R.mipmap.yifahuo);
                    tv_bottom.setBackgroundResource(R.mipmap.look_b);
                    tv_order_amount.setTextColor(getResources().getColor(R.color.eight_f));

                } else if (state == 5) {// 已发货iv_loo
                    tv_istimer.setTextColor(getResources().getColor(R.color.eight_four));
                    iv_satte.setBackgroundResource(R.mipmap.yituikuanlan);
                    tv_bottom.setBackgroundResource(R.mipmap.delete_b);
                    tv_order_amount.setTextColor(getResources().getColor(R.color.black));

                }else { // 已失效yisiao
                    tv_istimer.setTextColor(getResources().getColor(R.color.eight_four));
                    iv_satte.setBackgroundResource(R.mipmap.yisiao);
                    tv_bottom.setBackgroundResource(R.mipmap.delete_b);
                    tv_order_amount.setTextColor(getResources().getColor(R.color.black));
                }
                break;

            case 2:
                BaseBean baseBean = gson.fromJson(stringResult, BaseBean.class);
                if (baseBean.getResult().equals("1")) {
                    SpUtil.initEditor(SpCodeName.SPNAME).putBoolean(SpCodeName.ISOPERATION, true).commit();
                    tv_delete_s.setVisibility(View.VISIBLE);
                    CommontUtil.lateTime(1500, this);
                } else {
                    bg.setVisibility(View.GONE);
                    ToastUtils.show(this, baseBean.getMsg());
                }
                break;
        }


    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        switch (resultCode) {
            case 1:
                break;
            case 2:
                ToastUtils.show(this, errMsg);
                break;
        }
        loadingView.dismiss();
    }

    private void poPya() {
        String ordernum = haoWDetaileBean.getData().getOrder_sn();// 订单号
        String dingdanid = haoWDetaileBean.getData().getOrder_id();//商品id
        double money = ConvertUtil.convertToDouble(haoWDetaileBean.getData().getOrder_amount(), 00.0f);
        String orderName = haoWDetaileBean.getData().getProduct_name();
        String fenlei = haoWDetaileBean.getData().getSpec_name();
        String count = haoWDetaileBean.getData().getBuy_number();
        String url = haoWDetaileBean.getData().getProduct_img();
        Intent intent = new Intent(context, HaoWuZhiFuActivity.class);
        intent.putExtra("ordernum", ordernum);
        intent.putExtra("dingdanid", dingdanid);
        intent.putExtra("money", money+"");
        intent.putExtra("orderName", orderName);
        intent.putExtra("fenlei", fenlei);
        intent.putExtra("count", count);
        intent.putExtra("url", url);
        startActivity(intent);
    }

    private void showDialog() {
        bg.setVisibility(View.VISIBLE);
        CommenDialogUtil.getInstance().showDialog(this, "删除后订单记录将？", "无法找回，确定删除吗？", this, 1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /* 用于接收微信支付结果回调*/
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(NormalEventBean messageEvent) {

        if (messageEvent.getMessageCode().equals("77777") || messageEvent.getMessageCode().equals("88888")) {
            finish();
        }
        if (messageEvent.getMessageCode().equals(SataicCode.PAYSUCESSHAOWU)) {
            finish();
        }
    }

    @Override
    public void onTimerListener() {
        finish();
    }

    @Override
    public void onSureClick(int tpye) {
        TurnClassHttp.delhaowuorder(haoWDetaileBean.getData().getOrder_id(), 2, GoodthingsOrderDetailActivity.this, GoodthingsOrderDetailActivity.this);
        loadingView.show();
    }

    @Override
    public void onCansoClick(int tpye) {
        bg.setVisibility(View.GONE);
    }
}
