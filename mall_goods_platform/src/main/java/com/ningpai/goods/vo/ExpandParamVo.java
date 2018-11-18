package com.ningpai.goods.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 扩展属性VO, 非线程安全的
 * </p>
 * 
 * @author liangck
 * @version 1.0
 * @since 15/8/18 15:31
 */
public class ExpandParamVo {

    /**
     * 扩展属性名 的ID
     */
    private Long paramId;

    /**
     * 扩展属性名
     */
    private String paramName;

    /**
     * 属性值集合
     */
    private List<ParamValueVo> valueVoList;

    /**
     * 构造
     */
    public ExpandParamVo() {
    }

    /**
     * 构造
     * @param paramName
     */
    public ExpandParamVo(String paramName) {
        this.paramName = paramName;
    }

    /**
     * 构造
     * @param paramId
     * @param paramName
     */
    public ExpandParamVo(Long paramId, String paramName) {
        this.paramId = paramId;
        this.paramName = paramName;
    }

    public Long getParamId() {
        return paramId;
    }

    public void setParamId(Long paramId) {
        this.paramId = paramId;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public List<ParamValueVo> getValueVoList() {
        return valueVoList;
    }

    public void setValueVoList(List<ParamValueVo> valueVoList) {
        this.valueVoList = valueVoList;
    }

    /**
     * 添加属性VO值
     * 
     * @param valueVo
     */
    public void addParamValue(ParamValueVo valueVo) {
        if (valueVoList == null) {
            valueVoList = new ArrayList<>();
        }
        valueVoList.add(valueVo);
    }
}
