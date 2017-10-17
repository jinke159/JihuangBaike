package com.mystrawberry.baikedonotstarve.bing;

import android.content.Context;
import android.content.res.ColorStateList;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.Log;
import android.widget.ImageView;

import com.mystrawberry.baikedonotstarve.R;

import java.lang.reflect.Field;

/**
 * 资源工具类
 * Created by jk on 2017/10/12.
 */

public class ResourcesUtils {

    @BindingAdapter("android:src")
    public void setSrc(ImageView view, int resId, int i) {

        view.setImageResource(resId);
    }

    public static Drawable tintDrawable(Drawable drawable, ColorStateList colors) {
        //DrawableCompat是v4支持包的类 可以改变图片颜色,
        //也可以设置颜色选择器
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(wrappedDrawable, colors);
        return wrappedDrawable;


    }

    /**
     *
     * @param drawableId 需要添加状态的图像id
     * @return 带默认颜色状态选择器的图像
     */
    public static Drawable getStateDrawable(Context context, int drawableId) {

        return getStateDrawable(context, getDrawable(context, drawableId));

    }

    /**
     *
     * @param drawable 需要添加状态的图像对象
     * @return 带默认颜色状态选择器的图像
     */
    public static Drawable getStateDrawable(Context c, Drawable drawable) {
        return getStateDrawable(c, drawable, R.color.rv_main_item_text_selector);
    }

    /**
     *
     * @param drawable 需要添加状态的图像对象
     * @param ColorStateListId 需要添加的颜色状态选择器ID
     * @return 带颜色状态选择器的图像
     */
    public static Drawable getStateDrawable(Context c, Drawable drawable, int ColorStateListId) {

        return tintDrawable(drawable, getColorStateList(c, ColorStateListId));
    }

    /**
     *
     * @param drawableId 需要添加状态的图像ID
     * @param colorStateList 需要添加的颜色状态选择器
     * @return 带颜色状态选择器的图像
     */
    public static Drawable getStateDrawable(Context context, int drawableId, ColorStateList colorStateList) {

        Drawable drawable = getDrawable(context, drawableId);

        return tintDrawable(drawable, colorStateList);

    }

    /**
     *
     * @param drawableId 需要添加状态的图像ID
     * @param ColorStateListId 需要添加的颜色状态选择器ID
     * @return 带颜色状态选择器的图像
     */
    public static Drawable getStateDrawable(Context context, int drawableId, int ColorStateListId) {


        ColorStateList colorStateList = getColorStateList(context, ColorStateListId);

        return getStateDrawable(context,drawableId, colorStateList);

    }

    /**
     *
     * @param drawableId 图像ID
     * @return Drawable
     */
    public static Drawable getDrawable(Context context, int drawableId) {
        return context.getResources().getDrawable(drawableId);
    }

    /**
     *
     * @param colorId 颜色id
     *
     * @return 颜色状态选择器
     */
    public static ColorStateList getColorStateList(Context context, int colorId) {
        return context.getResources().getColorStateList(colorId);
    }

    /**
     * 通过名字获取 drawable的Id,比系统的方法略快一丢丢
     * @param drawableName 获取drawableId的名字
     * @return R.drawable.drawableName
     */
    public static int getDrawableId(String drawableName) {

            try {

                Class res = R.drawable.class;
                Field field = res.getField(drawableName);

                return field.getInt(null);
//                int drawableId = field.getInt(null);
//              return drawableId;
            } catch (Exception e) {
                Log.e("MyTag", "Failure to get drawable id.", e);
                return 0;
            }


    }
}
