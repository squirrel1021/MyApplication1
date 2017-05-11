package com.example.administrator.myapplication.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.myapplication.MainActivity;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.bean.UserTable;
import com.example.administrator.myapplication.bean._User;
import com.example.administrator.myapplication.ui.activity.HomeActivity;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;


/**
 * 注册
 */
public class RegisterFragment extends Fragment implements View.OnClickListener {
    private EditText phoneEt, psdEt;
    private View view;
    private Button btn_regist;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_new_register, null);
        initView();
        return view;
    }

    private void initView() {
        phoneEt = (EditText) view.findViewById(R.id.et_write_phone);
        psdEt = (EditText) view.findViewById(R.id.et_put_identify);
        btn_regist = (Button) view.findViewById(R.id.btn_regist);
        btn_regist.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_regist:
                regist();
                break;
            default:
                break;
        }
    }

    private void regist() {
//        BmobQuery<_User> query = new BmobQuery<_User>();
//        query.addWhereEqualTo("username", phoneEt.getText() + "");
//        Log.i("loginsdf", phoneEt.getText() + "");
//        query.findObjects(new FindListener<_User>() {
//            @Override
//            public void done(List<_User> list, BmobException e) {
//                if (e == null) {
//                    Toast.makeText(getActivity(), "该账号已存在", Toast.LENGTH_LONG).show();
//                    Log.i("bmob12", list.get(0).getUsername());
//                } else {
//                    Toast.makeText(getActivity(), "网络连接异常,请重试", Toast.LENGTH_LONG).show();
//                    registNow();
//                }
//            }
//        });
        BmobQuery<UserTable> query = new BmobQuery<UserTable>();
        query.addWhereEqualTo("username",phoneEt.getText()+"");
        Log.i("loginsdf", phoneEt.getText()+"");
        query.findObjects(new FindListener<UserTable>() {
            @Override
            public void done(List<UserTable> list, BmobException e) {
                if(e==null){
                    if(list.size()!=0){
                        Toast.makeText(getActivity(), "该账号已存在", Toast.LENGTH_LONG).show();
                    }else{
                        registNow();
                    }

                }else{
                    Log.i("bmobfail", e.getMessage()+","+e.getErrorCode());
                    Toast.makeText(getActivity(),"登陆失败",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void registNow() {
        UserTable user = new UserTable();
        user.setUsername(phoneEt.getText() + "");
        user.setPassword(psdEt.getText()+"");
        user.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e==null){
                    Toast.makeText(getActivity(), "注册成功", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent();
                    intent.setClass(getActivity(), HomeActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getActivity(), "网络连接异常,注册失败", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
