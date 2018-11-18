package com.ningpai.searchplatform.enumeration;

/**
 * of1081_yxd on 2015/1/7. Description： ES 内置分析器
 */
public enum ESAnalyzer {

    /**
     * standard tokenizer, standard filter, lower case filter, stop filter
     */
    standard(),

    /**
     * lower case tokenizer
     */
    simple(),
    /**
     * ik
     */
    ik(),

    /**
     * lower case tokenizer, stop filter
     */
    stop(),

    /**
     * 不分词，内容整体作为一个token(not_analyzed)
     */
    keyword(),

    /**
     * 正则表达式分词，默认匹配\W+
     */
    whitespace(),

    /**
     * 各种语言
     */
    // lang(),

    /**
     * standard tokenizer, standard filter, lower case filter, stop filter,
     * snowball filter
     */
    snowball(),

    /**
     * 不进行索引
     */
    not_analyzed(),

    /**
     * Ansj搜索条件分词
     */
    ansj_query(),

    /**
     * Ansj索引文档分词
     */
    ansj_index(),

    /**
     * Ansj智能分词，即索引文档时使用ansj_index,搜索文档时使用ansj_query分词
     */
    ansj_auto(),

    /**
     * 一个Tokenizer, 零个或多个Token Filter, 零个或多个Char Filter
     */
    ESAnalyzer() {
    }
}