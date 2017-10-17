package com.mystrawberry.baikedonotstarve;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mystrawberry.baikedonotstarve.databinding.ActivityMainBinding;
import com.mystrawberry.baikedonotstarve.info.TextAndDrawable;

import java.util.ArrayList;
import java.util.List;

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

        //准备数据
        mDataBinding.setLayoutManager(new LinearLayoutManager(this));
        final Resources resources = getResources();
        String[] stringArray = resources.getStringArray(R.array.main_menu_name);
        TypedArray drawabless = resources.obtainTypedArray(R.array.main_menu_pic);
        int length = stringArray.length;
        List<TextAndDrawable> stringList = new ArrayList<>(length);
        TextAndDrawable textAndDrawable;
        for (int i = 0; i < length; i++) {
            textAndDrawable = new TextAndDrawable(stringArray[i], drawabless.getDrawable(i));
            stringList.add(textAndDrawable);
        }
        drawabless.recycle();
        final MyAdapter myAdapter = new MyAdapter(stringList);
        myAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //当条目被点击更改条目的焦点
                myAdapter.setSelectedPos(position);
                /*xml图片文件搭配DrawableCompat设置颜色会出小bug
                 * 大概是xml文件的问题(加载有延迟???),必须用notifyDataSetChanged刷新才行
                  * 不能用notifyItemChanged 会造成图片选取状态错乱*/
                myAdapter.notifyDataSetChanged();

                mDataBinding.drawerLayout.closeDrawer(GravityCompat.START);

            }
        });
        mDataBinding.setMyAdapter(myAdapter);





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
