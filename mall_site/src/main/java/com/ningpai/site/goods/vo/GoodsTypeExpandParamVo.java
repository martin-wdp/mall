/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.site.goods.vo;

import java.util.List;

import com.ningpai.goods.bean.GoodsTypeExpandParamValue;

/**
 * 商品类型扩展属性Vo
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月21日 下午1:37:19
 * @version
 */
public class GoodsTypeExpandParamVo {
    // 主键ID
    private Long expandparamId;

    // 类型ID
    private Long typeId;

    // 扩展属性的名称
    private String expandparamName;

    // 扩展属性别名
    private String expandparamNickname;

    // 是否显示 0：不显示 1：显示
    private String expanparamIsshow;

    // 排序
    private Integer expandparamSort;

    // 删除标记 0：未删除 1：已删除 默认0
    private String expandparamDelflag;

    // 扩展属性值集合
    private List<GoodsTypeExpandParamValue> valueList;

    public Long getExpandparamId() {
        return expandparamId;
    }

    public void setExpandparamId(Long expandparamId) {
        this.expandparamId = expandparamId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getExpandparamName() {
        return expandparamName;
    }

    public void setExpandparamName(String expandparamName) {
        this.expandparamName = expandparamName;
    }

    public String getExpandparamNickname() {
        return expandparamNickname;
    }

    public void setExpandparamNickname(String expandparamNickname) {
        this.expandparamNickname = expandparamNickname;
    }

    public String getExpanparamIsshow() {
        return expanparamIsshow;
    }

    public void setExpanparamIsshow(String expanparamIsshow) {
        this.expanparamIsshow = expanparamIsshow;
    }

    public Integer getExpandparamSort() {
        return expandparamSort;
    }

    public void setExpandparamSort(Integer expandparamSort) {
        this.expandparamSort = expandparamSort;
    }

    public List<GoodsTypeExpandParamValue> getValueList() {
        return valueList;
    }

    public void setValueList(List<GoodsTypeExpandParamValue> valueList) {
        this.valueList = valueList;
    }

    public String getExpandparamDelflag() {
        return expandparamDelflag;
    }

    public void setExpandparamDelflag(String expandparamDelflag) {
        this.expandparamDelflag = expandparamDelflag;
    }

}
