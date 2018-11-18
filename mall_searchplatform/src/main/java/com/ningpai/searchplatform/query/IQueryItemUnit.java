package com.ningpai.searchplatform.query;

import com.ningpai.searchplatform.enumeration.ESBoolQueryType;
import org.elasticsearch.index.query.QueryBuilder;

/**
 * <p>
 * bool查询单元接口
 * </p>
 * 
 * @author liangck
 * @version 1.0
 * @since 15/8/10 14:48
 */
public interface IQueryItemUnit {

    /**
     * 获取查询bool类型
     * 
     * @return {@link ESBoolQueryType}
     */
    ESBoolQueryType boolQueryType();

    /**
     * 获取QueryBuilder
     * 
     * @return {@link QueryBuilder}
     */
    QueryBuilder buildQuery();
}
