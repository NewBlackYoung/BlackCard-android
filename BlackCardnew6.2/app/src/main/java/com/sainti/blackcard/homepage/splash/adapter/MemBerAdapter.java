package com.sainti.blackcard.homepage.splash.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sainti.blackcard.R;
import com.sainti.blackcard.mblcakcard.bean.FriendBean;
import com.sainti.blackcard.mhttp.glide.GlideManager;

/**
 * Created by YuZhenpeng on 2018/7/23.
 */

public class MemBerAdapter extends BaseQuickAdapter<FriendBean.DataBean, BaseViewHolder> {
    public MemBerAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, FriendBean.DataBean item) {
        GlideManager.getsInstance().loadImageToUrLCircle(mContext, item.getUser_upimg(), (ImageView) helper.getView(R.id.image));
        helper.addOnClickListener(R.id.copy);
        if (item.getUser_pwd().equals("")) {
            helper.setVisible(R.id.copy, false);
        } else {
            helper.setVisible(R.id.copy, true);
        }
        helper.setText(R.id.k, "黑卡卡号:" + item.getUser_card() + "密码:" + item.getUser_pwd());
        helper.setText(R.id.name, item.getUser_name());
        String state = item.getState();
        if (state.equals("1")) {
            helper.setText(R.id.state, "亲友已激活");
        } else {
            helper.setText(R.id.state, "亲友未激活");
        }
        helper.setText(R.id._tvkahao, "黑卡卡号   " + item.getUser_card());
        helper.setText(R.id.number, "手机号码   " + item.getUser_tel());
    }
}
