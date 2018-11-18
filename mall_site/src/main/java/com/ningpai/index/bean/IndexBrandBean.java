package com.ningpai.index.bean;

/**
 * 实体类-品牌
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月25日上午9:32:35
 */
public class IndexBrandBean {
    /** 频道品牌编号 */
    private Long trademarkId;
    /** 品牌编号 */
    private String brandId;
    /** 品牌logo地址 */
    private String logoSrc;
    /** 品牌名称 */
    private String trademarkName;
    /** 品牌链接 */
    private String url;
    /** 显示类型 0 文字；1 图片 */
    private String showType;
    /** 品牌标题 */
    private String title;
    /** 描述 */
    private String des;
    /** 排序 */
    private Short sort;

    public Long getTrademarkId() {
        return trademarkId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTrademarkId(Long trademarkId) {
        this.trademarkId = trademarkId;
    }

    public String getLogoSrc() {
        return logoSrc;
    }

    public void setLogoSrc(String logoSrc) {
        this.logoSrc = logoSrc;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Short getSort() {
        return sort;
    }

    public void setSort(Short sort) {
        this.sort = sort;
    }

    public String getTrademarkName() {
        return trademarkName;
    }

    public void setTrademarkName(String trademarkName) {
        this.trademarkName = trademarkName;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

}
