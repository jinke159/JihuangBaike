package com.mystrawberry.baikedonotstarve.bing;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

/**
 * Created by jk on 2017/10/12.
 */

public class AttrAdapter {

    @BindingAdapter("android:src")
    public static void setSrc(ImageView view, int resId) {
        view.setImageResource(resId);
    }
}
