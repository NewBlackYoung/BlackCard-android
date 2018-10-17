package com.hyphenate.helpdesk.easeui.widget.chatrow;


import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.hyphenate.chat.Message;
import com.hyphenate.helpdesk.R;
import com.hyphenate.helpdesk.model.MessageHelper;

public class OrderEvaluation extends ChatRow {

    Button btnEval;

    public OrderEvaluation(Context context, Message message, int position, BaseAdapter adapter) {
        super(context, message, position, adapter);
    }

    @Override
    protected void onInflatView() {
        inflater.inflate(message.direct() == Message.Direct.RECEIVE ? R.layout.em_row_received_satisfaction
                : R.layout.em_row_sent_satisfaction, this);
    }

    @Override
    protected void onFindViewById() {
        btnEval = (Button) findViewById(R.id.btn_eval);
    }

    @Override
    protected void onUpdateView() {
    }

    @Override
    protected void onSetUpView() {
        try {
            if (MessageHelper.getEvalRequest(message) != null) {
                btnEval.setEnabled(true);
                btnEval.setText(R.string.chatrow_eval_btn_text);
                btnEval.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent();
                        intent.setAction("com.PingJiaActivity.action");
                        intent.addCategory("android.intent.category.DEFAULT");
                        intent.putExtra("msgId", message.messageId());
                        context.startActivity(intent);
                    }
                });

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onBubbleClick() {


    }

}
