package com.ningpai.searchplatform.query;

import com.ningpai.searchplatform.enumeration.ESBoolQueryType;
import com.ningpai.searchplatform.enumeration.ESQueryWay;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *     nested查询单元
 * </p>
 * @author liangck
 * @version 1.0
 * @since 15/8/23 12:16
 */
public class NestedQueryItem extends AbstractQueryItem {


    /**
     * 针对 nest对象的查询
     */
    private Map<String,List<IQueryItemUnit>> subNestedQueryItems;

    /**
     * 无参数构造器
     */
    public NestedQueryItem() {
    }

    /**
     * 参数构造器,默认提供的查询为 must{ term :{ fieldname : fieldValue}}
     *
     * @param fieldName  查询属性名
     * @param fieldValue
     */
    public NestedQueryItem(String fieldName, String fieldValue) {
        super(fieldName, fieldValue);
    }

    /**
     * 参数构造器
     *
     * @param queryType  {@link ESBoolQueryType} 查询类型
     * @param queryWay   {@link ESQueryWay} 查询方式
     * @param fieldName  字段名
     * @param fieldValue
     */
    public NestedQueryItem(ESBoolQueryType queryType, ESQueryWay queryWay, String fieldName, String fieldValue) {
        super(queryType, queryWay, fieldName, fieldValue);
    }

    public Map<String, List<IQueryItemUnit>> getSubNestedQueryItems() {
        return subNestedQueryItems;
    }

    public void setSubNestedQueryItems(Map<String, List<IQueryItemUnit>> subNestedQueryItems) {
        this.subNestedQueryItems = subNestedQueryItems;
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
