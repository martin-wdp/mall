package com.ningpai.goods.util;

/**
 * 计算库存的辅助Bean
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年7月23日 上午9:44:25
 * @version 1.0
 */
public class CalcStockUtil {
    /*
    *货品ID
     */
    private Long productId;
    /*
     *第三方ID
      */
    private String isThird;
    /*
     *需要计算的库存
      */
    private Integer stock;
    /*
     *地区ID
      */
    private Long distinctId;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getIsThird() {
        return isThird;
    }

    public void setIsThird(String isThird) {
        this.isThird = isThird;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Long getDistinctId() {
        return distinctId;
    }

    public void setDistinctId(Long distinctId) {
        this.distinctId = distinctId;
    }

}
