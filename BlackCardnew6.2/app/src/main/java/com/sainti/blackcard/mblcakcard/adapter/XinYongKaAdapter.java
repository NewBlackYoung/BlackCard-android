package com.sainti.blackcard.mblcakcard.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sainti.blackcard.R;
import com.sainti.blackcard.mblcakcard.bean.BlackcardBean;
import com.sainti.blackcard.mhttp.glide.GlideManager;

/**
 * Created by YuZhenpeng on 2018/5/10.
 */

public class XinYongKaAdapter extends BaseQuickAdapter<BlackcardBean.DataBean.CbankBean, BaseViewHolder> {
    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public XinYongKaAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, BlackcardBean.DataBean.CbankBean item) {
        GlideManager.getsInstance().loadImageToUrL(context, item.getLogo(), (ImageView) helper.getView(R.id.iv_image));
        helper.setText(R.id.tv_name, item.getName());
        helper.setText(R.id.tv_miaoshu,"·"+ item.getDes1());
        helper.setText(R.id.tv_miaoshu2, "·"+item.getDes2());
        helper.setText(R.id.tv_miaoshu3, "·"+item.getDes3());
        helper.addOnClickListener(R.id.lay_xinnyonkg);

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
