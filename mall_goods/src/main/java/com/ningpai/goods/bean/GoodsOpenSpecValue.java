package com.ningpai.goods.bean;

import javax.validation.constraints.NotNull;

/**
 * 商品开启规格值
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年6月30日 下午5:34:33
 * @version 1.0
 */
public class GoodsOpenSpecValue {
    /*
     * 主键ID
     */
    private Long id;
    /*
     * 商品ID
     */
    private Long goodsId;
    /*
     * 规格ID
     */
    private Long specId;
    /*
     * 规格值ID
     */
    @NotNull
    private Long specValueId;
    /*
     * 删除标记
     */
    private String delFlag;
    /*
     * 图片地址
     */
    private String imgUrl;
    /*
     * 用户自定义的规格值
     */
    private String specValueRemark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getSpecId() {
        return specId;
    }

    public void setSpecId(Long specId) {
        this.specId = specId;
    }

    public Long getSpecValueId() {
        return specValueId;
    }

    public void setSpecValueId(Long specValueId) {
        this.specValueId = specValueId;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getSpecValueRemark() {
        return specValueRemark;
    }

    public void setSpecValueRemark(String specValueRemark) {
        this.specValueRemark = specValueRemark;
    }

}
