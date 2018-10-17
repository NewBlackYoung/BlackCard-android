package com.sainti.blackcard.goodthings.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sainti.blackcard.R;
import com.sainti.blackcard.goodthings.bean.GoodThingsBean;
import com.sainti.blackcard.mhttp.glide.GlideManager;

import static com.sainti.blackcard.R.*;

/**
 * Created by YuZhenpeng on 2018/6/4.
 */

public class GoodComentAdapter extends BaseQuickAdapter<GoodThingsBean.DataBean,BaseViewHolder> {
    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public GoodComentAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodThingsBean.DataBean item) {
        helper.setText(R.id.tv_commentPice,"¥ "+item.getW_jiage());
        helper.setText(R.id.tv_commentYuanjia,"¥ "+item.getW_yuanjia());
        helper.setText(R.id.tv_commentTtiletv,"¥ "+item.getW_title());
        GlideManager.getsInstance().loadImageToUrL(context, item.getW_pic(), (ImageView) helper.getView(id.iv_comment_goodthings));

    }
}
