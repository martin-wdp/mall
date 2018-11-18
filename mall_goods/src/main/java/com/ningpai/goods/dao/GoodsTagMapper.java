package com.ningpai.goods.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.goods.bean.GoodsTag;
import com.ningpai.goods.util.SelectBean;

/**
 * 商品标签数据层
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月14日 下午1:56:40
 * @version 1.0
 */
public interface GoodsTagMapper {
    /**
     * 根据主键删除
     * 
     * @param map
     *            标签ID，删除人名称{@link java.util.Map}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int deleteByPrimaryKey(Map<String, String> map);

    /**
     * 插入一个商品标签(全属性)
     * 
     * @param record
     *            待插入的商品标签实体 {@link com.ningpai.goods.baan.GoodsTag}
     * @return 影响的行数 {@link java.lang.Integer}
     */
    int insert(GoodsTag record);

    /**
     * 插入一个商品标签(可选属性)
     * 
     * @param record
     *            待插入的商品标签实体 {@link com.ningpai.goods.bean.GoodsTag}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int insertSelective(GoodsTag record);

    /**
     * 根据主键查询标签
     * 
     * @param tagId
     *            标签的主键ID {@link java.lang.Long}
     * @return 查询到的标签实体 {@link com.ningpai.goods.bean.GoodsTag}
     */
    GoodsTag selectByPrimaryKey(Long tagId);

    /**
     * 更新商品标签(可选属性)
     * 
     * @param record
     *            待更新的标签实体 {@link java.com.ningpai.goods.bean.GoodsTag}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int updateByPrimaryKeySelective(GoodsTag record);

    /**
     * 更新商品标签(全属性)
     * 
     * @param record
     *            待更新的标签实体 {@link java.com.ningpai.goods.bean.GoodsTag}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int updateByPrimaryKey(GoodsTag record);

    /**
     * 查询所有的商品标签
     * 
     * @return 查询到的标签列表 {@link com.ningpai.goods.bean.GoodsTag}
     *         {@link java.util.Map}
     */
    List<Object> selectAllTag(Map<String, Object> map);

    /**
     * 根据条件模糊查询
     * 
     * @param map
     *            {@link java.util.Map}
     * @return {@link com.ningpai.goods.bean.GoodsTag} {@link java.util.List}
     */
    List<GoodsTag> queryTagByParam(Map<String, String> map);

    /**
     * 查询所有的行数
     * 
     * @return {@link java.lang.Integer}
     */
    int queryTotalCount(SelectBean selectBean);

    /**
     * 查询所有的商品标签，已经删除的也算
     * 
     * @return 查询到的标签列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsTag}
     */
    List<GoodsTag> queryAllTag();

    /**
     * 根据标签名称查询行数
     * 
     * @param tagName
     *            标签名称
     * @return 行数
     */
    int queryByTagName(String tagName);
}
