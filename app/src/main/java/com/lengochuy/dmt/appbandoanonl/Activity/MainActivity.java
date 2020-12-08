 package com.lengochuy.dmt.appbandoanonl.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.lengochuy.dmt.appbandoanonl.Adapter.ViewpagerAdapter;
import com.lengochuy.dmt.appbandoanonl.Object.NewOrder;
import com.lengochuy.dmt.appbandoanonl.Other.Database;
import com.lengochuy.dmt.appbandoanonl.R;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;
    ViewPager viewPager;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    long backPressedTime;
    Toast toast;
    public static ArrayList <NewOrder> newOrderArrayList;
    public static Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initDrawerNavigation();
        createDatabaseSQLite();
    }

    public void createDatabaseSQLite() {
        database = new Database(this,"Orderlove.sqlite",null,1);
        database.QueryData("CREATE TABLE IF NOT EXISTS OrderLove1 (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username varchar(50)," +
                "photo varchar(500), " +
                "namefood varchar(200), " +
                "price varchar(200)" +
                ")");
    }


    @SuppressLint("NonConstantResourceId")
    private void initData() {
        //Create screen main activity
        bottomNavigationView    = findViewById(R.id.bottomNavigation);
        viewPager               = findViewById(R.id.viewPager);
        //Chặn phân trang khi vuốt viewpager
        //viewPager.beginFakeDrag();
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.order:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.notification:
                    viewPager.setCurrentItem(2);
                    break;
            }
            return true;
        });
        setUpViewPager();
    }

    private void initDrawerNavigation(){
        if (newOrderArrayList   == null){
            newOrderArrayList   = new ArrayList<>();
        }
        drawerLayout            = findViewById(R.id.drawer_layout);
        navigationView          = findViewById(R.id.navigationView);
        toolbar                 = findViewById(R.id.toolBar);
        toolbar.setLogo(R.drawable.ic_baseline_menu_open_24);
        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setUpViewPager() {
        ViewpagerAdapter viewPagerAdapter = new ViewpagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.home).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.order).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.notification).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            if (backPressedTime + 2000 > System.currentTimeMillis()){
                toast.cancel();
                finishAffinity();
                super.onBackPressed();
                return;
            }else{
                toast = Toast.makeText(this, "Nhấn thêm một lần nữa để thoát ứng dụng", Toast.LENGTH_SHORT);
                toast.show();
            }
            backPressedTime = System.currentTimeMillis();
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_home:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.action_setting:
                Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_mail:
                Toast.makeText(this, "Mail", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_app:
                Toast.makeText(this, "Application", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_contact:
                Toast.makeText(this, "Contact", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_support:
                Toast.makeText(this, "Support", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_information:
                Toast.makeText(this, "Information", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_logout:
                LogoutApp();
                break;
        }
        return true;
    }

    private void LogoutApp() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.create();
        alert.setMessage("Do you want logout app now?");
        alert.setNegativeButton("No",(dialogInterface, i) -> {});
        alert.setPositiveButton("Yes",(dialogInterface, i) -> {
            startActivity(new Intent(this,LoginActivity.class));
            finishAffinity();
        });
        alert.show();
    }

}