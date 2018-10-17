package com.sainti.blackcard.mblcakcard.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sainti.blackcard.R;
import com.sainti.blackcard.mblcakcard.bean.FriendBean;
import com.sainti.blackcard.mhttp.glide.GlideManager;

/**
 * Created by YuZhenpeng on 2018/5/10.
 */

public class FriendListAdapter extends BaseQuickAdapter<FriendBean.DataBean, BaseViewHolder> {
    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public FriendListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, FriendBean.DataBean item) {
        helper.setText(R.id.tv_name, item.getUser_name());
        GlideManager.getsInstance().loadImageToUrLCircle(context, item.getUser_upimg(), (ImageView) helper.getView(R.id.iv_iamge));
        String stae = item.getState();
        if (stae.equals("0")) {
            helper.setText(R.id.tv_jih, "亲友未激活");
        } else {
            helper.setText(R.id.tv_jih, "亲友已激活");
        }

        helper.setText(R.id.tv_card_h, item.getUser_card());
        helper.setText(R.id.tv_shoujihao, item.getUser_tel());


    }
}
