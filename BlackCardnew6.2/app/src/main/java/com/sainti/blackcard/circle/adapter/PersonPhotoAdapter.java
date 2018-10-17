package com.sainti.blackcard.circle.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sainti.blackcard.R;
import com.sainti.blackcard.circle.bean.FindDetailBean;
import com.sainti.blackcard.mhttp.glide.GlideManager;

/**
 * Created by YuZhenpeng on 2018/6/12.
 */

public class PersonPhotoAdapter extends BaseQuickAdapter<FindDetailBean.DataBean.PraiselistBean,BaseViewHolder> {
    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public PersonPhotoAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, FindDetailBean.DataBean.PraiselistBean item) {

        GlideManager.getsInstance().loadImageToUrL(context,item.getUser_upimg(), (ImageView) helper.getView(R.id.iamehg));
    }
}
