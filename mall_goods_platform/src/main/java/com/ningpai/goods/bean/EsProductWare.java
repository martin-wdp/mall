package com.ningpai.goods.bean;

import com.ningpai.searchplatform.annotation.ESDocObject;
import com.ningpai.searchplatform.annotation.ESField;
import com.ningpai.searchplatform.bean.IESMappingType;
import com.ningpai.searchplatform.enumeration.ESFieldType;

import java.lang.reflect.Field;
import java.math.BigDecimal;

/**
 * 货品库存信息
 * 
 * @author ggn
 *
 */
@ESDocObject
public class EsProductWare implements IESMappingType {
    /**
     * 库存ID
     */
    @ESField(fieldType = ESFieldType.LONG)
    private Long id;
    /**
     * 货品ID
     */
    @ESField(fieldType = ESFieldType.LONG)
    private Long productId;
    /**
     * 仓库ＩＤ
     */
    @ESField(fieldType = ESFieldType.LONG)
    private Long wareId;
    /**
     * 库存数量
     */
    @ESField(fieldType = ESFieldType.LONG)
    private Long wareStock;
    /**
     * 库存价格
     */
    @ESField(fieldType = ESFieldType.DOUBLE)
    private BigDecimal warePrice;
    /**
     * 库存会员价格
     * 2015.10.22 wuyanbo 添加
     */
    @ESField(fieldType = ESFieldType.DOUBLE)
    private BigDecimal wareVipPrice;
    /**
     * 是否删除
     */
    @ESField()
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

    /**
     * 返回该文档对象进行索引的field, 用于反射调用生成MapType
     *
     * @return {@link Field}
     */
    @Override
    public Field[] foundTypeField() {
        return this.getClass().getDeclaredFields();
    }

    /**
     * 文档ID
     * 
     * @see com.ningpai.searchplatform.bean.IESMappingType#generateDocId()
     */
    @Override
    public String generateDocId() {
        return this.getId() == null ? "" : this.getId().toString();
    }
}