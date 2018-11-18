package com.ningpai.site.thirdseller.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 楼层-标签实体
 * @author qiyuanyuan
 *
 */
public class ThirdIndexStoreyTagBean {

    /** 编号 */
    private Long channelStoreyTagId;
    /** 标签名称 */
    private String name;
    /** 排序 */
    private Long sort;
    /** 标签商品 */
    private List<ThirdIndexStoreyGoodsBean> indexGoodsList = new ArrayList<ThirdIndexStoreyGoodsBean>();
    /** 标签广告 */
    private List<ThirdIndexStoreyAdverBean> indexAdverList = new ArrayList<ThirdIndexStoreyAdverBean>();

    public Long getChannelStoreyTagId() {
        return channelStoreyTagId;
    }

    public void setChannelStoreyTagId(Long channelStoreyTagId) {
        this.channelStoreyTagId = channelStoreyTagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public List<ThirdIndexStoreyGoodsBean> getIndexGoodsList() {
        return indexGoodsList;
    }

    public void setIndexGoodsList(List<ThirdIndexStoreyGoodsBean> indexGoodsList) {
        this.indexGoodsList = indexGoodsList;
    }

    public List<ThirdIndexStoreyAdverBean> getIndexAdverList() {
        return indexAdverList;
    }

    public void setIndexAdverList(List<ThirdIndexStoreyAdverBean> indexAdverList) {
        this.indexAdverList = indexAdverList;
    }

}
