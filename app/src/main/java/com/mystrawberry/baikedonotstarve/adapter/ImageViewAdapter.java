package com.mystrawberry.baikedonotstarve.adapter;

import android.support.annotation.Nullable;

import com.mystrawberry.baikedonotstarve.R;
import com.mystrawberry.baikedonotstarve.bing.ResourcesUtils;
import com.mystrawberry.baikedonotstarve.databinding.ImageViewBinding;

import java.util.List;

/**
 * Created by jk on 2017/11/5.
 */

public class ImageViewAdapter extends BaseDataBindingAdapter<String, ImageViewBinding> {

    public ImageViewAdapter(@Nullable List<String> data) {
        super(R.layout.image_view,data);
    }


    @Override
    protected void convert(ImageViewBinding binding, String item, int position) {

        binding.setDrawableId(ResourcesUtils.getDrawableId(item));
    }

}
