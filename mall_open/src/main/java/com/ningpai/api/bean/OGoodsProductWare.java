package com.ningpai.api.bean;

/**
 * 商品库存
 * @author lih
 * @version 2.0
 * @since 15/9/7
 */
public class OGoodsProductWare {

    /**
     * 仓库名称
     */
    private String wareName;

    /**
     * 仓库价格
     */
    private String warePrice;

    /**
     * 货品id
     */
    private Long productId;

    /**
     * 货品库存
     */
    private Long waretock;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 仓库名称
     */
    public String getWareName() {
        return wareName;
    }
    /**
     * 设置仓库名称
     * */
    public OGoodsProductWare setWareName(String wareName) {
        this.wareName = wareName;
        return this;
    }

    /**
     * 仓库价格
     */
    public String getWarePrice() {
        return warePrice;
    }
    /**
     * 设置仓库价格
     * */
    public OGoodsProductWare setWarePrice(String warePrice) {
        this.warePrice = warePrice;
        return this;
    }

    /**
     * 货品id
     */
    public Long getProductId() {
        return productId;
    }
    /**
     * 设置货品id
     * */
    public OGoodsProductWare setProductId(Long productId) {
        this.productId = productId;
        return this;
    }

    /**
     * 货品库存
     */
    public Long getWaretock() {
        return waretock;
    }
    /**
     * 设置仓库库存
     * */
    public OGoodsProductWare setWaretock(Long waretock) {
        this.waretock = waretock;
        return this;
    }

    /**
     * 主键id
     */
    public Long getId() {
        return id;
    }
    /**
     * 设置Id
     * */
    public OGoodsProductWare setId(Long id) {
        this.id = id;
        return this;
    }
}
