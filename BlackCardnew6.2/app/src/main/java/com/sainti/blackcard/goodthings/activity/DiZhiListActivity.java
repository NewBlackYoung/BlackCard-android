package com.sainti.blackcard.goodthings.activity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.mtuils.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DiZhiListActivity extends BaseTitleActivity implements OnNetResultListener {
    private LinearLayout lin_dizhi;
    private TextView tv_xinzeng;
    private Context context;
    private LayoutInflater inflater;
    private String morenID = "";
    private ArrayList<ImageView> imgList = new ArrayList<>();

    private View view_yinying;

    @Override
    public int setLayout() {
        return R.layout.activity_di_zhi_list;
    }

    @Override
    protected void initView() {
        view_yinying = findViewById(R.id.view_yinying);
        view_yinying.setVisibility(View.VISIBLE);
        inflater = LayoutInflater.from(this);
        lin_dizhi = (LinearLayout) findViewById(R.id.lin_dizhi);
        tv_xinzeng = (TextView) findViewById(R.id.tv_xinzeng);
        context= this;

    }

    @Override
    protected void initEvent() {

        tv_xinzeng.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        setTitle("地址列表");
        getDiZhi();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.orderback:
                setResult(200);
                finish();
                break;
            case R.id.tv_xinzeng:
                Intent intent = new Intent(context, XinZengDiZhiActivity.class);
                intent.putExtra("type", 0);
                startActivityForResult(intent, 100);
                break;
        }
    }

    public void getDiZhi() {
        TurnClassHttp.getDiZhi(1, this, this);
    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        switch (resultCode) {
            case 1:
                try {
                    JSONObject object = new JSONObject(stringResult);
                    Log.i("shouhuodizhi", "获取地址列表" + stringResult);
                    if (object.getString("result").equals("1")) {
                        final JSONArray array = object.getJSONArray("data");
                        if (array != null && array.length() > 0) {
                            for (int i = 0; i < array.length(); i++) {
                                View view = inflater.inflate(R.layout.item_dizhi, null);
                                TextView tv_xingming = (TextView) view.findViewById(R.id.tv_xingming);
                                TextView tv_phone = (TextView) view.findViewById(R.id.tv_phone);
                                TextView tv_location = (TextView) view.findViewById(R.id.tv_location);
                                LinearLayout lin_moren = (LinearLayout) view.findViewById(R.id.lin_moren);
                                LinearLayout lin_delete = (LinearLayout) view.findViewById(R.id.lin_delete);
                                LinearLayout lin_bianji = (LinearLayout) view.findViewById(R.id.lin_bianji);
                                ImageView iv_anniu = (ImageView) view.findViewById(R.id.iv_anniu);
                                tv_xingming.setText(array.getJSONObject(i).getString("ad_user"));
                                tv_phone.setText(array.getJSONObject(i).getString("ad_tel"));
                                tv_location.setText(array.getJSONObject(i).getString("ad_city") + " " + array.getJSONObject(i).getString("ad_area") + " " + array.getJSONObject(i).getString("ad_address"));
                                if (array.getJSONObject(i).getString("ad_moren").equals("1")) {
                                    iv_anniu.setImageResource(R.mipmap.xuanzhong);
                                    morenID = array.getJSONObject(i).getString("ad_id");
                                }
                                imgList.add(iv_anniu);
                                final int finalI = i;
                                view.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        try {
                                            upDateDiZhi();
                                            Intent intent = new Intent();
                                            intent.putExtra("id", array.getJSONObject(finalI).getString("ad_id"));
                                            intent.putExtra("sheng", array.getJSONObject(finalI).getString("ad_province"));
                                            intent.putExtra("shi", array.getJSONObject(finalI).getString("ad_city"));
                                            intent.putExtra("qu", array.getJSONObject(finalI).getString("ad_area"));
                                            intent.putExtra("xiangxi", array.getJSONObject(finalI).getString("ad_address"));
                                            intent.putExtra("xingming", array.getJSONObject(finalI).getString("ad_user"));
                                            intent.putExtra("dianhua", array.getJSONObject(finalI).getString("ad_tel"));
                                            setResult(100, intent);
                                            finish();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                                lin_delete.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        try {
                                            view_yinying.setVisibility(View.VISIBLE);
//                                                    view_yuan.setVisibility(View.VISIBLE);
                                            deleteDiZhi(array.getJSONObject(finalI).getString("ad_id"), finalI);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                                lin_bianji.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        try {
                                            Intent intent = new Intent(context, XinZengDiZhiActivity.class);
                                            intent.putExtra("type", 1);
                                            intent.putExtra("ad_id", array.getJSONObject(finalI).getString("ad_id"));
                                            intent.putExtra("sheng", array.getJSONObject(finalI).getString("ad_province"));
                                            intent.putExtra("shi", array.getJSONObject(finalI).getString("ad_city"));
                                            intent.putExtra("qu", array.getJSONObject(finalI).getString("ad_area"));
                                            intent.putExtra("xiangxi", array.getJSONObject(finalI).getString("ad_address"));
                                            intent.putExtra("xingming", array.getJSONObject(finalI).getString("ad_user"));
                                            intent.putExtra("dianhua", array.getJSONObject(finalI).getString("ad_tel"));
                                            startActivityForResult(intent, 100);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                                lin_moren.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        for (int k = 0; k < imgList.size(); k++) {
                                            if (k == finalI) {
                                                imgList.get(k).setImageResource(R.mipmap.xuanzhong);
                                            } else {
                                                imgList.get(k).setImageResource(R.mipmap.moren);
                                            }
                                            try {
                                                morenID = array.getJSONObject(finalI).getString("ad_id");
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                        upDateDiZhi();
                                    }
                                });
                                lin_dizhi.addView(view);
                            }
                        }
                    } else {
                    }
                    view_yinying.setVisibility(View.GONE);


                } catch (JSONException e) {
                    view_yinying.setVisibility(View.GONE);


                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    Log.i("shouhuodizhi", "修改默认地址结果" + stringResult);
                    JSONObject object = new JSONObject(stringResult);
                    if (object.getString("result").equals("1")) {
                    } else {

                    }
                    view_yinying.setVisibility(View.GONE);
                } catch (JSONException e) {
                    view_yinying.setVisibility(View.GONE);

                    e.printStackTrace();
                }
                break;
            case 3:
                try {
                    JSONObject object = new JSONObject(stringResult);
                    if (object.getString("result").equals("1")) {
                        lin_dizhi.removeAllViews();
                        getDiZhi();
                    } else {
                        ToastUtils.show(context, object.getString("msg"));
                    }
                } catch (JSONException e) {
                    view_yinying.setVisibility(View.GONE);
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        switch (resultCode) {
            case 1:
                view_yinying.setVisibility(View.GONE);
                break;
            case 2:
                view_yinying.setVisibility(View.GONE);
                break;
            case 3:
                view_yinying.setVisibility(View.GONE);
                break;
        }
    }
    public void upDateDiZhi() {
        if (morenID == null) {
            morenID = "";
        }
        TurnClassHttp.upDateDiZhi("", "", "", "", "", "", morenID, "1",2,this,this);

    }
    public void deleteDiZhi(String ad_id, final int index) {
        TurnClassHttp.deleteDiZhi(ad_id,3,this,this);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("shouhuodizhi", "DiZhiListActivity:requestCode=" + requestCode + "  resultCode=" + resultCode);
        if (requestCode == 100 && resultCode == 100) {
            view_yinying.setVisibility(View.VISIBLE);
            lin_dizhi.removeAllViews();
            getDiZhi();
        }
    }
}
