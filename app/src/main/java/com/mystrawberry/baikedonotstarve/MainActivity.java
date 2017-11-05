package com.mystrawberry.baikedonotstarve;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.mystrawberry.baikedonotstarve.adapter.MyAdapter;
import com.mystrawberry.baikedonotstarve.context.BiomeDetailsActivity;
import com.mystrawberry.baikedonotstarve.databinding.ActivityMainBinding;
import com.mystrawberry.baikedonotstarve.fragment.BiomesViewPagerFragment;
import com.mystrawberry.baikedonotstarve.fragment.MainItemFragment;
import com.mystrawberry.baikedonotstarve.fragment.NullFragment;
import com.mystrawberry.baikedonotstarve.info.BaseInfo;
import com.mystrawberry.baikedonotstarve.info.Biomes;
import com.mystrawberry.baikedonotstarve.info.Characters;
import com.mystrawberry.baikedonotstarve.info.Craftings;
import com.mystrawberry.baikedonotstarve.info.Foods;
import com.mystrawberry.baikedonotstarve.info.Goods;
import com.mystrawberry.baikedonotstarve.info.Mobs;
import com.mystrawberry.baikedonotstarve.info.TextAndDrawable;
import com.mystrawberry.baikedonotstarve.interfaces.OnListFragmentInteractionListener;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.mystrawberry.baikedonotstarve.bing.IOUtils.inputStream2String;

public class MainActivity extends AppCompatActivity implements OnListFragmentInteractionListener, BaseQuickAdapter.OnItemClickListener {

    private static final String TAG = "MainActivity";
    public static final String SAVE_SELECTED_POS_KEY = "侧滑菜单选中位置";
    public static final String ITEM_KEY = "选中条目的详情";
    public ActivityMainBinding mDataBinding;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private MyAdapter mMyAdapter;
    private List<TextAndDrawable> mStringList;
    private SparseArray<Fragment> mFragmentArray;
    //角色数据对象
    private ArrayList<Characters.CharacterBean> mArrayCharacters;
    private static final int CHARACTERS_POS = 0;
    //食物数据对象
    private Foods mFoods;
    private static final int FOODS_POS = 1;
    //科技数据对象
    private Craftings mCraftings;
    private static final int CRAFTINGS_POS = 2;
    //生物数据对象
    private Mobs mMobs;
    private static final int MOBS_POS = 3;
    //自然数据对象
    private ArrayList<Biomes.BiomesListBean> mBiomesList;
    private static final int BIOMES_POS = 4;
    //物品数据对象
    private Goods mGoods;
    private static final int GOODS_POS = 5;
    private Thread mThread;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setSupportActionBar(mDataBinding.included.toolbar);
        mFragmentArray = new SparseArray<>(6);

        if (mActionBarDrawerToggle == null)
            mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDataBinding.drawerLayout,
                    mDataBinding.included.toolbar, R.string.open, R.string.close) {
                //打开菜单时监听回调
                @Override
                public void onDrawerClosed(View view) {
                    if (getSupportActionBar() != null) {

                        getSupportActionBar().setTitle(mStringList.get(mMyAdapter.getSelectedPos()).mString);
                    }
                    // 声明菜单栏状态被更改,系统会调用onPrepareOptionsMenu(),而且会加载toolbar菜单按钮的动画效果
                    invalidateOptionsMenu();
                }

                @Override
                public void onDrawerOpened(View drawerView) {
                    if (getSupportActionBar() != null) {

                        getSupportActionBar().setTitle(R.string.app_name);
                    }
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

        if (mMyAdapter == null) {
            mMyAdapter = new MyAdapter(POS);
            mMyAdapter.setOnItemClickListener(this);
        }

        initData();
        mDataBinding.setMyAdapter(mMyAdapter);

        if (mStringList == null)
            getDrawerLayoutData();


    }

    private void initData() {
        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Resources resources = getResources();
                InputStream inputStream = resources.openRawResource(R.raw.characters);
                String json = inputStream2String(inputStream);
                InputStream biomeJson = resources.openRawResource(R.raw.biome);

                String biomejson = inputStream2String(biomeJson);
                if (!TextUtils.isEmpty(json) && !TextUtils.isEmpty(biomejson)) {
                    Gson gson = new Gson();

                    mArrayCharacters = gson.fromJson(json, Characters.class).mCharacter;
                    Biomes biomes = gson.fromJson(biomejson, Biomes.class);
                    mBiomesList = biomes.biomesList;
                    replaceFragment(mMyAdapter.getSelectedPos());

                }
            }
        });
        mThread.start();
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

//        /* 如果我们调用invalidateOptionsMenu() 系统会调用onPrepareOptionsMenu*/
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
    public void onListFragmentInteraction(BaseInfo item, int selectedPos) {
        Intent intent;
        switch (selectedPos) {
            case CHARACTERS_POS:
//                intent = new Intent(this, testActivity.class);
//                startActivitys(item, intent);
                break;
            case BIOMES_POS:
                intent = new Intent(this, BiomeDetailsActivity.class);
                startActivitys(item, intent);

                break;

        }

    }

    private void startActivitys(BaseInfo item, Intent intent) {
        intent.putExtra(ITEM_KEY, item);
        startActivity(intent);
    }

    /**
     * 侧边栏点击事件
     */
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {


        if (position == mMyAdapter.getSelectedPos()) {
            mDataBinding.drawerLayout.closeDrawer(GravityCompat.START);
            return;
        }
        mMyAdapter.setSelectedPos(position);
        //xml图不能用notifyItemChanged   改成普通图片可以
        mMyAdapter.notifyDataSetChanged();
        mDataBinding.drawerLayout.closeDrawer(GravityCompat.START);

        if (!mThread.isAlive()) replaceFragment(position);



    }

    private void replaceFragment(int pos) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = mFragmentArray.get(pos);
        switch (pos) {
            case CHARACTERS_POS:
                if (fragment == null) {
                    fragment = MainItemFragment.newInstance(3, mArrayCharacters, CHARACTERS_POS);
                    mFragmentArray.put(CHARACTERS_POS, fragment);
                }

                fragmentTransaction.replace(R.id.main_frame, fragment).commit();
                break;
            case BIOMES_POS:
                if (fragment == null) {
                    fragment = BiomesViewPagerFragment.newInstance(mBiomesList, BIOMES_POS);
                    mFragmentArray.put(pos, fragment);
                }

                fragmentTransaction.replace(R.id.main_frame, fragment).commit();

                break;
            case MOBS_POS:
            case GOODS_POS:
            case CRAFTINGS_POS:
            case FOODS_POS:
                if (fragment == null) {
                    fragment = NullFragment.newInstance("" + pos, "");
                    mFragmentArray.put(pos, fragment);
                }
                fragmentTransaction.replace(R.id.main_frame, fragment).commit();
                break;


        }

    }


}
