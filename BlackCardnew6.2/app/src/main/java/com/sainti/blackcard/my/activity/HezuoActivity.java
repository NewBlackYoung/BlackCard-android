package com.sainti.blackcard.my.activity;

import android.Manifest;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseNoTitleActivity;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.widget.SaintiDialogTwo;

public class HezuoActivity extends BaseNoTitleActivity {
    private ImageView hezuoback;
    private SaintiDialogTwo saintiDialog = null;
    private RelativeLayout tl_fankui, tl_hezuo, tl_kefu;
    private String hezuo = "1@qing-hei.com";
    private String fankui = "vernon@qing-hei.com";
    private Context context;
    private ClipboardManager cmb;


    @Override
    public int setLayout() {
        return R.layout.activity_hezuo;
    }

    @Override
    protected void initView() {
        context = this;
        cmb = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
    }

    @Override
    protected void initEvents() {
        tl_fankui = (RelativeLayout) findViewById(R.id.tl_fankui);
        tl_hezuo = (RelativeLayout) findViewById(R.id.tl_hezuo);
        tl_kefu = (RelativeLayout) findViewById(R.id.tl_kefu);
        hezuoback = (ImageView) findViewById(R.id.hezuoback);
        hezuoback.setOnClickListener(mListener);
        tl_fankui.setOnClickListener(mListener);
        tl_hezuo.setOnClickListener(mListener);
        tl_kefu.setOnClickListener(mListener);
    }


    @Override
    protected void initData() {

    }

    private View.OnClickListener mListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.hezuoback:
                    finish();
                    break;
                case R.id.tl_kefu:
                    showDialog();
                    break;
                case R.id.tl_fankui:
                    cmb.setText(fankui);
                    ToastUtils.show(context, "产品反馈邮箱已复制到剪切板");
                    break;
                case R.id.tl_hezuo:
                    cmb.setText(hezuo);
                    ToastUtils.show(context, "洽谈邮箱已复制到剪切板");
                    break;
                default:
                    break;
            }
        }
    };

    private void showDialog() {
        saintiDialog = SaintiDialogTwo.createDialog(HezuoActivity.this);
        saintiDialog.setMainTitle("提示");
        saintiDialog.setTitile("是否拨打客服热线:4000-490-700");
        saintiDialog.setButton("取消", "确认");
        saintiDialog.setMessage("");
        saintiDialog
                .setOnLeftButtonClickListener(new SaintiDialogTwo.setOnButton1ClickListener() {

                    @Override
                    public void setOnButton1Clicked(Button left) {
                        if (saintiDialog != null) {
                            saintiDialog.dismiss();
                            saintiDialog = null;
                        }
                    }
                });
        saintiDialog
                .setOnRightButtonClickListener(new SaintiDialogTwo.setOnButton2ClickListener() {

                    @Override
                    public void setOnButton2Clicked(Button right) {
                        saintiDialog.dismiss();
                        saintiDialog = null;
                     /*   Intent intent = new Intent(Intent.ACTION_CALL, Uri
                                .parse("tel:" + "4000-490-700"));
                        if ((PackageManager.PERMISSION_GRANTED ==
                                getPackageManager().checkPermission("android.permission.CALL_PHONE", "com.sainti.blackcard"))) {
                            startActivity(intent);
                        } else {
                            Uri packageURI = Uri.parse("package:" + getPackageName());
                            Intent intent1 = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                            startActivity(intent1);
                            ToastUtils.show(context, "请手机前往权限设置页面电话权限");
                        }*/
                        onCall("4000-490-700");
                    }
                });
        saintiDialog.show();
    }


    final public static int REQUEST_CODE_ASK_CALL_PHONE = 123;

    public void onCall(String mobile) {

        if (Build.VERSION.SDK_INT >= 23) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(this,
                    Manifest.permission.CALL_PHONE);
            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.CALL_PHONE
                }, REQUEST_CODE_ASK_CALL_PHONE);
                return;
            } else {
                callDirectly(mobile);
            }
        } else {
            callDirectly(mobile);
        }
    }

    //动态权限申请后处理
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_CALL_PHONE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callDirectly("4000-490-700");
                } else {
                    ToastUtils.show(this, "CALL_PHONE Denied");
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void callDirectly(String mobile) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.CALL");
        intent.setData(Uri.parse("tel:" + mobile));
        this.startActivity(intent);
    }
}
