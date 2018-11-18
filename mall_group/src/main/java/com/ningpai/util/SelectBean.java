package com.ningpai.util;

/**
 * 搜索
 * @author ggn
 *
 */
public class SelectBean {
    /**
     * 类型
     */
    private String condition;
    /**
     * 内容
     */
    private String searchText;

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
