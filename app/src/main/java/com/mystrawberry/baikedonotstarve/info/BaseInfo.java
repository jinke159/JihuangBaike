package com.mystrawberry.baikedonotstarve.info;

import android.os.Parcel;
import android.os.Parcelable;

import com.mystrawberry.baikedonotstarve.bing.ResourcesUtils;

/**
 * 信息的基类
 * 包括名字,图片id,
 * Created by jk on 2017/10/20.
 */

@SuppressWarnings("WeakerAccess")
public class BaseInfo implements Parcelable{

    public String picture;
    public String name;
    private int drawableRId;

    public int getDrawableRId() {
        if (drawableRId == 0) {
            drawableRId = ResourcesUtils.getDrawableId(picture);
        }
        return drawableRId;
    }

    public BaseInfo(){}


    protected BaseInfo(Parcel in) {
        picture = in.readString();
        name = in.readString();
        drawableRId = in.readInt();
    }

    public static final Creator<BaseInfo> CREATOR = new Creator<BaseInfo>() {
        @Override
        public BaseInfo createFromParcel(Parcel in) {
            return new BaseInfo(in);
        }

        @Override
        public BaseInfo[] newArray(int size) {
            return new BaseInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(picture);
        dest.writeString(name);
        dest.writeInt(drawableRId);
    }
}


