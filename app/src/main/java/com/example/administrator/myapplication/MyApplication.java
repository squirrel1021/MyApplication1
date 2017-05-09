package com.example.administrator.myapplication;

import android.app.Application;

import cn.bmob.v3.Bmob;

/**
 * Created by Administrator on 2017/5/4 0004.
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this, "5f48b3db0b865cdf909f11439d42ddb1");
    }
}
