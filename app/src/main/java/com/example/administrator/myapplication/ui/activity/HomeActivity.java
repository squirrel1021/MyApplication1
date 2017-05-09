package com.example.administrator.myapplication.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.example.administrator.myapplication.R;

/**
 * Created by Administrator on 2017/5/9 0009.
 */

public class HomeActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_main);
    }
}
