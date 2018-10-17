package com.sainti.blackcard.goodthings.goodtingsorder.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sainti.blackcard.R;
import com.sainti.blackcard.goodthings.goodtingsorder.bean.AllGoodthingsBean;
import com.sainti.blackcard.mhttp.glide.GlideManager;

/**
 * Created by YuZhenpeng on 2018/5/10.
 */

public class AllThingsAdapter extends BaseQuickAdapter<AllGoodthingsBean.DataBean, BaseViewHolder> {
    private Context context;
    private int state;

    public void setContext(Context context) {
        this.context = context;
    }

    public AllThingsAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, AllGoodthingsBean.DataBean item) {
        GlideManager.getsInstance().loadImageToUrLb(context, item.getProduct_img(), (ImageView) helper.getView(R.id.iv_title));
        helper.addOnClickListener(R.id.iv_goPay);
        state = item.getState();
        if (state == 0) {
            //代付款
            helper.setBackgroundRes(R.id.lly_bg, R.mipmap.daifukuan);
            helper.setVisible(R.id.iv_goPay, true);
            helper.setBackgroundRes(R.id.iv_goPay, R.mipmap.go_pay_g);
        } else if (state == 1) {// 已付款
            helper.setBackgroundRes(R.id.lly_bg, R.mipmap.yifukuan);
            helper.setVisible(R.id.iv_goPay, false);
        } else if (state == 2) {
            helper.setVisible(R.id.iv_goPay, true);
            helper.setBackgroundRes(R.id.lly_bg, R.mipmap.yifahuio);
            helper.setBackgroundRes(R.id.iv_goPay, R.mipmap.iv_loo);
// 已发货iv_loo
        } else if (state == 5) {
            helper.setVisible(R.id.iv_goPay, true);
            helper.setBackgroundRes(R.id.lly_bg, R.mipmap.tuikuam);
            helper.setBackgroundRes(R.id.iv_goPay, R.mipmap.iv_delelte);
        } else {
            helper.setVisible(R.id.iv_goPay, true);
            helper.setBackgroundRes(R.id.lly_bg, R.mipmap.yishixiao);
            helper.setBackgroundRes(R.id.iv_goPay, R.mipmap.iv_delelte);
// 已失效
        }

        helper.setText(R.id.product_name, item.getProduct_name());
        helper.setText(R.id.spec_name, "型号   " + item.getSpec_name());
        helper.setText(R.id.buy_number, "x" + item.getBuy_number());
        helper.setText(R.id.add_time, item.getAdd_time());
        helper.setText(R.id.product_price, "价格: ¥" + item.getProduct_price());
        if (!item.getYouhq_money().equals("0.00")) {
            helper.setVisible(R.id.youhq_money, true);
            helper.setText(R.id.youhq_money, "优惠劵:  -¥" + item.getYouhq_money());
        } else {
            helper.setVisible(R.id.youhq_money, false);
        }
        if (!item.getWo_blackkey().equals("0.00")) {
            helper.setVisible(R.id.wo_blackkey, true);
            helper.setText(R.id.wo_blackkey, "黑钥匙:  -¥" + item.getWo_blackkey());
        } else {
            helper.setVisible(R.id.wo_blackkey, false);
        }


        helper.setText(R.id.order_amount, "¥" + item.getOrder_amount());

    }
}
