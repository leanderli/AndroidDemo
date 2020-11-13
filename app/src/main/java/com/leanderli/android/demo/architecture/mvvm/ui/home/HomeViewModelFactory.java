package com.leanderli.android.demo.architecture.mvvm.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.leanderli.android.demo.architecture.mvvm.data.HomeDataSource;
import com.leanderli.android.demo.architecture.mvvm.data.HomeRepository;

public class HomeViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            return (T) new HomeViewModel(HomeRepository.getInstance(new HomeDataSource()));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
