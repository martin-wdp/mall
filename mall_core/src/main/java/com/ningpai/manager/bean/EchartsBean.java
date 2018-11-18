package com.ningpai.manager.bean;

import java.util.List;

/**
 * echarts 使用
 * 
 * @author ggn
 *
 */
public class EchartsBean {

    // 名字
    private String name;
    // 类型
    private String type;
    // 数量
    private String stock;
    // 数据
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

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public List<Long> getData() {
        return data;
    }

    public void setData(List<Long> data) {
        this.data = data;
    }

}
