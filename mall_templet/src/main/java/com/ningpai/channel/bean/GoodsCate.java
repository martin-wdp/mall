package com.ningpai.channel.bean;

/**
 * 商品分类实体类
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月17日 下午4:23:42
 * @version 1.0
 */
public class GoodsCate {
    /*
     * 分类ID
     */
    private Long catId;
    /*
     * 分类名称
     */
    private String catName;
    /*
     * 父级分类ID
     */
    private Long catParentId;
    /*
     * 是否显示
     */
    private String catIsShow;
    /*
     * 删除标记
     */
    private String catDelflag;

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public Long getCatParentId() {
        return catParentId;
    }

    public void setCatParentId(Long catParentId) {
        this.catParentId = catParentId;
    }

    public String getCatIsShow() {
        return catIsShow;
    }

    public void setCatIsShow(String catIsShow) {
        this.catIsShow = catIsShow;
    }

    public String getCatDelflag() {
        return catDelflag;
    }

    public void setCatDelflag(String catDelflag) {
        this.catDelflag = catDelflag;
    }

}
