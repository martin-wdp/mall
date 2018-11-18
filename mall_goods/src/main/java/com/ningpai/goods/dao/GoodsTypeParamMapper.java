package com.ningpai.goods.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.goods.bean.GoodsTypeParam;

/**
 * 商品类型详细参数DAO
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月21日 上午10:25:58
 * @version 1.0
 */
public interface GoodsTypeParamMapper {
    /**
     * 根据主键删除
     * 
     * @param map
     *            主键ID，删除人名称 {@link java.util.Map}
     * @return 删除的行数 {@link java.lang.Integer}
     */
    int deleteByPrimaryKey(Map<String, String> map);

    /**
     * 插入记录 （全属性 不推荐）
     * 
     * @param record
     *            插入的实体 {@link com.ningpai.goods.bean.GoodsTypeParam}
     * @return 插入的行数
     */
    int insert(GoodsTypeParam record);

    /**
     * 插入记录 （可选属性 推荐）
     * 
     * @param record
     *            插入的实体 {@link com.ningpai.goods.bean.GoodsTypeParam}
     * @return 插入的行数
     */
    int insertSelective(GoodsTypeParam record);

    /**
     * 根据主键ID查询
     * 
     * @param paramId
     *            主键ID{@link java.lang.Long}
     * @return 查询到的实体 {@link com.ningpai.goods.bean.GoodsTypeParam}
     */
    GoodsTypeParam selectByPrimaryKey(Long paramId);

    /**
     * 更新（可选属性 推荐）
     * 
     * @param record
     *            更新的实体 {@link com.ningpai.goods.bean.GoodsTypeParam}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int updateByPrimaryKeySelective(GoodsTypeParam record);

    /**
     * 更新（全属性 不推荐）
     * 
     * @param record
     *            更新的实体 {@link com.ningpai.goods.bean.GoodsTypeParam}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int updateByPrimaryKey(GoodsTypeParam record);

    /**
     * 根据类型ID查询详细参数列表
     * 
     * @param typeId
     *            类型ID {@link java.lang.Long}
     * @return 查询到的详细参数列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsTypeParam}
     */
    List<GoodsTypeParam> queryTypeParamByTypeId(Long typeId);
}
