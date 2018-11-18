package com.ningpai.system.bean;





/**
 * 库存报警表
 * @author xiaogu
 *
 */
public class StockWarning {
    /*
     * 库存报警ID
     */
    private Long swId;
    /*
     * 设置的库存报警下限值
     */
    private Long swValue;
    

    public Long getSwId() {
        return swId;
    }

    public void setSwId(Long swId) {
        this.swId = swId;
    }

    public Long getSwValue() {
        return swValue;
    }

    public void setSwValue(Long swValue) {
        this.swValue = swValue;
    }
    
    

}
