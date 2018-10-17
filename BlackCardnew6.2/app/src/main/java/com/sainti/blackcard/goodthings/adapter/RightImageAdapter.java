package com.sainti.blackcard.goodthings.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sainti.blackcard.R;
import com.sainti.blackcard.goodthings.bean.NewListBean;
import com.sainti.blackcard.mhttp.glide.GlideManager;

/**
 * Created by YuZhenpeng on 2018/7/19.
 */

public class RightImageAdapter extends BaseQuickAdapter<NewListBean.DataBean.GoodsBean,BaseViewHolder> {
    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public RightImageAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper,NewListBean.DataBean.GoodsBean item) {
        GlideManager.getsInstance().loadImageToUrLCircle(context, item.getW_pic(), (ImageView) helper.getView(R.id.iv_goodthings));
        helper.setText(R.id.tv_price,"¥" + item.getW_jiage());
        helper.setText(R.id.tv_title,item.getW_title());
        helper.setText(R.id.tv_smallTitle,item.getW_title2());
        if (item.getW_yuanjia().equals("0")) {
            helper.setVisible(R.id.tv_yuanjia, false);
        } else {
            if (item.getW_yuanjia_name().equals("")) {
                helper.setVisible(R.id.tv_yuanjia_name, false);
            } else {
                helper.setVisible(R.id.tv_yuanjia_name, true);
                helper.setText(R.id.tv_yuanjia_name, item.getW_yuanjia_name() + ":");
            }
            TextView textView = helper.getView(R.id.tv_yuanjia);
            textView.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG ); //中间横线
            textView.getPaint().setAntiAlias(true);// 抗锯齿
            helper.setVisible(R.id.tv_yuanjia, true);
            helper.setText(R.id.tv_yuanjia, "¥" + item.getW_yuanjia());
        }

    }
}
