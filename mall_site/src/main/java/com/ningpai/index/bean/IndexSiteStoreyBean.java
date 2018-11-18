package com.ningpai.index.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 实体类-楼层
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月29日上午10:43:52
 */
public class IndexSiteStoreyBean {
    /** 楼层编号 */
    private Long channelStoreyId;
    /** 楼层名称 */
    private String storeyName;
    /** 频道ID */
    private Long channelId;
    /** 模板ID */
    private Long tempId;
    /** 楼层层数 */
    private Integer floorId;
    /** 楼层图片地址 */
    private String storeyImg;
    /** 楼层右侧导航图片地址 */
    private String storeyRightImg;
    /** 楼层图片链接地址 */
    private String storeyImgHref;
    /** 楼层SEO */
    private String storeySeo;
    /** 楼层商品分类ID */
    private Long goodsCatId;

    /** 楼层子商品分类 */
    private List<IndexCate> indexCateList = new ArrayList<IndexCate>();
    /** 楼层商品列表 */
    private List<IndexGoodsBean> indexGoodsList = new ArrayList<IndexGoodsBean>();
    /** 楼层广告列表 */
    private List<IndexAdvertBean> indexAdvertList = new ArrayList<IndexAdvertBean>();
    /** 楼层品牌列表 */
    private List<IndexBrandBean> indexBrandList = new ArrayList<IndexBrandBean>();
    /** 楼层标签列表 */
    private List<IndexStoreyTagBean> indexStoreyTagList = new ArrayList<IndexStoreyTagBean>();

    public Long getChannelStoreyId() {
        return channelStoreyId;
    }

    public void setChannelStoreyId(Long channelStoreyId) {
        this.channelStoreyId = channelStoreyId;
    }

    public String getStoreyName() {
        return storeyName;
    }

    public void setStoreyName(String storeyName) {
        this.storeyName = storeyName;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public Long getTempId() {
        return tempId;
    }

    public void setTempId(Long tempId) {
        this.tempId = tempId;
    }

    public Integer getFloorId() {
        return floorId;
    }

    public void setFloorId(Integer floorId) {
        this.floorId = floorId;
    }

    public String getStoreyImg() {
        return storeyImg;
    }

    public void setStoreyImg(String storeyImg) {
        this.storeyImg = storeyImg;
    }

    public String getStoreyImgHref() {
        return storeyImgHref;
    }

    public void setStoreyImgHref(String storeyImgHref) {
        this.storeyImgHref = storeyImgHref;
    }

    public String getStoreySeo() {
        return storeySeo;
    }

    public void setStoreySeo(String storeySeo) {
        this.storeySeo = storeySeo;
    }

    public Long getGoodsCatId() {
        return goodsCatId;
    }

    public void setGoodsCatId(Long goodsCatId) {
        this.goodsCatId = goodsCatId;
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

    public List<IndexStoreyTagBean> getIndexStoreyTagList() {
        return indexStoreyTagList;
    }

    public void setIndexStoreyTagList(List<IndexStoreyTagBean> indexStoreyTagList) {
        this.indexStoreyTagList = indexStoreyTagList;
    }

    public List<IndexCate> getIndexCateList() {
        return indexCateList;
    }

    public void setIndexCateList(List<IndexCate> indexCateList) {
        this.indexCateList = indexCateList;
    }

    public String getStoreyRightImg() {
        return storeyRightImg;
    }

    public void setStoreyRightImg(String storeyRightImg) {
        this.storeyRightImg = storeyRightImg;
    }

}
