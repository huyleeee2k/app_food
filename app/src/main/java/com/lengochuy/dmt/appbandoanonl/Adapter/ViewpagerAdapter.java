package com.lengochuy.dmt.appbandoanonl.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.lengochuy.dmt.appbandoanonl.Fragment.HomeFragment;
import com.lengochuy.dmt.appbandoanonl.Fragment.NotificationFragment;
import com.lengochuy.dmt.appbandoanonl.Fragment.OrderFragment;

public class ViewpagerAdapter extends FragmentPagerAdapter {
    public ViewpagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 1:
                return new OrderFragment();
            case 2:
                return new NotificationFragment();
            default: return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
