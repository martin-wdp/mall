package com.ningpai.goods.bean;

import java.math.BigDecimal;

/**
 * 货品根据仓库分别维护价格和库存
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年4月28日 下午7:33:52
 * @version 1.0
 */
public class ProductWare {
    /*
     * 主键ID
     */
    private Long id;
    /*
     * 货品ID
     */
    private Long productId;
    /*
     * 仓库ID
     */
    private Long wareId;
    /*
     * 库存
     */
    private Long wareStock;
    /*
     * 价格
     */
    private BigDecimal warePrice;
    /*
     * 会员价格
     * 2015.10.22  wuyanbo 添加
     */
    private BigDecimal wareVipPrice;
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

    public Long getWareId() {
        return wareId;
    }

    public void setWareId(Long wareId) {
        this.wareId = wareId;
    }

    public Long getWareStock() {
        return wareStock;
    }

    public void setWareStock(Long wareStock) {
        this.wareStock = wareStock;
    }

    public BigDecimal getWarePrice() {
        return warePrice;
    }

    public void setWarePrice(BigDecimal warePrice) {
        this.warePrice = warePrice;
    }

    public BigDecimal getWareVipPrice() {
        return wareVipPrice;
    }

    public void setWareVipPrice(BigDecimal wareVipPrice) {
        this.wareVipPrice = wareVipPrice;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
