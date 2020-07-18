package com.leanderli.android.demo.mvp.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

/**
 * Created by Administrator on 2018-07-03.
 */

public abstract class BaseActivity extends Activity implements BaseView {

    private ProgressDialog progressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在同步数据，稍等");
        progressDialog.setCancelable(false);
    }

    @Override
    public void showLoading() {
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError() {
        showToast("error");
    }

    @Override
    public Context getContext() {
        return BaseActivity.this;
    }
}
