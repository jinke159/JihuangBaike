package com.mystrawberry.baikedonotstarve.bing;

import android.content.Context;
import android.content.res.ColorStateList;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.widget.ImageView;

/**
 * Created by jk on 2017/10/12.
 */

public class AttrAdapter {

    @BindingAdapter("android:src")
    public void setSrc(ImageView view, int resId,int i) {

        view.setImageResource(resId);
    }

    public static Drawable tintDrawable(Drawable drawable, ColorStateList colors) {
        //DrawableCompat是v4支持包的类 可以改变图片颜色,
        //也可以设置颜色选择器
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(wrappedDrawable, colors);
        return wrappedDrawable;


    }

    public static Drawable getDrawable(Context context, int drawableId, ColorStateList state) {

        Drawable drawable = getDrawable(context, drawableId);

        return tintDrawable(drawable, state);

    }

    private static Drawable getDrawable(Context context, int drawableId) {
        return context.getResources().getDrawable(drawableId);
    }

    private static ColorStateList getColorStateList(Context context, int colorId) {
        return context.getResources().getColorStateList(colorId);
    }

}
