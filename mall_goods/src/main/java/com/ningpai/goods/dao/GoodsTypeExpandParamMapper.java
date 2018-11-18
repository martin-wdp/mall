package com.ningpai.goods.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.goods.bean.GoodsTypeExpandParam;
import com.ningpai.goods.vo.GoodsTypeExpandParamVo;

/**
 * 商品类型扩展属性DAO
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月21日 上午10:05:01
 * @version 1.0
 */
public interface GoodsTypeExpandParamMapper {
    /**
     * 根据主键删除
     * 
     * @param map
     *            主键ID，删除人名称 {@link java.util.Map}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int deleteByPrimaryKey(Map<String, String> map);

    /**
     * 插入记录 （全属性 不推荐）
     * 
     * @param record
     *            插入的实体 {@link com.ningpai.goods.bean.GoodsTypeExpandParam}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int insert(GoodsTypeExpandParam record);

    /**
     * 插入记录 （可选属性 推荐）
     * 
     * @param record
     *            插入的实体 {@link com.ningpai.goods.bean.GoodsTypeExpandParam}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int insertSelective(GoodsTypeExpandParam record);

    /**
     * 根据主键查询
     * 
     * @param expandparamId
     *            扩展属性主键 {@link java.lang.Long}
     * @return 查询到的扩展属性实体 {@link com.ningpai.goods.bean.GoodsTypeExpandParam}
     */
    GoodsTypeExpandParam selectByPrimaryKey(Long expandparamId);

    /**
     * 更新扩展属性 (可选属性 推荐)
     * 
     * @param record
     *            待更新的实体 {@link com.ningpai.goods.bean.GoodsTypeExpandParam}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int updateByPrimaryKeySelective(GoodsTypeExpandParam record);

    /**
     * 更新扩展属性 (全属性 不推荐)
     * 
     * @param record
     *            待更新的实体 {@link com.ningpai.goods.bean.GoodsTypeExpandParam}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int updateByPrimaryKey(GoodsTypeExpandParam record);

    /**
     * 根据类型ID查询所有的扩展属性
     * 
     * @param typeId
     *            商品类型的主键ID {@link java.lang.Long}
     * @return 查询到的列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsTypeExpandParam}
     */
    List<GoodsTypeExpandParamVo> queryAllExpandParam(Long typeId);

    /**
     * 查询最后插入的id
     * 
     * @return 查询到的ID
     */
    Long selectLastId();
}
