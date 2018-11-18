package com.ningpai.goods.vo;

/**
 * <p>
 * 品牌VO
 * </p>
 * 
 * @author liangck
 * @version 1.0
 * @since 15/8/18 15:28
 */
public class BrandVo {

    /**
     * 品牌ID
     */
    private Long brandId;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 构造
     */
    public BrandVo() {
    }

    /**
     * 构造
     * @param brandName
     */
    public BrandVo(String brandName) {
        this.brandName = brandName;
    }

    /**构造
     * @param brandId
     * @param brandName
     */
    public BrandVo(Long brandId, String brandName) {
        this.brandId = brandId;
        this.brandName = brandName;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
