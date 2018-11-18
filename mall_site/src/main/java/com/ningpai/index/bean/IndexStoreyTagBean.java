package com.ningpai.index.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 实体类-楼层标签
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月12日下午3:10:42
 */
public class IndexStoreyTagBean {
    /** 编号 */
    private Long channelStoreyTagId;
    /** 标签名称 */
    private String name;
    /** 排序 */
    private Long sort;

    /** 楼层商品列表 */
    private List<IndexGoodsBean> indexGoodsList = new ArrayList<IndexGoodsBean>();
    /** 楼层广告列表 */
    private List<IndexAdvertBean> indexAdvertList = new ArrayList<IndexAdvertBean>();
    /** 楼层品牌列表 */
    private List<IndexBrandBean> indexBrandList = new ArrayList<IndexBrandBean>();

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

    public List<IndexGoodsBean> getIndexGoodsList() {
        return indexGoodsList;
    }

    public void setIndexGoodsList(List<IndexGoodsBean> indexGoodsList) {
        this.indexGoodsList = indexGoodsList;
    }

    public List<IndexAdvertBean> getIndexAdvertList() {
        return indexAdvertList;
    }

    public void setIndexAdvertList(List<IndexAdvertBean> indexAdvertList) {
        this.indexAdvertList = indexAdvertList;
    }

    public List<IndexBrandBean> getIndexBrandList() {
        return indexBrandList;
    }

    public void setIndexBrandList(List<IndexBrandBean> indexBrandList) {
        this.indexBrandList = indexBrandList;
    }

}
