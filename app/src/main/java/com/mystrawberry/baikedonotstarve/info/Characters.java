package com.mystrawberry.baikedonotstarve.info;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色 POJO 类
 * Created by jk on 2017/10/18.
 */

public class Characters {
    public ArrayList<CharacterBean> mCharacter;



    public static Characters getCharacters(String json){
        if (!TextUtils.isEmpty(json)){
            Gson gson = new Gson();
            return gson.fromJson(json, Characters.class);
        }
        return null;
    }

    @SuppressWarnings("WeakerAccess")
    public static class CharacterBean extends BaseInfo implements Parcelable{
        /**
         * picture : ???
         * name : 威尔逊
         * Nickname : 绅士科学家
         * enName : Wilson
         * motto : 「我会用思考的力量战胜一切!」
         * Lines : "Do you like science?
         你喜欢科学吗？"
         * ability : ["长出一大把胡须"]
         * isDynamic : false
         * health : 150
         * hunger : 150
         * sanity : 200
         * damage : X1
         * introduce : 介绍
         * isSummoner : false
         * maxHealth : 300
         * maxHunger : 400
         * maxSanity : 300
         */


        public String Nickname;
        public String enName;
        public String motto;
        public String Lines;
        public boolean isDynamic;
        public int health;
        public int hunger;
        public int sanity;
        public String damage;
        public String introduce;
        public boolean isSummoner;
        public int maxHealth;
        public int maxHunger;
        public int maxSanity;
        public List<String> ability;

        protected CharacterBean(Parcel in) {
            super(in);
            Nickname = in.readString();
            enName = in.readString();
            motto = in.readString();
            Lines = in.readString();
            isDynamic = in.readByte() != 0;
            health = in.readInt();
            hunger = in.readInt();
            sanity = in.readInt();
            damage = in.readString();
            introduce = in.readString();
            isSummoner = in.readByte() != 0;
            maxHealth = in.readInt();
            maxHunger = in.readInt();
            maxSanity = in.readInt();
            ability = in.createStringArrayList();
        }

        public static final Creator<CharacterBean> CREATOR = new Creator<CharacterBean>() {
            @Override
            public CharacterBean createFromParcel(Parcel in) {
                return new CharacterBean(in);
            }

            @Override
            public CharacterBean[] newArray(int size) {
                return new CharacterBean[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeString(Nickname);
            dest.writeString(enName);
            dest.writeString(motto);
            dest.writeString(Lines);
            dest.writeByte((byte) (isDynamic ? 1 : 0));
            dest.writeInt(health);
            dest.writeInt(hunger);
            dest.writeInt(sanity);
            dest.writeString(damage);
            dest.writeString(introduce);
            dest.writeByte((byte) (isSummoner ? 1 : 0));
            dest.writeInt(maxHealth);
            dest.writeInt(maxHunger);
            dest.writeInt(maxSanity);
            dest.writeStringList(ability);
        }
    }
}
