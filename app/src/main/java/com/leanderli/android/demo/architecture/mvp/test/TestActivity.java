package com.leanderli.android.demo.architecture.mvp.test;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.leanderli.android.demo.R;
import com.leanderli.android.demo.architecture.mvp.base.BaseActivity;

public class TestActivity extends BaseActivity implements TestView {

    TextView dataView;

    TestPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        dataView = findViewById(R.id.tv_data);

        presenter = new TestPresenter();
        presenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void showData(String data) {
        dataView.setText(data);
    }

    public void getDataError(View view) {
        presenter.getData("error");
    }

    public void getDataFailure(View view) {
        presenter.getData("failed");
    }

    public void getDataSuccess(View view) {
        presenter.getData("success");
    }
}
