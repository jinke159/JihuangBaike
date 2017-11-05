package com.mystrawberry.baikedonotstarve.context;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;

import com.mystrawberry.baikedonotstarve.R;
import com.mystrawberry.baikedonotstarve.adapter.ImageViewAdapter;
import com.mystrawberry.baikedonotstarve.databinding.ActivityBiomeDetailsBinding;
import com.mystrawberry.baikedonotstarve.info.Biomes;

import static com.mystrawberry.baikedonotstarve.MainActivity.ITEM_KEY;

public class BiomeDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityBiomeDetailsBinding dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_biome_details);

        setSupportActionBar(dataBinding.included.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        Intent intent = getIntent();

        Biomes.BiomesListBean.BiomesDataListBean data =getIntent().getParcelableExtra(ITEM_KEY);
        dataBinding.setData(data);
        dataBinding.abundantRv.setHasFixedSize(true);
        dataBinding.occasionalRv.setHasFixedSize(true);
        dataBinding.rareRv.setHasFixedSize(true);
        dataBinding.abundantRv.setLayoutManager(new GridLayoutManager(this,5));
        dataBinding.occasionalRv.setLayoutManager(new GridLayoutManager(this,5));
        dataBinding.rareRv.setLayoutManager(new GridLayoutManager(this,5));

        dataBinding.setAbundantAdapter(new ImageViewAdapter(data.abundant));
        dataBinding.setOccasionalAdapter(new ImageViewAdapter(data.occasional));
        dataBinding.setRareAdapter(new ImageViewAdapter(data.rare));
    }
}
