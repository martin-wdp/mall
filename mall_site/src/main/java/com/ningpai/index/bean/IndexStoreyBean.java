package com.ningpai.index.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 楼层类
 * @author AthrunNatu
 * 
 * @since 2014年4月10日上午10:35:45
 */
public class IndexStoreyBean {

    /**
     * 楼层编号
     */
    private String storeyCateId;
    /**
     * 楼层名称
     */
    private String storeyName;
    /**
     * 楼层数
     */
    private String storeyNum;
    /**
     * 广告
     */
    private List<Abs> abs = new ArrayList<Abs>();
    /**
     * 显示商品
     */
    private List<IndexGoodsBean> indexGoodList = new ArrayList<IndexGoodsBean>();
    /**
     * 显示商品
     */
    private List<IndexGoodsBean> indexGoodListRecommend = new ArrayList<IndexGoodsBean>();
    /**
     * 商品分类
     */
    private List<IndexCate> indexCateList = new ArrayList<IndexCate>();

    public List<IndexCate> getIndexCateList() {
        return indexCateList;
    }

    public void setIndexCateList(List<IndexCate> indexCateList) {
        this.indexCateList = indexCateList;
    }

    public List<IndexGoodsBean> getIndexGoodList() {
        return indexGoodList;
    }

    public void setIndexGoodList(List<IndexGoodsBean> indexGoodList) {
        this.indexGoodList = indexGoodList;
    }

    public String getStoreyName() {
        return storeyName;
    }

    public void setStoreyName(String storeyName) {
        this.storeyName = storeyName;
    }

    public String getStoreyNum() {
        return storeyNum;
    }

    public void setStoreyNum(String storeyNum) {
        this.storeyNum = storeyNum;
    }

    public List<IndexGoodsBean> getIndexGoodListRecommend() {
        return indexGoodListRecommend;
    }

    public void setIndexGoodListRecommend(List<IndexGoodsBean> indexGoodListRecommend) {
        this.indexGoodListRecommend = indexGoodListRecommend;
    }

    public String getStoreyCateId() {
        return storeyCateId;
    }

    public void setStoreyCateId(String storeyCateId) {
        this.storeyCateId = storeyCateId;
    }

    public List<Abs> getAbs() {
        return abs;
    }

    public void setAbs(List<Abs> abs) {
        this.abs = abs;
    }

}
