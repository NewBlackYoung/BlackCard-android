package com.sainti.blackcard.homepage.splash.activity;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.text.SpannableString;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseBean;
import com.sainti.blackcard.base.BaseNoTitleActivity;
import com.sainti.blackcard.homepage.activity.HomePageActivity;
import com.sainti.blackcard.commen.mconstant.SataicCode;
import com.sainti.blackcard.commen.mconstant.SpCodeName;
import com.sainti.blackcard.meventbus.NormalEventBean;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.mtuils.MD5Factory;
import com.sainti.blackcard.mtuils.SpUtil;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.mwebview.WuliuWebViewActivity;
import com.sainti.blackcard.homepage.splash.bean.LoginBean;
import com.sainti.blackcard.widget.LoadingView;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

public class MemberLoginActivity extends BaseNoTitleActivity implements View.OnClickListener {

    private TextView tvchong, tvyanzhengma, btnlogin, tv_puth, fakajinu;
    private ImageView iv_back;
    private EditText etcard, etpwd;
    private RelativeLayout rela_yanzhengma, rela_mima, lay_back;
    private Animation animationIn, animationOut;
    private boolean isClick = true;
    private String yanzhengma = "";
    private LoadingView loadingView;
    private Context mContext;
    private String number;

    @Override
    public int setLayout() {
        return R.layout.activity_member_login;
    }

    @Override
    protected void initView() {
        iv_back = bindView(R.id.iv_back);
        tvchong = (TextView) findViewById(R.id.tvchong);// 忘记密码
        tvyanzhengma = bindView(R.id.tvyanzhengma);// 验证码登录
        etcard = bindView(R.id.etcard);// 卡号
        etpwd = bindView(R.id.etpwd);// 密码或者验证码
        btnlogin = bindView(R.id.btnlogin);// 登录按钮
        tv_puth = bindView(R.id.tv_puth);// 发送验证码
        mContext = this;
        lay_back = bindView(R.id.lay_back);
        fakajinu = bindView(R.id.fakajinu);
    }

    @Override
    protected void initEvents() {
        tvyanzhengma.setOnClickListener(this);
        tvchong.setOnClickListener(this);
        tv_puth.setOnClickListener(this);
        btnlogin.setOnClickListener(this);
        lay_back.setOnClickListener(this);
        fakajinu.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        animationIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        animationOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        loadingView = new LoadingView(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tvyanzhengma:
                yanzheng();
                break;
            case R.id.tvchong:
                startActivity(new Intent(this, ForgetPsWdActivity.class));
                break;
            case R.id.tv_puth:
                if (isClick) {
                    getYanZhengMa();
                } else {
                    ToastUtils.show(this, "验证码已发送，请稍后重试");
                }
                break;
            case R.id.btnlogin:
                login();
                break;
            case R.id.lay_back:
                finish();
                break;
            case R.id.fakajinu:
                String jindu = SpUtil.getString(SpCodeName.SPNAME, SpCodeName.CARDJINDU, "");
                Intent intents = new Intent(this, WuliuWebViewActivity.class);
                intents.putExtra("wel_url",jindu);
                intents.putExtra("code", "1");
              startActivity(intents);

                break;
        }
    }

    public void getYanZhengMa() {
        if (etcard.getText().toString().length() == 11) {
            isClick = false;
            timer.start();
            loadingView.show();
            TurnClassHttp.getYanZhengMa(etcard.getText().toString(),
                    "2", 1, this, new OnNetResultListener() {
                        @Override
                        public void onSuccessfulListener(String stringResult, int resultCode) {

                            try {
                                JSONObject object = new JSONObject(stringResult);
                                if (object.getString("result").equals("1")) {
                                    JSONObject object1 = object.getJSONObject("data");
                                    yanzhengma = object1.getString("code");
                                } else {
                                    Toast.makeText(MemberLoginActivity.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
                                }
                                loadingView.dismiss();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailureListener(String errMsg, int resultCode) {
                            loadingView.dismiss();
                            ToastUtils.show(MemberLoginActivity.this, "发送验证码失败！，请稍后重试");
                            isClick = true;
                            tv_puth.setText("发送验证码");
                        }


                    });
        } else {
            Toast.makeText(this, "请正确输入手机号码", Toast.LENGTH_SHORT).show();
        }
    }

    CountDownTimer timer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            tv_puth.setText("已发送(" + millisUntilFinished / 1000 + ")");
        }

        @Override
        public void onFinish() {
            isClick = true;
            tv_puth.setText("发送验证码");
        }
    };

    private void login() {
        String yan = tvyanzhengma.getText().toString();
        if (yan.equals("验证码登录")) {
            // 用的是密码登录
            zhanghaomimadenglu();
        }
        if (yan.equals("返回登录")) {
            // 用的是验证码登录
            if (etcard.getText().length() == 11) {
                if (etpwd.getText().toString().equals(yanzhengma)) {
                    yanzhemgmalogin();
                } else {
                    Toast.makeText(this, "验证码不正确", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "请正确输入手机号码", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /*账号密码登录*/
    private void zhanghaomimadenglu() {
        number = etcard.getText().toString();
        String password = etpwd.getText().toString();
        if (number.equals("")) {
            ToastUtils.show(this, "请输入卡号");
        } else if (password.equals("")) {
            ToastUtils.show(this, "请输入密码");
        } else {
            loadingView.show();
            MD5Factory md5Factory = new MD5Factory(password);
            md5Factory.digestStr();
            String md5Psd = md5Factory.getResult().toLowerCase();
            TurnClassHttp.login(number, md5Psd, 11, this, new OnNetResultListener() {
                @Override
                public void onSuccessfulListener(String stringResult, int resultCode) {
                    loadingView.dismiss();
                    try {
                        Gson gson = GsonSingle.getGson();
                        BaseBean baseBean = gson.fromJson(stringResult, BaseBean.class);
                        if (baseBean.getResult().equals("0")) {
                            ToastUtils.show(MemberLoginActivity.this, baseBean.getMsg());
                            return;
                        }
                        LoginBean loginBean = gson.fromJson(stringResult, LoginBean.class);
                        if (loginBean.getResult() != null
                                && !loginBean.getResult().equals("")
                                && loginBean.getResult().equals("1")) {
                            SpUtil.initEditor(SpCodeName.SPNAME).putString(SpCodeName.UID, loginBean.getData()
                                    .getUid()).commit();
                            SpUtil.initEditor(SpCodeName.SPNAME).putString(SpCodeName.TOKEN, loginBean.getData()
                                    .getToken()).commit();
                            if (loginBean.getData().getIs_bind().equals("0")) {
                                Intent intent2 = new Intent(MemberLoginActivity.this,
                                        MemberMsgActivity.class);
                                startActivity(intent2);
                                finish();
                            } else {
                                SpUtil.initEditor(SpCodeName.SPNAME).putBoolean(SpCodeName.ISLOGIN, true).commit();// 用户已经登录
                                SpUtil.initEditor(SpCodeName.SPNAME).putString(SpCodeName.TEL, loginBean.getData().getUser_tel()).commit();
                                SpUtil.initEditor(SpCodeName.SPNAME).putString(SpCodeName.KAHAO, number).commit();
                                ToastUtils.show(MemberLoginActivity.this, "登录成功");
                                Intent intent = new Intent(MemberLoginActivity.this,
                                        HomePageActivity.class);

                                intent.putExtra("type", 1);
                                EventBus.getDefault().post(new NormalEventBean(SataicCode.FINISHCHOICELOGIN));
                                startActivity(intent);
                                finish();
                            }

                        } else if (loginBean.getResult().equals("3")) {

                        } else {
                            ToastUtils.show(mContext, loginBean.getMsg().toString());

                        }
                    } catch (Exception e) {

                    }

                }

                @Override
                public void onFailureListener(String errMsg, int resultCode) {
                    loadingView.dismiss();
                    ToastUtils.show(mContext, errMsg);
                }
            });
        }
    }

    /* 验证码登录*/
    private void yanzhemgmalogin() {
        TurnClassHttp.yanZhengMaLogin(etcard.getText().toString(),
                etpwd.getText().toString(), 2, mContext, new OnNetResultListener() {
                    @Override
                    public void onSuccessfulListener(String stringResult, int resultCode) {
                        try {
                            JSONObject object1 = new JSONObject(stringResult);
                            if (object1.getString("result").equals("1")) {
                                JSONObject object = object1.getJSONObject("data");
                                SpUtil.initEditor(SpCodeName.SPNAME).putString(SpCodeName.UID, object.getString("uid")).commit();
                                SpUtil.initEditor(SpCodeName.SPNAME).putString(SpCodeName.TOKEN, object.getString("token")).commit();
                                SpUtil.initEditor(SpCodeName.SPNAME).putString(SpCodeName.TEL, object.getString("user_tel")).commit();
                                SpUtil.initEditor(SpCodeName.SPNAME).putString(SpCodeName.KAHAO, object.getString("user_tel")).commit();
                                if (object.getString("is_bind").equals("0")) {
                                    Intent intent = new Intent(mContext,
                                            MemberMsgActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    SpUtil.initEditor(SpCodeName.SPNAME).putBoolean(SpCodeName.ISLOGIN, true).commit();// 用户已经登录
                                    ToastUtils.show(mContext, "登录成功");
                                    Intent intent = new Intent(mContext,
                                            HomePageActivity.class);
                                    intent.putExtra("type", 1);
                                    EventBus.getDefault().post(new NormalEventBean(SataicCode.FINISHCHOICELOGIN));
                                    startActivity(intent);
                                    finish();
                                }

                            } else {
                                Toast.makeText(mContext, object1.getString("msg"), Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailureListener(String errMsg, int resultCode) {
                        String a = errMsg;
                    }
                });

    }

    /* 切换验证码或者密码登录*/
    private void yanzheng() {
        String yan = tvyanzhengma.getText().toString();
        if (yan.equals("验证码登录")) {
            tv_puth.setVisibility(View.VISIBLE);
            tv_puth.setAnimation(animationIn);
            tvchong.setVisibility(View.GONE);
            tvchong.setAnimation(animationOut);
            etcard.setText("");
            SpannableString spannableString = new SpannableString("手机号");
            etcard.setHint(new SpannableString(spannableString));
            SpannableString spannableStrings = new SpannableString("验证码");
            etpwd.setHint(new SpannableString(spannableStrings));
            tvyanzhengma.setText("返回登录");
        }
        if (yan.equals("返回登录")) {
            tvchong.setVisibility(View.VISIBLE);
            etcard.setText("");
            SpannableString spannableString = new SpannableString("卡号/手机号");
            etcard.setHint(new SpannableString(spannableString));
            SpannableString spannableStrings = new SpannableString("登录密码");
            etpwd.setHint(new SpannableString(spannableStrings));
            tvyanzhengma.setText("验证码登录");
            tv_puth.setVisibility(View.GONE);
            tv_puth.setAnimation(animationOut);
            tvchong.setAnimation(animationIn);
        }
    }


}
