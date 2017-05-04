package com.example.administrator.myapplication;

import android.app.Activity;
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
import cn.bmob.v3.listener.QueryListener;

/**
 * Created by ou on 2017/4/11.
 */
public class Login extends Activity implements View.OnClickListener {
    private EditText editText, editText2;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        Bmob.initialize(this, "5f48b3db0b865cdf909f11439d42ddb1");
        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button:
                login();
                break;

            default:
                break;

        }
    }

    private void login() {
        BmobQuery<_User> query = new BmobQuery<_User>();
        query.addWhereEqualTo("username",editText.getText()+"");
        Log.i("loginsdf", editText.getText()+"");
        query.findObjects(new FindListener<_User>() {
            @Override
            public void done(List<_User> list, BmobException e) {
                if(e==null){
                    Toast.makeText(Login.this,"登陆成功",Toast.LENGTH_LONG).show();
                    Log.i("bmob12", list.get(0).getUsername());
                }else{
                    Log.i("bmobfail", e.getMessage()+","+e.getErrorCode());
//                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                    Toast.makeText(Login.this,"登陆失败",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
