package com.ningpai.goods.util;

import com.ningpai.util.PageBean;

import java.util.List;

/**
 * <p>
 * 搜索结果分页封装
 * </p>
 * 
 * @author liangck
 * @version 1.0
 * @since 15/8/17 15:10
 */
public class SearchPageBean<T> extends PageBean {

    /**
     * 结果数据
     */
    private List<T> data;

    public SearchPageBean() {
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
