package app.cddic.com.smarter.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import app.cddic.com.smarter.fragment.base.ContactFragment;
import app.cddic.com.smarter.fragment.base.DeviceFragment;
import app.cddic.com.smarter.fragment.base.MessageFragment;


/**
 * Created by Pantiy on 2017/3/12.
 * Copyright © 2016 All rights Reserved by Pantiy
 */

public class DrawerFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList = new ArrayList<>();

    public DrawerFragmentPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        mFragmentList.add(0, new DeviceFragment());
        mFragmentList.add(1, new ContactFragment());
        mFragmentList.add(2, new MessageFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
