package com.sainti.blackcard.privilege.adapter;

import android.content.Context;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sainti.blackcard.R;
import com.sainti.blackcard.privilege.bean.TixianBean;

/**
 * Created by YuZhenpeng on 2018/5/3.
 */

public class TixainAdapter extends BaseQuickAdapter<TixianBean.DataBean, BaseViewHolder> {

    private TextView textView;

    public TixainAdapter(int layoutResId) {
        super(layoutResId);
    }

    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, TixianBean.DataBean item) {
        helper.setText(R.id.tvmessage, item.getT_money());
        helper.setText(R.id.tvmessagetime, item.getT_time());
        helper.setText(R.id.tvtype, item.getT_type());
        if (item.getT_money().substring(0, 1).equals("+")) {
            textView = helper.getView(R.id.tvmessage);
            textView.setTextColor(context.getResources().getColor(R.color.green));
        } else {
            textView.setTextColor(context.getResources().getColor(R.color.red));
        }


    }
}
