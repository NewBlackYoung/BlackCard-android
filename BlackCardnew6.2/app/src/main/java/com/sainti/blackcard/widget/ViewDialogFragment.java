package com.sainti.blackcard.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sainti.blackcard.R;

/**
 * Created by YuZhenpeng on 2018/5/29.
 */

public class ViewDialogFragment extends DialogFragment {

    private TextView xiugai_name;

    public interface SureCallback {
        void onSureClick(String content, int code);
    }



    private SureCallback surecallback;

    private String titles ;

    public void setTitle(String titles) {
        this.titles = titles;
    }

    public void show(FragmentManager fragmentManager) {
        show(fragmentManager, "ViewDialogFragment");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.view_dialog_per, null);
        builder.setView(view);
        xiugai_name = (TextView) view.findViewById(R.id.xiugai_name);
        xiugai_name.setText(titles);
        TextView tv_quxiao = (TextView) view.findViewById(R.id.tv_quxiao);
        TextView tv_queding = (TextView) view.findViewById(R.id.tv_queding);
        tv_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (surecallback != null) {
                    EditText content = (EditText) view.findViewById(R.id.xiugai_neirong);
                    surecallback.onSureClick(content.getText().toString(), 2);
                }
            }
        });
        tv_queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (surecallback != null) {
                    EditText content = (EditText) view.findViewById(R.id.xiugai_neirong);
                    surecallback.onSureClick(content.getText().toString(), 1);
                }
            }
        });


        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SureCallback) {
            surecallback = (SureCallback) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement Callback");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        surecallback = null;
    }
}
