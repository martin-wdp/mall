package com.ningpai.goods.bean;

/**
 * 商品组合关联货品实体
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月31日 上午11:54:46
 * @version 1.0
 */
public class GoodsGroupReleProduct {
    /*
     * 主键ID
     */
    private Long releProductid;
    /*
     * 组合ID
     */
    private Long groupId;
    /*
     * 货品ID
     */
    private Long productId;
    /*
     * 删除标记
     */
    private String releProductDelflag;

    public Long getReleProductid() {
        return releProductid;
    }

    public void setReleProductid(Long releProductid) {
        this.releProductid = releProductid;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getReleProductDelflag() {
        return releProductDelflag;
    }

    public void setReleProductDelflag(String releProductDelflag) {
        this.releProductDelflag = releProductDelflag;
    }
}
