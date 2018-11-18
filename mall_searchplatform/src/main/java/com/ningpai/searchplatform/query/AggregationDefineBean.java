package com.ningpai.searchplatform.query;

/**
 * <p>
 * 聚合定义
 * </p>
 *
 * @author liangck
 * @version 1.0
 * @since 15/8/10 16:32
 */
public class AggregationDefineBean {

    /**
     * 聚合后显示的标签值
     */
    private String aggTitle;

    /**
     * 将要聚合的字段值
     */
    private String aggField;

    /**
     * 子聚合对象,为空时 即没有再进行细分的聚合
     */
    private AggregationDefineBean subAgg;

    /**
     * 无参数狗杂气
     */
    public AggregationDefineBean() {
    }

    /**
     * 根据聚合字段 构造聚合查询条件
     *
     * @param aggField
     */
    public AggregationDefineBean(String aggField) {
        this.aggField = aggField;
    }

    /**
     * 参数构造器
     *
     * @param aggTitle 聚合标签值
     * @param aggField 聚合字段值
     */
    public AggregationDefineBean(String aggTitle, String aggField) {
        this.aggTitle = aggTitle;
        this.aggField = aggField;
    }

    /**
     * 获取标题
     */
    public String getAggTitle() {
        return (aggTitle == null || "".equalsIgnoreCase(aggTitle)) ? aggField + "s" : aggTitle;
    }

    public void setAggTitle(String aggTitle) {
        this.aggTitle = aggTitle;
    }

    public String getAggField() {
        return aggField;
    }

    public void setAggField(String aggField) {
        this.aggField = aggField;
    }

    public AggregationDefineBean getSubAgg() {
        return subAgg;
    }

    public void setSubAgg(AggregationDefineBean subAgg) {
        this.subAgg = subAgg;
    }
}
