package com.ningpai.goods.bean;

/**
 * 货品信息关联规格
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月27日 下午3:39:07
 * @version 1.0
 */
public class GoodsProductReleSpec {
    /*
     * 主键ID
     */
    private Long releSpecDetailId;
    /*
     * 货品信息ID
     */
    private Long goodsInfoId;
    /*
     * 规格值ID
     */
    private Long specDetailId;
    /*
     * 规格ID
     */
    private Long specId;
    /*
     * 用户自定义的规格值
     */
    private String specValueRemark;

    public Long getReleSpecDetailId() {
        return releSpecDetailId;
    }

    public void setReleSpecDetailId(Long releSpecDetailId) {
        this.releSpecDetailId = releSpecDetailId;
    }

    public Long getGoodsInfoId() {
        return goodsInfoId;
    }

    public void setGoodsInfoId(Long goodsInfoId) {
        this.goodsInfoId = goodsInfoId;
    }

    public Long getSpecDetailId() {
        return specDetailId;
    }

    public void setSpecDetailId(Long specDetailId) {
        this.specDetailId = specDetailId;
    }

    public Long getSpecId() {
        return specId;
    }

    public void setSpecId(Long specId) {
        this.specId = specId;
    }

    public String getSpecValueRemark() {
        return specValueRemark;
    }

    public void setSpecValueRemark(String specValueRemark) {
        this.specValueRemark = specValueRemark;
    }

}
