package com.sainti.blackcard.goodthings.spcard.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sainti.blackcard.R;
import com.sainti.blackcard.db.bean.GoodthingsBean;
import com.sainti.blackcard.mhttp.glide.GlideManager;


/**
 * Created by YuZhenpeng on 2018/4/3.
 */

public class ShopOrderInfoAdapter extends BaseQuickAdapter<GoodthingsBean, BaseViewHolder> {
    private Context context;
    public ShopOrderInfoAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodthingsBean item) {
        GlideManager.getsInstance().loadImageToUrL(context, item.getImageurl(), (ImageView) helper.getView(R.id.iv_image_sp));
        helper.setText(R.id.tv_titleItem_sp, item.getTingsName());// 商品名字
        helper.setText(R.id.tv_countItem_sp, item.getTingsCount());// 商品个数
        helper.setText(R.id.tv_priceItem_sp, "¥" + item.getTingsPrice());// 现价
        if (!item.getTingsKind().equals("")){
            helper.setText(R.id.tv_kind_sp,item.getTingsKind());
        }else {
            helper.setText(R.id.tv_kind_sp,"无");
        }
        View ck_item_sp = helper.getView(R.id.ck_item_sp);
        ck_item_sp.setVisibility(View.GONE);
        helper.setVisible(R.id.iv_xiajia,false);
        helper.setVisible(R.id.iv_bianji,false);
        helper.setVisible(R.id.tv_old_price,false);
    }
}
