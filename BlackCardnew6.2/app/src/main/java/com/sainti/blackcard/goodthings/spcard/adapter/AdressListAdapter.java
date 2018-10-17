package com.sainti.blackcard.goodthings.spcard.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sainti.blackcard.R;
import com.sainti.blackcard.goodthings.spcard.baen.AddressBean;


/**
 * Created by YuZhenpeng on 2018/4/3.
 */

public class AdressListAdapter extends BaseQuickAdapter<AddressBean.DataBean, BaseViewHolder> {
    private Context context;
    private LinearLayout llyBg;
    private String morenCode = "";

    public void setMorenCode(String morenCode) {
        this.morenCode = morenCode;
        notifyDataSetChanged();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public AdressListAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }


    @Override
    protected void convert(BaseViewHolder helper, AddressBean.DataBean item) {
        helper.addOnClickListener(R.id.tv_delete).addOnClickListener(R.id.lly_text).addOnClickListener(R.id.ck_adress);
        helper.setText(R.id.tv_adress, item.getAd_province() + item.getAd_city() + item.getAd_area() + item.getAd_address());
        helper.setText(R.id.tv_name_ad, item.getAd_user());
        helper.setText(R.id.tv_tel_ad, item.getAd_tel());
        CheckBox checkBox = helper.getView(R.id.ck_adress);
        TextView tv_moren = helper.getView(R.id.tv_moren);
        llyBg = helper.getView(R.id.lly_bg);
        if (morenCode.equals(item.getAd_id())) {
            checkBox.setChecked(true);
            tv_moren.setTextColor(context.getResources().getColor(R.color.e_eight));
            llyBg.setBackgroundResource(R.mipmap.adress_sp);

        } else {
            checkBox.setChecked(false);
            tv_moren.setTextColor(context.getResources().getColor(R.color.f_f));
            llyBg.setBackgroundResource(R.mipmap.bg_add_h);
        }

    }


}
