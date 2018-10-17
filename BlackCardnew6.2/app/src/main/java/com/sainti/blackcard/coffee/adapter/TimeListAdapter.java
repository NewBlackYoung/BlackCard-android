package com.sainti.blackcard.coffee.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sainti.blackcard.R;

/**
 * Created by YuZhenpeng on 2018/5/16.
 */

public class TimeListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public TimeListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_tiem,item);

    }
}
