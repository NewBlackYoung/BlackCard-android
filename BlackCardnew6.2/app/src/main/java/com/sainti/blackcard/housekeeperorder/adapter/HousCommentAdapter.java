package com.sainti.blackcard.housekeeperorder.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sainti.blackcard.R;
import com.sainti.blackcard.housekeeperorder.bean.GuanJiaOrderBean;

/**
 * Created by YuZhenpeng on 2018/5/10.
 */

public class HousCommentAdapter extends BaseQuickAdapter<GuanJiaOrderBean.DataBean, BaseViewHolder> {
    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public HousCommentAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, GuanJiaOrderBean.DataBean item) {
        String state = item.getState();
        helper.addOnClickListener(R.id.iv_delete);
        if (state.equals("0")) {
            // 已完成
            helper.setBackgroundRes(R.id.ll_guanjia, R.mipmap.yiwancheng);
            helper.setVisible(R.id.iv_delete, false);
            helper.setTextColor(R.id.tv_order_amount,context.getResources().getColor(R.color.more));
        } else if (state.equals("1")) {
            helper.setVisible(R.id.iv_delete, true);
            helper.setBackgroundRes(R.id.ll_guanjia, R.mipmap.daifukuan);
            helper.setBackgroundRes(R.id.iv_delete, R.mipmap.go_pay_g);
            helper.setTextColor(R.id.tv_order_amount,context.getResources().getColor(R.color.more));
            // 未付款
        } else if (state.equals("2")) {
            helper.setVisible(R.id.iv_delete, false);
            helper.setBackgroundRes(R.id.ll_guanjia, R.mipmap.yifukuan);
            helper.setTextColor(R.id.tv_order_amount,context.getResources().getColor(R.color.more));
            // 已付款
        } else if (state.equals("4")) {
            helper.setVisible(R.id.iv_delete, true);
            helper.setBackgroundRes(R.id.ll_guanjia, R.mipmap.tuikuam);
            helper.setBackgroundRes(R.id.iv_delete, R.mipmap.iv_delelte);
            helper.setTextColor(R.id.tv_order_amount,context.getResources().getColor(R.color.colorWhite));
        }else {
            helper.setVisible(R.id.iv_delete, true);
            helper.setBackgroundRes(R.id.ll_guanjia, R.mipmap.yishixiao);
            helper.setBackgroundRes(R.id.iv_delete, R.mipmap.iv_delelte);
            helper.setTextColor(R.id.tv_order_amount,context.getResources().getColor(R.color.colorWhite));
            // 已失效
        }
        helper.setText(R.id.tv_product_name, "产品名称:" + item.getProduct_name());
        helper.setText(R.id.tv_add_time, "订单创建时间:   " + item.getAdd_time());
        helper.setText(R.id.tv_product_price, "价格: ¥" + item.getProduct_price());
        String youhuiqua = item.getYouhq_money();
        if (!youhuiqua.equals("0.00")){
            helper.setVisible(R.id.tv_youhq_money,true);
            helper.setText(R.id.tv_youhq_money, "优惠券: -¥" + youhuiqua);
        }else {
            helper.setVisible(R.id.tv_youhq_money,false);
        }

        helper.setText(R.id.tv_order_amount, "¥"+item.getOrder_amount());

    }
}
