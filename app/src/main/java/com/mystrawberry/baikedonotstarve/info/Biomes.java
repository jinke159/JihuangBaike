package com.mystrawberry.baikedonotstarve.info;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * 生物群落 POJO 类
 * Created by jk on 2017/10/21.
 */

public class Biomes {


    public ArrayList<BiomesListBean> biomesList;

    public static class BiomesListBean extends BaseTabName implements Parcelable {
        /**
         * categoryName : ROG
         * biomesDataList : [{"enName":"Grasslands","introduce":"ROG版本玩家的出生地，此地区资源比较丰富，也没有什么危险，盛产各种基础资源。","abundant":["redbird","flower_10"],"occasional":["flint","carrot","berry_bush_1","evergreen_3","grass","sapling","beehive","butterfly","fireflies_1","pond","rabbit","moleworm_2"],"rare":["walrus_camp_winter","spider_den","red_mushroom"],"picture":"grassland","name":"平原"},{"enName":"Savanna","introduce":"此区域有大量干草，如果草原范围大到一定程度大概率有牛群，因为空旷和牛群的保护以及大量的干草资源是设立基地的优先选择。","abundant":["grass","rabbit","redbird","crow"],"occasional":["beefalo","manure","boulder"],"rare":["flower_5","butterfly","spider_den","crank_thing"],"picture":"savanna","name":"草原"},{"enName":"Forest","introduce":"树多，可能会有墓地，有两种树，一种可再生一种不可以，偶尔会碰见一只猪哥pk一群蜘蛛，探图时一般直接跑过去。","abundant":["evergreen_3","a_lumpy_evergreen","sapling","redbird","crow"],"occasional":["mushrooms","spider_den","pig_house_build","flower_6","headstone_4"],"rare":["berry_bush_1","grass","boulder","boulder_2","sinkhole_2","totally_normal_tree"],"picture":"forest_icon","name":"森林"},{"enName":"Marsh","introduce":"沼泽充满了危险，尤其是触手，在没护甲的情况下被打很伤，这里的生物都是敌对状态经常会大乱斗，但坐收渔翁之利要小心触手怪。","abundant":["tentacle_1","spiky_bush","spiky_tree","crow","green_mushroom","blue_mushroom","reeds"],"occasional":["spider_den","pond","rundown_house_big"],"rare":["evergreen_3","grass"],"picture":"marsh_icon","name":"沼泽"},{"enName":"Mosaic","introduce":"该地形由多种地形混合而成，一般会包裹小块海洋，各种矿物资源比较多，一般情况下一个地图只有一处。","abundant":["rocks","flint","crow"],"occasional":["gold_nugget","evergreen_3","boulder","boulder_2"],"rare":["tallbird_nest","sapling","berry_bush_1","grass"],"picture":"mosaic_icon","name":"马赛克地形"},{"enName":"Road","introduce":"道路、小径遍布整个地图，能使在上面行走的生物移动速度增加30%，在铺着卵石小路的一头有概率连着猪村。","abundant":[],"occasional":[],"rare":[],"picture":"curvy_roads","name":"道路"},{"enName":"Rockyland","introduce":"此地含有丰富的矿物资源，冬天时会有冰矿生成，偶尔会看见大量蜘蛛或者高脚鸟，蜘蛛巢清不掉可以一把火...","abundant":["flint","rocks","crow","boulder_2","boulder"],"occasional":["gold_nugget","spider_den","mini_glacier_1"],"rare":["tallbird_1","tallbird_nest"],"picture":"rockland_icon","name":"矿区"},{"enName":"Graveyard","introduce":"墓地的标志时起雾，墓地上经常散落着许多金块，缺金块时可以来这里挖坟，用给的玩具和猪王换金块。","abundant":["headstone_1","gold_nugget"],"occasional":["evergreen_3","flint","rocks"],"rare":[],"picture":"graveyard_map","name":"墓地"},{"enName":"Chess","introduce":"此地形一般有发条生物守护，可能会有传送零件或者底座，还有各种大理石雕像和大理石树，此地的花也变成了恶魔花。","abundant":["evil_flower_1","marble_trees","marble_pillar"],"occasional":["clockwork_bishop","clockwork_knight","clockwork_rook"],"rare":["wooden_thing_1","maxwell_statue","harp_statue"],"picture":"chess","name":"棋盘地形"},{"enName":"Desert","introduce":"此地型跟矿区很相似都有大量矿物资源，不过比矿区多了仙人掌山羊等特产，因为有仙人掌而适合作为夏季基地。不过有可能会有大量猎狗窝。","abundant":["buzzards","cactus_flower","boulder_3","tumbleweed"],"occasional":["hound_mound","bones","spiky_bush","spiky_tree","grass","boulder"],"rare":["volt_goat"],"picture":"desert_icon","name":"荒漠"},{"enName":"Birchnut Forest","introduce":"这里是物资最丰富的地区，一般会有猪王和猪村在深处生成，盛产浣熊猫、萤火虫、各种蘑菇。也可能会有浆果等资源彩蛋，最重要的是只有这里会出现格鲁姆。","abundant":["birchnut_tree","fireflies_1","mushrooms"],"occasional":["hollow_stump","sapling","berry_bush_1","moleworms","burrow","flower_1"],"rare":["grass","glommers_statue","boulder"],"picture":"deciduous_icon","name":"桦树林"}]
         */

        @SerializedName(value = "biomesDataList")
        public ArrayList<BiomesDataListBean> biomesDataLists;

        public BiomesListBean() {
        }






        /*序列化*/
        protected BiomesListBean(Parcel in) {
            categoryName = in.readString();
            biomesDataLists = in.createTypedArrayList(BiomesDataListBean.CREATOR);
        }

        public static final Creator<BiomesListBean> CREATOR = new Creator<BiomesListBean>() {
            @Override
            public BiomesListBean createFromParcel(Parcel in) {
                return new BiomesListBean(in);
            }

            @Override
            public BiomesListBean[] newArray(int size) {
                return new BiomesListBean[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeTypedList(biomesDataLists);
        }
        /*序列化*/

        public static class BiomesDataListBean extends BaseInfo implements Parcelable {
            /**
             * enName : Grasslands
             * introduce : ROG版本玩家的出生地，此地区资源比较丰富，也没有什么危险，盛产各种基础资源。
             * abundant : ["redbird","flower_10"]
             * occasional : ["flint","carrot","berry_bush_1","evergreen_3","grass","sapling","beehive","butterfly","fireflies_1","pond","rabbit","moleworm_2"]
             * rare : ["walrus_camp_winter","spider_den","red_mushroom"]
             * picture : grassland
             * name : 平原
             */

            public String enName;
            public String introduce;
            public List<String> abundant;
            public List<String> occasional;
            public List<String> rare;

            public BiomesDataListBean() {
            }




            /*序列化*/
            protected BiomesDataListBean(Parcel in) {
                super(in);
                enName = in.readString();
                introduce = in.readString();
                abundant = in.createStringArrayList();
                occasional = in.createStringArrayList();
                rare = in.createStringArrayList();
            }

            public static final Creator<BiomesDataListBean> CREATOR = new Creator<BiomesDataListBean>() {
                @Override
                public BiomesDataListBean createFromParcel(Parcel in) {
                    return new BiomesDataListBean(in);
                }

                @Override
                public BiomesDataListBean[] newArray(int size) {
                    return new BiomesDataListBean[size];
                }
            };

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                super.writeToParcel(dest, flags);
                dest.writeString(enName);
                dest.writeString(introduce);
                dest.writeStringList(abundant);
                dest.writeStringList(occasional);
                dest.writeStringList(rare);
            }

             /*序列化*/
        }
    }
}
