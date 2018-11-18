package com.ningpai.searchplatform.query;

import com.ningpai.searchplatform.enumeration.ESBoolQueryType;
import com.ningpai.searchplatform.enumeration.ESQueryWay;

/**
 * <p>
 *     抽象查询基类
 * </p>
 * @author liangck
 * @version 1.0
 * @since 15/8/23 12:12
 */
public abstract class AbstractQueryItem implements IQueryItemUnit{

    /**
     * 查询类型:must/should/must_not
     */
    protected ESBoolQueryType queryType;

    /**
     * 查询方式:match/term
     */
    protected ESQueryWay queryWay;

    /**
     * 字段名
     */
    protected String fieldName;

    /**
     * 查询值
     */
    protected String fieldValue;

    /**
     * 无参数构造器
     */
    public AbstractQueryItem() {
    }

    /**
     * 参数构造器,默认提供的查询为 must{ term :{ fieldname : fieldValue}}
     *
     * @param fieldName
     *            查询属性名
     * @param fieldValue
     *            查询属性值
     */
    public AbstractQueryItem(String fieldName, String fieldValue) {
        this(ESBoolQueryType.MUST, ESQueryWay.TERM, fieldName, fieldValue);
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
    public AbstractQueryItem(ESBoolQueryType queryType, ESQueryWay queryWay,
                     String fieldName, String fieldValue) {
        this.queryType = queryType;
        this.queryWay = queryWay;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
    /**
     * 获取查询类型
     * */
    public ESBoolQueryType getQueryType() {
        return queryType;
    }
    /**
     * 设置查询类型
     * */
    public void setQueryType(ESBoolQueryType queryType) {
        this.queryType = queryType;
    }
    /**
     * 获取文件名称
     * */
    public String getFieldName() {
        return fieldName;
    }
    /**
     * 设置文件名称
     * */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
    /**
     * 获取文件值
     */
    public String getFieldValue() {
        return fieldValue;
    }
    /**
     * 设置文件值
     */
    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    /**
     * 获取查询bool类型
     *
     * @return {@link ESBoolQueryType}
     */
    public ESBoolQueryType boolQueryType() {
        return getQueryType();
    }
    /**
     * 获取查询
     */
    public ESQueryWay getQueryWay() {
        return queryWay;
    }
    /**
     * 设置查询
     */
    public void setQueryWay(ESQueryWay queryWay) {
        this.queryWay = queryWay;
    }

}
