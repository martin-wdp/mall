package com.ningpai.goods.bean;

import java.util.Date;

/**
 * 类型关联商品品牌实体类
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月21日 上午9:41:19
 * @version 1.0
 */
public class GoodsTypeBrand {
    /*
     * 主键ID
     */
    private Long typeBrandId;
    /*
     * 商品类型的主键ID
     */
    private Long typeId;
    /*
     * 商品品牌ID
     */
    private Long brandId;
    /*
     * 删除标记 0：未删除 1：已删除 默认0
     */
    private String delflag;
    /*
     * 删除人名称
     */
    private String delName;
    /*
     * 删除时间 默认为删除时的当前时间
     */
    private Date delTime;

    public Long getTypeBrandId() {
        return typeBrandId;
    }

    public void setTypeBrandId(Long typeBrandId) {
        this.typeBrandId = typeBrandId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getDelflag() {
        return delflag;
    }

    public void setDelflag(String delflag) {
        this.delflag = delflag;
    }

    public String getDelName() {
        return delName;
    }

    public void setDelName(String delName) {
        this.delName = delName;
    }
    /**
     * 获取删除时间
     * */
    public Date getDelTime() {
        if (this.delTime != null) {
            return new Date(this.delTime.getTime());
        }
        return null;
    }
    /**
     * 设置删除时间
     * */
    public void setDelTime(Date delTime) {
        if (delTime != null) {
            Date tEmp = (Date) delTime.clone();
            if (tEmp != null) {
                this.delTime = tEmp;
            }
        }
    }
}
