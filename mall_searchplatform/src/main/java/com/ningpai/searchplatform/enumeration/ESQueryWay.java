package com.ningpai.searchplatform.enumeration;

/**
 * <p>
 * es查询的方式 : match:走分词 term:不走分词,全次匹配 nested:嵌套对象查询
 * </p>
 * 
 * @author liangck
 * @version 1.0
 * @since 15/8/12 15:07
 */
public enum ESQueryWay {
    /**
     * match匹配查询
     */
    MATCH(),

    /**
     * 匹配整条短语,不进行分词
     */
    MATCH_PHRASE(),

    /**
     * term查询
     */
    TERM(),

    /**
     * nest查询
     */
    NESTED();

    ESQueryWay() {
    }
}
