package com.sainti.blackcard.my.flyorder.drivinglicense.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sainti.blackcard.R;
import com.sainti.blackcard.my.flyorder.drivinglicense.bean.DrivingCommentBena;

/**
 * Created by YuZhenpeng on 2018/7/3.
 */

public class AllAdapter extends BaseQuickAdapter<DrivingCommentBena.DataBean,BaseViewHolder> {
    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public AllAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, DrivingCommentBena.DataBean item) {

        String state = item.getState();
        helper.addOnClickListener(R.id.iv_delete);
// 订单状态，0待付款，1待接单，2待签收，    3无效，     4已完成，   5退款中，6已退款
        if (state.equals("4")) {
            // 已完成
            helper.setBackgroundRes(R.id.ll_guanjia, R.mipmap.yiwancheng);
            helper.setVisible(R.id.iv_delete, false);
            helper.setTextColor(R.id.tv_order_amount,context.getResources().getColor(R.color.more));
        } else if (state.equals("0")) {
            helper.setVisible(R.id.iv_delete, true);
            helper.setBackgroundRes(R.id.ll_guanjia, R.mipmap.daifukuan);
            helper.setBackgroundRes(R.id.iv_delete, R.mipmap.go_pay_g);
            helper.setTextColor(R.id.tv_order_amount,context.getResources().getColor(R.color.more));
            // 未付款
        } else if (state.equals("1")) {
            helper.setVisible(R.id.iv_delete, false);
            helper.setBackgroundRes(R.id.ll_guanjia, R.mipmap.yifukuan);
            helper.setTextColor(R.id.tv_order_amount,context.getResources().getColor(R.color.more));
            // 已付款
        } else if (state.equals("6") ||state.equals("5")) {
            // 已退款
            helper.setVisible(R.id.iv_delete, true);
            helper.setBackgroundRes(R.id.ll_guanjia, R.mipmap.tuikuam);
            helper.setVisible(R.id.iv_delete, false);
            helper.setTextColor(R.id.tv_order_amount,context.getResources().getColor(R.color.colorWhite));
        }else if (state.equals("2") ) {
            // 已发货
            helper.setVisible(R.id.iv_delete, false);
            helper.setBackgroundRes(R.id.ll_guanjia, R.mipmap.yifahuio);
            helper.setBackgroundRes(R.id.iv_delete, R.mipmap.iv_delelte);
            helper.setTextColor(R.id.tv_order_amount,context.getResources().getColor(R.color.colorWhite));
        }else {
            helper.setVisible(R.id.iv_delete, true);
            helper.setBackgroundRes(R.id.ll_guanjia, R.mipmap.yishixiao);
            helper.setBackgroundRes(R.id.iv_delete, R.mipmap.iv_delelte);
            helper.setTextColor(R.id.tv_order_amount,context.getResources().getColor(R.color.colorWhite));
            // 已失效
        }
        helper.setText(R.id.tv_product_name, "产品名称:" + item.getOrder_name());
        helper.setText(R.id.tv_add_time, "订单创建时间:   " + item.getOrder_time());
        helper.setText(R.id.tv_product_price, "价格: ¥" +item.getOrder_amount());
       /* String youhuiqua = item.getYouhq_money();
        if (!youhuiqua.equals("0.00")){
            helper.setVisible(R.id.tv_youhq_money,true);
            helper.setText(R.id.tv_youhq_money, "优惠券: -¥" + youhuiqua);
        }else {
            helper.setVisible(R.id.tv_youhq_money,false);
        }*/

        helper.setText(R.id.tv_order_amount, "¥"+item.getOrder_amount());

    }
}
