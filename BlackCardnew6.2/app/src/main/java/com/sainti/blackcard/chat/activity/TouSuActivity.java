package com.sainti.blackcard.chat.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseBean;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.mtuils.ToastUtils;

/**
 * Created by YuZhenpeng on 2018/5/23.
 */

public class TouSuActivity extends BaseTitleActivity implements OnNetResultListener {

    private RelativeLayout liyou;
    private TextView tvtou;

    private EditText neirong;
    private EditText guanid;
    private Intent intent;
    private String str = "";
    private String id = "";

    @Override
    public int setLayout() {
        return R.layout.activity_tousu;
    }

    @Override
    protected void initView() {

        liyou = (RelativeLayout) findViewById(R.id.liyou);
        tvtou = (TextView) findViewById(R.id.tvtou);
        neirong = (EditText) findViewById(R.id.neirong);
        guanid = (EditText) findViewById(R.id.guanid);
        liyou.setOnClickListener(mListener);

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        setTitle("投诉管家");
        setBaseRightText("发送", new OnClickRightTextCallBack() {
            @Override
            public void clickRightText() {
                String guanjia = guanid.getText().toString();
                String string = neirong.getText().toString();
                if (guanjia.equals("")) {
                    guanjia = "";
                    tousu(guanjia, id, string);
                } else if (string.equals("")) {
                    ToastUtils.show(TouSuActivity.this, "文字描述不能为空");
                } else {
                    tousu(guanjia, id, string);
                }
            }
        });

    }

    private View.OnClickListener mListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.liyou:
                    intent = new Intent(TouSuActivity.this, LiyouActivity.class);
                    startActivityForResult(intent, 101);
                    break;

                default:
                    break;
            }
        }
    };

    public void tousu(String guanjiaid, String id, String str) {
        TurnClassHttp.complain(guanjiaid, id, str, 1, this, this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case 10:
                str = data.getStringExtra("str");
                id = data.getStringExtra("id");
                tvtou.setText(str);
                System.out.println("id===" + id);
                break;
            default:
                break;
        }
    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        BaseBean baseBean = GsonSingle.getGson().fromJson(stringResult, BaseBean.class);
        if (baseBean.getResult().equals("1")) {
            ToastUtils.show(this, "提交成功");
            finish();
        } else {
            ToastUtils.show(this, baseBean.getMsg());
        }
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        String a = errMsg;
    }
}
