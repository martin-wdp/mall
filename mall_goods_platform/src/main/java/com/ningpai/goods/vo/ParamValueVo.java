package com.ningpai.goods.vo;

/**
 * <p>
 * 扩展参数属性值VO
 * </p>
 * 
 * @author liangck
 * @version 1.0
 * @since 15/8/18 15:34
 */
public class ParamValueVo {

    /**
     * 扩展属性值ID
     */
    private Long valueId;

    /**
     * 扩展属性值名称
     */
    private String valueName;

    /**
     * 构造
     */
    public ParamValueVo() {
    }

    /**
     * 构造
     * @param valueName
     */
    public ParamValueVo(String valueName) {
        this.valueName = valueName;
    }

    /**
     * 构造
     * @param valueId
     * @param valueName
     */
    public ParamValueVo(Long valueId, String valueName) {
        this.valueId = valueId;
        this.valueName = valueName;
    }

    public Long getValueId() {
        return valueId;
    }

    public void setValueId(Long valueId) {
        this.valueId = valueId;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }
}
