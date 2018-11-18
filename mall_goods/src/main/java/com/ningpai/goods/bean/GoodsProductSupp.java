package com.ningpai.goods.bean;

/**
 * 货品关联服务支持
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年7月31日 上午9:46:47
 * @version 1.0
 */
public class GoodsProductSupp {
    /*
     * 主键ID
     */
    private Long id;
    /*
     * 货品ID
     */
    private Long productId;
    /*
     * 支持ID
     */
    private Long suppId;
    /*
     * 删除标记
     */
    private String delFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getSuppId() {
        return suppId;
    }

    public void setSuppId(Long suppId) {
        this.suppId = suppId;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
