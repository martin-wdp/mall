package com.ningpai.site.thirdseller.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 楼层
 * @author qiyuanyuan
 *
 */
public class ThirdIndexFloor {

    List<ThirdIndexStoreyBean> floorList = new ArrayList<ThirdIndexStoreyBean>();

    /**
     * 获取楼层集合
     * @return
     */
    public List<ThirdIndexStoreyBean> getFloorList() {
        return floorList;
    }

    /**
     * 设置楼层集合
     * @param floorList
     */
    public void setFloorList(List<ThirdIndexStoreyBean> floorList) {
        this.floorList = floorList;
    }
}
