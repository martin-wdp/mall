package com.ningpai.searchplatform.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.elasticsearch.search.SearchHits;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liangck
 * @version 1.0
 * @since 15/8/12 11:02
 */
public class IndexSearchResponse {

    /** result中的聚合结果 **/
    private static final String AGGS_RESULT = "aggs";
    /** result中的查询结果数据 **/
    private static final String DATA_RESULT = "data";

    /**
     * 结果总数
     */
    private int totalCount;

    /**
     * 当前页码
     */
    private int pageNum;

    /**
     * 每页数据条数
     */
    private int pageSize;

    /**
     * 数据结果集
     */
    private Map<String, Object> result;

    /**
     * 查询的源结果
     */
    private SearchHits originHits;

    /**
     * 无参数构造器
     */
    public IndexSearchResponse() {
    }

    /**
     * 根据结果纪录总数,当前查询页码,每页数据条数 构造返回结果集
     * 
     * @param totalCount
     *            查询到的数据总条数
     * @param pageNum
     *            当前页码
     * @param pageSize
     *            每页数据条数
     */
    public IndexSearchResponse(int totalCount, int pageNum, int pageSize) {
        this(totalCount, pageNum, pageSize, null);
    }

    /**
     * 根据结果纪录总数,当前查询页码,每页数据条数 构造返回结果集
     * 
     * @param totalCount
     *            查询到的数据总条数
     * @param pageNum
     *            当前页码
     * @param pageSize
     *            每页数据条数
     * @param result
     *            数据集
     */
    public IndexSearchResponse(int totalCount, int pageNum, int pageSize,
            Map<String, Object> result) {
        this.totalCount = totalCount;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.result = result;
    }

    /**
     * 放入聚合结果
     * 
     * @param aggResult
     *            聚合结果
     */
    public void putResultAggs(Object aggResult) {
        if (this.result == null) {
            result = new HashMap<>();
        }
        result.put(AGGS_RESULT, aggResult);
    }

    /**
     * 放入查询结果数据
     * 
     * @param data
     *            查询结果数据
     */
    public void putReslutData(Object data) {
        if (result == null) {
            result = new HashMap<>();
        }
        result.put(DATA_RESULT, data);
    }

    /**
     * 获取聚合结果
     * 
     * @return
     */
    public Object getResultAgg() {
        return result == null ? null : result.get(AGGS_RESULT);
    }

    /**
     * 获取查询结果数据
     * 
     * @return
     */
    public Object getResultData() {
        return result == null ? null : result.get(DATA_RESULT);
    }

    /**
     * 返回结果数据的json字符串,方便进行反序列化
     * 
     * @return
     */
    public String getResultDataJsonString() {
        return (result == null || result.get(DATA_RESULT) == null) ? null
                : JSON.toJSONString(result.get(DATA_RESULT))
                        .replace("[\"{", "[{").replace("}\"]", "}]")
                        .replace("\\", "").replace("}\"", "}")
                        .replace("\"{", "{");
    }

    /**
     * 返回指定类型的查询数据结果
     * 
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> List<T> getResultDataBeans(Class<T> clazz) {
        return getResultDataJsonString() == null ? new ArrayList() : JSONArray
                .parseArray(getResultDataJsonString(), clazz);
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }

    public SearchHits getOriginHits() {
        return originHits;
    }

    public void setOriginHits(SearchHits originHits) {
        this.originHits = originHits;
    }

    @Override
    public String toString() {
        return "[{totalCount:" + totalCount + "},{pageNum:" + pageNum
                + "},{pageSize:" + pageSize + "}],{result:" + result + "}]";
    }
}
