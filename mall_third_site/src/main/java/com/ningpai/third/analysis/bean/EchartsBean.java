package com.ningpai.third.analysis.bean;

import java.util.List;
/**
 *<p>图标实体类</p>
 *@author zhanghl
 *@versuin 2.0
 *@since 2015.07.30
 */
public class EchartsBean {
    /**
     * 图标名称
     */
    private String name;
    /**
     * 图表类型
     */

    private String type;
    /**
     * 图标位置
     */

    private String stack;

    /**
     * 图标数据
     */
    private List<Long> data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    public List<Long> getData() {
        return data;
    }

    public void setData(List<Long> data) {
        this.data = data;
    }

}
