package com.example.boardcar;

import android.app.Application;

public class LoginInfo extends Application {
    private boolean isLogin;

    @Override
    public void onCreate() {
        isLogin = false;
        super.onCreate();
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }
}
