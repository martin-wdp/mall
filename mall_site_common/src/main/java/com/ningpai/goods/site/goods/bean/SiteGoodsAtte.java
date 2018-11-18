package com.ningpai.goods.site.goods.bean;

import java.util.Date;

/**  *
 * 商品关注实体Bean
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年3月18日 下午3:58:46
 * @version 1.0
 */
public class SiteGoodsAtte {
    /**  
     *主键ID
     */
    private Long atteId;

    /**  
     *会员ID
     */
    private Long custId;

    /**  
     *货品ID
     */
    private Long productId;

    /**  
     *创建时间
     */
    private Date createTime;

    /**  
     *修改时间
     */
    private Date modifyTime;

    private String flag;

    public Long getAtteId() {
        return atteId;
    }

    public void setAtteId(Long atteId) {
        this.atteId = atteId;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**  
     *创建时间
     */
    public Date getCreateTime() {
        if (this.createTime != null) {
            return new Date(this.createTime.getTime());
        }
        return null;
    }

    /**  
     *创建时间
     */
    public void setCreateTime(Date createTime) {
        if (createTime != null) {
            Date tEmp = (Date) createTime.clone();
            if (tEmp != null) {
                this.createTime = tEmp;
            }
        }
    }

    /**  
     *修改时间
     */
    public Date getModifyTime() {
        if (this.modifyTime != null) {
            return new Date(this.modifyTime.getTime());
        }
        return null;
    }

    /**  
     *修改时间
     */
    public void setModifyTime(Date modifyTime) {
        if (modifyTime != null) {
            Date tEmp = (Date) modifyTime.clone();
            if (tEmp != null) {
                this.modifyTime = tEmp;
            }
        }
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}