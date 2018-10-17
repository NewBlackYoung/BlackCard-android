
package com.sainti.blackcard.circle.releasecircle.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidong.photopicker.PhotoPickerActivity;
import com.lidong.photopicker.PhotoPreviewActivity;
import com.lidong.photopicker.SelectModel;
import com.lidong.photopicker.intent.PhotoPickerIntent;
import com.lidong.photopicker.intent.PhotoPreviewIntent;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.circle.releasecircle.adapter.ChoicePhotoAdapter;
import com.sainti.blackcard.circle.releasecircle.bean.FabuResultBean;
import com.sainti.blackcard.circle.releasecircle.bean.ImageUrL;
import com.sainti.blackcard.goodthings.bean.GetKeyCountBean;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OkGoUtils;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.minterface.MyItemClickListener;
import com.sainti.blackcard.minterface.TimerListenerHasCode;
import com.sainti.blackcard.mtuils.CommontUtil;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.mtuils.PictureUtil;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.widget.LoadingView;
import com.umeng.analytics.MobclickAgent;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReleaseCircleActivity extends BaseTitleActivity implements View.OnClickListener, MyItemClickListener, OnNetResultListener, BaseTitleActivity.OnClickRightTextCallBack, TimerListenerHasCode {


    private EditText ed_countent;
    private ImageView iv_addphoto;
    private RecyclerView rv_addphoto;
    private TextView tv_addbiaoqian, tv_key;
    private ArrayList<String> imagePaths = new ArrayList<>();
    private ChoicePhotoAdapter choicePhotoAdapter;
    private static final int REQUEST_CAMERA_CODE = 10;
    private static final int REQUEST_PREVIEW_CODE = 20;
    private String to_name;
    private String to_id = "";
    private LoadingView p;
    private FabuResultBean f;
    String content;
    private Gson gson;
    private boolean isXiangji = false;
    private ImageView iv_back_list;
    private LinearLayout ll_releaseSucess;
    private String to_name1;
    private boolean canRelease;
    private String imglists;

    @Override
    public int setLayout() {
        return R.layout.activity_release_circle;
    }

    @Override
    protected void initView() {
        ed_countent = bindView(R.id.ed_countent);
        iv_addphoto = bindView(R.id.iv_addphoto);
        rv_addphoto = bindView(R.id.rv_addphoto);
        tv_addbiaoqian = bindView(R.id.tv_addbiaoqian);
        iv_back_list = bindView(R.id.iv_back_list);
        ll_releaseSucess = bindView(R.id.ll_releaseSucess);
        tv_key = bindView(R.id.tv_key);

    }

    @Override
    protected void initEvent() {
        iv_addphoto.setOnClickListener(this);
        tv_addbiaoqian.setOnClickListener(this);
        ed_countent.setImeOptions(EditorInfo.IME_ACTION_SEND);
        iv_back_list.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        canRelease = true;
        p = new LoadingView(this);
        setTitle("写点什么");
        imagePaths.add("addphoto");
        rv_addphoto.setLayoutManager(new GridLayoutManager(this, 3));
        choicePhotoAdapter = new ChoicePhotoAdapter(this);
        choicePhotoAdapter.setListener(this);
        rv_addphoto.setAdapter(choicePhotoAdapter);
        choicePhotoAdapter.setListUrls(imagePaths);
        setBaseRightText("发布", this);

        to_name1 = getIntent().getStringExtra("to_name");
        String to_ids = getIntent().getStringExtra("to_id");
        if (!to_ids.equals("") && !to_name1.equals("")) {
            to_id = to_ids;
            tv_addbiaoqian.setText(to_name1);
        }
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

    private void fabu() {
        p.show();
        content = "";
        content = ed_countent.getText().toString();
        imagePaths.remove(imagePaths.size() - 1);

        ArrayList<File> files = new ArrayList<File>();
        for (int i = 0; i < imagePaths.size(); i++) {
            String b = PictureUtil.compressImage(imagePaths.get(i));
            if (b == null || b.equals("")) {
                ToastUtils.show(this, "有无法发布的图片");
            }
            File file = new File(imagePaths.get(i));
            files.add(file);
        }
        //uploadFiles("http://api.qing-hei.com/index.php/Index/Api?type=upload_pic", "image_data[]", files);
        OkGoUtils.getInstance().postFile("http://api.qing-hei.com/index.php/Index/Api?type=upload_pic", "image_data[]", files, 1, this, this);

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tv_addbiaoqian:
                Intent intent = new Intent(this, TopicActivity.class);
                startActivityForResult(intent, 119);
                break;
            case R.id.iv_back_list:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                // 选择照片
                case REQUEST_CAMERA_CODE:
                    imagePaths.clear();
                    imagePaths = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
                    if (imagePaths.size() < 9) {
                        imagePaths.add("addphoto");
                    }
                    choicePhotoAdapter.setListUrls(imagePaths);
                    break;
                // 预览
                case REQUEST_PREVIEW_CODE:
                    imagePaths.clear();
                    imagePaths = data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT);
                    if (imagePaths.size() < 9) {
                        imagePaths.add("addphoto");
                    }
                    choicePhotoAdapter.setListUrls(imagePaths);
                    break;
                case 119:
                    to_id = data.getStringExtra("to_id");
                    to_name = data.getStringExtra("to_name");
                    tv_addbiaoqian.setText(to_name);
                    break;
            }
        }
    }

    @Override
    public void onItemClick(int position, int code) {
   /*     if (!isQuanxian) {
            if (CommontUtil.requestPower(this)) {
                ToastUtils.show(this, "请前往设置打开手访问手机多媒体权限！");
                return;
            }
        }*/
        if (!isXiangji) {
            showToast("请前往设置打开手访问手机多媒体权限");
            return;
        }
        switch (code) {
            case 1:
                String imgs = imagePaths.get(position);
                if ("addphoto".equals(imgs)) {
                    imagePaths.remove(imagePaths.size() - 1);
                    PhotoPickerIntent intent = new PhotoPickerIntent(this);
                    intent.setSelectModel(SelectModel.MULTI);
                    intent.setShowCarema(true); // 是否显示拍照
                    intent.setMaxTotal(9); // 最多选择照片数量，默认为6
                    intent.setSelectedPaths(imagePaths); // 已选中的照片地址， 用于回显选中状态
                    startActivityForResult(intent, REQUEST_CAMERA_CODE);
                } else {
                    PhotoPreviewIntent intent = new PhotoPreviewIntent(this);
                    intent.setCurrentItem(position);
                    intent.setPhotoPaths(imagePaths);
                    intent.setCode(0);
                    startActivityForResult(intent, REQUEST_PREVIEW_CODE);
                }
                break;
            case 2:
                if (imagePaths.size() == 9 && !imagePaths.get(imagePaths.size() - 1).equals("addphoto")) {
                    imagePaths.remove(position);
                    imagePaths.add("addphoto");
                } else {
                    imagePaths.remove(position);
                }
                choicePhotoAdapter.setListUrls(imagePaths);
                break;
        }

    }


    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        gson = GsonSingle.getGson();
        switch (resultCode) {
            case 1:
                ImageUrL imageUrL = gson.fromJson(stringResult, ImageUrL.class);
                if (imageUrL != null && imageUrL.getResult().equals("1")) {
                    imglists = "";
                    for (int i = 0; i < imageUrL.getData().size(); i++) {
                        if (i < imageUrL.getData().size() - 1) {
                            imglists = imglists + imageUrL.getData().get(i).getImageb() + ",";
                        } else {
                            imglists = imglists + imageUrL.getData().get(i).getImageb();
                        }
                    }
                    CommontUtil.lateTimeHasCode(1000, this, 1);
                } else {
                    p.dismiss();
                    canRelease = true;
                    ToastUtils.show(ReleaseCircleActivity.this, "发布失败！ 请稍后尝试");
                }
                break;
            case 2:
                p.dismiss();
                canRelease = true;
                FabuResultBean fabuResultBean = gson.fromJson(stringResult, FabuResultBean.class);
                if (fabuResultBean != null && fabuResultBean.getResult().equals("1")) {
                    CommontUtil.lateTimeHasCode(1000, this, 2);
                } else {
                    imagePaths.add("addphoto");
                    ToastUtils.show(ReleaseCircleActivity.this, fabuResultBean.getMsg());
                }

                break;
            case 3:
                GetKeyCountBean countBean = gson.fromJson(stringResult, GetKeyCountBean.class);
                String count = countBean.getBlackkey();
                if (count.equals("0.00")) {
                    tv_key.setVisibility(View.INVISIBLE);
                } else {
                    tv_key.setVisibility(View.VISIBLE);
                    tv_key.setText("恭喜您获得黑钥匙" + count + "把");
                }
                ll_releaseSucess.setVisibility(View.VISIBLE);
                hideTitle();
                break;
        }


    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        p.dismiss();
        canRelease = true;
        ToastUtils.show(ReleaseCircleActivity.this, errMsg);
    }


    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1311:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    isXiangji = true;
                } else {
                    isXiangji = false;

                }
                break;
            default:
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        // MobclickAgent.onPageStart("fabuquanzi");
        MobclickAgent.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        // MobclickAgent.onPageEnd("fabuquanzi");
        MobclickAgent.onPause(this);
    }

    @Override
    public void clickRightText() {
        if (!canRelease) {
            showToast("正在发布圈子消息...");
            return;
        }
        if (imagePaths == null || imagePaths.size() == 1) {
            content = ed_countent.getText().toString();
            if (content.equals("")) {
                showToast("请输入发布内容");
                return;
            }
            p.show();
            canRelease = false;
            imagePaths.remove(imagePaths.size() - 1);
            TurnClassHttp.fabu(content, "", "", "", "", "0", to_id, 2, ReleaseCircleActivity.this, ReleaseCircleActivity.this);
        } else {
            canRelease = false;
            fabu();
        }
    }

    @Override
    public void onTimerListener(int code) {
        switch (code) {
            case 1:
                TurnClassHttp.fabu(content, imglists, imglists, "", "", "0", to_id, 2, this, this);
                break;
            case 2:
                TurnClassHttp.getBlackkey("", "group", 3, this, this);
                if (!to_id.equals("")) {
                    Map<String, String> ms = new HashMap<String, String>();
                    ms.put("huatiname", to_name1);
                    MobclickAgent.onEvent(context, "huaticanyuren", ms);// 友盟统计
                }
                break;
        }

    }

}
