package com.sainti.blackcard.goodthings.spcard.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sainti.blackcard.R;
import com.sainti.blackcard.db.bean.GoodthingsBean;
import com.sainti.blackcard.mhttp.glide.GlideManager;

import java.util.Map;

/**
 * Created by YuZhenpeng on 2018/3/29.
 */

public class ShoppingCardAdapter extends BaseQuickAdapter<GoodthingsBean, BaseViewHolder> {
    private Context context;
    private Map<String, String> cbxMap;
    private CheckBox ckItemSp;
    private String completeCode = "";
    private RelativeLayout item_sp;

    public void setCompleteCode(String completeCode) {
        this.completeCode = completeCode;
       //0 notifyDataSetChanged();
    }

    public void setCbxMap(Map<String, String> cbxMap, String allCode) {
        this.cbxMap = cbxMap;
        if (allCode.equals("0")) {
            notifyDataSetChanged();
        }
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ShoppingCardAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodthingsBean item) {
        helper.addOnClickListener(R.id.iv_bianji).addOnClickListener(R.id.tv_wancheng_sp).addOnClickListener(R.id.ck_item_sp).addOnClickListener(R.id.iv_jiahao_sp).addOnClickListener(R.id.iv_jianhao_sp);
        helper.setText(R.id.tv_titleItem_sp, item.getTingsName());// 商品名字
        helper.setText(R.id.tv_countItem_sp, item.getTingsCount());// 商品个数
        helper.setText(R.id.tv_priceItem_sp, "¥" + item.getTingsPrice());// 现价
        helper.setText(R.id.tv_old_price, "¥" + item.getTingsYuanjia());// 原价
        helper.setText(R.id.tv_countItem,item.getTingsCount());// 加减号的数量
        GlideManager.getsInstance().loadImageToUrL(context, item.getImageurl(), (ImageView) helper.getView(R.id.iv_image_sp));
        ImageView jianhao  = helper.getView(R.id.iv_jianhao_sp);
        if (item.getTingsCount().equals("1")){
            jianhao.setImageDrawable(context.getResources().getDrawable(R.mipmap.jianhao_sph));
        }else {
            jianhao.setImageDrawable(context.getResources().getDrawable(R.mipmap.jianhao_sp));

        }
        ckItemSp = helper.getView(R.id.ck_item_sp);
        if (cbxMap != null && cbxMap.containsKey(item.getTingsId())) {
            ckItemSp.setChecked(true);
        } else {
            ckItemSp.setChecked(false);
        }
        isXiaJia(item.getTingsType(), helper);// 判断商品是否已经下架

        if (!item.getTingsKind().equals("")){
            helper.setText(R.id.tv_kind_sp,item.getTingsKind());
        }else {
            helper.setText(R.id.tv_kind_sp,"无");
        }
    }

    /*判断上篇是否下架*/
    private void isXiaJia(String type, BaseViewHolder helper) {
        if (type.equals("1")) {
            //当返回结果为1时 视为下架 禁止checkbox点击 和 隐藏下架图片
            helper.setVisible(R.id.iv_xiajia, true);
            ckItemSp.setClickable(false);

            helper.setVisible(R.id.iv_bianji, false);//隐藏
        } else {
            helper.setVisible(R.id.iv_xiajia, false); // 未下架 隐藏下架图片
            if (completeCode.equals("0")){
                helper.setVisible(R.id.iv_bianji, false);//隐藏
                helper.setVisible(R.id.lay_countPrice, false);//隐藏价钱等
                helper.setVisible(R.id.lay_jiaJian,true);
            }else {
                helper.setVisible(R.id.iv_bianji, true);//显示
                helper.setVisible(R.id.lay_jiaJian,false);
                helper.setVisible(R.id.lay_countPrice, true);//隐藏价钱等
            }

        }
    }
}
