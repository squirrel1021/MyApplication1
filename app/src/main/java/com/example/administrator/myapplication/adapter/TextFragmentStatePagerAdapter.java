package com.example.administrator.myapplication.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.administrator.myapplication.bean.TextBean;
import com.example.administrator.myapplication.ui.fragment.LoginAnimTextBaseFragment;

import java.util.ArrayList;



/**
 * 下层文字动画有4页
 */
public class TextFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

    ArrayList<TextBean> mTextBeans;

    public TextFragmentStatePagerAdapter(FragmentManager fm, ArrayList<TextBean> tbs) {
        super(fm);
        if(tbs != null){
            mTextBeans = tbs;
        }
        else{
            mTextBeans = new ArrayList<TextBean>();
        }
    }

    @Override
    public Fragment getItem(int position) {
        return new LoginAnimTextBaseFragment(mTextBeans.get(position));
    }

    @Override
    public int getCount() {
        return mTextBeans.size();
    }
}
