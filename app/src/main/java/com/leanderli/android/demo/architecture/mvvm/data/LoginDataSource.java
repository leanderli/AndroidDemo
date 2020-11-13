package com.leanderli.android.demo.architecture.mvvm.data;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.leanderli.android.demo.common.http.AsyncDataLoaderCallback;
import com.leanderli.android.demo.common.http.AsyncHttp;
import com.leanderli.android.demo.architecture.mvvm.data.model.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {
    private final static String LOGIN_URL = "";

    public void login(String username, String password, DataSourceCallback loginCallback) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("loginName", username);
            jsonObject.addProperty("password", password);
            new AsyncHttp().post(LOGIN_URL, jsonObject.toString(), new AsyncDataLoaderCallback<String>() {
                @Override
                public void onSuccess(String data) {
                    LogUtils.d(data);
                    JsonObject jsonObject = JsonParser.parseString(data).getAsJsonObject();
                    String id = "";
                    try {
                        id = jsonObject.get("id").getAsString();
                        if (StringUtils.isEmpty(id)) {
                            throw new RuntimeException("Logging error! id is null");
                        }
                        LoggedInUser loggedInUser = new LoggedInUser(id, username);
                        loginCallback.onSuccess(new Result.Success<>(loggedInUser));
                    } catch (Exception e) {
                        e.printStackTrace();
                        loginCallback.onError(new Result.Error(e));
                    }
                }

                @Override
                public void onFailure(String msg) {
                    LogUtils.d(msg);
                    loginCallback.onError(new Result.Error(new RuntimeException(msg)));
                }
            });
        } catch (Exception e) {
            loginCallback.onError(new Result.Error(new IOException("Error logging in", e)));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }

}