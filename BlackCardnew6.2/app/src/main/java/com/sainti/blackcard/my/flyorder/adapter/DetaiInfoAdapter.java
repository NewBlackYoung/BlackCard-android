package com.sainti.blackcard.my.flyorder.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sainti.blackcard.R;
import com.sainti.blackcard.my.flyorder.bean.FlyDetailBean;

/**
 * Created by YuZhenpeng on 2018/5/17.
 */

public class DetaiInfoAdapter extends BaseQuickAdapter<FlyDetailBean.DataBean.AoPersonBean,BaseViewHolder>{
    public DetaiInfoAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, FlyDetailBean.DataBean.AoPersonBean item) {
        helper.setText(R.id.fr_name,item.getFr_name());
        helper.setText(R.id.fr_idcard,"身份证/"+item.getFr_idcard());
        helper.setText(R.id.fr_phone,item.getFr_phone());

    }
}
