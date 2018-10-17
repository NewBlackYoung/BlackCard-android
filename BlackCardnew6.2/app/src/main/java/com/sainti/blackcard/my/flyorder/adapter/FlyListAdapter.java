package com.sainti.blackcard.my.flyorder.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sainti.blackcard.R;
import com.sainti.blackcard.my.flyorder.bean.FlyListBean;

/**
 * Created by YuZhenpeng on 2018/5/16.
 */

public class FlyListAdapter extends BaseQuickAdapter<FlyListBean.DataBean, BaseViewHolder> {
    public FlyListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, FlyListBean.DataBean item) {
        String sta = item.getAo_state();
        if (sta.equals("0")){
            helper.setText(R.id.statessss,"未支付");
        }
        if (sta.equals("1")){
            helper.setText(R.id.statessss,"出票中");
        }
        if (sta.equals("2")){
            helper.setText(R.id.statessss,"退票中");
        }
        if (sta.equals("3")){
            helper.setText(R.id.statessss,"已退票");
        }
        if (sta.equals("4")){
            helper.setText(R.id.statessss,"已取消");
        }
        if (sta.equals("5")){
            helper.setText(R.id.statessss,"已出票");
        } if (sta.equals("6")){
            helper.setText(R.id.statessss,"改签中");
        }
        if (sta.equals("7")){
            helper.setText(R.id.statessss,"已改签");
        }
        if (sta.equals("8")){
            helper.setText(R.id.statessss,"出票失败(退款中)");
        }

        if (sta.equals("9")){
            helper.setText(R.id.statessss,"退票失败");
        }

        if (sta.equals("10")){
            helper.setText(R.id.statessss,"改签失败");
        }

        if (sta.equals("11")){
            helper.setText(R.id.statessss,"改签确认中");
            helper.setText(R.id.ao_price, "待确认");
        }

        if (sta.equals("12")){
            helper.setText(R.id.statessss,"待退款");
        }

        helper.setText(R.id.ao_orderno, item.getAo_orderno());
        helper.setText(R.id.ao_date, item.getAo_date());
        helper.setText(R.id.ao_qifei, item.getAo_qifei());
        helper.setText(R.id.ao_qftime, item.getAo_qftime());
        helper.setText(R.id.ao_company, item.getAo_company() + item.getAo_flight());
        helper.setText(R.id.ao_jiangluo, item.getAo_jiangluo());
        helper.setText(R.id.ao_jltime, item.getAo_jltime());
        helper.setText(R.id.ao_price, "¥" + item.getAo_price());


    }
}
