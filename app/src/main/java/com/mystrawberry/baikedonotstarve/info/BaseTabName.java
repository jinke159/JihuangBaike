package com.mystrawberry.baikedonotstarve.info;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jk on 2017/11/3.
 */

public class BaseTabName implements Parcelable {
    public  String categoryName;


    public BaseTabName(){}

    protected BaseTabName(Parcel in) {
        categoryName = in.readString();
    }

    public static final Creator<BaseTabName> CREATOR = new Creator<BaseTabName>() {
        @Override
        public BaseTabName createFromParcel(Parcel in) {
            return new BaseTabName(in);
        }

        @Override
        public BaseTabName[] newArray(int size) {
            return new BaseTabName[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(categoryName);
    }
}
