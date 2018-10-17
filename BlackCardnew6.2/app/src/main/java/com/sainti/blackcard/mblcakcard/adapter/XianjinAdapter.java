package com.sainti.blackcard.mblcakcard.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sainti.blackcard.R;
import com.sainti.blackcard.mblcakcard.bean.BlackcardBean;

/**
 * Created by YuZhenpeng on 2018/5/10.
 */

public class XianjinAdapter extends BaseQuickAdapter<BlackcardBean.DataBean.XianjinBean, BaseViewHolder> {
    public XianjinAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, BlackcardBean.DataBean.XianjinBean item) {
        helper.setText(R.id.tv_name,item.getName());
        helper.setText(R.id.tv_title,item.getSubname());
        helper.setText(R.id.tv_title_t,item.getDescription());
        if (item.getName().equals("小赢卡贷")){
            helper.setBackgroundRes(R.id.ll_bg,R.mipmap.iv_xianjin);
        }else {
            helper.setBackgroundRes(R.id.ll_bg,R.mipmap.xianjin);
        }

    }

   /* @Override
    public int getItemCount() {
        return 3;
    }*/
}
