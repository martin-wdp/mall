package com.ningpai.searchplatform.request;

import com.ningpai.searchplatform.enumeration.ESBoolQueryType;
import com.ningpai.searchplatform.enumeration.ESQueryWay;
import com.ningpai.searchplatform.query.AggregationDefineBean;
import com.ningpai.searchplatform.query.IQueryItemUnit;
import com.ningpai.searchplatform.query.QueryItem;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 索引查询请求包装,非线程安全的哦!!!
 * </p>
 * 
 * @author liangck
 * @version 1.0
 * @since 15/8/10 15:30
 */
public class IndexSearchRequest {

    /**
     * 索引名称(必传)
     */
    private String[] indexes;

    /**
     * 索引类型(必传)
     */
    private String[] types;

    /**
     * 单个搜索字段集合
     */
    private List<IQueryItemUnit> queryItemUnits;

    /**
     * 针对 nest对象的查询
     */
    private List<Map<String, List<IQueryItemUnit>>> nestedTermList;

    /**
     * 聚合字段
     */
    private List<AggregationDefineBean> aggregationDefineBeans;

    /**
     * 针对nest对象聚合定义值
     */
    private Map<String, List<AggregationDefineBean>> nestedAggMap;

    /**
     * 排序
     */
    private List<SortBuilder> sortBuilders;

    /**
     * 过滤
     */
    private List<FilterBuilder> filterBuilderList;

    /**
     * 当前页码
     */
    private int pageNum;

    /**
     * 每页纪录条数
     */
    private int pageSize;

    /**
     * 高亮显示字段
     */
    private String highLightField;

    /**
     * 高亮显示前置标签
     */
    private String highLightPreTag;

    /**
     * 高亮显示后置标签
     */
    private String highLightSufTag;

    /**
     * 无参数构造器
     */
    public IndexSearchRequest() {
    }

    /**
     * 根据索引\类型名称构造请求数据
     * 
     * @param indexes
     *            索引
     * @param types
     *            类型
     */
    public IndexSearchRequest(String[] indexes, String[] types) {
        this.indexes = indexes;
        this.types = types;
    }

    /**
     * 获取查询索引值
     * 
     * @return
     */
    public String[] getIndexes() {
        return indexes;
    }

    /**
     * 设置查询索引
     * 
     * @param indexes
     */
    public void setIndexes(String[] indexes) {
        this.indexes = indexes;
    }

    /**
     * 获取查询类型
     * 
     * @return
     */
    public String[] getTypes() {
        return types;
    }

    /**
     * 设置查询类型
     * 
     * @param types
     */
    public void setTypes(String[] types) {
        this.types = types;
    }

    /**
     * 获取查询条件列表
     * 
     * @return {@link IQueryItemUnit}
     */
    public List<IQueryItemUnit> getQueryItemUnits() {
        return queryItemUnits;
    }

    /**
     * 设置查询条件列表
     * 
     * @param queryItemUnits
     */
    public void setQueryItemUnits(List<IQueryItemUnit> queryItemUnits) {
        this.queryItemUnits = queryItemUnits;
    }

    /**
     * 获取nested搜索条件
     * 
     * @return
     */
    public List<Map<String, List<IQueryItemUnit>>> getNestedTermList() {
        return nestedTermList;
    }

    /**
     * 设置nested搜索条件
     * 
     * @param nestedTermList
     */
    public void setNestedTermList(List<Map<String, List<IQueryItemUnit>>> nestedTermList) {
        this.nestedTermList = nestedTermList;
    }

    /**
     * 根据属性名,属性值添加查询条件 默认查询类型为 {@link ESBoolQueryType} must
     * 
     * @param fieldName
     *            查询属性名
     * @param fieldValue
     *            查询属性值
     */
    public void addQueryItem(String fieldName, String fieldValue) {
        this.addQueryItem(fieldName, fieldValue, ESBoolQueryType.MUST, ESQueryWay.TERM);
    }

    /**
     * 添加一个查询条件
     * 
     * @param fieldName
     *            查询字段
     * @param fieldValue
     *            查询字段值
     * @param queryType
     *            查询类型
     */
    public void addQueryItem(String fieldName, String fieldValue, ESBoolQueryType queryType, ESQueryWay queryWay) {
        if (queryItemUnits == null) {
            queryItemUnits = new ArrayList<IQueryItemUnit>();
        }
        QueryItem queryItem = new QueryItem(queryType, queryWay, fieldName, fieldValue);
        queryItemUnits.add(queryItem);
    }

    /**
     * 根据对象名称,属性名,属性值设置 nest 对象类型查询条件 查询类型为must{@link ESBoolQueryType}
     * 
     * @param nestObj
     *            nest对象名
     * @param fieldName
     *            属性名
     * @param fieldValue
     *            属性值
     */
    public void addShouldNestedTermField(String nestObj, String fieldName, String fieldValue) {
        this.addSingleNestedTermField(nestObj, fieldName, fieldValue, ESBoolQueryType.MUST, ESQueryWay.TERM);
    }

    /**
     * 根据对象名称,属性名,属性值 设置nest对象类型must查询条件 must{@link ESBoolQueryType}
     * 
     * @param nestObj
     * @param fieldName
     * @param fieldValue
     */
    public void addMustNestedTermField(String nestObj, String fieldName, String fieldValue) {
        this.addSingleNestedTermField(nestObj, fieldName, fieldValue, ESBoolQueryType.MUST, ESQueryWay.TERM);
    }

    /**
     * 添加nested查询条件
     * 
     * @param nestedObj
     *            nested对象
     * @param queryItemUnitList
     *            查询条件列表
     */
    public void addNestedTermField(String nestedObj, List<IQueryItemUnit> queryItemUnitList) {
        if (nestedTermList == null) {
            nestedTermList = new ArrayList<>();
        }
        Map<String, List<IQueryItemUnit>> queryItemMap = new HashMap<>();
        queryItemMap.put(nestedObj, queryItemUnitList);
        nestedTermList.add(queryItemMap);
    }

    /**
     * 添加一个单独的nestObj查询条件
     * 
     * @param nestObj
     *            nestobj
     * @param fieldName
     *            字段名
     * @param fieldValue
     *            字段值
     */
    public void addSingleNestedTermField(String nestObj, String fieldName, String fieldValue, ESBoolQueryType queryType, ESQueryWay queryWay) {
        if (nestedTermList == null) {
            nestedTermList = new ArrayList<Map<String, List<IQueryItemUnit>>>();
        }
        Map<String, List<IQueryItemUnit>> queryItemUnitMap = new HashMap<>();
        queryItemUnitMap.put(nestObj, new ArrayList<IQueryItemUnit>());
        queryItemUnitMap.get(nestObj).add(new QueryItem(queryType, queryWay, fieldName, fieldValue));
        nestedTermList.add(queryItemUnitMap);
    }

    /**
     * 添加一个组合的nested查询
     * 
     * @param nestedObj
     *            nestedObj
     * @param fiedlName
     *            nested字段名称
     * @param fieldValue
     *            nested字段值
     * @param queryType
     *            查询方式
     * @param queryWay
     *            匹配方式
     */
    public void addGroupNestedTermField(String nestedObj, String fiedlName, String fieldValue, ESBoolQueryType queryType, ESQueryWay queryWay) {
        // nested查询条件为空,直接作为添加单独的nested条件处理
        if (nestedTermList != null) {
            boolean containObj = false;
            // 遍历nested查询条件
            for (Map<String, List<IQueryItemUnit>> queryItemUnitMap : nestedTermList) {
                if (queryItemUnitMap.containsKey(nestedObj)) {// 包含该nestedObj的查询条件时
                    queryItemUnitMap.get(nestedObj).add(new QueryItem(queryType, queryWay, fiedlName, fieldValue));
                    containObj = true;
                    break;
                }
            }
            if (!containObj) {// 不包含该nestedObj的查询条件,做添加单独的nested查询条件处理
                addSingleNestedTermField(nestedObj, fiedlName, fieldValue, queryType, queryWay);
            }
        } else {
            addSingleNestedTermField(nestedObj, fiedlName, fieldValue, queryType, queryWay);
        }
    }

    /**
     * 获取简单聚合条件
     * 
     * @return
     */
    public List<AggregationDefineBean> getAggregationDefineBeans() {
        return aggregationDefineBeans;
    }

    /**
     * 设置简单聚合条件
     * 
     * @param aggregationDefineBeans
     */
    public void setAggregationDefineBeans(List<AggregationDefineBean> aggregationDefineBeans) {
        this.aggregationDefineBeans = aggregationDefineBeans;
    }

    /**
     * 添加一级聚合
     * 
     * @param aggregationDefineBean
     */
    public void addAggregation(AggregationDefineBean aggregationDefineBean) {
        if (aggregationDefineBeans == null) {
            aggregationDefineBeans = new ArrayList<AggregationDefineBean>();
        }
        aggregationDefineBeans.add(aggregationDefineBean);
    }

    /**
     * nest对象聚合条件
     * 
     * @return
     */
    public Map<String, List<AggregationDefineBean>> getNestedAggMap() {
        return nestedAggMap;
    }

    /**
     * 设置nest对象聚合条件
     * 
     * @param nestedAggMap
     */
    public void setNestedAggMap(Map<String, List<AggregationDefineBean>> nestedAggMap) {
        this.nestedAggMap = nestedAggMap;
    }

    /**
     * 添加nested聚合
     * 
     * @param nestedObj
     *            nested对象
     * @param nestedField
     *            nested对象属性
     */
    public void addNestedAgg(String nestedObj, String nestedField) {
        addNestedAgg(nestedObj, new AggregationDefineBean(nestedField));
    }

    /**
     * 添加nest聚合
     * 
     * @param aggBean
     *            {@link AggregationDefineBean}
     */
    public void addNestedAgg(String nestObjField, AggregationDefineBean aggBean) {
        if (nestedAggMap == null) {
            nestedAggMap = new HashMap<String, List<AggregationDefineBean>>();
        }
        if (nestedAggMap.containsKey(nestObjField)) {
            nestedAggMap.get(nestObjField).add(aggBean);
        } else {
            List<AggregationDefineBean> aggBeans = new ArrayList<AggregationDefineBean>();
            aggBeans.add(aggBean);
            nestedAggMap.put(nestObjField, aggBeans);
        }
    }

    /**
     * 设置排序
     * 
     * @return
     */
    public List<SortBuilder> getSortBuilders() {
        return sortBuilders;
    }

    /**
     * 获取排序
     * 
     * @param sortBuilders
     */
    public void setSortBuilders(List<SortBuilder> sortBuilders) {
        this.sortBuilders = sortBuilders;
    }

    /**
     * 添加一个排序
     * 
     * @param sortBuilder
     */
    public void addSort(SortBuilder sortBuilder) {
        if (sortBuilders == null) {
            sortBuilders = new ArrayList<>();
        }
        sortBuilders.add(sortBuilder);
    }

    /**
     * 添加一个排序
     * 
     * @param fieldName
     *            排序字段
     * @param order
     *            排序方式 ASC正序/DESC倒序 {@link SortOrder}
     */
    public void addSort(String fieldName, SortOrder order) {
        if (sortBuilders == null) {
            sortBuilders = new ArrayList<>();
        }
        sortBuilders.add(new FieldSortBuilder(fieldName).order(order));
    }

    /**
     * 设置过滤操作
     * 
     * @return
     */
    public List<FilterBuilder> getFilterBuilderList() {
        return filterBuilderList;
    }

    /**
     * 获取当前请求的过滤操作
     * 
     * @param filterBuilderList
     */
    public void setFilterBuilderList(List<FilterBuilder> filterBuilderList) {
        this.filterBuilderList = filterBuilderList;
    }

    /**
     * 添加一个filter
     * 
     * @param filterBuilder
     */
    public void addFilter(FilterBuilder filterBuilder) {
        if (filterBuilderList == null) {
            filterBuilderList = new ArrayList<>();
        }
        filterBuilderList.add(filterBuilder);
    }

    /**
     * 当前页码
     * 
     * @return
     */
    public int getPageNum() {
        return pageNum;
    }

    /**
     * 设置当前页码
     * 
     * @param pageNum
     */
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    /**
     * 每页条数
     * 
     * @return
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置每页条数
     * 
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 查询起始纪录位置
     * 
     * @return
     */
    public int getFrom() {
        return pageNum <= 0 ? 0 : (pageNum - 1) * pageSize;
    }

    public String getHighLightField() {
        return highLightField;
    }

    public void setHighLightField(String highLightField) {
        this.highLightField = highLightField;
    }

    public String getHighLightPreTag() {
        return highLightPreTag;
    }

    public void setHighLightPreTag(String highLightPreTag) {
        this.highLightPreTag = highLightPreTag;
    }

    public String getHighLightSufTag() {
        return highLightSufTag;
    }

    public void setHighLightSufTag(String highLightSufTag) {
        this.highLightSufTag = highLightSufTag;
    }
}
