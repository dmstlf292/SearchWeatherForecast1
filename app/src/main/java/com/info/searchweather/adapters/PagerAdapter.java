package com.info.searchweather.adapters;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class PagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> mData;
    public PagerAdapter(FragmentManager fm, ArrayList<Fragment> list) {
        super(fm);
        mData=list;
    }

    @Override
    public int getCount(){
        return mData.size();
    }

    @Override
    public Fragment getItem(int position){
        return mData.get(position);
    }



}