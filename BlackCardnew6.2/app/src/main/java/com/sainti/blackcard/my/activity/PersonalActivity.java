package com.sainti.blackcard.my.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseBean;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.commen.mconstant.SataicCode;
import com.sainti.blackcard.commen.mconstant.SpCodeName;
import com.sainti.blackcard.meventbus.WxBindEventBean;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.glide.GlideManager;
import com.sainti.blackcard.mhttp.okgo.OkGoUtils;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.minterface.TimerListener;
import com.sainti.blackcard.mtuils.CommontUtil;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.mtuils.MLog;
import com.sainti.blackcard.mtuils.SpUtil;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.my.adapter.MyLikeAdapter;
import com.sainti.blackcard.my.bean.MyLikeBean;
import com.sainti.blackcard.my.bean.PerSonBena;
import com.sainti.blackcard.circle.releasecircle.bean.ImageUrL;
import com.sainti.blackcard.widget.CircleImageView;
import com.sainti.blackcard.widget.LoadingView;
import com.sainti.blackcard.widget.ViewDialogFragment;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yalantis.ucrop.UCrop;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PersonalActivity extends BaseTitleActivity implements OnNetResultListener, BaseQuickAdapter.OnItemClickListener, ViewDialogFragment.SureCallback, TimerListener {
    private RelativeLayout rlimgtou, rl12306, lay_p, rlphone, rlweixin, loaction_layout, Rela_yincang1, rela_yincang2, rlsex, rlname, Rela_yincang, intro_layout, zhiye_layout, hobby_layout;
    private LinearLayout ll_popup, lin1;
    private Animation animationIn, animationOut;
    private PopupWindow pop = null;
    private CircleImageView minetou;
    private LoadingView loadingView;
    private ImageView img6;
    private static final int GALLERY_REQUEST_CODE = 0; // 从图库请求图片
    private static final int CAMERA_REQUEST_CODE = 1; // 从照相机请求图片
    private static final int RESIZE_REQUEST_CODE = 6;// 图片剪切后返回
    private int heightPan = 0;
    private Gson gson;
    // 头像文件名
    private static final String IMAGE_FILE_NAME = "header.jpg";
    private Uri uri;
    private PerSonBena perSonBena;
    private TextView tv_hint, mcard, mname, msex, tv_save, mnike, mine_intro, mine_zhiye, mine_loaction, mphone, mnum, mweixin, m12306, xiugai_name, tv_quxiao, tv_queding;
    private View view_yingcang1, view_yingcang;
    private TextView xiugai_name1, xiugai_name2;
    private int type = 0;
    private EditText xiugai_neirong;
    private BaseBean baseBean;
    private MyLikeBean myLikeBean;
    private RecyclerView rcv_like;
    private MyLikeAdapter likeAdapter;
    private List<String> list;
    private IWXAPI api;
    private ViewDialogFragment viewDialogFragment;
    private String changeContent;
    private String openId;
    private Uri destinationUri;
    private boolean isXiangji = false;
    @Override
    public int setLayout() {
        return R.layout.activity_personal;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        animationIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        animationOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        rlimgtou = bindView(R.id.rlimgtou);
        ll_popup = bindView(R.id.ll_popup);
        lay_p = bindView(R.id.lay_p);
        minetou = bindView(R.id.minetou);
        mcard = bindView(R.id.mcard);
        mname = bindView(R.id.mname);
        msex = bindView(R.id.msex);
        mnike = bindView(R.id.mnike);
        mine_intro = bindView(R.id.mine_intro);
        mine_zhiye = bindView(R.id.mine_zhiye);
        mine_loaction = bindView(R.id.mine_loaction);
        mphone = bindView(R.id.mphone);
        mnum = bindView(R.id.mnum);
        mweixin = bindView(R.id.mweixin);
        m12306 = bindView(R.id.m12306);
        img6 = bindView(R.id.img6);
        rlweixin = bindView(R.id.rlweixin);
        Rela_yincang1 = bindView(R.id.Rela_yincang1);
        rlsex = bindView(R.id.rlsex);
        view_yingcang1 = bindView(R.id.view_yingcang1);
        xiugai_name1 = bindView(R.id.xiugai_name1);
        xiugai_name2 = bindView(R.id.xiugai_name2);
        rlname = bindView(R.id.rlname);
        xiugai_name = bindView(R.id.xiugai_name);
        xiugai_neirong = bindView(R.id.xiugai_neirong);
        Rela_yincang = bindView(R.id.Rela_yincang);
        view_yingcang = bindView(R.id.view_yingcang);
        lin1 = bindView(R.id.lin1);
        tv_queding = bindView(R.id.tv_queding);
        tv_quxiao = bindView(R.id.tv_quxiao);
        intro_layout = bindView(R.id.intro_layout);
        zhiye_layout = bindView(R.id.zhiye_layout);
        hobby_layout = bindView(R.id.hobby_layout);
        rela_yincang2 = bindView(R.id.rela_yincang2);
        rcv_like = bindView(R.id.rcv_like);
        tv_save = bindView(R.id.tv_save);
        loaction_layout = bindView(R.id.loaction_layout);
        rlphone = bindView(R.id.rlphone);
        rl12306 = bindView(R.id.rl12306);
        rlweixin = bindView(R.id.rlweixin);
    }

    @Override
    protected void initEvent() {
        rlimgtou.setOnClickListener(this);
        rlsex.setOnClickListener(this);
        view_yingcang1.setOnClickListener(this);
        xiugai_name1.setOnClickListener(this);
        xiugai_name2.setOnClickListener(this);
        rlname.setOnClickListener(this);
        view_yingcang.setOnClickListener(this);
        lin1.setOnClickListener(this);
        tv_queding.setOnClickListener(this);
        tv_quxiao.setOnClickListener(this);
        intro_layout.setOnClickListener(this);
        zhiye_layout.setOnClickListener(this);
        hobby_layout.setOnClickListener(this);
        rela_yincang2.setOnClickListener(this);
        tv_save.setOnClickListener(this);
        loaction_layout.setOnClickListener(this);
        rlphone.setOnClickListener(this);
        rl12306.setOnClickListener(this);
        rlweixin.setOnClickListener(this);

        api = WXAPIFactory.createWXAPI(this, SataicCode.LOGIN_APPID, true);
        api.registerApp(SataicCode.LOGIN_APPID);
        //api.handleIntent(getIntent(), this);
        viewDialogFragment = new ViewDialogFragment();
    }

    @Override
    protected void initData() {
        setTitle("个人资料");
        loadingView = new LoadingView(this);
        setPop();
        loadingView.show();
        TurnClassHttp.mine_data(1, this, this);
        xiugai_neirong.setOnFocusChangeListener(new android.view.View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    lin1.setBackgroundResource(R.drawable.bg_corner_white);
                    // 此处为得到焦点时的处理内容
                } else {
                    // 此处为失去焦点时的处理内容
                }
            }
        });

        CommontUtil.delayed(1000);
        likeAdapter = new MyLikeAdapter(R.layout.item_mylike);
        rcv_like.setLayoutManager(new GridLayoutManager(this, 4));
        rcv_like.setAdapter(likeAdapter);
        likeAdapter.setOnItemClickListener(this);
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
        String a = stringResult;
        gson = GsonSingle.getGson();
        switch (resultCode) {
            case 1:
                perSonBena = gson.fromJson(stringResult, PerSonBena.class);
                if (perSonBena.getResult().equals("1")) {
                    GlideManager.getsInstance().loadImageToUrL(this, perSonBena.getData().getHead(), minetou);
                    mcard.setText(perSonBena.getData().getCardid());
                    mname.setText(perSonBena.getData().getName());
                    mnike.setText(perSonBena.getData().getNickname());
                    mine_intro.setText(perSonBena.getData().getIntroinfo());
                    mine_zhiye.setText(perSonBena.getData().getZhiyeinfo());
                    mine_loaction.setText(perSonBena.getData().getLocation());
                    mphone.setText(perSonBena.getData().getPhone());
                    mnum.setText(perSonBena.getData().getId_number());
                    m12306.setText(perSonBena.getData().get_$12306id());
                    String state = perSonBena.getData().getWeixin_state();
                    if (state.equals("0")) {
                        mweixin.setText("未绑定");
                    } else {
                        rlweixin.setClickable(false);
                        img6.setVisibility(View.GONE);
                        mweixin.setText("已绑定");
                    }
                    if (perSonBena.getData().getSex().equals("1")) {
                        msex.setText("男");
                    } else {
                        msex.setText("女");
                    }
                }
                break;
            case 2:
                msex.setText("男");
                break;
            case 3:
                // ToastUtils.show(this, "性别修改成功");
                msex.setText("女");
                break;
            case 4:
                baseBean = gson.fromJson(stringResult, BaseBean.class);
                if (baseBean.getResult().equals("1")) {
                    mnike.setText(changeContent);
                    viewDialogFragment.dismiss();
                    ToastUtils.show(this, "修改成功");
                    SpUtil.initEditor(SpCodeName.SPNAME).putBoolean(SataicCode.REFRESH,true).commit();
                } else {
                    ToastUtils.show(this, baseBean.getMsg());
                }

                break;
            case 5:
                baseBean = gson.fromJson(stringResult, BaseBean.class);
                if (baseBean.getResult().equals("1")) {
                    mine_intro.setText(changeContent);
                    viewDialogFragment.dismiss();
                    ToastUtils.show(this, "修改简介成功");
                    SpUtil.initEditor(SpCodeName.SPNAME).putBoolean(SataicCode.REFRESH,true).commit();
                } else {
                    ToastUtils.show(this, baseBean.getMsg());
                }
                break;
            case 6:
                baseBean = gson.fromJson(stringResult, BaseBean.class);
                if (baseBean.getResult().equals("1")) {
                    mine_zhiye.setText(changeContent);
                    viewDialogFragment.dismiss();
                    ToastUtils.show(this, "修改职业成功");
                } else {
                    ToastUtils.show(this, baseBean.getMsg());
                }
                break;
            case 7:
                rela_yincang2.setVisibility(View.VISIBLE);
                animationOut = AnimationUtils.loadAnimation(this, R.anim.fade_in);
                rela_yincang2.setAnimation(animationOut);
                myLikeBean = gson.fromJson(stringResult, MyLikeBean.class);
                likeAdapter.setNewData(myLikeBean.getData().getAll());
                list = new ArrayList<>();
                for (int i = 0; i < myLikeBean.getData().getAll().size(); i++) {
                    if (myLikeBean.getData().getAll().get(i).getSelected() == 1) {
                        list.add(myLikeBean.getData().getAll().get(i).getHo_id());
                    }
                }
                break;
            case 8:
                loadingView.dismiss();
                rela_yincang2.setVisibility(View.INVISIBLE);
                animationOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
                rela_yincang2.setAnimation(animationIn);
                ToastUtils.show(this, "保存成功");
                break;
            case 9:
                baseBean = gson.fromJson(stringResult, BaseBean.class);
                if (baseBean.getResult().equals("1")) {
                    mine_loaction.setText(changeContent);
                    viewDialogFragment.dismiss();
                    ToastUtils.show(this, "修改成功");
                } else {
                    ToastUtils.show(this, baseBean.getMsg());
                }
                break;
            case 10:
                baseBean = gson.fromJson(stringResult, BaseBean.class);
                if (baseBean.getResult().equals("1")) {
                    mphone.setText(changeContent);
                    viewDialogFragment.dismiss();
                    ToastUtils.show(this, "修改成功");
                } else {
                    ToastUtils.show(this, baseBean.getMsg());
                }
                break;
            case 11:
                ImageUrL imageUrL = gson.fromJson(stringResult, ImageUrL.class);
                TurnClassHttp.head_update(imageUrL.getData().get(0).getImageb(), 12, this, this);
                break;
            case 12:
                baseBean = gson.fromJson(stringResult, BaseBean.class);
                if (baseBean.getResult().equals("1")) {
                    ToastUtils.show(this, "修改成功");
                    SpUtil.initEditor(SpCodeName.SPNAME).putBoolean(SataicCode.REFRESH,true).commit();
                   // TurnClassHttp.mine_data(1, context, this);
                } else {
                    ToastUtils.show(this, baseBean.getMsg());
                }
                break;
            case 13:
                baseBean = gson.fromJson(stringResult, BaseBean.class);
                if (baseBean.getResult().equals("1")) {
                    ToastUtils.show(this, "绑定成功");
                    rlweixin.setClickable(false);
                    img6.setVisibility(View.GONE);
                    mweixin.setText("已绑定");
                } else {

                    ToastUtils.show(this, baseBean.getMsg());
                }

                break;

        }

        loadingView.dismiss();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        String id = myLikeBean.getData().getAll().get(position).getHo_id();
        TextView textView = (TextView) adapter.getViewByPosition(rcv_like, position, R.id.tv_mylike);
        if (list.contains(id)) {
            list.remove(id);
            textView.setBackgroundResource(R.drawable.bg_corner_heixian);
        } else {
            list.add(id);
            textView.setBackgroundResource(R.drawable.bg_corner_huangquanxiao);
        }
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        loadingView.dismiss();
        ToastUtils.show(this, errMsg);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.rlimgtou://头像点击
                if (!isXiangji) {
                    showToast("请前往设置打开手访问手机多媒体权限");
                    return;
                }
                pop.showAtLocation(lay_p, Gravity.BOTTOM, 0, 0);
                break;

            case R.id.rlsex:
                relaIn1();
                break;
            case R.id.view_yingcang1:
                relaOut1();
                break;
            case R.id.xiugai_name1:
                loadingView.show();
                TurnClassHttp.sex_update("1", 2, PersonalActivity.this, this);
                relaOut1();
                break;
            case R.id.xiugai_name2:
                loadingView.show();
                TurnClassHttp.sex_update("0", 3, PersonalActivity.this, this);
                relaOut1();
                break;
            case R.id.rlname:
                showViewDialogFragment();
                viewDialogFragment.setTitle("修改昵称");
                type = 1;
                xiugai_name.setText("修改昵称");
                break;
            case R.id.view_yingcang:
                relaOut();
                break;
            case R.id.lin1:
                return;
            case R.id.intro_layout:
                showViewDialogFragment();
                viewDialogFragment.setTitle("修改简介");
                type = 8;
                xiugai_name.setText("修改简介");
                break;
            case R.id.zhiye_layout:
                showViewDialogFragment();
                viewDialogFragment.setTitle("修改职业");
                type = 10;
                xiugai_name.setText("修改职业");
                break;
            case R.id.hobby_layout:
                loadingView.show();
                TurnClassHttp.getHobby(7, this, this);
                break;
            case R.id.rela_yincang2:
                rela_yincang2.setVisibility(View.INVISIBLE);
                animationOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
                rela_yincang2.setAnimation(animationOut);
                break;
            case R.id.tv_save:
                String id = "";
                for (int i = 0; i < list.size(); i++) {
                    if (i == list.size() - 1) {
                        id = id + list.get(i);
                    } else {
                        id = id + list.get(i) + ",";
                    }

                }
                loadingView.show();
                TurnClassHttp.setHobby(id, 8, this, this);
                break;
            case R.id.loaction_layout:
                showViewDialogFragment();
                viewDialogFragment.setTitle("修改地区");

                type = 11;
                xiugai_name.setText("修改地区");
                break;
            case R.id.rlphone:
                showViewDialogFragment();
                viewDialogFragment.setTitle("修改电话");
                type = 2;
                xiugai_name.setText("修改电话");
                break;
            case R.id.rl12306:
                Intent intent = new Intent(this, ChangeTrainActivity.class);
                String car = m12306.getText().toString();
                if (car.equals("")) {

                } else {
                    intent.putExtra("car", car);
                }
                startActivityForResult(intent, 103);
                break;
            case R.id.rlweixin:
                final SendAuth.Req req = new SendAuth.Req();
                //授权读取用户信息
                req.scope = "snsapi_userinfo";
                //自定义信息
                req.state = "青年黑卡";
                //向微信发送请求
                api.sendReq(req);
                // getOpenID("");
                break;


        }
    }

    private void changeInfo(String content) {
        if (content == null && content.length() <= 0) {
            return;
        }

        switch (type) {
            case 1:
                if (content != null && content.length() > 0) {
                    TurnClassHttp.nickname_update(content, 4, PersonalActivity.this, this);
                    loadingView.show();
                } else {
                    ToastUtils.show(this, "昵称不能为空");
                    return;
                }
                break;

            case 8:
                if (content != null) {
                    TurnClassHttp.setJianjie(content, 5, this, this);
                    loadingView.show();
                }
                break;
            case 10:
                TurnClassHttp.setZhiYe(content, 6, this, this);
                loadingView.show();
                break;
            case 11:
                TurnClassHttp.setLocation(content, 9, this, this);
                loadingView.show();
                break;
            case 2:
                if (content.length() == 11) {
                    TurnClassHttp.phone_update(content, 10, this, this);
                    loadingView.show();
                } else {
                    ToastUtils.show(this, "请输入正确的手机号码");
                }

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        } else {
            switch (requestCode) {
                case GALLERY_REQUEST_CODE:// 图库选择照片
                    resizeImage(data.getData());// 剪切照片
                    break;
                case CAMERA_REQUEST_CODE:// 拍照片回来
                    resizeImage(getImageUri());// 剪切照片
                    break;
            }
        }
        if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            final Uri resultUri = UCrop.getOutput(data);
            MLog.d("URI", "destinationUri---" + resultUri);
            setHeaderBitmap(destinationUri);// 剪切回来后设置头像并提交
        } else if (resultCode == UCrop.RESULT_ERROR) {
            final Throwable cropError = UCrop.getError(data);
            ToastUtils.show(this, cropError.getMessage());
        }
        switch (requestCode) {
            case 15:
                String car = data.getStringExtra("car");
                m12306.setText(car);
                break;

        }
    }

    /**
     * 选择剪切区
     *
     * @param uri
     */
    public void resizeImage(Uri uri) {
        File file = new File(getExternalCacheDir(), System.currentTimeMillis() + ".jpg");
        destinationUri = Uri.fromFile(file);
        UCrop.Options options = new UCrop.Options();
        options.setToolbarColor(getResources().getColor(R.color.statusBar));
        options.setStatusBarColor(getResources().getColor(R.color.statusBar));
        options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
        options.setCompressionQuality(50);
        UCrop.of(uri, destinationUri)
                .withAspectRatio(1, 1)
                .withMaxResultSize(600, 600)
                .withOptions(options)
                .start(context);
    }

    private void commitPhoto() {
        String keyName = "image_data[]";
        String url = "http://api.qing-hei.com/index.php/Index/Api?type=upload_pic";
        ArrayList<File> files = new ArrayList<File>();
        File file = new File(destinationUri.getPath());
        files.add(file);
        OkGoUtils.getInstance().postFile(url, keyName, files, 11, this, this);
    }

    /**
     * 设置头像
     *
     * @param
     */
    private void setHeaderBitmap(Uri uri) {

        MLog.d("AAAAAAAAAAAAAAAA", uri.toString());
        ArrayList<String> pics = new ArrayList<String>();
        //   pics.add(url.getPath().toString());
        minetou.setImageURI(uri);
        commitPhoto();
    }


    /**
     * 通过不同方式获得头像
     *
     * @param tag
     */
    private void getHeader(int tag) {
        switch (tag) {
            // 图库
            case 0:
                Intent galleryIntent = null;
                if (Build.VERSION.SDK_INT < 19) {
                    galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                    galleryIntent.setType("image/*");
                } else {
                    galleryIntent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                }
                startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE);

                break;
            // 拍照
            case 1:
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, getImageUri());
                startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
                break;
        }
    }

    /**
     * 获得图片保存Uri
     *
     * @return
     */
    private Uri getImageUri() {
        if (Build.VERSION.SDK_INT >= 24) {
            return FileProvider.getUriForFile(context.getApplicationContext(), "com.sainti.blackcard.fileprovider", new File(Environment.getExternalStorageDirectory(),
                    IMAGE_FILE_NAME));
        } else {
            return Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                    IMAGE_FILE_NAME));
        }

    }

    private void setPop() {
        pop = new PopupWindow(this);
        View popview = (this).getLayoutInflater().inflate(
                R.layout.item_popupwindows, null);
        ll_popup = (LinearLayout) popview.findViewById(R.id.ll_popup);
/*        tv_hint = (TextView) popview.findViewById(R.id.tv_hint);
        tv_hint.setVisibility(View.GONE);*/
        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(popview);

        RelativeLayout parent = (RelativeLayout) popview
                .findViewById(R.id.parent);
        Button bt1 = (Button) popview
                .findViewById(R.id.item_popupwindows_camera);
        Button bt2 = (Button) popview
                .findViewById(R.id.item_popupwindows_Photo);
        Button bt3 = (Button) popview
                .findViewById(R.id.item_popupwindows_cancel);
        parent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // photo();
                getHeader(1);
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                getHeader(0);
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
    }

    private void relaIn1() {
        Rela_yincang1.setVisibility(View.VISIBLE);
        animationIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Rela_yincang1.setAnimation(animationIn);
    }

    private void relaOut1() {
        Rela_yincang1.setVisibility(View.INVISIBLE);
        animationOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        Rela_yincang1.setAnimation(animationOut);
    }


    private void relaOut() {
        xiugai_neirong.setText("");
        type = 0;
        Rela_yincang.setVisibility(View.INVISIBLE);
        animationOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        Rela_yincang.setAnimation(animationOut);
    }


    private void getOpenID(String code) {
        String codes = "071ujNKG10yJi80mLcNG1gP1LG1ujNKK";
        MLog.d("opinid", "code  ---------" + code);
        String path = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
                + SataicCode.LOGIN_APPID
                + "&secret="
                + SataicCode.LOGIN_APPSECRET
                + "&code="
                + code
                + "&grant_type=authorization_code";
        TurnClassHttp.getOpenID(path, 11, this, new OnNetResultListener() {
            @Override
            public void onSuccessfulListener(String stringResult, int resultCode) {
                MLog.d("opinid", "stringResult---" + stringResult);
                JSONObject object = null;
                try {
                    object = new JSONObject(stringResult);
                    MLog.d("opinid", "请求opinID=" + object.getString("openid"));
                    openId = object.getString("openid");
                    loadingView.show();
                    CommontUtil.lateTime(1000, PersonalActivity.this);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailureListener(String errMsg, int resultCode) {
                MLog.d("opinid", "errMsg=" + errMsg);
                ToastUtils.show(PersonalActivity.this, errMsg);
            }
        });
    }

    @Override
    public void onSureClick(String content, int code) {
        if (code == 1) {
            // 确定
            changeInfo(content);
            changeContent = content;
        }
        if (code == 2) {
            viewDialogFragment.dismiss();
        }

    }

    public void showViewDialogFragment() {
        viewDialogFragment.show(getSupportFragmentManager());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /* 用于接收微信支付结果回调*/
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(WxBindEventBean messageEvent) {
        if (messageEvent.getCode().equals("1")) {
            String openID = messageEvent.getMessage();
            getOpenID(openID);
        }
    }

    @Override
    public void onTimerListener() {
        TurnClassHttp.update_openid(openId, 13, this, this);
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
