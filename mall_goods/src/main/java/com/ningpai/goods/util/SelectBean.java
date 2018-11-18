
package com.ningpai.goods.util;

/**
 * 分页参数Bean
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月18日 下午2:56:14
 * @version 1.0
 */
public class SelectBean {

    /*
     *条件标记
      */
    private String condition;
    /*
     *查询文本
      */
    private String searchText;
    /*
     *品牌名称
      */
    private String brandName;

    /*
     *品牌别名
      */
    private String brandNickname;

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

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandNickname() {
        return brandNickname;
    }

    public void setBrandNickname(String brandNickname) {
        this.brandNickname = brandNickname;
    }

}
