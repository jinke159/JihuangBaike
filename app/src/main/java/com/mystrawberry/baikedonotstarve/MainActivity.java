package com.mystrawberry.baikedonotstarve;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.mystrawberry.baikedonotstarve.adapter.MyAdapter;
import com.mystrawberry.baikedonotstarve.databinding.ActivityMainBinding;
import com.mystrawberry.baikedonotstarve.fragment.MainItemFragment;
import com.mystrawberry.baikedonotstarve.info.BaseInfo;
import com.mystrawberry.baikedonotstarve.info.Characters;
import com.mystrawberry.baikedonotstarve.info.TextAndDrawable;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.mystrawberry.baikedonotstarve.bing.IOUtils.inputStream2String;

public class MainActivity extends AppCompatActivity implements MainItemFragment.OnListFragmentInteractionListener{

    private static final String TAG = "MainActivity";
    public static final String SAVE_SELECTED_POS_KEY = "侧滑菜单选中位置";
    public ActivityMainBinding mDataBinding;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private MyAdapter mMyAdapter;
    private List<TextAndDrawable> mStringList;
    private Characters mCharacters;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setSupportActionBar(mDataBinding.toolbar);


        if (mActionBarDrawerToggle == null)
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

        //设置打开关闭监听器
        mDataBinding.drawerLayout.addDrawerListener(mActionBarDrawerToggle);

        //创建并刷新左上角的侧滑菜单图标
        mActionBarDrawerToggle.syncState();

        mDataBinding.rvMain.setHasFixedSize(true);
        mDataBinding.setLayoutManager(new LinearLayoutManager(this));


        int POS = 0;
        if (savedInstanceState != null)
            POS = savedInstanceState.getInt(SAVE_SELECTED_POS_KEY);

        if (mMyAdapter == null)
            mMyAdapter = new MyAdapter(mDataBinding.drawerLayout, POS);

        mDataBinding.setMyAdapter(mMyAdapter);

        if (mStringList == null)
            getDrawerLayoutData();


        getCharacters();



    }

    private void getCharacters() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream inputStream = getResources().openRawResource(R.raw.characters);
                String json = inputStream2String(inputStream);
                if (!TextUtils.isEmpty(json)){
                    Gson gson = new Gson();
                    mCharacters = gson.fromJson(json, Characters.class);
                    Log.d(TAG, "run: "+mCharacters.toString());
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.add(R.id.main_frame, MainItemFragment.newInstance(3,mCharacters.mCharacter));
                    fragmentTransaction.commit();
                }
            }
        }).start();
    }


    private void getDrawerLayoutData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
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
                mStringList = stringList;
                mMyAdapter.setNewData(mStringList);
            }
        }).start();

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mMyAdapter != null) {
            outState.putInt(SAVE_SELECTED_POS_KEY, mMyAdapter.getSelectedPos());
        } else {
            outState.putInt(SAVE_SELECTED_POS_KEY, 0);
        }
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


    @Override
    public void onListFragmentInteraction(BaseInfo item) {
        Intent intent = new Intent(this, testActivity.class);
        intent.putExtra("123",item);
        startActivity(intent);
    }
}
