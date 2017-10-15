package com.mystrawberry.baikedonotstarve;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.mystrawberry.baikedonotstarve.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ActivityMainBinding mDataBinding;
    private ActionBarDrawerToggle mActionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(mDataBinding.toolbar);

        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDataBinding.drawerLayout,
                mDataBinding.toolbar, R.string.open, R.string.close) {
            //打开菜单时监听回调
            @Override
            public void onDrawerClosed(View view) {

                // 声明菜单栏状态被更改,系统会调用onPrepareOptionsMenu(),而且会加载toolbar菜单按钮的动画效果
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {

                invalidateOptionsMenu(); // 声明菜单栏状态被更改,系统会调用onPrepareOptionsMenu()
            }
        };
        //将侧滑菜单的打开和关闭监听添加到drawerLayout,ActionBarDrawerToggle实现了此监听器
        mDataBinding.drawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();
        mDataBinding.rvMain.setHasFixedSize(true);
        mDataBinding.setLayoutManager(new LinearLayoutManager(this));
        mDataBinding.setMyAdapter(new MyAdapter());


    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        //使侧滑菜单的状态初始化并且在activity意外销毁后可以恢复显示状态,不调用此方法toolbar会不显示菜单按钮
        mActionBarDrawerToggle.syncState();

    }

    //    /* 如果我们调用invalidateOptionsMenu() 系统会调用onPrepareOptionsMenu*/
//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        // 如果菜单栏已打开，请隐藏与内容视图相关的操作项比如搜索按钮
//        boolean drawerOpen = mDataBinding.drawerLayout.isDrawerOpen(mDataBinding.flMeu);
////        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
//        return super.onPrepareOptionsMenu(menu);
//    }
    @Override
    public void onBackPressed() {

        if (mDataBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDataBinding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}
