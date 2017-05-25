package com.example.administrator.myapplication.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.utils.ShareUtil;

/**
 * Created by Administrator on 2017/5/9 0009.
 */

public class HomeActivity extends Activity {
    private Button shareBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_main);
        initView();
    }

    private void initView() {

        String nickname=getIntent().getStringExtra("nickname");
        String imageUrl=getIntent().getStringExtra("headImage");
        ((TextView)findViewById(R.id.welcomeTv)).setText("欢迎"+nickname+"");
        ImageView headImage= (ImageView) findViewById(R.id.headImage);
        Glide.with(this)
                .load(imageUrl)
                .into(headImage);
        shareBtn = (Button) findViewById(R.id.shareBtn);
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareUtil.showShare(HomeActivity.this);
            }
        });
    }
}
