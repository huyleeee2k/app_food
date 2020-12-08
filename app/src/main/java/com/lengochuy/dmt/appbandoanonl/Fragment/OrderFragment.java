package com.lengochuy.dmt.appbandoanonl.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.lengochuy.dmt.appbandoanonl.Fragment.ViewOrderFragment.ViewPagerAdapter;
import com.lengochuy.dmt.appbandoanonl.R;

import java.util.Objects;

public class OrderFragment extends Fragment {
    View view;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_order, container, false);
        initUI();
        return view;
    }

    private void initUI() {
        tabLayout   = view.findViewById(R.id.tabLayoutOrderFM);
        viewPager   = view.findViewById(R.id.viewPagerOrderFM);
        viewPagerAdapter    = new ViewPagerAdapter(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}