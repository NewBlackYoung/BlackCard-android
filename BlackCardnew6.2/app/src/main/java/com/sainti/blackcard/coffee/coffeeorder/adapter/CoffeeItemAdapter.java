package com.sainti.blackcard.coffee.coffeeorder.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sainti.blackcard.R;
import com.sainti.blackcard.coffee.coffeeorder.bean.CoffeelistBean;

/**
 * Created by YuZhenpeng on 2018/5/14.
 */

public class CoffeeItemAdapter extends BaseQuickAdapter<CoffeelistBean.DataBean.XfListBean.OrderdetailsBean,BaseViewHolder> {
    public CoffeeItemAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, CoffeelistBean.DataBean.XfListBean.OrderdetailsBean item) {
        helper.setText(R.id.or_num,item.getOr_num());
        helper.setText(R.id.or_price,"¥"+item.getOr_price());
        helper.setText(R.id.or_name,item.getOr_name());

    }
}
