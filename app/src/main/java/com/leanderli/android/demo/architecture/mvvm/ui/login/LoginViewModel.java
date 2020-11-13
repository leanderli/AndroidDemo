package com.leanderli.android.demo.architecture.mvvm.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Patterns;

import com.leanderli.android.demo.R;
import com.leanderli.android.demo.architecture.mvvm.data.DataSourceCallback;
import com.leanderli.android.demo.architecture.mvvm.data.LoginRepository;
import com.leanderli.android.demo.architecture.mvvm.data.Result;
import com.leanderli.android.demo.architecture.mvvm.data.model.LoggedInUser;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String username, String password) {
        loginRepository.login(username, password, new DataSourceCallback() {
            @Override
            public void onSuccess(Result<Result.Success> loggedInUserResult) {
                LoggedInUser data = ((Result.Success<LoggedInUser>) loggedInUserResult).getData();
                loginResult.postValue(new LoginResult(new LoggedInUserView(data.getDisplayName())));
            }

            @Override
            public void onError(Result<Result.Error> errorResult) {
                loginResult.postValue(new LoginResult(R.string.login_failed));
            }
        });
    }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}