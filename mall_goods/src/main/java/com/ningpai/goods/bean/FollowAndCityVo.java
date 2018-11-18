package com.ningpai.goods.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 收藏商品Vo
 * */
public class FollowAndCityVo {
    /*
     * 主键ID
     */
    private Long id;
    /*
     * 仓库ID
     */
    private Long wareId;
    /*
     * 区县ID
     */
    private Long cityId;
    /*
     * 删除标记
     */
    private String delFlag;
    /*
     * 主键ID
     */
    private Long atteId;
    /*
     * 会员ID
     */
    private Long custId;
    /*
     * 货品ID
     */
    private Long productId;
    /*
     * 创建时间
     */
    private Date createTime;
    /*
     * 修改时间
     */
    private Date modifyTime;
    /*
     * 删除标记
     */
    private String flag;
    /*
     * 收藏商品对应的地区
     */
    private Long districtId;
    /*
     * 收藏商品的价格
     */
    private BigDecimal followPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWareId() {
        return wareId;
    }

    public void setWareId(Long wareId) {
        this.wareId = wareId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Long getAtteId() {
        return atteId;
    }

    public void setAtteId(Long atteId) {
        this.atteId = atteId;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public BigDecimal getFollowPrice() {
        return followPrice;
    }

    public void setFollowPrice(BigDecimal followPrice) {
        this.followPrice = followPrice;
    }

}
