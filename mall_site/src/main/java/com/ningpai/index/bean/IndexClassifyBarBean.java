package com.ningpai.index.bean;

import java.util.ArrayList;
import java.util.List;

import com.ningpai.temp.bean.ClassifyBarCate;
import com.ningpai.temp.bean.ClassifyBarQuick;
import com.ningpai.temp.vo.ClassifyBarVo;

/**
 * VO实体类-分类导航
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年5月6日下午3:42:03
 */
public class IndexClassifyBarBean {
    /** 编号 */
    private Long classifyBarId;
    /** 商品分类ID */
    private Long goodsCatId;
    /** 分类名称 */
    private String name;
    /** 分类链接 */
    private String url;

    /** 分类图片 */
    private String imgSrc;

    /** 层级 */
    private Integer grade;
    /** 排序 */
    private Integer sort;
    /** 父分类 */
    private Long parentId;
    /** 子分类 */
    private List<ClassifyBarVo> childs = new ArrayList<ClassifyBarVo>();
    /** 分类导航广告列表 */
    private List<IndexAdvertBean> indexAdvertList = new ArrayList<IndexAdvertBean>();
    /** 分类导航品牌列表 */
    private List<IndexBrandBean> indexBrandList = new ArrayList<IndexBrandBean>();
    /** 一级分类导航关联的商品分类 */
    private List<ClassifyBarCate> barCate = new ArrayList<ClassifyBarCate>();
    /** 一级分类导航关联的快捷分类 */
    private List<ClassifyBarQuick> barQuick = new ArrayList<ClassifyBarQuick>();
    /** 图标字符串 */
    private String imgString;

    public String getImgString() {
        return imgString;
    }

    public void setImgString(String imgString) {
        this.imgString = imgString;
    }

    public Long getClassifyBarId() {
        return classifyBarId;
    }

    public void setClassifyBarId(Long classifyBarId) {
        this.classifyBarId = classifyBarId;
    }

    public Long getGoodsCatId() {
        return goodsCatId;
    }

    public void setGoodsCatId(Long goodsCatId) {
        this.goodsCatId = goodsCatId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<ClassifyBarVo> getChilds() {
        return childs;
    }

    public void setChilds(List<ClassifyBarVo> childs) {
        this.childs = childs;
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

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public List<ClassifyBarCate> getBarCate() {
        return barCate;
    }

    public void setBarCate(List<ClassifyBarCate> barCate) {
        this.barCate = barCate;
    }

    public List<ClassifyBarQuick> getBarQuick() {
        return barQuick;
    }

    public void setBarQuick(List<ClassifyBarQuick> barQuick) {
        this.barQuick = barQuick;
    }

}
