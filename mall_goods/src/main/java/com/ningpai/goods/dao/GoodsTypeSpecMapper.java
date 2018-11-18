package com.ningpai.goods.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.goods.bean.GoodsTypeSpec;
import com.ningpai.goods.vo.GoodsTypeSpecVo;

/**
 * 商品关联规格Dao
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月21日 上午10:32:10
 * @version 1.0
 */
public interface GoodsTypeSpecMapper {
    /**
     * 根据主键删除
     * 
     * @param map
     *            主键ID，删除人名称 {@link java.util.Map}
     * @return 删除的行数 {@link java.lang.Integer}
     */
    int deleteByPrimaryKey(Map<String, String> map);

    /**
     * 插入 （全属性 不推荐）
     * 
     * @param record
     *            插入的实体 {@link com.ningpai.goods.bean.GoodsTypeSpec}
     * @return 插入的行数
     */
    int insert(GoodsTypeSpec record);

    /**
     * 插入 （可选属性 推荐）
     * 
     * @param record
     *            插入的实体 {@link com.ningpai.goods.bean.GoodsTypeSpec}
     * @return 插入的行数
     */
    int insertSelective(GoodsTypeSpec record);

    /**
     * 根据主键ID查询
     * 
     * @param typeSpecId
     *            主键ID {@link java.lang.Long}
     * @return 查询到的实体 {@link com.ningpai.goods.bean.GoodsTypeSpec}
     */
    GoodsTypeSpec selectByPrimaryKey(Long typeSpecId);

    /**
     * 更新 （可选属性 推荐）
     * 
     * @param record
     *            更新的实体 {@link com.ningpai.goods.bean.GoodsTypeSpec}
     * @return 更新的行数 {@link java.lang.Integer}
     */
    int updateByPrimaryKeySelective(GoodsTypeSpec record);

    /**
     * 更新 （全属性 不 推荐）
     * 
     * @param record
     *            更新的实体 {@link com.ningpai.goods.bean.GoodsTypeSpec}
     * @return 更新的行数 {@link java.lang.Integer}
     */
    int updateByPrimaryKey(GoodsTypeSpec record);

    /**
     * 根据类型ID查询列表
     * 
     * @param typeId
     *            类型ID {@link java.lang.Longs}
     * @return 查询到的列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsTypeSpec}
     */
    List<GoodsTypeSpecVo> queryTypeSpecBytypeId(Long typeId);

    /**
     * 根据类型ID和规格id查询
     * 
     * @param map
     *            封装参数 {@link java.util.Map}
     * @return 查询到的类型关联规格
     */
    GoodsTypeSpec queryTypeSpecByTypeIdAndSpecId(Map<String, Long> map);
}
