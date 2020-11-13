package com.leanderli.android.demo.architecture.mvvm.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.leanderli.android.demo.architecture.mvvm.data.DataSourceCallback;
import com.leanderli.android.demo.architecture.mvvm.data.HomeRepository;
import com.leanderli.android.demo.architecture.mvvm.data.Result;
import com.leanderli.android.demo.architecture.mvvm.data.model.Hitokoto;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<Hitokoto> hitokotoLiveData = new MutableLiveData<>();

    private HomeRepository repository;

    HomeViewModel(HomeRepository homeRepository) {
        this.repository = homeRepository;
    }

    LiveData<Hitokoto> getHitokoto() {
        repository.loadHitokoto(new DataSourceCallback() {
            @Override
            public void onSuccess(Result<Result.Success> successResult) {
                hitokotoLiveData.postValue((Hitokoto) ((Result.Success) successResult).getData());
            }

            @Override
            public void onError(Result<Result.Error> errorResult) {

            }
        });
        return hitokotoLiveData;
    }




}
