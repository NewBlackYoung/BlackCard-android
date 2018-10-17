package com.sainti.blackcard.circle.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sainti.blackcard.R;
import com.sainti.blackcard.circle.bean.DakaBean;

/**
 * Created by YuZhenpeng on 2018/6/25.
 */

public class DaKaAdapter extends BaseQuickAdapter<DakaBean.DataBean.DaysBean, BaseViewHolder> {
    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public DaKaAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, DakaBean.DataBean.DaysBean item) {

        int p = helper.getLayoutPosition();
        if (p == 0) {
            helper.setVisible(R.id.left, false);
        }
        if (p == 6) {
            helper.setVisible(R.id.right_b, false);
        }
        String isdaka = item.getIs_sign();
        ImageView imageView = helper.getView(R.id.keycount);
        ImageView daycount = helper.getView(R.id.daycount);
        if (isdaka.equals("0")) {
            helper.setTextColor(R.id.tv_Day, context.getResources().getColor(R.color.white));
            imageView.setBackgroundResource(R.mipmap.one_b);
            helper.setVisible(R.id.keycount, false);
            daycount.setBackgroundResource(R.mipmap.undaka);

        } else {
            helper.setVisible(R.id.keycount, true);
            helper.setTextColor(R.id.tv_Day, context.getResources().getColor(R.color.e_eight));

            if (item.getIs_curday().equals("1")){
                daycount.setBackgroundResource(R.mipmap.dangtian);
            }else {
                daycount.setBackgroundResource(R.mipmap.tuyuan_one);
            }
            if (p == 6) {
                imageView.setBackgroundResource(R.mipmap.four_b);
            } else {
                imageView.setBackgroundResource(R.mipmap.one_b);
            }
        }
        helper.setText(R.id.tv_Day, item.getDay() + "天");
    }
}
