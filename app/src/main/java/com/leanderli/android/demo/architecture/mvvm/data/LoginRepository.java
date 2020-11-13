package com.leanderli.android.demo.architecture.mvvm.data;

import com.leanderli.android.demo.architecture.mvvm.data.model.LoggedInUser;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class LoginRepository {

    private static volatile LoginRepository instance;

    private LoginDataSource dataSource;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    private LoggedInUser user = null;

    // private constructor : singleton access
    private LoginRepository(LoginDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static LoginRepository getInstance(LoginDataSource dataSource) {
        if (instance == null) {
            instance = new LoginRepository(dataSource);
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void logout() {
        user = null;
        dataSource.logout();
    }

    private void setLoggedInUser(LoggedInUser user) {
        this.user = user;
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

    public void login(String username, String password, DataSourceCallback callback) {
        // handle login
        dataSource.login(username, password, new DataSourceCallback() {
            @Override
            public void onSuccess(Result<Result.Success> loggedInUserResult) {
                setLoggedInUser(((Result.Success<LoggedInUser>) loggedInUserResult).getData());
                callback.onSuccess(loggedInUserResult);
            }

            @Override
            public void onError(Result<Result.Error> errorResult) {
                callback.onError(errorResult);
            }
        });
    }
}