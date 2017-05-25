package com.example.administrator.myapplication.ui.fragment;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.MainActivity;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.bean.UserTable;
import com.example.administrator.myapplication.bean._User;
import com.example.administrator.myapplication.ui.activity.HomeActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;


/**
 * 登录
 */
public class LoginFragment extends Fragment implements View.OnClickListener{
    private EditText username, password;
    private View view;
    private Button button;
    private ImageView qqLogin,wxLogin,weiboLogin;

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
        qqLogin= (ImageView) view.findViewById(R.id.iv_qq);
        wxLogin= (ImageView) view.findViewById(R.id.iv_wechat);
        weiboLogin= (ImageView) view.findViewById(R.id.iv_weibo);
        qqLogin.setOnClickListener(this);
        wxLogin.setOnClickListener(this);
        weiboLogin.setOnClickListener(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        BmobQuery<UserTable> query = new BmobQuery<UserTable>();
        query.addWhereEqualTo("username",username.getText()+"");
        Log.i("loginsdf", username.getText()+"");
        query.findObjects(new FindListener<UserTable>() {
            @Override
            public void done(List<UserTable> list, BmobException e) {
                if(e==null){
                    if(list.size()!=0){
                        Toast.makeText(getActivity(),"登陆成功",Toast.LENGTH_LONG).show();
                        Log.i("bmob12", list.get(0).getUsername());
                        Intent intent=new Intent();
                        intent.setClass(getActivity(), HomeActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getActivity(),"账号不存在",Toast.LENGTH_LONG).show();
                    }

                }else{
                    Log.i("bmobfail", e.getMessage()+","+e.getErrorCode());
                    Toast.makeText(getActivity(),"登陆失败",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            //qq登陆
            case R.id.iv_qq:
            loginByQQ();
                break;
            //微信登陆
            case R.id.iv_wechat:

                break;
            //微博登陆
            case R.id.iv_weibo:

                break;
            default:
                break;
        }
    }

    private void loginByQQ() {
        Platform qq = ShareSDK.getPlatform(QQ.NAME);
//回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行
        qq.setPlatformActionListener(new PlatformActionListener() {

            @Override
            public void onError(Platform arg0, int arg1, Throwable arg2) {
                // TODO Auto-generated method stub
                arg2.printStackTrace();
            }

            @Override
            public void onComplete(final Platform arg0, int arg1, HashMap<String, Object> arg2) {
                // TODO Auto-generated method stub
                //输出所有授权信息
                arg0.getDb().exportData();
                try {
                     JSONObject json=new JSONObject(arg0.getDb().exportData());
                    final String nickname=json.getString("nickname")+"";
                    final  String icon=json.getString("icon")+"";
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent=new Intent();
                            intent.putExtra("nickname",nickname);
                            intent.putExtra("headImage",icon);
                            intent.setClass(getActivity(), HomeActivity.class);
                            startActivity(intent);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onCancel(Platform arg0, int arg1) {
                // TODO Auto-generated method stub

            }
        });
//authorize与showUser单独调用一个即可
//        QQ.authorize();//单独授权,OnComplete返回的hashmap是空的
        qq.showUser(null);//授权并获取用户信息
//移除授权
//weibo.removeAccount(true);
    }
}
