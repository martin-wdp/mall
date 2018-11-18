package com.ningpai.system.bean;

/**
 * 系统设置实体类
 * 
 * @author jiping
 * @since 2015年8月21日 下午7:10:56
 * @version
 */
public class SystemsSet {

    /**
     * 设置Id
     */
    private Long setId;
    /**
     * 是否允许退单设置
     */
    private Long isBackOrder;
    /**
     * 系统更新未付款订单时间
     */
    private Long bsetOrderTime;
    /**
     * 退货说明
     */
    private String backInfoRemark;
    /**
     * 退款说明
     */
    private String backPriceRemark;
    /**
     * 订单自动退货设置
     */
    private Long receiptTime;

    /**
     * 无参构造
     */
    public SystemsSet() {
    }

    /**
     * 有参构造
     *
     * @param setId
     * @param isBackOrder
     */
    public SystemsSet(Long setId, Long isBackOrder) {
        this.setId = setId;
        this.isBackOrder = isBackOrder;
    }
    /**
     * 获取BackPriceRemark
     * */
    public String getBackPriceRemark(){
        return this.backPriceRemark;
    }
    /**
     * 设置BackPriceRemark
     * */
    public void setBackPriceRemark(String backPriceRemark){
        this.backPriceRemark=backPriceRemark;
    }
    /**
     * 获取BackInfoRemark
     * */
    public String getBackInfoRemark() {
        return backInfoRemark;
    }
    /**
     * 设置BackInfoRemark
     * */
    public void setBackInfoRemark(String backInfoRemark) {
        this.backInfoRemark = backInfoRemark;
    }
    /**
     * 时间
     * @return
     */
    public Long getBsetOrderTime() {
        return bsetOrderTime;
    }
    /**
     * 时间
     * @return
     */
    public void setBsetOrderTime(Long bsetOrderTime) {
        this.bsetOrderTime = bsetOrderTime;
    }
    /**
     * 时间
     * @return
     */
    public Long getReceiptTime() {
        return receiptTime;
    }

    /**
     * 时间
     * 
     * @return
     */
    public void setReceiptTime(Long receiptTime) {
        this.receiptTime = receiptTime;
    }
    /**
     * 获取SetId
     * */
    public Long getSetId() {
        return setId;
    }
    /**
     * 设置SetId
     * */
    public void setSetId(Long setId) {
        this.setId = setId;
    }
    /**
     * 获取IsBackOrder
     * */
    public Long getIsBackOrder() {
        return isBackOrder;
    }
    /**
     * 设置IsBackOrder
     * */
    public void setIsBackOrder(Long isBackOrder) {
        this.isBackOrder = isBackOrder;
    }

}
