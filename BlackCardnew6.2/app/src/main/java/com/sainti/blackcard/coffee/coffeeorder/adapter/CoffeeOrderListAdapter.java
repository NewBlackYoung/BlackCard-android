package com.sainti.blackcard.coffee.coffeeorder.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sainti.blackcard.R;
import com.sainti.blackcard.coffee.coffeeorder.bean.CoffeelistBean;

/**
 * Created by YuZhenpeng on 2018/5/14.
 */

public class CoffeeOrderListAdapter extends BaseQuickAdapter<CoffeelistBean.DataBean, BaseViewHolder> {

    private RecyclerView recyclerView;

    public CoffeeOrderListAdapter(int layoutResId) {
        super(layoutResId);
    }

    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, CoffeelistBean.DataBean item) {
        helper.setText(R.id.xf_time, item.getXf_time());
        helper.addOnClickListener(R.id.tv_actionBtn);
        int state = item.getXf_state();
        recyclerView = helper.getView(R.id.rv_coffee_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        CoffeeItemAdapter coffeeItemAdapter = new CoffeeItemAdapter(R.layout.item_coffe_in);
        recyclerView.setAdapter(coffeeItemAdapter);
        coffeeItemAdapter.setNewData(item.getXf_list().getOrderdetails());
        if (state == 1) {
            helper.setText(R.id.status, "下单成功");
            helper.setTextColor(R.id.status, context.getResources().getColor(R.color.more));
            helper.setText(R.id.statessss, "预计送达时间:" + item.getXf_shipping_time());
            helper.setVisible(R.id.tv_actionBtn, false);
// 已支付,带配送
        } else if (state == 2) {
            helper.setText(R.id.status, "配送中");
            helper.setTextColor(R.id.status, context.getResources().getColor(R.color.five_t));
            helper.setText(R.id.statessss, "预计送达时间:" + item.getXf_shipping_time());
            helper.setVisible(R.id.tv_actionBtn, false);
//已付款， 配送中
        } else if (state == 3) {
            helper.setText(R.id.status, "失效");
            helper.setTextColor(R.id.status, context.getResources().getColor(R.color.five_eight));
            helper.setText(R.id.statessss, "总价: ¥" + item.getXf_price());
            helper.setVisible(R.id.tv_actionBtn, true);
            helper.setBackgroundRes(R.id.tv_actionBtn, R.mipmap.delete_coffee);
//失效
        } else if (state == 4) {
            helper.setText(R.id.status, "订单已送达");
            helper.setTextColor(R.id.status, context.getResources().getColor(R.color.s_e));
            helper.setText(R.id.statessss, "总价: ¥" + item.getXf_price());
            helper.setVisible(R.id.tv_actionBtn, false);
//已完成。订单已送达
        } else if (state == 0) {
            helper.setVisible(R.id.tv_actionBtn, true);
            helper.setText(R.id.status, "待支付");
            helper.setTextColor(R.id.status, context.getResources().getColor(R.color.eight_f));
            helper.setText(R.id.statessss, "总价: ¥" + item.getXf_price());
            helper.setBackgroundRes(R.id.tv_actionBtn, R.mipmap.pay_c);
//代付款
        }

    }
}
