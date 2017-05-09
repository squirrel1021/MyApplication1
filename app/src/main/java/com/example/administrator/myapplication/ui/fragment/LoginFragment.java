package com.example.administrator.myapplication.ui.fragment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.MainActivity;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.bean._User;
import com.example.administrator.myapplication.ui.activity.HomeActivity;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


/**
 * 登录
 */
public class LoginFragment extends Fragment {
    private EditText username, password;
    private View view;
    private Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_new_login, null);
        initView();
        return view;
    }

    private void initView() {
        username = (EditText) view.findViewById(R.id.et_phone);
        password = (EditText) view.findViewById(R.id.et_psw);
        button= (Button) view.findViewById(R.id.login_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        BmobQuery<_User> query = new BmobQuery<_User>();
        query.addWhereEqualTo("username",username.getText()+"");
        Log.i("loginsdf", username.getText()+"");
        query.findObjects(new FindListener<_User>() {
            @Override
            public void done(List<_User> list, BmobException e) {
                if(e==null){
                    Toast.makeText(getActivity(),"登陆成功",Toast.LENGTH_LONG).show();
                    Log.i("bmob12", list.get(0).getUsername());
                    Intent intent=new Intent();
                    intent.setClass(getActivity(), HomeActivity.class);
                    startActivity(intent);
                }else{
                    Log.i("bmobfail", e.getMessage()+","+e.getErrorCode());
                    Toast.makeText(getActivity(),"登陆失败",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}
