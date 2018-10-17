package com.sainti.blackcard.chat.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sainti.blackcard.R;
import com.sainti.blackcard.chat.bean.LiyouBean;

/**
 * Created by YuZhenpeng on 2018/5/23.
 */

public class LiyouAdapter extends BaseQuickAdapter<LiyouBean.DataBean, BaseViewHolder> {
    public LiyouAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, LiyouBean.DataBean item) {
        helper.setText(R.id.name, item.getName());

    }
}
