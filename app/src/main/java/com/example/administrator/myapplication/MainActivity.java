package com.example.administrator.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.myapplication.bean._User;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class MainActivity extends AppCompatActivity {
    private EditText userName, password;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        Bmob.initialize(this, "5f48b3db0b865cdf909f11439d42ddb1");
        userName = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLogin();
            }
        });
    }

    private void setLogin() {
//        BmobQuery<_User> query = new BmobQuery<_User>();
//        query.addWhereEqualTo("username", userName.getText()+"");
//        query.findObjects(new FindListener<_User>() {
//            @Override
//            public void done(List<_User> list, BmobException e) {
//                if(e==null){
//                    Log.i("_User",list.get(0).getPassword());
//                    Toast.makeText(MainActivity.this,"查询成功",Toast.LENGTH_LONG).show();
//                }else{
//                    Log.i("BmobExceptiond",e.toString());
//                    Toast.makeText(MainActivity.this,"查询失败",Toast.LENGTH_LONG).show();
//                }
//            }
//        });

        BmobQuery<_User> query = new BmobQuery<_User>();
        query.addWhereEqualTo("username",userName.getText()+"");
        Log.i("loginsdf", userName.getText()+"");
        query.findObjects(new FindListener<_User>() {
            @Override
            public void done(List<_User> list, BmobException e) {
                if(e==null){
                    Toast.makeText(MainActivity.this,"登陆成功",Toast.LENGTH_LONG).show();
                    Log.i("bmob12", list.get(0).getUsername());
                }else{
                    Log.i("bmobfail", e.getMessage()+","+e.getErrorCode());
//                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                    Toast.makeText(MainActivity.this,"登陆失败",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
