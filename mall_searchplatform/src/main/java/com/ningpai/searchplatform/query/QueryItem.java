package com.ningpai.searchplatform.query;

import com.ningpai.searchplatform.enumeration.ESBoolQueryType;
import com.ningpai.searchplatform.enumeration.ESQueryWay;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

/**
 * <p>
 *      基本查询单元
 * </p>
 * @author liangck
 * @version 1.0
 * @since 15/8/10 15:31
 */
public final class QueryItem extends AbstractQueryItem{


    /**
     * 无参数构造器
     */
    public QueryItem() {
        super();
    }

    /**
     * 参数构造器,默认提供的查询为 must{ term :{ fieldname : fieldValue}}
     *
     * @param fieldName
     *            查询属性名
     * @param fieldValue
     *            查询属性值
     */
    public QueryItem(String fieldName, String fieldValue) {
        super(fieldName, fieldValue);
    }

    /**
     * 参数构造器
     *
     * @param queryType
     *            {@link ESBoolQueryType} 查询类型
     * @param queryWay
     *            {@link ESQueryWay} 查询方式
     * @param fieldName
     *            字段名
     * @param fieldValue
     *            匹配值
     */
    public QueryItem(ESBoolQueryType queryType, ESQueryWay queryWay,
            String fieldName, String fieldValue) {
        super(queryType,queryWay,fieldName,fieldValue);
    }


    /**
     * 获取QueryBuilder
     *
     * @return {@link QueryBuilder}
     */
    @Override
    public QueryBuilder buildQuery() {
        QueryBuilder queryBuilder;
        switch (queryWay) {
        case MATCH:
            queryBuilder = QueryBuilders.matchQuery(fieldName, fieldValue);
            break;
        case MATCH_PHRASE:
            queryBuilder = QueryBuilders
                    .matchPhraseQuery(fieldName, fieldValue);
            break;
        // 没有设置值,或为term时,都进行term查询
        case TERM:
        default:
            queryBuilder = QueryBuilders.termQuery(fieldName, fieldValue);
            break;
        }
        return queryBuilder;
    }

}
