/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.util;

/**
 * 分页参数Bean
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月18日 下午2:56:14
 * @version 1.0
 */
public class SelectBean {

    // 条件标记
    private String condition;
    // 查询文本
    private String searchText;

    private String searchTextTwo;

    public String getSearchTextTwo() {
        return searchTextTwo;
    }

    public void setSearchTextTwo(String searchTextTwo) {
        this.searchTextTwo = searchTextTwo;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

}
