package com.sainti.blackcard.chat.activity;

import android.os.Handler;
import android.widget.EditText;

import com.hyphenate.chat.ChatClient;
import com.hyphenate.chat.Message;
import com.hyphenate.helpdesk.callback.Callback;
import com.hyphenate.helpdesk.model.EvaluationInfo;
import com.hyphenate.helpdesk.model.MessageHelper;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.mtuils.MLog;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.widget.LoadingView;
import com.willy.ratingbar.BaseRatingBar;
import com.willy.ratingbar.ScaleRatingBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by YuZhenpeng on 2018/5/25.
 */

public class PingJiaActivity extends BaseTitleActivity implements BaseTitleActivity.OnClickRightTextCallBack, BaseRatingBar.OnRatingChangeListener, Callback {
    private ScaleRatingBar ratingBar;
    private EditText ed_comment;
    private Message currentMessage;
    private EvaluationInfo evaluationInfo;
    private volatile EvaluationInfo.Degree currentDegree;
    private List<EvaluationInfo.TagInfo> selectedTags = Collections.synchronizedList(new ArrayList<EvaluationInfo.TagInfo>());
    private LoadingView loadingView;


    @Override
    public int setLayout() {
        return R.layout.activity_pingjia;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initEvent() {
        ratingBar = bindView(R.id.ratingBar);
        ed_comment = bindView(R.id.ed_comment);

    }

    @Override
    protected void initData() {

        setTitle("满意度评价");
        setBaseRightText("提交", this);
        ratingBar.setStarPadding(30);
        ratingBar.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        ratingBar.setOnRatingChangeListener(this);
        String msgId = getIntent().getStringExtra("msgId");
        currentMessage = ChatClient.getInstance().chatManager().getMessage(msgId);
        evaluationInfo = MessageHelper.getEvalRequest(currentMessage);
        currentDegree = evaluationInfo.getDegree(5);
        selectedTags.addAll(currentDegree.getAppraiseTag());
        loadingView = new LoadingView(this);
    }

    /*提交*/
    @Override
    public void clickRightText() {
        loadingView.show();
        MessageHelper.sendEvalMessage(currentMessage, ed_comment.getText().toString(), currentDegree, selectedTags, this);
    }

    @Override
    public void onRatingChange(BaseRatingBar baseRatingBar, float v) {
        MLog.d("选择的星星个数", "选择的星星个数" + v + "");
        currentDegree = evaluationInfo.getDegree((int) v);
        if (currentDegree != null) {
            selectedTags.clear();
            selectedTags.addAll(currentDegree.getAppraiseTag());
        }

    }

    @Override
    public void onSuccess() {

        handler.sendEmptyMessage(0);
    }

    @Override
    public void onError(int code, String error) {

        ToastUtils.show(this, error);
    }

    @Override
    public void onProgress(int progress, String status) {

    }

    //消息处理者,创建一个Handler的子类对象,目的是重写Handler的处理消息的方法(handleMessage())
    Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            loadingView.dismiss();
            ToastUtils.show(PingJiaActivity.this, "评价成功");
            finish();
        }
    };
}
