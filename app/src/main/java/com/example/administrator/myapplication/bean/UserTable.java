package com.example.administrator.myapplication.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/5/11 0011.
 */

public class UserTable extends BmobObject {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
