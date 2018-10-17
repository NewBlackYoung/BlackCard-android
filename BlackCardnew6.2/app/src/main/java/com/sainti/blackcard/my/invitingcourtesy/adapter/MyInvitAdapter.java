package com.sainti.blackcard.my.invitingcourtesy.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sainti.blackcard.R;
import com.sainti.blackcard.my.invitingcourtesy.bean.MyInvitBean;

/**
 * Created by YuZhenpeng on 2018/8/1.
 */

public class MyInvitAdapter extends BaseQuickAdapter<MyInvitBean.DataBean.InvitesBean, BaseViewHolder> {


    public MyInvitAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyInvitBean.DataBean.InvitesBean item) {
        helper.setText(R.id.tv_time, item.getS_time());
        helper.setText(R.id.tv_name, "成功邀请" + item.getUser_name1() + "开通青年黑卡" + item.getS_ship() + "会员");

    }
}
