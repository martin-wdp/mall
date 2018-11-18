package com.ningpai.index.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 实体类-分类导航整体封装数据
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年5月6日下午3:42:03
 */
public class IndexClassifyBar {

    /** 分类导航列表 */
    private List<IndexClassifyBarBean> classifyBarList = new ArrayList<IndexClassifyBarBean>();
    /** 分类导航框广告列表 */
    private List<IndexAdvertBean> indexAdvertList = new ArrayList<IndexAdvertBean>();
    /** 分类导航框品牌列表 */
    private List<IndexBrandBean> indexBrandList = new ArrayList<IndexBrandBean>();

    public List<IndexClassifyBarBean> getClassifyBarList() {
        return classifyBarList;
    }

    public void setClassifyBarList(List<IndexClassifyBarBean> classifyBarList) {
        this.classifyBarList = classifyBarList;
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
