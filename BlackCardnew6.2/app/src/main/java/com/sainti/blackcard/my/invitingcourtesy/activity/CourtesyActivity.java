

package com.sainti.blackcard.my.invitingcourtesy.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyphenate.chat.ChatClient;
import com.hyphenate.helpdesk.easeui.util.IntentBuilder;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseNoTitleActivity;
import com.sainti.blackcard.commen.mconstant.SataicCode;
import com.sainti.blackcard.commen.mconstant.SpCodeName;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.glide.GlideManager;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.minterface.TimerListenerHasCode;
import com.sainti.blackcard.mtuils.CommontUtil;
import com.sainti.blackcard.mtuils.EncodingHandler;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.mtuils.SpUtil;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.my.invitingcourtesy.bean.ShairBean;
import com.sainti.blackcard.widget.LoadingView;
import com.umeng.analytics.MobclickAgent;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import cn.sharesdk.onekeyshare.OnekeyShare;

public class CourtesyActivity extends BaseNoTitleActivity implements OnNetResultListener, View.OnClickListener, TimerListenerHasCode {

    private LoadingView loadingView;
    private TextView tv_name;
    private ImageView iamge, logo, top, iv_title_leftOne;
    private String filePath;
    private RelativeLayout jieu;
    private LinearLayout tvToGuanjia;

    @Override
    public int setLayout() {
        return R.layout.activity_courtesy;
    }

    @Override
    protected void initView() {
        loadingView = new LoadingView(this);
        tv_name = bindView(R.id.tv_name);
        iamge = bindView(R.id.iamge);
        logo = bindView(R.id.logo);
        top = bindView(R.id.top);
        jieu = bindView(R.id.jieu);
        iv_title_leftOne = bindView(R.id.iv_title_leftOne);
        tvToGuanjia = bindView(R.id.tv_toGuanjia);
    }

    @Override
    protected void initEvents() {
        top.setOnClickListener(this);
        iv_title_leftOne.setOnClickListener(this);
        tvToGuanjia.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.top:
                //  top.setVisibility(View.GONE);
                loadingView.show();
                getView();
                CommontUtil.lateTimeHasCode(200, this, 1);
                break;
            case R.id.iv_title_leftOne:
                finish();
                break;
            case R.id.tv_toGuanjia:
                if (ChatClient.getInstance().isLoggedInBefore()) {
                    Map<String, String> map_ekv0 = new HashMap<String, String>();
                    map_ekv0.put("kahao", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.KAHAO, ""));
                    map_ekv0.put("rukou", "tequanxaingqingye");
                    MobclickAgent.onEvent(CourtesyActivity.this, "zhaohuanguanjia", map_ekv0);// 友盟统计
                    //已经登录，可以直接进入会话界面
                    Intent intent = new IntentBuilder(this)
                            .setServiceIMNumber("test1") //获取地址：kefu.easemob.com，“管理员模式 > 渠道管理 > 手机APP”页面的关联的“IM服务号”
                            .build();
                    startActivity(intent);
                    SpUtil.initEditor(SpCodeName.SPNAME).putBoolean(SataicCode.ISRENCOIN, false).commit();
                } else {
                    //未登录，需要登录后，再进入会话界面
                    ToastUtils.show(this, "登陆失败");
                }
                break;

        }
    }

    private void getView() {
        View dView = jieu;
        Bitmap bitmap = Bitmap.createBitmap(dView.getWidth(), dView.getHeight(), Bitmap.Config.ARGB_8888);
//使用Canvas，调用自定义view控件的onDraw方法，绘制图片
        Canvas canvas = new Canvas(bitmap);
        dView.draw(canvas);
        dView.setDrawingCacheEnabled(true);
        dView.buildDrawingCache();
        Bitmap bitmaps = Bitmap.createBitmap(dView.getDrawingCache());
        if (bitmaps != null) {
            try {
                // 获取内置SD卡路径
                String sdCardPath = Environment.getExternalStorageDirectory().getPath();
                // 图片文件路径
                filePath = sdCardPath + File.separator + "shaird.png";
                File file = new File(filePath);
                FileOutputStream os = new FileOutputStream(file);
                bitmaps.compress(Bitmap.CompressFormat.PNG, 100, os);
                os.flush();
                os.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    protected void initData() {
        loadingView.show();
        TurnClassHttp.myShare(1, this, this);
    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        loadingView.dismiss();
        switch (resultCode) {
            case 1:
                ShairBean shairBean = GsonSingle.getGson().fromJson(stringResult, ShairBean.class);
                tv_name.setText(shairBean.getData().getUser().getNick());
                GlideManager.getsInstance().loadImageToUrL(this, shairBean.getData().getQrcodelogo(), logo);
                EncodingHandler.generate(shairBean.getData().getUrl(), iamge, this);
                break;
            case 2:
                break;
        }
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        loadingView.dismiss();
        showToast(errMsg);
    }


    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        oks.setImagePath(filePath);//确保SDcard下面存在此张图片
        oks.show(this);
    }

    @Override
    public void onTimerListener(int code) {
        loadingView.dismiss();
        showShare();
    }
}
