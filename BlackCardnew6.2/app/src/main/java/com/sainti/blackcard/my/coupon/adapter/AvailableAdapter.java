package com.sainti.blackcard.my.coupon.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sainti.blackcard.R;
import com.sainti.blackcard.my.coupon.bean.CommenCouponBean;

/**
 * Created by YuZhenpeng on 2018/7/5.
 */

public class AvailableAdapter extends BaseQuickAdapter<CommenCouponBean.DataBean.KeyyBonusDataBean,BaseViewHolder> {
    private String cl_id ;

    public void setCl_id(String cl_id) {
        this.cl_id = cl_id;
    }

    public AvailableAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommenCouponBean.DataBean.KeyyBonusDataBean item) {
        String clid  = item.getCl_id();
        if (cl_id.equals(clid)){
            helper.setBackgroundRes(R.id.bg,R.mipmap.choince);
        }else {
            helper.setBackgroundRes(R.id.bg,R.mipmap.anused);
        }

        helper.setText(R.id.co_name,item.getCo_name());
        helper.setText(R.id.cl_time,item.getCl_time());
        helper.setText(R.id.co_dizhi,item.getCo_dizhi());
        helper.setText(R.id.co_stype,item.getCo_stype());

    }
}
