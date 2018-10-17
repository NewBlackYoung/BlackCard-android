package com.sainti.blackcard.privilege.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sainti.blackcard.R;
import com.sainti.blackcard.mhttp.glide.GlideManager;
import com.sainti.blackcard.mtuils.CommontUtil;
import com.sainti.blackcard.privilege.bean.PrivilegeBean;

/**
 * Created by YuZhenpeng on 2018/8/8.
 */

public class HaoWuAdapter extends BaseQuickAdapter<PrivilegeBean.DataBean.WelfareBean,BaseViewHolder> {
    public HaoWuAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PrivilegeBean.DataBean.WelfareBean item) {
        GlideManager.getsInstance().loadImageToUrLCircle(mContext,item.getW_pic(), (ImageView) helper.getView(R.id.image));
        // holder.imageView.setTag(position);
        helper.setText(R.id.content,item.getW_title2());
        helper.setText(R.id.title,item.getW_title());
        helper.setText(R.id.price,item.getW_price());

      /*  holder.lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(position, 3);
                }

            }
        });*/
        if (item.getW_yuanjia().equals("0")) {
           helper.setGone(R.id.w_yuanjia,false);

        } else {
            helper.setVisible(R.id.w_yuanjia,true);
            if (item.getW_yuanjia_name().equals("")) {
                helper.setGone(R.id.w_yuanjia_name,false);
                helper.setText(R.id.w_yuanjia,item.getW_yuanjia());

            } else {
                helper.setVisible(R.id.w_yuanjia_name,true);
                helper.setText(R.id.w_yuanjia_name,item.getW_yuanjia_name()+ ":");
                helper.setText(R.id.w_yuanjia,"¥" + item.getW_yuanjia());

            }
            CommontUtil.addCenterLine((TextView) helper.getView(R.id.w_yuanjia));
        }
    }
}
