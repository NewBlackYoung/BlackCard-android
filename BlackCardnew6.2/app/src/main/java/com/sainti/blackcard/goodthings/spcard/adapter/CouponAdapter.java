package com.sainti.blackcard.goodthings.spcard.adapter;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sainti.blackcard.R;
import com.sainti.blackcard.goodthings.spcard.baen.CouponlistBean;

;


/**
 * Created by YuZhenpeng on 2018/4/4.
 */

public class CouponAdapter extends BaseQuickAdapter<CouponlistBean.DataBean.BonusDataBean, BaseViewHolder> {
    private String choiceCode = "";
    private String code = "";



    public void setChoiceCode(String choiceCode ,String code) {
        this.choiceCode = choiceCode;
        this.code = code;
        notifyDataSetChanged();
    }

    public CouponAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, CouponlistBean.DataBean.BonusDataBean item) {


        helper.setText(R.id.tv_title, item.getCo_name());
        helper.setText(R.id.tv_time,"有效期限:"+ item.getCl_time());
        helper.setText(R.id.tv_tiaojian, item.getCo_stype());
     //   String coupon = item.getCo_dizhi().substring(0, item.getCo_dizhi().length() - 3);
        helper.setText(R.id.tv_money, item.getCo_dizhi());
        CheckBox checkBox = helper.getView(R.id.checkbox);

        checkBox.setClickable(false);
        if (choiceCode.equals(item.getCl_id())) {
            if (checkBox.isChecked()) {
                checkBox.setChecked(false);
            } else {
                checkBox.setChecked(true);
            }

        } else {
            checkBox.setChecked(false);
        }
        if (code.equals("1")){
            checkBox.setVisibility(View.GONE);
            checkBox.setChecked(false);
        }
    }


}
