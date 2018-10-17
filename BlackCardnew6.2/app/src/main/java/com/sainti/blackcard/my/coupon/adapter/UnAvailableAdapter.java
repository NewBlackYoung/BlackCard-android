package com.sainti.blackcard.my.coupon.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sainti.blackcard.R;
import com.sainti.blackcard.my.coupon.bean.CommenCouponBean;

/**
 * Created by YuZhenpeng on 2018/7/5.
 */

public class UnAvailableAdapter extends BaseQuickAdapter<CommenCouponBean.DataBean.BukyBonusDataBean,BaseViewHolder> {
    public UnAvailableAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommenCouponBean.DataBean.BukyBonusDataBean item) {
        
        helper.setText(R.id.co_name,item.getCo_name());
        helper.setText(R.id.cl_time,item.getCl_time());
        helper.setText(R.id.co_dizhi,item.getCo_dizhi());
        helper.setText(R.id.co_stype,item.getCo_stype());

    }
}
