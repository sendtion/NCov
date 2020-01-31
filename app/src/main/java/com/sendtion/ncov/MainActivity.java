package com.sendtion.ncov;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sendtion.ncov.ui.home.HomeFragment;
import com.sendtion.ncov.util.StatusBarUtil;
import com.sendtion.ncov.util.StatusBarUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends BaseActivity implements IBackInterface {
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void initView() {
        BottomNavigationView navView = findViewById(R.id.nav_view);
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    public void setSelectedFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void onBackPressed() {
        if (fragment != null && ((HomeFragment) fragment).onBackPressed()) {
            //实现具体的点击效果
        } else {
            super.onBackPressed();
        }
    }
}
