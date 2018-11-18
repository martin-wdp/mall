package com.ningpai.site.thirdseller.bean;

/**
 * 楼层-广告实体
 * @author qiyuanyuan
 *
 */
public class ThirdIndexStoreyAdverBean {
     /** 频道广告ID */
    private Long channelAdverId;
    /** 广告标题 */
    private String adverName;
    /** 广告图片路径 */
    private String adverPath;
    /** 广告链接地址 */
    private String adverHref;
    /** 广告排序 */
    private Integer adverSort;
    /** 广告描述 */
    private String des;
    /** 副标题 */
    private String temp2;
    public Long getChannelAdverId() {
        return channelAdverId;
    }
    public void setChannelAdverId(Long channelAdverId) {
        this.channelAdverId = channelAdverId;
    }
    public String getAdverName() {
        return adverName;
    }
    public void setAdverName(String adverName) {
        this.adverName = adverName;
    }
    public String getAdverPath() {
        return adverPath;
    }
    public void setAdverPath(String adverPath) {
        this.adverPath = adverPath;
    }
    public String getAdverHref() {
        return adverHref;
    }
    public void setAdverHref(String adverHref) {
        this.adverHref = adverHref;
    }
    public Integer getAdverSort() {
        return adverSort;
    }
    public void setAdverSort(Integer adverSort) {
        this.adverSort = adverSort;
    }
    public String getDes() {
        return des;
    }
    public void setDes(String des) {
        this.des = des;
    }
    public String getTemp2() {
        return temp2;
    }
    public void setTemp2(String temp2) {
        this.temp2 = temp2;
    }
}
