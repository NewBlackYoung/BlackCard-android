package com.sainti.blackcard.homepage.splash.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sainti.blackcard.R;
import com.sainti.blackcard.mhttp.glide.GlideManager;
import com.sainti.blackcard.minterface.MyItemClickListener;
import com.sainti.blackcard.privilege.bean.PrivilegeBean;
import com.sainti.blackcard.homepage.splash.bean.GuangGaoBean;

import java.util.List;


/**
 * Created by YuZhenpeng on 2018/3/9.
 */

public class MyAdapter extends PagerAdapter {
    private MyItemClickListener litener;
    private Context context;
    private List<GuangGaoBean.DataBean> welfareBeanList;
    private List<PrivilegeBean.DataBean.WelfareBean> list;
    private List<PrivilegeBean.DataBean.TopBannerBean> bannerBeans;
    private int code = 0;

    public void setBannerBeans(List<PrivilegeBean.DataBean.TopBannerBean> bannerBeans) {
        this.bannerBeans = bannerBeans;
        notifyDataSetChanged();
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setList(List<PrivilegeBean.DataBean.WelfareBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    public void setWelfareBeanList(List<GuangGaoBean.DataBean> welfareBeanList) {
        this.welfareBeanList = welfareBeanList;
        notifyDataSetChanged();
    }

    public MyAdapter(Context context) {
        this.context = context;
    }

    public void setLitener(MyItemClickListener litener) {
        this.litener = litener;
    }

    @Override
    public int getCount() {
        if (code == 1) {
            return list != null && list.size() > 0 ? list.size() : 0;
        }
        if (code == 3) {
            return bannerBeans != null && bannerBeans.size() > 0 ? bannerBeans.size() : 0;
        }
        return welfareBeanList != null && welfareBeanList.size() > 0 ? welfareBeanList.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

//            @Override
//            public float getPageWidth(int position) {
//                return 0.8f;
//            }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        if (code == 1) {
            View view = View.inflate(context, R.layout.widget_gallery_view, null);
            ImageView iv = (ImageView) view.findViewById(R.id.headRIV);
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    litener.onItemClick(position, 0);
                }
            });
            GlideManager.getsInstance().loadImageToUrLYsuo(context, list.get(position).getW_pic(), iv);
            container.addView(view);
            return view;
        } else if (code == 3) {
            View view = View.inflate(context, R.layout.banner_t, null);
            ImageView iv = (ImageView) view.findViewById(R.id.headRIV);
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    litener.onItemClick(position, 3);
                }
            });
            GlideManager.getsInstance().loadImageToUrLCircle(context, bannerBeans.get(position).getL_img(), iv);
            container.addView(view);
            return view;
        } else {
            View view = View.inflate(context, R.layout.widget_gallery_view, null);
            ImageView iv = (ImageView) view.findViewById(R.id.headRIV);
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    litener.onItemClick(position, 0);
                }
            });
            GlideManager.getsInstance().loadImageToUrL(context, welfareBeanList.get(position).getG_img(), iv);
            container.addView(view);
            return view;
        }

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
}
