package com.ningpai.site.thirdseller.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 楼层实体类
 * @author qiyuanyuan
 *
 */
public class ThirdIndexStoreyBean {
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
    /** 楼层图片链接地址 */
    private String storeyImgHref;
    /** 楼层SEO */
    private String storeySeo;
    /** 楼层商品分类ID */
    private Long goodsCatId;
    
    /** 楼层商品 */
    private List<ThirdIndexStoreyGoodsBean> indexGoodsList = new ArrayList<ThirdIndexStoreyGoodsBean>();
    /** 楼层广告 */
    private List<ThirdIndexStoreyAdverBean> indexAdverList = new ArrayList<ThirdIndexStoreyAdverBean>();
    /**楼层标签*/
    private List<ThirdIndexStoreyTagBean> indexStoreyTagBeanList = new ArrayList<ThirdIndexStoreyTagBean>();
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
    public List<ThirdIndexStoreyTagBean> getIndexStoreyTagBeanList() {
        return indexStoreyTagBeanList;
    }
    public void setIndexStoreyTagBeanList(
            List<ThirdIndexStoreyTagBean> indexStoreyTagBeanList) {
        this.indexStoreyTagBeanList = indexStoreyTagBeanList;
    }
}
