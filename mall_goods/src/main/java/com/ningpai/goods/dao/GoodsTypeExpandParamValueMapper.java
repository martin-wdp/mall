package com.ningpai.goods.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.goods.bean.GoodsTypeExpandParamValue;

/**
 * 类型扩展属性值DAO
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月21日 上午10:13:39
 * @version 1.0
 */
public interface GoodsTypeExpandParamValueMapper {
    /**
     * 删除类型扩展属性值
     * 
     * @param map
     *            主键ID，删除人名称 {@link java.util.Map}
     * @return 删除的行数 {@link java.lang.Integer}
     */
    int deleteByPrimaryKey(Map<String, String> map);

    /**
     * 插入一条记录 （全属性 不推荐）
     * 
     * @param record
     *            待插入的实体
     *            {@link com.ningpai.goods.bean.GoodsTypeExpandParamValue}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int insert(GoodsTypeExpandParamValue record);

    /**
     * 插入一条记录 （可选属性 推荐）
     * 
     * @param record
     *            待插入的实体
     *            {@link com.ningpai.goods.bean.GoodsTypeExpandParamValue}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int insertSelective(GoodsTypeExpandParamValue record);

    /**
     * 根据主键ID查询
     * 
     * @param expandparamValueId
     *            主键ID {@link java.lang.Long}
     * @return 查询到的实体 {@link com.ningpai.goods.bean.GoodsTypeExpandParamValue}
     */
    GoodsTypeExpandParamValue selectByPrimaryKey(Long expandparamValueId);

    /**
     * 更新 （可选属性 推荐）
     * 
     * @param record
     *            待更新的实体
     *            {@link com.ningpai.goods.bean.GoodsTypeExpandParamValue}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int updateByPrimaryKeySelective(GoodsTypeExpandParamValue record);

    /**
     * 更新 （全属性 不推荐）
     * 
     * @param record
     *            待更新的实体
     *            {@link com.ningpai.goods.bean.GoodsTypeExpandParamValue}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int updateByPrimaryKey(GoodsTypeExpandParamValue record);

    /**
     * 根据属性ID查询所有的属性值
     * 
     * @param paramId
     *            扩展属性的ID {@link java.lang.Long}
     * @return 查询到的实体列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsTypeExpandParamValue}
     */
    List<GoodsTypeExpandParamValue> queryParamValueByParamId(Long paramId);
}
