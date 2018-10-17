package com.sainti.blackcard.my.bonus.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sainti.blackcard.R;
import com.sainti.blackcard.my.bonus.bean.CanUsedBean;

/**
 * Created by YuZhenpeng on 2018/7/5.
 */

public class NotUsedAdapter extends BaseQuickAdapter<CanUsedBean.DataBean,BaseViewHolder> {
    public NotUsedAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, CanUsedBean.DataBean item) {
        
        helper.setText(R.id.co_name,item.getCo_name());
        helper.setText(R.id.cl_time,item.getCl_time());
        helper.setText(R.id.co_dizhi,item.getCo_dizhi());
        helper.setText(R.id.co_stype,item.getCo_stype());

    }
}
