package com.leanderli.android.demo.architecture.mvvm.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.leanderli.android.demo.R;

public class LoginTestHomeActivity extends AppCompatActivity {

    private TextView hitokotoContentTextView;
    private TextView hitikotoSourceTextView;

    private HomeViewModel homeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_test_home);
        homeViewModel = new ViewModelProvider(this, new HomeViewModelFactory()).get(HomeViewModel.class);

        hitokotoContentTextView = findViewById(R.id.tv_hitokoto_content);
        hitikotoSourceTextView = findViewById(R.id.tv_hitokoto_source);

        final ImageView hitokotoRefreshImageView = findViewById(R.id.iv_refresh);
        hitokotoRefreshImageView.setOnClickListener(v -> {
            updateHitokotoUi();
        });

        updateHitokotoUi();
    }

    private void updateHitokotoUi() {
        homeViewModel.getHitokoto().observe(this, hitokoto -> {
            hitokotoContentTextView.setText(hitokoto.getContent());
            hitikotoSourceTextView.setText(getString(R.string.middle_line) + hitokoto.getSource());
        });
    }
}