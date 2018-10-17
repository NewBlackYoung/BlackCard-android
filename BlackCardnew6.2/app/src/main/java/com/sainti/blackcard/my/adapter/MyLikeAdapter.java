package com.sainti.blackcard.my.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sainti.blackcard.R;
import com.sainti.blackcard.my.bean.MyLikeBean;

/**
 * Created by YuZhenpeng on 2018/5/7.
 */

public class MyLikeAdapter extends BaseQuickAdapter<MyLikeBean.DataBean.AllBean, BaseViewHolder> {


    public MyLikeAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyLikeBean.DataBean.AllBean item) {
        int typt = item.getSelected();
        TextView textView =helper.getView(R.id.tv_mylike);
        helper.setText(R.id.tv_mylike, item.getHo_name());
        if (typt==0){
            textView.setBackgroundResource(R.drawable.bg_corner_heixian);
        }else {
            textView.setBackgroundResource(R.drawable.bg_corner_huangquanxiao);
        }


    }
}
