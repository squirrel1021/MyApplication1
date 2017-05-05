package com.example.administrator.myapplication.ui.fragment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.myapplication.R;


/**
 * 登录
 */
public class LoginFragment extends Fragment {
private EditText username,password;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_login, null);
       username= (EditText) view.findViewById(R.id.et_phone);
        password= (EditText) view.findViewById(R.id.et_psw);
        return view;
    }

}
