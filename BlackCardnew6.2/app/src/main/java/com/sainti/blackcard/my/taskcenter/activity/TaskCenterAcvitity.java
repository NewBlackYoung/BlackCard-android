package com.sainti.blackcard.my.taskcenter.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyphenate.helpdesk.easeui.util.IntentBuilder;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseNoTitleActivity;
import com.sainti.blackcard.circle.activity.DakaActivity;
import com.sainti.blackcard.my.invitingcourtesy.activity.InvitAcitvity;
import com.sainti.blackcard.my.taskcenter.bean.TaskBean;
import com.sainti.blackcard.commen.mconstant.SpCodeName;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.mtuils.AnimationUtil;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.mtuils.SpUtil;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.mwebview.NormalWebActivity;
import com.sainti.blackcard.circle.releasecircle.activity.ReleaseCircleActivity;
import com.sainti.blackcard.widget.LoadingView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TaskCenterAcvitity extends BaseNoTitleActivity implements OnNetResultListener, View.OnClickListener, View.OnLongClickListener {

    private LoadingView loadingView;
    private ImageView iv_title_leftOne, iv_guanzhu;
    private TextView blackkey, tv_todaka, tv_quanzi, tv_toyaoqing, tv_quanzhu, tv_count,tv_quanzicuont;
    private LinearLayout mingxi, shangcheng;
    private Intent intents;
    private String uids;
    private String tokens;
    private RelativeLayout ll_zhaohuanguanjia, ll_bg;
    private AnimationUtil animationUtil;
    private boolean isXiangji = false;

    @Override
    public int setLayout() {
        return R.layout.activity_task_center_acvitity;
    }

    @Override
    protected void initView() {
        loadingView = new LoadingView(this);
        iv_title_leftOne = bindView(R.id.iv_title_leftOne);
        blackkey = bindView(R.id.blackkey);
        tv_todaka = bindView(R.id.tv_todaka);
        tv_quanzi = bindView(R.id.tv_quanzi);
        mingxi = bindView(R.id.mingxi);
        shangcheng = bindView(R.id.shangcheng);
        tv_toyaoqing = bindView(R.id.tv_toyaoqing);
        ll_zhaohuanguanjia = bindView(R.id.ll_zhaohuanguanjia);
        ll_bg = bindView(R.id.ll_bg);
        iv_guanzhu = bindView(R.id.iv_guanzhu);
        tv_quanzhu = bindView(R.id.tv_quanzhu);
        animationUtil = new AnimationUtil(this);
        tv_count = bindView(R.id.tv_count);
        tv_quanzicuont = bindView(R.id.tv_quanzicuont);
    }

    @Override
    protected void initEvents() {
        iv_title_leftOne.setOnClickListener(this);
        tv_todaka.setOnClickListener(this);
        tv_quanzi.setOnClickListener(this);
        mingxi.setOnClickListener(this);
        shangcheng.setOnClickListener(this);
        tv_toyaoqing.setOnClickListener(this);
        ll_zhaohuanguanjia.setOnClickListener(this);
        tv_quanzhu.setOnClickListener(this);
        iv_guanzhu.setOnClickListener(this);
        iv_guanzhu.setOnLongClickListener(this);
        ll_bg.setOnClickListener(this);
        tv_count.setOnClickListener(this);
        tv_quanzicuont.setOnClickListener(this);
        uids = SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, "");
        tokens = SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, "");
    }

    @Override
    protected void initData() {
        loadingView.show();
        TurnClassHttp.myTask(1, this, this);
        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) || ContextCompat.checkSelfPermission(this.getBaseContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //如果没有授权，则请求授权
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1311);
            //String数组中为申请的权限，第一个是相机，第二个为修改内存，最后的参数即为申请授权的返回值，我设置的1311
            //showToast("已经打开权限");
            isXiangji = false;
        } else {
            isXiangji = true;
        }
    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        loadingView.dismiss();
        switch (resultCode) {
            case 1:
                TaskBean taskBean = GsonSingle.getGson().fromJson(stringResult, TaskBean.class);
                blackkey.setText(taskBean.getData().getBlackkey());
                tv_count.setText("+" + taskBean.getData().getDaytask().get(0).getTask_reward() + taskBean.getData().getDaytask().get(0).getTask_reward_unit());
                tv_quanzicuont.setText("+" + taskBean.getData().getDaytask().get(1).getTask_reward() + taskBean.getData().getDaytask().get(1).getTask_reward_unit());
                String dakaState = taskBean.getData().getDaytask().get(0).getTask_state();
                if (dakaState.equals("0")) {
                    tv_todaka.setClickable(true);
                    tv_todaka.setBackgroundResource(R.mipmap.qudaka);
                    tv_todaka.setText(taskBean.getData().getDaytask().get(0).getTask_state_name());
                } else {
                    tv_todaka.setClickable(false);
                    tv_todaka.setBackgroundResource(R.mipmap.wancheng);
                    tv_todaka.setText(taskBean.getData().getDaytask().get(0).getTask_state_name());
                }
                String yaoqingState = taskBean.getData().getDaytask().get(1).getTask_state();
                if (yaoqingState.equals("0")) {
                    tv_quanzi.setClickable(true);
                    tv_quanzi.setBackgroundResource(R.mipmap.qudaka);
                    tv_quanzi.setText(taskBean.getData().getDaytask().get(1).getTask_state_name());
                } else {
                    tv_quanzi.setClickable(false);
                    tv_quanzi.setBackgroundResource(R.mipmap.wancheng);
                    tv_quanzi.setText(taskBean.getData().getDaytask().get(1).getTask_state_name());
                }
                break;

            case 2:
                break;
        }
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_leftOne:
                finish();
                break;
            case R.id.tv_todaka:
                startActivity(new Intent(this, DakaActivity.class));
                break;
            case R.id.tv_quanzi:
                Intent intent1 = new Intent(this, ReleaseCircleActivity.class);
                intent1.putExtra("to_name", "");
                intent1.putExtra("to_id", "");
                startActivity(intent1);
                break;
            case R.id.mingxi:
                intents = new Intent(this, NormalWebActivity.class);
                intents.putExtra("code", "9");
                intents.putExtra("url", "http://www.qing-hei.com/mobile.php/Key/?uid=" + uids + "&token=" + tokens);
                startActivity(intents);

                break;
            case R.id.shangcheng:
                intents = new Intent(this, NormalWebActivity.class);
                intents.putExtra("code", "8");
                intents.putExtra("url", "http://www.qing-hei.com/mobile.php/Key/change/?uid=" + uids + "&token=" + tokens);
                startActivity(intents);
                break;
            case R.id.tv_toyaoqing:
                intents = new Intent(this, InvitAcitvity.class);
                startActivity(intents);
                break;
            case R.id.ll_zhaohuanguanjia:
                boolean t = SpUtil.getBoolean(SpCodeName.SPNAME, SpCodeName.TYPEFORE, false);
                if (t) {
                    toChat("5");
                    SpUtil.initEditor(SpCodeName.SPNAME).putBoolean(SpCodeName.TYPEFORE, false).commit();
                } else {
                    toChat("");
                }

                break;

            case R.id.tv_quanzhu:
                iv_guanzhu.setImageResource(R.mipmap.guanzhutank);
                animationUtil.viewAppear(ll_bg);
                animationUtil.viewAppear(iv_guanzhu);
                break;
            case R.id.ll_bg:
                animationUtil.viewDisappear(iv_guanzhu);
                animationUtil.viewDisappear(ll_bg);
                break;
            case R.id.iv_guanzhu:

                break;


        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public boolean onLongClick(View v) {
        if (!isXiangji) {
            showToast("请前往设置打开手访问手机多媒体权限");

        } else {
            iv_guanzhu.setDrawingCacheEnabled(true);
            Bitmap bitmap = iv_guanzhu.getDrawingCache();
            saveFile(bitmap);
            iv_guanzhu.setDrawingCacheEnabled(false);
        }

        return false;
    }

    private final static String ALBUM_PATH = Environment
            .getExternalStorageDirectory() + "/download_blackcard/";
    private String mFileName = "shair.jpg";

    public void saveFile(Bitmap bm) {
        try {
            File dirFile = new File(ALBUM_PATH);
            if (!dirFile.exists()) {
                dirFile.mkdir();
            }
            File myCaptureFile = new File(ALBUM_PATH + mFileName);
            BufferedOutputStream bos = null;
            bos = new BufferedOutputStream(
                    new FileOutputStream(myCaptureFile));
            bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
            bos.flush();
            bos.close();
            Uri uri = Uri.fromFile(myCaptureFile);
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
            ToastUtils.show(this, "保存成功");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void toChat(String type) {
        //已经登录，可以直接进入会话界面
        Intent intents = new IntentBuilder(this)
                .setServiceIMNumber("test1") //获取地址：kefu.easemob.com，“管理员模式 > 渠道管理 > 手机APP”页面的关联的“IM服务号”
                .build()
                .putExtra("type", type);
        startActivity(intents);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1311:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    isXiangji = true;
                    //pop.showAtLocation(lay_p, Gravity.BOTTOM, 0, 0);
                } else {
                    isXiangji = false;

                }
                break;
            default:
        }
    }
}
