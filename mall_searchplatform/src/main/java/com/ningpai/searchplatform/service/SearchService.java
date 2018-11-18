package com.ningpai.searchplatform.service;

import com.ningpai.searchplatform.client.ESClientManager;
import com.ningpai.searchplatform.query.AggregationDefineBean;
import com.ningpai.searchplatform.query.IQueryItemUnit;
import com.ningpai.searchplatform.request.IndexSearchRequest;
import com.ningpai.searchplatform.response.IndexSearchResponse;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.*;
import org.elasticsearch.search.aggregations.bucket.nested.InternalNested;
import org.elasticsearch.search.aggregations.bucket.terms.*;
import org.elasticsearch.search.sort.SortBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 查询service   杨国栋
 * </p>
 *
 * @author liangck
 * @version 1.0
 * @since 15/8/10 11:16
 */
@Service
public class SearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchService.class);

    /**
     * clientmanager
     */
    @Autowired
    private ESClientManager clientManager;

    /**
     * 搜索   杨国栋 搜索执行的具体方法
     *
     * @param searchRequest
     *            {@link IndexSearchRequest}
     */
    public IndexSearchResponse search(IndexSearchRequest searchRequest) {
        // 获得client
        TransportClient transportClient = clientManager.getClient();
        // step1: 处理bool query,例如: 关键词,品牌
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (searchRequest.getQueryItemUnits() != null && !searchRequest.getQueryItemUnits().isEmpty()) {
            // 设置查询条件
            buildBoolQuery(boolQueryBuilder, searchRequest.getQueryItemUnits());
        }
        //QueryBuilder qb = QueryBuilders.multiMatchQuery("OEM 现代","brandName");
        //MultiMatchQueryBuilder

        // step2: nestObject查询 例如 扩展属性 规格
        if (searchRequest.getNestedTermList() != null && !searchRequest.getNestedTermList().isEmpty()) {
            // 当nestquery条件不为空
            for (Map<String, List<IQueryItemUnit>> queryItemUnitMap : searchRequest.getNestedTermList()) {
                for (Map.Entry<String, List<IQueryItemUnit>> nestQueryItem : queryItemUnitMap.entrySet()) {
                    BoolQueryBuilder builder = QueryBuilders.boolQuery();
                    // 批量设置nestquery条件
                    buildBoolQuery(builder, nestQueryItem.getValue());
                    NestedQueryBuilder queryBuilders = QueryBuilders.nestedQuery(nestQueryItem.getKey(), builder);
                    // 加入到总bool query
                    boolQueryBuilder.must(queryBuilders);
                }
            }
        }

        // 聚合
        List<AggregationBuilder> aggregationBuilderList = new ArrayList<>();
        // step3: 普通聚合
        if (searchRequest.getAggregationDefineBeans() != null) {
            for (AggregationDefineBean aggregationDefineBean : searchRequest.getAggregationDefineBeans()) {
                aggregationBuilderList.add(convertAggDefBean(aggregationDefineBean));
            }
        }

        // step4: nestObject聚合
        if (searchRequest.getNestedAggMap() != null) {
            for (Map.Entry<String, List<AggregationDefineBean>> nestItemEntry : searchRequest.getNestedAggMap().entrySet()) {
                AggregationBuilder nestBuilder = AggregationBuilders.nested(nestItemEntry.getKey()).path(nestItemEntry.getKey());
                for (AggregationDefineBean aggDefBean : nestItemEntry.getValue()) {
                    nestBuilder.subAggregation(convertAggDefBean(aggDefBean));
                }
                aggregationBuilderList.add(nestBuilder);
            }
        }

        //杨国栋  在此处把查询请求放入查询的客户端
        SearchRequestBuilder searchRequestBuilder = transportClient.prepareSearch(searchRequest.getIndexes()).setTypes(searchRequest.getTypes()).setQuery(boolQueryBuilder).setFrom(searchRequest.getFrom()).setSize(searchRequest.getPageSize());

        SearchRequestBuilder aggBuilder = transportClient.prepareSearch(searchRequest.getIndexes()).setTypes(searchRequest.getTypes()).setQuery(boolQueryBuilder)
                .setSearchType(SearchType.COUNT);

        // step5: 过滤
        if (searchRequest.getFilterBuilderList() != null) {
            FilterBuilder filter = null;
            for (FilterBuilder filterBuilder : searchRequest.getFilterBuilderList()) {
                filter = FilterBuilders.andFilter(filterBuilder);
            }
            searchRequestBuilder.setPostFilter(filter);
        }

        // step6: 添加排序
        if (searchRequest.getSortBuilders() != null) {
            for (SortBuilder sortBuilder : searchRequest.getSortBuilders()) {
                searchRequestBuilder.addSort(sortBuilder);
            }
        }

        for (AggregationBuilder aggregationBuilder : aggregationBuilderList) {
            searchRequestBuilder.addAggregation(aggregationBuilder);
            aggBuilder.addAggregation(aggregationBuilder);
        }

        // 设置高亮显示
        if (searchRequest.getHighLightField() != null) {
            // 高亮字段
            searchRequestBuilder.addHighlightedField(searchRequest.getHighLightField());
            // 前缀标签
            searchRequestBuilder.setHighlighterPreTags(searchRequest.getHighLightPreTag());
            // 后缀标签
            searchRequestBuilder.setHighlighterPostTags(searchRequest.getHighLightSufTag());
        }

        LOGGER.info("查询QL: " + searchRequestBuilder.toString());

        // 执行查询并获得结果
        SearchResponse searchResponse = null;
        SearchResponse aggResponse = null;
        try {
            searchResponse = searchRequestBuilder.execute().actionGet();  //杨国栋 查询后的结果
            aggResponse = aggBuilder.execute().actionGet();
        } catch (ElasticsearchException e) {
            // e.printStackTrace();
            LOGGER.error("执行查询失败...", e);
            throw e;
        }

        // 封装返回结果
        IndexSearchResponse response = new IndexSearchResponse();

        List<String> sources = new ArrayList<>();
        // 查询结果
        SearchHits hits = searchResponse.getHits();
        // 放入源数据
        response.setOriginHits(hits);

        for (SearchHit hit : hits.getHits()) {
            sources.add(hit.getSourceAsString());
        }
        response.putReslutData(sources);
        // 聚合结果
        if (aggResponse.getAggregations() != null) {
            response.putResultAggs(convertAggregations2Map(aggResponse.getAggregations()));
        }
        // 总数据
        response.setPageNum(searchRequest.getPageNum());
        response.setPageSize(searchRequest.getPageSize());
        response.setTotalCount((int) hits.getTotalHits());
        return response;
    }

    //region 查询品牌数量--getcount
    /***
     * 查询品牌数量
     * zz：pl
     * @param searchRequest
     * @return
     */
    public long getcount(IndexSearchRequest searchRequest){
        // 获得client
        TransportClient transportClient = clientManager.getClient();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (searchRequest.getQueryItemUnits() != null && !searchRequest.getQueryItemUnits().isEmpty()) {
            // 设置查询条件
            buildBoolQuery(boolQueryBuilder, searchRequest.getQueryItemUnits());
        }
        //QueryBuilder qb = QueryBuilders.multiMatchQuery("","","");

        if (searchRequest.getNestedTermList() != null && !searchRequest.getNestedTermList().isEmpty()) {
            // 当nestquery条件不为空
            for (Map<String, List<IQueryItemUnit>> queryItemUnitMap : searchRequest.getNestedTermList()) {
                for (Map.Entry<String, List<IQueryItemUnit>> nestQueryItem : queryItemUnitMap.entrySet()) {
                    BoolQueryBuilder builder = QueryBuilders.boolQuery();
                    // 批量设置nestquery条件
                    buildBoolQuery(builder, nestQueryItem.getValue());
                    NestedQueryBuilder queryBuilders = QueryBuilders.nestedQuery(nestQueryItem.getKey(), builder);
                    // 加入到总bool query
                    boolQueryBuilder.must(queryBuilders);
                }
            }
        }

        SearchRequestBuilder searchRequestBuilder = transportClient.prepareSearch(searchRequest.getIndexes()).setTypes(searchRequest.getTypes()).setQuery(boolQueryBuilder).setFrom(0).setSize(16);

        // 执行查询并获得结果
        SearchResponse searchResponse = null;

        try {
            searchResponse = searchRequestBuilder.execute().actionGet();
        } catch (ElasticsearchException e) {
            // e.printStackTrace();
            LOGGER.error("执行查询失败...", e);
            throw e;
        }
        // 封装返回结果
        IndexSearchResponse response = new IndexSearchResponse();
        // 查询结果
        SearchHits hits = searchResponse.getHits();
        return hits.getTotalHits();
    }
    //endregion



    /**
     * 处理一个聚合对象数据{@link Aggregations}
     *
     * @param aggregations
     * @return Map{key:一个聚合的名称,value:聚合后的桶数据}
     */
    public Map<String, Object> convertAggregations2Map(Aggregations aggregations) {
        Map<String, Object> resultMap = new HashMap<>();
        if (aggregations == null) {
            return resultMap;
        }
        for (Aggregation aggregation : aggregations) {
            resultMap.put(aggregation.getName(), dealOneAggregation(aggregation));
        }
        return resultMap;
    }

    /**
     * 处理一个聚合对象
     *
     * @param aggregation
     *            {@link Aggregation}
     * @return 如果只有一个桶数据, 则返回对象, 如果有多个桶数据, 则返回数组
     */
    private Object dealOneAggregation(Aggregation aggregation) {
        if (aggregation instanceof StringTerms) {
            Collection<Terms.Bucket> buckets = ((StringTerms) aggregation).getBuckets();
            return dealBunkets(buckets);
        } else if (aggregation instanceof DoubleTerms) {
            Collection<Terms.Bucket> buckets = ((DoubleTerms) aggregation).getBuckets();
            return dealBunkets(buckets);
        } else if (aggregation instanceof LongTerms) {
            Collection<Terms.Bucket> buckets = ((LongTerms) aggregation).getBuckets();
            return dealBunkets(buckets);
        } else if (aggregation instanceof UnmappedTerms) {
            Collection<Terms.Bucket> buckets = ((UnmappedTerms) aggregation).getBuckets();
            return dealBunkets(buckets);
        } else if (aggregation instanceof InternalNested) {
            InternalAggregations internalAggregations = ((InternalNested) aggregation).getAggregations();
            return convertAggregations2List(internalAggregations);
        } else {
            throw new IllegalArgumentException("未知聚合类型,不可处理");
        }
    }

    /**
     * 处理一个聚合下边的一个或多个桶数据
     *
     * @param buckets
     * @return 如果有一个桶, 那么就返回一个对象, 而不是列表 如果有多个桶,则返回列表 如果没有值,则返回一个空对象
     */
    private Object dealBunkets(Collection<Terms.Bucket> buckets) {
        List<Object> list = new ArrayList<>();
        for (Terms.Bucket bucket : buckets) {
            list.add(dealOneBunket(bucket));
        }
        return list.isEmpty() ? new Object() : list.size() == 1 ? list.get(0) : list;
    }

    /**
     * 处理一个bunkey
     *
     * @param bucket
     * @return 如果没有子查询返回bunkey中的值[String]
     *         如果有子查询,返回一个对应的map对象[HashMap]--{key:bunketKey,value:子查询返回的map}
     */
    private Object dealOneBunket(Terms.Bucket bucket) {
        Object params = null;
        if (bucket.getAggregations().iterator().hasNext()) {
            params = convertAggregations2List(bucket.getAggregations());
        }
        if (params == null) {
            return bucket.getKey();// 没有自查询
        } else if (params instanceof List) {
            List<Object> resultList = (List) params;
            if (resultList.size() == 1) {
                return resultList.get(0);
            } else if (resultList.size() > 1) {
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put(bucket.getKey(), resultList);
                return resultMap;
            } else {
                return resultList;
            }
        } else {
            // 没有子查询
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put(bucket.getKey(), params);
            return resultMap;
        }
    }

    /**
     * 处理子聚合的方法
     *
     * @param aggregations
     * @return
     * @author of1081
     */
    public Object convertAggregations2List(Aggregations aggregations) {
        List<Object> resultList = new ArrayList<>();
        for (Aggregation aggregation : aggregations) {
            resultList.add(dealOneAggregation(aggregation));
        }
        return resultList.isEmpty() ? new Object() : resultList.size() == 1 ? resultList.get(0) : resultList;
    }

    /**
     * 把聚合对象{@link AggregationDefineBean} 转化为 {@link TermsBuilder}
     *
     * @param aggDefBean
     * @return
     */
    private TermsBuilder convertAggDefBean(AggregationDefineBean aggDefBean) {
        if (aggDefBean == null) {
            throw new NullPointerException("传入聚合定义对象为空");
        }
        if (aggDefBean.getSubAgg() == null) {
            return AggregationBuilders.terms(aggDefBean.getAggTitle()).field(aggDefBean.getAggField()).size(0);
        } else {
            return AggregationBuilders.terms(aggDefBean.getAggTitle()).field(aggDefBean.getAggField()).subAggregation(convertAggDefBean(aggDefBean.getSubAgg())).size(0);
        }
    }

    /**
     * 批量设置boolquery条件
     *
     * @param boolQueryBuilder
     * @param queryItemUnits
     *            {@link IQueryItemUnit}
     */
    public static void buildBoolQuery(BoolQueryBuilder boolQueryBuilder, List<IQueryItemUnit> queryItemUnits) {
        for (IQueryItemUnit queryItemUnit : queryItemUnits) {
            switch (queryItemUnit.boolQueryType()) {
            case MUST:
                boolQueryBuilder.must(queryItemUnit.buildQuery());
                break;
            case SHOULD:
                boolQueryBuilder.should(queryItemUnit.buildQuery());
                break;
            case MUST_NOT:
                boolQueryBuilder.mustNot(queryItemUnit.buildQuery());
                break;
            default:
                boolQueryBuilder.must(queryItemUnit.buildQuery());
                break;
            }
        }
    }
}
